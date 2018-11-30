package zw.co.questionPaper.AutomaticGeneration.controller.lecturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.questionPaper.AutomaticGeneration.domain.Chapter;
import zw.co.questionPaper.AutomaticGeneration.domain.Topic;
import zw.co.questionPaper.AutomaticGeneration.repository.CourseRepository;
import zw.co.questionPaper.AutomaticGeneration.repository.TopicRepository;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/lecturer/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Topic topic;
        if (id != null) {
            topic = topicRepository.findById(id).get();
        } else {
            topic = new Topic();
        }
        model.addAttribute("topic", topic);
        model.addAttribute("title", "Create/ Edit Topic");
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("chapters", Chapter.asList());
        return "lecturer/topic/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("topic") @Validated Topic topic,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "lecturer/topic/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        topicRepository.save(topic);
        return "redirect:/lecturer/topic/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Iterable<Topic> topics = topicRepository.findAll();
        model.addAttribute("topics", topics);
        return "lecturer/topic/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("topic", topicRepository.findById(id).get());
        model.addAttribute("title", "Create/ Edit Topic");
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("chapters", Chapter.asList());
        return "lecturer/topic/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = topicRepository.findById(id).get().getId();
        topicRepository.deleteById(subjectId);
        return "redirect:/lecturer/topic/list";
    }

}
