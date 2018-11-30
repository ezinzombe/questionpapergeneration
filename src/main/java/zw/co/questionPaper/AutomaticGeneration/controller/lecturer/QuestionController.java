package zw.co.questionPaper.AutomaticGeneration.controller.lecturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.questionPaper.AutomaticGeneration.domain.*;
import zw.co.questionPaper.AutomaticGeneration.repository.CourseRepository;
import zw.co.questionPaper.AutomaticGeneration.repository.QuestionRepository;
import zw.co.questionPaper.AutomaticGeneration.repository.TopicRepository;
import zw.co.questionPaper.AutomaticGeneration.repository.UserRepository;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/lecturer/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Question question;
        if (id != null) {
            question = questionRepository.findById(id).get();
        } else {
            question = new Question();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userRepository.findByEmail(name);
        model.addAttribute("question", question);
        model.addAttribute("title", "Create/ Edit Question");
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("questionTypes", QuestionType.values());
        model.addAttribute("courses", courseRepository.findAllByUser(user));
        System.out.println("################################"+user);
        model.addAttribute("topics", topicRepository.findAllByCourseUser(user));
        return "lecturer/question/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("question") @Validated Question question,
                       BindingResult result, SessionStatus status, Model model,
                       final RedirectAttributes redirectAttributes) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userRepository.findByEmail(name);
        //Check validation errors
        if (result.hasErrors()) {
            model.addAttribute("priorities", Priority.values());
            model.addAttribute("questionTypes", QuestionType.values());
            model.addAttribute("topics", topicRepository.findAllByCourseUser(user));
            return "lecturer/question/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        questionRepository.save(question);
        return "redirect:/lecturer/question/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userRepository.findByEmail(name);
        Iterable<Question> questions = questionRepository.findAllByTopicCourseUser(user);
        model.addAttribute("topics", questions);
        return "lecturer/question/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionRepository.findById(id).get());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("questionTypes", QuestionType.values());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userRepository.findByEmail(name);
        model.addAttribute("topics", topicRepository.findAllByCourseUser(user));
        return "lecturer/question/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = questionRepository.findById(id).get().getId();
        questionRepository.deleteById(subjectId);
        return "redirect:/lecturer/question/list";
    }

}
