package zw.co.questionPaper.AutomaticGeneration.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author TMUTERO
 */
public  enum Chapter {

    CHAPTER_ONE("CHAPTER_ONE"),
    CHAPTER_TWO("CHAPTER_TWO"),
    CHAPTER_THREE("CHAPTER_THREE"),
    CHAPTER_FOUR("CHAPTER_FOUR"),
    CHAPTER_FIVE("CHAPTER_FIVE"),
    CHAPTER_SIX("CHAPTER_SIX"),
    CHAPTER_SEVEN("CHAPTER_SEVEN"),
    CHAPTER_EIGHT("CHAPTER_EIGHT"),
    CHAPTER_TEN("CHAPTER_TEN");

    private final String name;

    Chapter(String name) {
        this.name = name;
    }

    public static List<Chapter> asList() {
        return Arrays.asList(Chapter.values());
    }

    public String getName() {
        return name;
    }
}
