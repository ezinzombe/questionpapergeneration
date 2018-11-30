package zw.co.questionPaper.AutomaticGeneration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zw.co.questionPaper.AutomaticGeneration.domain.Topic;
import zw.co.questionPaper.AutomaticGeneration.domain.User;

import java.util.List;


@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    List<Topic> findAllByCourseName (String courseName);

    List<Topic> findAllByCourseUser(User user);
}
