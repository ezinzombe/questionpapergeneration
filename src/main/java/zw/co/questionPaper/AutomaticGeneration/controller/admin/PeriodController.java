package zw.co.questionPaper.AutomaticGeneration.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.questionPaper.AutomaticGeneration.domain.Period;
import zw.co.questionPaper.AutomaticGeneration.repository.PeriodRepository;
import zw.co.questionPaper.AutomaticGeneration.repository.FacultyRepository;

import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/period")
public class PeriodController {

    @Autowired
    private PeriodRepository periodRepository;
@Autowired
private FacultyRepository facultyRepository;
    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Period period;
        if (id != null) {
            period = periodRepository.findById(id).get();
        } else {
            period = new Period();
        }
        model.addAttribute("period", period);
        return "admin/period/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("period") @Validated Period period,
                       BindingResult result, SessionStatus status, Model model,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/period/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        periodRepository.save(period);
        return "redirect:/admin/period/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Period> periods = periodRepository.findAll();
        model.addAttribute("periods", periods);
        return "admin/period/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("period", periodRepository.findById(id).get());
        return "admin/period/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long subjectId = periodRepository.findById(id).get().getId();
        periodRepository.deleteById(subjectId);
        return "redirect:/admin/period/list";
    }

}
