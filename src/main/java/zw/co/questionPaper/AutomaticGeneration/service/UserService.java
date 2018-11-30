package zw.co.questionPaper.AutomaticGeneration.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import zw.co.questionPaper.AutomaticGeneration.domain.User;
import zw.co.questionPaper.AutomaticGeneration.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService, IService<User> {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
