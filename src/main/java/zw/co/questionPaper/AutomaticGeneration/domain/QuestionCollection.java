package zw.co.questionPaper.AutomaticGeneration.domain;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */

@Entity
public class QuestionCollection extends BaseEntityId {


    @OneToMany
    private List<Question> easyQuestions = new ArrayList<>();
    @OneToMany
    private List<Question> mediumQuestions = new ArrayList<>();
    @OneToMany
    private List<Question> hardQuestions = new ArrayList<>();


}
