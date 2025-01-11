import java.util.ArrayList;
import java.util.List;

public class CourseRecommenderService {

    private final CourseRecommenderRepository courseRecommenderRepository;

    public CourseRecommenderService() {

        courseRecommenderRepository = new CourseRecommenderRepository();

    }

//    public CourseRecommender deleteItem() {
//        return courseRecommederList.remove(1);
//
//    }

    public CourseRecommender write(String courseName, String courseCode, int courseCredit, int courseGrade, String preCourseName, String courseTime) {
        CourseRecommender courseRecommender = new CourseRecommender(courseName, courseCode, courseCredit, courseGrade, preCourseName, courseTime);
        return courseRecommenderRepository.save(courseRecommender);
    }

    public List<CourseRecommender> getAllItems() {
        return courseRecommenderRepository.findAll();
    }
}
