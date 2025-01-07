import java.util.Scanner;

public class App {
    private final Scanner sc;
    private int lastId;

    public App(Scanner sc) {
        this.sc = sc;
        this.lastId = 0;
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
                System.out.println("과목 코드: ");
                System.out.println("학점: ");
                System.out.println("학년: ");
                System.out.println("선수 과목: ");
                System.out.println("수업 교시: ");

                System.out.println("%d번 과목이 등록되었습니다.".formatted(++lastId));

            }
        }
    }
}
