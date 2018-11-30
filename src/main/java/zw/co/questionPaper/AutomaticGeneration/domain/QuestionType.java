package zw.co.questionPaper.AutomaticGeneration.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */
public enum QuestionType {

    MULTIPLE_CHOICE("Multiple Choice"),
    OPEN_ENDED("Open Ended");


    private final String name;

    QuestionType(String name) {
        this.name = name;
    }

    public static List<QuestionType> asList() {
        return Arrays.asList(QuestionType.values());
    }

    public String getName() {
        return name;
    }}
