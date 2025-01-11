import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        FileCourseRecommenderRepository repository = new FileCourseRecommenderRepository("C:\\Users\\JMY\\IdeaProjects\\course-recommender-java\\src\\main\\java\\data/courses.txt");
        CourseRecommenderService service = new CourseRecommenderService(repository);
        CourseRecommenderController controller = new CourseRecommenderController(sc, service);
        App app = new App(sc, controller);
        app.run();
    }
}
