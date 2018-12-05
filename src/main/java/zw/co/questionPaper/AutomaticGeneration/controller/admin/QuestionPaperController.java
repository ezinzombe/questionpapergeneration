package zw.co.questionPaper.AutomaticGeneration.controller.admin;


import org.springframework.web.bind.annotation.*;
import zw.co.questionPaper.AutomaticGeneration.controller.admin.pdf.PDFGenerator;

import zw.co.questionPaper.AutomaticGeneration.domain.Question;
import zw.co.questionPaper.AutomaticGeneration.domain.Topic;
import zw.co.questionPaper.AutomaticGeneration.repository.QuestionRepository;import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import zw.co.questionPaper.AutomaticGeneration.repository.TopicRepository;

@RestController
@RequestMapping("/api/pdf")
public class QuestionPaperController {


    @Autowired
    QuestionRepository customerRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;




    @PostMapping(value = "/questions",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> questionsReport(ExamDTO examDTO) throws IOException, ParseException {
        System.out.println("==============================================="+examDTO.getCourseName());
        List<Topic> topics = topicRepository.findAllByCourseName(examDTO.getCourseName());

        List<Question> questions = new ArrayList<>();
        List<Question> questions1 = new ArrayList<>();
        for (Topic t : topics) {
            questions1 = questionRepository.findAllByTopicNameAndPeriodName(t.getName(), examDTO.getPeriodName());
            questions.addAll(questions1);

        }



        System.out.println("Original List : \n" + questions.size());

        Double sum = 100D;
        Set<Question> collectedSet = new HashSet<>();
        Double totalSum = 0.0;
        Collections.shuffle(questions);
        for (Question s : questions) {
            if (collectedSet.isEmpty()) {
                collectedSet.add(s);
                totalSum = s.getMarks();

            }


            if (collectedSet.size() >= 1) {
                System.out.println("#########"+totalSum+"-----"+collectedSet.size());
                if (totalSum <=100) {

                    if ((totalSum + s.getMarks()) <=100) {
                        if(!collectedSet.stream().findFirst().get().equals(s)) {
                            System.out.println("@@@@@@@@@@--"+s.getName());
                            collectedSet.add(s);
                            totalSum = totalSum + s.getMarks();
                        }
                        System.out.println("#####################"+totalSum);
                    }

                }
            }
        }



        System.out.println("---------------------------------------------"+collectedSet);


        ByteArrayInputStream bis = PDFGenerator.examPDFReport((collectedSet.stream().collect(Collectors.toList())));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=questions.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>

}
