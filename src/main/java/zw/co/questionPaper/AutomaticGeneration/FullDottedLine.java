package zw.co.questionPaper.AutomaticGeneration;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FullDottedLine {
    public static final String DEST = "results/objects/full_dotted_line.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FullDottedLine().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));


        document.open();
        // Add Text to PDF file ->
        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Font bold = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
        Font italics = FontFactory.getFont(FontFactory.TIMES_ITALIC, 12, BaseColor.BLACK);




        Paragraph para = new Paragraph( "WOMEN'S UNIVERSITY IN AFRICA", bold);
        para.setAlignment(Element.ALIGN_CENTER);




        document.add(para);



        Paragraph addre = new Paragraph( "Addressing gender disparity and fostering equity in University Education", italics);
        document.add(addre);
        Paragraph p = new Paragraph(" ");
        DottedLineSeparator dottedline = new DottedLineSeparator();
        dottedline.setOffset(-2);
        dottedline.setGap(2f);
        p.add(dottedline);
        p.setSpacingBefore(240f);
        document.add(p);



        document.add(Chunk.NEWLINE);
        Image image = Image.getInstance("/home/ezinzombe/Maricho/questionpapergeneration-november/src/main/resources/static/images/logo.jpeg");
        image.setAbsolutePosition(200f, 550f);

        document.add(image);

        document.addCreationDate();
        document.add(new Paragraph("FACULTY OF MANAGEMENT AND EN "));

        Paragraph p1 = new Paragraph(" ");
        DottedLineSeparator dottedlin = new DottedLineSeparator();
        dottedlin.setOffset(-2);
        dottedlin.setGap(2f);
        p1.add(dottedline);
        document.add(p1);



        Paragraph paper  = new Paragraph( "MAIN PAPER: " + " "+" ", font);
        paper.setSpacingBefore(50f);
        paper.setAlignment(Element.ALIGN_CENTER);
        document.add(paper);

        Date date = new Date();
        Paragraph name = new Paragraph( "eeeeee"+" : "+"   "+" "+" eeee ", font);
        name.setSpacingBefore(50f);
        document.add(name);

        Paragraph intake  = new Paragraph( "INTAKE: " + " "+" ", font);
        intake.setSpacingBefore(50f);
        document.add(intake);

        Paragraph dates  = new Paragraph( "DATE : "+" "+date.toString()+" "+ "TIME "+" "+ "3 HOURS MORNING", font);
        dates.setSpacingBefore(50f);
        document.add(dates);


        Paragraph instructions = new Paragraph( "INSTRUCTIONS TO CANDIDATES", font);
        Paragraph ques  = new Paragraph( "Answer any four questions", italics);
        instructions.setSpacingBefore(50f);
        document.add(instructions);
        document.add(ques);
        document.newPage();
        document.close();
    }
}