import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    /**
     * 추천 과목 목록을 파일에 저장
     * @param recommendations 추천 과목 목록
     * @param filePath 저장할 파일 경로
     */
    public static void saveRecommendations(List<CourseRecommender> recommendations, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("추천 과목 목록:\n");
            for (CourseRecommender course : recommendations) {
                writer.write(String.format("%d / %s / %s / %d / %d / %s / %s \n",
                        course.getId(),
                        course.getCourseName(),
                        course.getCourseCode(),
                        course.getCourseCredit(),
                        course.getCourseGrade(),
                        course.getPreCourseName(),
                        course.getCourseTime()));
            }
            logger.info("추천 과목 목록이 " + filePath + "에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            logger.severe("추천 결과를 파일에 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
