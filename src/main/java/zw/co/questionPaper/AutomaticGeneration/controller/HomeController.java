package zw.co.questionPaper.AutomaticGeneration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import zw.co.questionPaper.AutomaticGeneration.domain.User;
import zw.co.questionPaper.AutomaticGeneration.repository.CourseRepository;
import zw.co.questionPaper.AutomaticGeneration.repository.UserRepository;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by zinzombe on Oct
 */
@Controller
public class HomeController {


    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }


    @RequestMapping(value = {"/lecturerPage"}, method = RequestMethod.GET)
    public ModelAndView userPage(Principal principal) {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByEmail(name);
        model.addObject("courses", courseRepository.findAllByUser(user));
        model.setViewName("lecturer/home");
        return model;
    }



    @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addObject("username", name);
        model.setViewName("admin/home");
        return model;
    }




}
