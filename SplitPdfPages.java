package com.demo.core;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class SplitPdfPages {
	public static void main(String[] args) throws DocumentException, IOException {
		String pdfFileLocation = "/Users/jaikantdangi/Downloads/demo/demo.pdf";
	    PdfReader reader = new PdfReader(pdfFileLocation);
	    int n = reader.getNumberOfPages();
	    reader.close();
	    String path;
	    PdfStamper stamper;
	    for (int i = 1; i <= n; i++) {
	        reader = new PdfReader(pdfFileLocation);
	        reader.selectPages(String.valueOf(i));
	        path = String.format("p-%s.pdf", i);
	        stamper = new PdfStamper(reader,new FileOutputStream(path));
	        stamper.close();
	        reader.close();
	    }
	}
}

// add this delendency
/** <dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>5.5.10</version>
</dependency> **/
