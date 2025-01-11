import java.util.*;
import java.util.stream.Collectors;

public class CourseRecommenderService {

    private final CourseRecommenderRepository courseRecommenderRepository;

    // 기본 생성자: 파일 경로를 "data/courses.txt"로 설정하여 FileCourseRecommenderRepository 사용
    public CourseRecommenderService() {
        this.courseRecommenderRepository = new FileCourseRecommenderRepository("data/courses.txt");
    }

    // 의존성 주입을 위한 생성자
    public CourseRecommenderService(CourseRecommenderRepository repository) {
        this.courseRecommenderRepository = repository;
    }

    /**
     * 과목을 등록합니다.
     *
     * @param courseName     과목명
     * @param courseCode     과목코드
     * @param courseCredit   학점
     * @param courseGrade    학년
     * @param preCourseName  선수 과목명 (없으면 "없음")
     * @param courseTime     수업 교시 (예: "화 5 6 목 7")
     * @return 등록된 CourseRecommender 객체
     */
    public CourseRecommender write(String courseName, String courseCode, int courseCredit, int courseGrade, String preCourseName, String courseTime) {
        CourseRecommender courseRecommender = new CourseRecommender(courseName, courseCode, courseCredit, courseGrade, preCourseName, courseTime);
        return courseRecommenderRepository.save(courseRecommender);
    }

    /**
     * 모든 과목 목록을 조회합니다.
     *
     * @return 모든 CourseRecommender 객체의 리스트
     */
    public List<CourseRecommender> getAllItems() {
        return courseRecommenderRepository.findAll();
    }

    /**
     * 과목을 삭제합니다.
     *
     * @param id 삭제할 과목의 ID
     * @return 삭제 성공 여부
     */
    public boolean delete(int id) {
        return courseRecommenderRepository.deleteById(id);
    }

    /**
     * 특정 ID의 과목을 조회합니다.
     *
     * @param id 조회할 과목의 ID
     * @return Optional<CourseRecommender> 객체
     */
    public Optional<CourseRecommender> getItems(int id) {
        return courseRecommenderRepository.findById(id);
    }

    /**
     * 과목을 수정합니다.
     *
     * @param c               수정할 CourseRecommender 객체
     * @param newCourseName   새로운 과목명
     * @param newCourseCode   새로운 과목코드
     * @param newCourseCredit 새로운 학점
     * @param newCourseGrade  새로운 학년
     * @param newPreCourseName 새로운 선수 과목명
     * @param newCourseTime   새로운 수업 교시
     */
    public void modify(CourseRecommender c, String newCourseName, String newCourseCode, int newCourseCredit, int newCourseGrade, String newPreCourseName, String newCourseTime) {
        c.setCourseName(newCourseName);
        c.setCourseCode(newCourseCode);
        c.setCourseCredit(newCourseCredit);
        c.setCourseGrade(newCourseGrade);
        c.setPreCourseName(newPreCourseName);
        c.setCourseTime(newCourseTime);
        courseRecommenderRepository.save(c);
    }

    /**
     * 수강신청 추천 알고리즘
     *
     * @param currentGrade     사용자의 현재 학년
     * @param completedCourses 사용자가 이수한 과목 목록
     * @param targetCredits    사용자가 목표로 하는 학점
     * @return 추천 과목 목록
     */
    public List<CourseRecommender> recommendCourses(int currentGrade, List<String> completedCourses, int targetCredits) {
        List<CourseRecommender> allCourses = courseRecommenderRepository.findAll();

        // 1. 이수한 과목 제외
        List<CourseRecommender> filteredCourses = allCourses.stream()
                .filter(course -> !completedCourses.contains(course.getCourseName()))
                .collect(Collectors.toList());

        // 2. 학년 기준 필터링
        filteredCourses = filteredCourses.stream()
                .filter(course -> course.getCourseGrade() <= currentGrade)
                .collect(Collectors.toList());

        // 3. 선수 과목 검증
        filteredCourses = filteredCourses.stream()
                .filter(course -> {
                    String preCourses = course.getPreCourseName();
                    if (preCourses.equalsIgnoreCase("없음")) return true;
                    List<String> requiredCourses = Arrays.stream(preCourses.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    return completedCourses.containsAll(requiredCourses);
                })
                .collect(Collectors.toList());

        // 4. 과목 분류 및 우선순위 설정
        List<CourseRecommender> sameGradeCourses = filteredCourses.stream()
                .filter(course -> course.getCourseGrade() == currentGrade)
                .collect(Collectors.toList());

        List<CourseRecommender> lowerGradeCourses = filteredCourses.stream()
                .filter(course -> course.getCourseGrade() < currentGrade)
                .collect(Collectors.toList());

        // 5. 시간 충돌 및 학점 제한 고려
        List<CourseRecommender> recommended = new ArrayList<>();
        int accumulatedCredits = 0;
        List<String> scheduledTimes = new ArrayList<>();

        // Helper method to check time conflict
        for (CourseRecommender course : sameGradeCourses) {
            if (accumulatedCredits + course.getCourseCredit() > targetCredits) continue;
            if (!hasTimeConflict(course.getCourseTime(), scheduledTimes)) {
                recommended.add(course);
                accumulatedCredits += course.getCourseCredit();
                scheduledTimes.addAll(Arrays.asList(course.getCourseTime().split(" ")));
            }
        }

        for (CourseRecommender course : lowerGradeCourses) {
            if (accumulatedCredits + course.getCourseCredit() > targetCredits) continue;
            if (!hasTimeConflict(course.getCourseTime(), scheduledTimes)) {
                recommended.add(course);
                accumulatedCredits += course.getCourseCredit();
                scheduledTimes.addAll(Arrays.asList(course.getCourseTime().split(" ")));
            }
        }

        return recommended;
    }

    /**
     * 시간 충돌 검사
     *
     * @param courseTime     과목의 수업 시간 문자열 (예: "화 5 6 목 7")
     * @param scheduledTimes 현재까지 추천된 과목의 수업 시간 목록
     * @return 충돌 여부
     */
    private boolean hasTimeConflict(String courseTime, List<String> scheduledTimes) {
        List<String> times = Arrays.asList(courseTime.split(" "));
        for (String time : times) {
            if (scheduledTimes.contains(time)) {
                return true;
            }
        }
        return false;
    }
}
