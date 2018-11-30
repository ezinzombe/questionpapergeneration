package zw.co.questionPaper.AutomaticGeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zw.co.questionPaper.AutomaticGeneration.domain.Role;
import zw.co.questionPaper.AutomaticGeneration.domain.User;
import zw.co.questionPaper.AutomaticGeneration.service.UserService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class AutomaticGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomaticGenerationApplication.class, args);
	}


    @Inject
    private UserService userService;


	@PostConstruct
	public void init() {
		User user = new User("admin","admin",  "hitrac@hitrac.co.zw","admin", true);

		user.setApproved(true);
		user.setRoles(new HashSet<>(
				Arrays.asList(new Role("ADMIN"))));
		if (userService.findByEmail(user.getEmail()) == null) {
			userService.save(user);
		}

	}
}
