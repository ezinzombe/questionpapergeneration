package zw.co.questionPaper.AutomaticGeneration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.questionPaper.AutomaticGeneration.domain.Course;
import zw.co.questionPaper.AutomaticGeneration.domain.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    Course findByName(String name);

    List<Course> findAllByUser(User user);
}
