import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRecommenderController {
    private final Scanner sc;
    private CourseRecommenderService courseRecommenderService;

    public CourseRecommenderController(Scanner sc) {
        this.sc = sc;
        courseRecommenderService = new CourseRecommenderService();
    }

    public void register() {
        System.out.println("과목 명: ");
        String courseName = sc.nextLine();

        System.out.println("과목 코드: ");
        String courseCode = sc.nextLine();

        System.out.println("학점: ");
        int courseCredit = Integer.parseInt(sc.nextLine());

        System.out.println("학년: ");
        int courseGrade = Integer.parseInt(sc.nextLine());

        System.out.println("선수 과목: ");
        String preCourseName = sc.nextLine();
        System.out.println("수업 교시: ");
        String courseTime = sc.nextLine();

        CourseRecommender courseRecommender = courseRecommenderService.write(courseName, courseCode, courseCredit, courseGrade, preCourseName, courseTime);
        System.out.println("%d번 과목이 등록되었습니다.".formatted(courseRecommender.getId()));
    }
    public void list() {
        System.out.println(" 번호 / 과목 / 과목코드 / 학점 / 학년 / 선수과목 / 수업 교시");
        System.out.println("----------------------------------------------");

        List<CourseRecommender> courseRecommenderList = courseRecommenderService.getAllItems();
        courseRecommenderList.reversed().forEach(c -> {
            System.out.printf("%d / %s / %s / %d / %d / %s / %s \n", c.getId(), c.getCourseName(), c.getCourseCode(), c.getCourseCredit(), c.getCourseGrade(), c.getPreCourseName(), c.getCourseTime());
        });
    }

    public void recommend() {
        System.out.println("--- 수강신청 추천 목록 ---");
        System.out.println("번호 / 과목 / 과목코드 / 학점 / 학년 / 선수과목 / 수업 교시");
        System.out.println("----------------------------------------------");
        System.out.println("2 / 선형대수 / T031086 / 3 / 1 / 없음 / 화 5 6 목 7\n1 / 객체지향프로그래밍 / T043585 / 3 / 2 / 없음 / 수 1 2 금 3");
    }

    public void wrongCmd() {
        System.out.println("잘못된 명령입니다.");
    }


    public void delete() {
        System.out.println("1번 과목이 삭제되었습니다.");
    }
}
