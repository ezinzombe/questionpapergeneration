package zw.co.questionPaper.AutomaticGeneration.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */
public enum Priority {


    HIGH("High"),
    AVERAGE("Average"),
    LOW("Low");


    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public static List<Priority> asList() {
        return Arrays.asList(Priority.values());
    }

    public String getName() {
        return name;
    }
}
