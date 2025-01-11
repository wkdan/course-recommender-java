import java.util.ArrayList;
import java.util.List;

public class CourseRecommenderRepository {

    private final List<CourseRecommender> courseRecommenderList;
    private int lastId;

    public CourseRecommenderRepository() {
        courseRecommenderList = new ArrayList<>();
    }

    public CourseRecommender save(CourseRecommender courseRecommender) {
        int id = ++lastId;
        courseRecommender.setId(id);
        courseRecommenderList.add(courseRecommender);

        return courseRecommender;
    }

    public List<CourseRecommender> findAll() {
        return courseRecommenderList;
    }

    public boolean deleteById(int id) {
        return courseRecommenderList.removeIf(courseRecommender -> courseRecommender.getId() == id);
    }
}
