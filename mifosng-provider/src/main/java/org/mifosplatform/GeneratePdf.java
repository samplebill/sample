package org.mifosplatform;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePdf {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {

	      Document document = new Document();
	      PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
	      document.open();

	      Paragraph paragraph=new Paragraph("FIXED DEPOSIT CERTIFICATE ",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0)));

	      paragraph.setAlignment(Element.ALIGN_CENTER);
	      paragraph.setSpacingAfter(30);

	      document.add(paragraph);
	      Image img = Image.getInstance("logo.jpg");
		    img.scaleAbsolute(70,80);
		    img.setAbsolutePosition(450,670);
	     document.add(img);
	     Font font= FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD);
	     document.add(new Paragraph(""));
	      document.add(new Paragraph(""));
	      document.add(new Paragraph("Extenal Id:",font));
	      document.add(new Paragraph("Created Date:",font));
	      document.add(new Paragraph("Mature date:",font));
	      document.add(new Paragraph("Client Name:",font));
	      document.add(new Paragraph("Amount:",font));
	      document.add(new Paragraph("Interest Rate:",font));
	      document.add(new Paragraph("Tenure:",font));
	      document.add(new Paragraph("Maturity Amount:",font));



	      document.close();
	    }


	}
