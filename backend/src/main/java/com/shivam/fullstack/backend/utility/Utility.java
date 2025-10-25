package com.shivam.fullstack.backend.utility;
import com.shivam.fullstack.backend.Exception.MyException;
import com.shivam.fullstack.backend.helper.Helper;
import jdk.jfr.ContentType;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.net.URI;
import java.util.List;
import static java.lang.Integer.parseInt;
@Service
public class Utility {
    public Utility() {
    }
    public  ResponseEntity<byte[]> PageDelete(String numstr, MultipartFile file) throws Exception{
try{
    PDDocument document=PDDocument.load(file.getInputStream());
    int len_page=document.getNumberOfPages();
 int[] delete_arr= Helper.split_arr(numstr,len_page);
    Helper.mergeSort(delete_arr,0,delete_arr.length-1);
   for(int delete_num: delete_arr) {
           document.removePage(delete_num -1);
           System.out.println(delete_num+"  page deleted");
    }
   ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       document.save(outputStream);
       byte[] userdata=outputStream.toByteArray();

    HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://pdeditor1.netlify.app/html/download-doc"));

    headers.setContentType(MediaType.APPLICATION_PDF);
    document.close();
    headers.setContentDisposition(ContentDisposition.builder("attachment").filename("update.pdf").build());
return new
    ResponseEntity<>(userdata,headers, HttpStatus.OK);
} catch (Exception e) {

    throw new Exception(" technical Error with pdf");
}
    }
    public ResponseEntity<byte[]> MergePdf(List<MultipartFile> list_of_file) throws Exception{
        PDFMergerUtility merger=new PDFMergerUtility();
        InputStream inputStream;
        try {
            for (MultipartFile file : list_of_file) {
                inputStream = file.getInputStream();
                merger.addSource(inputStream);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            merger.setDestinationStream(outputStream);
            merger.mergeDocuments(null);

            byte[] userdata=outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create("https://pdeditor1.netlify.app/html/download-doc"));

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("update.pdf").build());
            return new
                         ResponseEntity<>(userdata,headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
  public ResponseEntity<byte[]> img_to_pdf(List<MultipartFile> file_of_img) throws Exception{
        try {
            PDDocument document=new PDDocument();

            for(MultipartFile file: file_of_img){


 PDImageXObject pdobj=PDImageXObject.createFromByteArray(document,file.getBytes(),file.getOriginalFilename());
float height=pdobj.getHeight();
float width= pdobj.getWidth();
PDRectangle shape= new PDRectangle(width,height);
PDPage page=new PDPage(shape);
document.addPage(page);
PDPageContentStream con= new PDPageContentStream(document,page);
con.drawImage(pdobj,0,0,width,height);

                con.close();
                
            }
            ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
            document.save(outputStream);
            byte[] userdata=outputStream.toByteArray();
            document.close();
            HttpHeaders header= new HttpHeaders();
                header.setLocation(URI.create("https://pdeditor1.netlify.app/html/download-doc"));

            header.setContentType(MediaType.APPLICATION_PDF);
            header.setContentDisposition(ContentDisposition.builder("attachment").filename("img_to_pdf.pdf").build());
            return  new ResponseEntity<>(userdata,header,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}





