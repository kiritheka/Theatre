package theatrebooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReport implements RevenueGeneration {

	@Override
	public boolean generateRevenueReport(ArrayList<Show> listOfShows) {
		Logger LOGGER = Logger.getLogger(PDFReport.class.getName());
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(new File("/home/linuxuser/Theatre Revenue.pdf")));
			document.open();
			PdfPTable table = new PdfPTable(3);

			PdfPCell c1 = new PdfPCell(new Phrase("Theatre"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("ShowTiming"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("TotalAmountSold"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			document.add(table);
			if (listOfShows != null) {
				for (Show show : listOfShows) {
					int revenue = 0;
					for (Entry<Seater, Integer> entry : show.seatAndFilledCount.entrySet()) {
						revenue = revenue + (entry.getValue() * entry.getKey().price);
					}
					if (show != null) {
						table.addCell(show.theatre.name);
						table.addCell(show.name);
						table.addCell("Rs." + String.valueOf(revenue));
					}
				}
			}
			document.add(table);
			document.close();
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error in writing !!!", e);
		}
		return false;
	}
}