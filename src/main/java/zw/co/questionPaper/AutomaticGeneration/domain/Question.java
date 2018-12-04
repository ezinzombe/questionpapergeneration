package zw.co.questionPaper.AutomaticGeneration.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Created by zinzombe on Oct
 */
@Entity
public class Question  extends BaseEntityId{

    private String name;
    private String notes;
    @Enumerated
    private QuestionType questionType;
    @Enumerated
    private Priority priority;

    private Topic topic;
    private Period period;
    private Double marks;

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    @ManyToOne
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @ManyToOne
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


    public QuestionType getQuestionType() {

        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", questionType=" + questionType +
                ", priority=" + priority +
                ", topic=" + topic +
                ", marks=" + marks +
                '}';
    }
}
