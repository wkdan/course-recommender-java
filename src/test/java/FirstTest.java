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

        String out = TestBot.run("종료");
        assertThat(out)
                .containsSubsequence("--- 수강신청 추천 시스템 ---", "프로그램을 종료합니다.");

    }
}
