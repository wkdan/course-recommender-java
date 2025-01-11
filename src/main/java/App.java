import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final CourseRecommenderController controller;
    private final SystemController systemController;

    public App(Scanner sc, CourseRecommenderController controller) {
        this.sc = sc;
        this.controller = controller;
        this.systemController = new SystemController();
    }

    public void run() {
        System.out.println("--- 수강신청 추천 시스템 ---");

        while (true) {
            System.out.println("\n명령 목록:");
            System.out.println("등록 - 과목 등록");
            System.out.println("목록 - 과목 목록 조회");
            System.out.println("수강신청 - 과목 추천");
            System.out.println("삭제?id=과목ID - 과목 삭제");
            System.out.println("수정?id=과목ID - 과목 수정");
            System.out.println("종료 - 프로그램 종료");
            System.out.print("명령을 입력하세요: ");
            String cmd = sc.nextLine().trim();

            String[] cmdBits = cmd.split("\\?");
            String actionName = cmdBits[0];

            switch(actionName) {
                case "종료" -> systemController.exit();
                case "등록" -> controller.register();
                case "목록" -> controller.list();
                case "수강신청" -> controller.recommend();
                case "삭제" -> {
                    if (cmdBits.length > 1) {
                        controller.delete(cmd);
                    } else {
                        controller.wrongCmd();
                    }
                }
                case "수정" -> {
                    if (cmdBits.length > 1) {
                        controller.modify(cmd);
                    } else {
                        controller.wrongCmd();
                    }
                }
                default -> controller.wrongCmd();
            }

            if(cmd.equals("종료")) break;
        }
    }
}
