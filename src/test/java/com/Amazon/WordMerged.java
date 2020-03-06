package com.Amazon;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

public class WordMerged {

    private final OutputStream result;
    private final List<InputStream> inputs;
    private XWPFDocument first;
    
    /*
    public static void main(String[] args) throws Exception {

        FileOutputStream faos = new FileOutputStream("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\result.docx");
        
        ArrayList<String> paths = new ArrayList<String>();
    	paths.add("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\Step_1.docx");
    	paths.add("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\Step_2.docx");
    	paths.add("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\Step_3.docx");
        
        WordMerged wm = new WordMerged(faos);

        for(String InpStrm : paths) {
        wm.add(new FileInputStream(InpStrm));	
        }
        
        //wm.add( new FileInputStream("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\Step_1.docx") );
        //wm.add( new FileInputStream("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\Step_2.docx") );
        //wm.add( new FileInputStream("C:\\Users\\P10444427\\Desktop\\Projects from GIT\\TrialFrame-master\\WordDocs\\3.4.14\\Step_3.docx") );
        
        
        wm.doMerge();
        wm.close();

    } 
    
    */
    
    
    

    public WordMerged(OutputStream result) {
        this.result = result;
        inputs = new ArrayList<InputStream>();
    }

    public void add(InputStream stream) throws Exception{            
        inputs.add(stream);
        OPCPackage srcPackage = OPCPackage.open(stream);
        XWPFDocument src1Document = new XWPFDocument(srcPackage);         
        if(inputs.size() == 1){
            first = src1Document;
        } else {            
            CTBody srcBody = src1Document.getDocument().getBody();
            first.getDocument().addNewBody().set(srcBody);            
        }        
    }

    public void doMerge() throws Exception{
        first.write(result);                
    }

    public void close() throws Exception{
        result.flush();
        result.close();
        for (InputStream input : inputs) {
            input.close();
        }
    }   
}