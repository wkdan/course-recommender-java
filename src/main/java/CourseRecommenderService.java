import java.util.ArrayList;
import java.util.List;

public class CourseRecommenderService {
    private int lastId;
    private final List<CourseRecommender> courseRecommederList;

    public CourseRecommenderService() {
        courseRecommederList = new ArrayList<>();
    }

    public CourseRecommender deleteItem() {
        return courseRecommederList.remove(1);

    }

    public CourseRecommender write(String courseName, String courseCode, int courseCredit, int courseGrade, String preCourseName, String courseTime) {
        int id = ++lastId;
        CourseRecommender cr = new CourseRecommender(id,courseName,courseCode,courseCredit,courseGrade,preCourseName,courseTime);
        courseRecommederList.add(cr);

        return cr;
    }

    public List<CourseRecommender> getAllItems() {
        return courseRecommederList;
    }
}
