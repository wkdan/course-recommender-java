import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final CourseRecommenderController courseRecommenderController;


    public App(Scanner sc) {
        this.sc = sc;
        courseRecommenderController = new CourseRecommenderController(sc);
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
                courseRecommenderController.register();

            } else if (cmd.equals("목록")) {
                courseRecommenderController.list();

            } else if (cmd.equals("수강신청")) {
                courseRecommenderController.recommend();
            } else if (cmd.equals("삭제?id=1")){
                System.out.println("1번 과목이 삭제되었습니다.");
            } else {
                courseRecommenderController.wrongCmd();
            }
        }
    }
}
