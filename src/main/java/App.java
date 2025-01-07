import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner sc;
    private int lastId;
    private List<CourseRecommender> courseRecommederList;

    public App(Scanner sc) {
        this.sc = sc;
        this.lastId = 0;
        courseRecommederList = new ArrayList<>();
    }
    public void run() {
        System.out.println("--- 수강신청 추천 시스템 ---");

        while (true) {
            System.out.println("명령 ) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;

            } else if (cmd.equals("등록")) {
                System.out.println("과목 명: ");
                String courseName = sc.nextLine();
                System.out.println("과목 코드: ");
                String courseCode = sc.nextLine();
                System.out.println("학점: ");
                int courseCredit = sc.nextInt();
                // 버퍼에 남은 \n 제거용
                sc.nextLine();
                System.out.println("학년: ");
                int courseGrade = sc.nextInt();
                // 버퍼에 남은 \n 제거용
                sc.nextLine();
                System.out.println("선수 과목: ");
                String preCourseName = sc.nextLine();
                System.out.println("수업 교시: ");
                String courseTime = sc.nextLine();

                int id = ++lastId;
                CourseRecommender cr = new CourseRecommender(id,courseName,courseCode,courseCredit,courseGrade,preCourseName,courseTime);
                courseRecommederList.add(cr);

                System.out.println("%d번 과목이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")) {

                System.out.println(" 번호 / 과목 / 과목코드 / 학점 / 학년 / 선수과목 / 수업 교시");
                System.out.println("----------------------------------------------");

                courseRecommederList.reversed().forEach(c -> {
                    System.out.printf("%d / %s / %s / %d / %d / %s / %s \n", c.getId(), c.getCourseName(), c.getCourseCode(), c.getCourseCredit(), c.getCourseGrade(), c.getPreCourseName(), c.getCourseTime());
                });
            }
        }
    }
}
