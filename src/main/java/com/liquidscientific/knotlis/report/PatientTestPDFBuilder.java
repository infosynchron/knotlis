package com.liquidscientific.knotlis.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.liquidscientific.knotlis.entity.Narcotics;

public class PatientTestPDFBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container

		@SuppressWarnings("unchecked")
		List<Narcotics> listpatienttest = (List<Narcotics>) model
				.get("patienttestlist");

		document.add(new Paragraph(
				"____________________________________________________"));

		for (Narcotics narcotics : listpatienttest) {
			Image image = Image.getInstance(narcotics.getFilepath());
			image.scaleAbsolute(200f, 200f);
			document.add(image);
		}

		PdfPTable table = new PdfPTable(2);
		table.setWidths(new float[] { 3.0f, 2.5f });
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(BaseColor.WHITE);
		font.setSize(9.0F);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.DARK_GRAY);
		cell.setPadding(5);

		// write table header
		cell.setPhrase(new Phrase("MRN#", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Client ID", font));
		table.addCell(cell);

		// write table row data
		for (Narcotics narcotics : listpatienttest) {
			table.addCell(narcotics.getMrn());
			table.addCell(narcotics.getClientid());

		}
		document.add(table);

	}

}
