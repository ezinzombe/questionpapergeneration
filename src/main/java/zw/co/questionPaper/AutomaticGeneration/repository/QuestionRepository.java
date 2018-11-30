package zw.co.questionPaper.AutomaticGeneration.repository;


import org.springframework.data.repository.CrudRepository;
import zw.co.questionPaper.AutomaticGeneration.domain.Question;
import zw.co.questionPaper.AutomaticGeneration.domain.User;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {


    List<Question> findAllByTopicName (String topicName);

    List<Question> findAllByTopicCourseUser(User user);
}
