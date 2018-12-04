package zw.co.questionPaper.AutomaticGeneration.controller.admin.pdf;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.sun.scenario.effect.ImageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import zw.co.questionPaper.AutomaticGeneration.domain.Question;

public class PDFGenerator {

    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream examPDFReport(List<Question> questions) throws IOException {
        Document document = new Document();
        /** Path to the resulting PDF file. */



        String examName= questions.stream().findFirst().get().getTopic().getCourse().getName();
        String examCode= questions.stream().findFirst().get().getTopic().getCourse().getCourseCode();
        String facultyName = questions.stream().findFirst().get().getTopic().getCourse().getDepartment().getFaculty().getName();
        String periodName = questions.stream().findFirst().get().getPeriod().getName();

        Random random = new Random();
        final String RESULT
                = "/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/exams/"+examName+random.nextInt(15)+".pdf";
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter writer = PdfWriter.getInstance(document, out);
            writer.getInstance(document, new FileOutputStream(RESULT));
            writer.setStrictImageSequence(true);

            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Font bold = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
            Font italics = FontFactory.getFont(FontFactory.TIMES_ITALIC, 12, BaseColor.BLACK);


            Paragraph para = new Paragraph( "WOMEN'S UNIVERSITY IN AFRICA", bold);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);




            Paragraph p = new Paragraph(" ");
            DottedLineSeparator dottedline = new DottedLineSeparator();
            dottedline.setOffset(-2);
            dottedline.setGap(2000f);
            p.add(dottedline);
            p.setSpacingBefore(240f);
            document.add(p);


            Paragraph addre = new Paragraph( "Addressing gender disparity and fostering equity in University Education", italics);
            addre.setAlignment(Element.ALIGN_CENTER);
            DottedLineSeparator ottedline = new DottedLineSeparator();
            ottedline.setOffset(-2);
            ottedline.setGap(2000f);
            addre.add(ottedline);
            document.add(addre);

            Paragraph p222 = new Paragraph(" ");
            DottedLineSeparator dotted = new DottedLineSeparator();
            dotted.setOffset(-2);
            dotted.setGap(2f);
            p222.add(dotted);
            p222.setSpacingBefore(5f);
            document.add(p222);



            document.add(Chunk.NEWLINE);
            Image image = Image.getInstance("/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/static/images/logo.jpeg");
            image.setAbsolutePosition(200f, 550f);

            document.add(image);


            Paragraph facultyNam= new Paragraph(facultyName);
            facultyNam.setAlignment(Element.ALIGN_CENTER);
            document.add(facultyNam);



            Paragraph paper  = new Paragraph( "MAIN PAPER " + " "+" ", font);
            paper.setSpacingBefore(50f);
            paper.setAlignment(Element.ALIGN_CENTER);
            document.add(paper);

            Date date = new Date();
            Paragraph name = new Paragraph( examCode+" : "+"   "+" "+ examName, font);
            name.setSpacingBefore(50f);
            document.add(name);

            Paragraph intake  = new Paragraph( "INTAKE: " + " "+" " + periodName, font);
            intake.setSpacingBefore(50f);
            document.add(intake);

            Paragraph dates  = new Paragraph( "DATE : "+" "+date.toString()+" "+ "TIME "+" "+ "3 HOURS ", font);
            dates.setSpacingBefore(50f);
            document.add(dates);


            Paragraph instructions = new Paragraph( "INSTRUCTIONS TO CANDIDATES", font);
            Paragraph ques  = new Paragraph( "Answer any four questions", italics);
            instructions.setSpacingBefore(50f);
            document.add(instructions);
            document.add(ques);
            document.newPage();



            for (Question question: questions) {

                int position=questions.indexOf(question);
                String s1 = 1 +position +" "+question.getNotes()+" "+"[ Marks :"+ question.getMarks().toString()+" ]";
                Paragraph paragraph1 = new Paragraph(s1);
                paragraph1.setSpacingBefore(20f);
                System.out.println("==========================="+s1);
                document.add(paragraph1);
            }

            document.close();
        }catch(DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
