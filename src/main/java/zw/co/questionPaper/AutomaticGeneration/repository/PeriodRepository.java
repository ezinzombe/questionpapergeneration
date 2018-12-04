package zw.co.questionPaper.AutomaticGeneration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.questionPaper.AutomaticGeneration.domain.Course;
import zw.co.questionPaper.AutomaticGeneration.domain.Period;
import zw.co.questionPaper.AutomaticGeneration.domain.User;

import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {


    Period findByName(String name);

}
