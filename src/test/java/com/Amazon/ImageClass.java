package com.Amazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class ImageClass {

	
	
	@SuppressWarnings("resource")
	public void ImageAttachClass(String Classname, String FileNme, String StepName, String StepDescrp, String ImagePath, String FailureDescrip) throws FileNotFoundException {
    	
    	
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        // Create a document file
		CustomXWPFDocument document = new CustomXWPFDocument();


     // Create a para 3 - Failure Description
        XWPFParagraph paragraphZero = document.createParagraph();
        paragraphZero.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphZeroRunOne = paragraphZero.createRun();
        paragraphZeroRunOne.addBreak();
        paragraphZeroRunOne.setBold(true);
        paragraphZeroRunOne.setUnderline(UnderlinePatterns.SINGLE);
        paragraphZeroRunOne.setFontSize(10);
        paragraphZeroRunOne.setFontFamily("Verdana");
        paragraphZeroRunOne.setColor("000070");
        paragraphZeroRunOne.setText(Classname);
        
        
        
        // insert doc details
        // Create a para -1 - Step Name and Step Description
        XWPFParagraph paragraphOne = document.createParagraph();
        paragraphOne.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphOneRunOne = paragraphOne.createRun();
        paragraphOneRunOne.setBold(true);
        paragraphOneRunOne.setFontSize(10);
        paragraphOneRunOne.setFontFamily("Verdana");
        paragraphOneRunOne.setColor("000070");
        paragraphOneRunOne.setText(StepName + ": " + StepDescrp);
        

     // Createa a para -2 - Step Description
        XWPFParagraph paragraphFour = document.createParagraph();
        paragraphFour.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphFourRunOne = paragraphFour.createRun();
        paragraphFourRunOne.setBold(true);
        paragraphFourRunOne.setFontSize(10);
        paragraphFourRunOne.setFontFamily("Verdana");
        paragraphFourRunOne.setColor("000070");
        paragraphFourRunOne.setText(FailureDescrip);
     
        
        // Createa a para -4 - DateStamp
        XWPFParagraph paragraphTwo = document.createParagraph();
        paragraphTwo.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
        paragraphTwoRunOne.setFontSize(8);
        paragraphTwoRunOne.setFontFamily("Verdana");
        paragraphTwoRunOne.setColor("000070");
        paragraphTwoRunOne.setText(date);

        // Createa a para -5  - TimeStamp
        XWPFParagraph paragraphThree = document.createParagraph();
        paragraphThree.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun paragraphThreeRunOne = paragraphThree.createRun();
        paragraphThreeRunOne.setFontSize(8);
        paragraphThreeRunOne.setFontFamily("Verdana");
        paragraphThreeRunOne.setColor("000070");
        paragraphThreeRunOne.setText(time);
           
        
        
        // Adding a file
        try {

            // Working addPicture Code below...
            XWPFParagraph paragraphX = document.createParagraph();
         // get image format
            int imgFormat = getImageFormat(ImagePath);
            paragraphX.setAlignment(ParagraphAlignment.CENTER);

            String blipId = paragraphX.getDocument().addPictureData(
                    new FileInputStream(new File(ImagePath)),
                    imgFormat);
            System.out.println("4" + blipId);
            //System.out.println(document.getNextPicNameNumber(imgFormat));
            document.createPicture(blipId,
                    document.getNextPicNameNumber(imgFormat),
                    600, 400);
            //System.out.println("5");

        } catch (InvalidFormatException e1) {
            e1.printStackTrace();
        }
        
        
        	
        
        FileOutputStream outStream = null;
        try {
            String fileName = "./WordDocs/" + Classname +"/"+ FileNme + ".docx";
            System.out.println(fileName);
            outStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("First Catch");
            e.printStackTrace();
        }
        try {
            document.write(outStream);
            outStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Second Catch");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Third Catch");
            e.printStackTrace();
        }//End of catch
	}//End of method ImageAttachClass
	
	
	/*
     * Checking the image file format...
     */
private static int getImageFormat(String ImagePath) {
  int format;
  if (ImagePath.endsWith(".emf"))
   format = Document.PICTURE_TYPE_EMF;
  else if (ImagePath.endsWith(".wmf"))
   format = Document.PICTURE_TYPE_WMF;
  else if (ImagePath.endsWith(".pict"))
   format = Document.PICTURE_TYPE_PICT;
  else if (ImagePath.endsWith(".jpeg") || ImagePath.endsWith(".jpg"))
   format = Document.PICTURE_TYPE_JPEG;
  else if (ImagePath.endsWith(".png"))
   format = Document.PICTURE_TYPE_PNG;
  else if (ImagePath.endsWith(".dib"))
   format = Document.PICTURE_TYPE_DIB;
  else if (ImagePath.endsWith(".gif"))
   format = Document.PICTURE_TYPE_GIF;
  else if (ImagePath.endsWith(".tiff"))
   format = Document.PICTURE_TYPE_TIFF;
  else if (ImagePath.endsWith(".eps"))
   format = Document.PICTURE_TYPE_EPS;
  else if (ImagePath.endsWith(".bmp"))
   format = Document.PICTURE_TYPE_BMP;
  else if (ImagePath.endsWith(".wpg"))
   format = Document.PICTURE_TYPE_WPG;
  else {
   return 0;
  }
  return format;
 }
}//End of Class ImageClass
