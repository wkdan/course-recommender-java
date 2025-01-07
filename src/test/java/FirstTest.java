import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {

    @Test
    @DisplayName("앱 시작 시 '--- 수강신청 추천 시스템 --- 출력")
    void t1() {

        String out = TestBot.run("");
        assertThat(out)
                .containsSubsequence("--- 수강신청 추천 시스템 ---", "프로그램을 종료합니다.");

    }

    @Test
    @DisplayName("create - DB에 과목 정보 1개 입력")
    void t2() {

        String out = TestBot.run("""
                등록
                객체지향프로그래밍
                T043385
                없음
                수 1 2 금 3
                """);
        assertThat(out)
                .containsSubsequence("과목 명: ", "과목 코드: ","학점: ","학년: ","선수 과목: ", "수업 교시: ");
    }

    @Test
    @DisplayName("명령을 여러번 입력할 수 있다.")
    void t3() {

        String out = TestBot.run("""
                등록
                등록
                등록
                등록
                종료
                """);
        long count = out.split("명령 \\)").length - 1;

        assertThat(count).isEqualTo(4);
    }
}
