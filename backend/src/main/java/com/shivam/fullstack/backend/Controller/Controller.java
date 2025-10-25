package com.shivam.fullstack.backend.Controller;

import com.shivam.fullstack.backend.Exception.MyException;
import com.shivam.fullstack.backend.utility.Utility;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class Controller {
    @Autowired
    private Utility utility;
    @PostMapping("/marge-pdf")
    public ResponseEntity<byte[]> marge_pdf(@RequestParam("multypdf[]")List<MultipartFile> list_of_file){
     try{  return  utility.MergePdf(list_of_file);

     } catch (Exception e) {
         HttpHeaders headers=new HttpHeaders();
         headers.setLocation(URI.create("http"));
         return new  ResponseEntity<>(headers,HttpStatus.SEE_OTHER);
     }

    }

    @PostMapping("/delete-pdf")
    public  ResponseEntity<byte[]> delete_page_pdf(@RequestParam("delete-page") MultipartFile file,
                                  @RequestParam("delete-page-no") String No_of_delete_page,
                                  Model model) {
        try {

          return   utility.PageDelete(No_of_delete_page, file);

        } catch (Exception e) {

            HttpHeaders headers=new HttpHeaders();
            headers.setLocation(URI.create("http"));
            return new  ResponseEntity<>(headers,HttpStatus.SEE_OTHER);


        }

    }


    @PostMapping("/img-to-pdf")
    public ResponseEntity<byte[]> img_to_pdf(@RequestParam("multyimg[]") List<MultipartFile> file_of_img){
try{
   return utility.img_to_pdf(file_of_img);

} catch (Exception e) {
    HttpHeaders headers=new HttpHeaders();
    headers.setLocation(URI.create("http"));
  return new  ResponseEntity<>(headers,HttpStatus.SEE_OTHER);

}



    }
}
