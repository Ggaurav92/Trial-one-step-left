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

	
	/*
	public static void main(String[] args) throws IOException{
		ImageClass img = new ImageClass();
		img.ImageAttachClass();
	}
	*/
	
	
	
	public void ImageAttachClass(String Classname, String FileNme, String StepName, String StepDescrp, String ImagePath, String FailureDescrip) throws FileNotFoundException {
    	
    	//String StepName = "Step 1";
    	//String ImagePath = "C:\\Users\\P10444427\\Downloads\\pngtree-light-effect-border-png-image_3552885.jpg";
    	//String StepDescrp = "Click on an Element"; 
    	//String FileNme = "3.4.14";
    	//String FailureDescrip = "This Test Fails";
    	
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        // Create a document file
        CustomXWPFDocument document = new CustomXWPFDocument();


        // insert doc details
        // Create a para -1
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
        //paragraphFourRunOne.addBreak();
        paragraphFourRunOne.setBold(true);
        //paragraphFourRunOne.setUnderline(UnderlinePatterns.SINGLE);
        paragraphFourRunOne.setFontSize(10);
        paragraphFourRunOne.setFontFamily("Verdana");
        paragraphFourRunOne.setColor("000070");
        paragraphFourRunOne.setText(FailureDescrip);
     
     // Create a para 3 - Failure Description
        XWPFParagraph paragraphFive = document.createParagraph();
        paragraphFive.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphFiveRunOne = paragraphFive.createRun();
        paragraphFiveRunOne.addBreak();
        paragraphFourRunOne.setBold(true);
        paragraphFourRunOne.setUnderline(UnderlinePatterns.SINGLE);
        paragraphFourRunOne.setFontSize(10);
        paragraphFourRunOne.setFontFamily("Verdana");
        paragraphFourRunOne.setColor("000070");
        paragraphFourRunOne.setText("This can be used as extra comment");
        
        // Createa a para -4 - DateStamp
        XWPFParagraph paragraphTwo = document.createParagraph();
        paragraphTwo.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
        paragraphTwoRunOne.setFontSize(10);
        paragraphTwoRunOne.setFontFamily("Verdana");
        paragraphTwoRunOne.setColor("000070");
        paragraphTwoRunOne.setText(date);
        paragraphTwoRunOne.addBreak();


        // Createa a para -5  - TimeStamp
        XWPFParagraph paragraphThree = document.createParagraph();
        paragraphThree.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphThreeRunOne = paragraphThree.createRun();
        paragraphThreeRunOne.setFontSize(10);
        paragraphThreeRunOne.setFontFamily("Verdana");
        paragraphThreeRunOne.setColor("000070");
        paragraphThreeRunOne.setText("5.30 AM IST");
        paragraphThreeRunOne.addBreak();
        
        
     
     
        
        
        
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
            System.out.println(document
                    .getNextPicNameNumber(imgFormat));
            document.createPicture(blipId,
                    document.getNextPicNameNumber(imgFormat),
                    600, 400);
            System.out.println("5");

        } catch (InvalidFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        	
        
        FileOutputStream outStream = null;
        try {
            //double x = Math.random();
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
