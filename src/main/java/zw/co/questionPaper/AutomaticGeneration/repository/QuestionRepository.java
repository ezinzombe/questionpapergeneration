package zw.co.questionPaper.AutomaticGeneration.repository;


import org.springframework.data.repository.CrudRepository;
import zw.co.questionPaper.AutomaticGeneration.domain.Question;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {


    List<Question> findAllByTopicName (String topicName);
}
