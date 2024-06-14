package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Init Bean Day");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Clean up Bean Day");
    }
    // END
}
