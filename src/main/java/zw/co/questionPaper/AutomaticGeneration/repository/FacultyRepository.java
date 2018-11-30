package zw.co.questionPaper.AutomaticGeneration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.questionPaper.AutomaticGeneration.domain.Course;
import zw.co.questionPaper.AutomaticGeneration.domain.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {


}
