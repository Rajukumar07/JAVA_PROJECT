package in.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.ashokit.entity.CitizenPlan;

public class PDFGenerator {
	
	public boolean getReportInPDF(List<CitizenPlan> citizenPlan, ServletContext context, HttpServletResponse response,
			HttpServletRequest request) {

		
		Document document = new Document(PageSize.A4);

		try {
			String filePath = context.getRealPath("D:/Pdf_Report");
			File file = new File(filePath);
			boolean exist = new File(filePath).exists();
			if (!exist) {
				  new File(filePath).mkdir();
			}

			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "report" + ".pdf"));
			document.open();

			Font font = FontFactory.getFont("Arial", 12, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("All Citizen", font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(11);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			table.setWidths(new float[] { 1.5f, 1.5f, 1.0f, 1.0f, 1.5f });
			table.setWidthPercentage(100f);

			PdfPCell CitizenId = new PdfPCell(new Paragraph("ID", font));
			CitizenId.setBorderColor(BaseColor.BLACK);
			CitizenId.setPaddingLeft(10f);
			CitizenId.setHorizontalAlignment(Element.ALIGN_CENTER);
			CitizenId.setVerticalAlignment(Element.ALIGN_CENTER);
			CitizenId.setExtraParagraphSpace(5f);
			CitizenId.setBackgroundColor(BaseColor.GRAY);
			table.addCell(CitizenId);

			PdfPCell holderValue = new PdfPCell(new Paragraph("Holder", font));
			holderValue.setBorderColor(BaseColor.BLACK);
			holderValue.setPaddingLeft(10f);
			holderValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			holderValue.setVerticalAlignment(Element.ALIGN_CENTER);
			holderValue.setExtraParagraphSpace(5f);
			holderValue.setBackgroundColor(BaseColor.GRAY);
			table.addCell(holderValue);

			PdfPCell genderValue = new PdfPCell(new Paragraph("Gender", font));
			genderValue.setBorderColor(BaseColor.BLACK);
			genderValue.setPaddingLeft(10f);
			genderValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			genderValue.setVerticalAlignment(Element.ALIGN_CENTER);
			genderValue.setExtraParagraphSpace(5f);
			genderValue.setBackgroundColor(BaseColor.GRAY);
			table.addCell(genderValue);

			PdfPCell PlanValue = new PdfPCell(new Paragraph("Plan", font));
			PlanValue.setBorderColor(BaseColor.BLACK);
			PlanValue.setPaddingLeft(10f);
			PlanValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			PlanValue.setVerticalAlignment(Element.ALIGN_CENTER);
			PlanValue.setExtraParagraphSpace(5f);
			PlanValue.setBackgroundColor(BaseColor.GRAY);
			table.addCell(PlanValue);

			PdfPCell statusValue = new PdfPCell(new Paragraph("Status", font));
			statusValue.setBorderColor(BaseColor.BLACK);
			statusValue.setPaddingLeft(10f);
			statusValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			statusValue.setVerticalAlignment(Element.ALIGN_CENTER);
			statusValue.setExtraParagraphSpace(5f);
			statusValue.setBackgroundColor(BaseColor.GRAY);
			table.addCell(statusValue);

			for (CitizenPlan c : citizenPlan) {

				PdfPCell id = new PdfPCell(new Paragraph(c.getCitizenId().toString(), font));
				id.setBorderColor(BaseColor.BLACK);
				id.setPaddingLeft(10f);
				id.setHorizontalAlignment(Element.ALIGN_CENTER);
				id.setVerticalAlignment(Element.ALIGN_CENTER);
				id.setExtraParagraphSpace(5f);
				id.setBackgroundColor(BaseColor.GRAY);
				table.addCell(id);

				PdfPCell holder = new PdfPCell(new Paragraph(c.getCitizenName(), font));
				holderValue.setBorderColor(BaseColor.BLACK);
				holder.setPaddingLeft(10f);
				holder.setHorizontalAlignment(Element.ALIGN_CENTER);
				holder.setVerticalAlignment(Element.ALIGN_CENTER);
				holder.setExtraParagraphSpace(5f);
				holder.setBackgroundColor(BaseColor.GRAY);
				table.addCell(holder);

				PdfPCell gender = new PdfPCell(new Paragraph(c.getGender(), font));
				gender.setBorderColor(BaseColor.BLACK);
				gender.setPaddingLeft(10f);
				gender.setHorizontalAlignment(Element.ALIGN_CENTER);
				gender.setVerticalAlignment(Element.ALIGN_CENTER);
				gender.setExtraParagraphSpace(5f);
				gender.setBackgroundColor(BaseColor.GRAY);
				table.addCell(gender);

				PdfPCell Plan = new PdfPCell(new Paragraph(c.getPlanName(), font));
				Plan.setBorderColor(BaseColor.BLACK);
				Plan.setPaddingLeft(10f);
				Plan.setHorizontalAlignment(Element.ALIGN_CENTER);
				Plan.setVerticalAlignment(Element.ALIGN_CENTER);
				Plan.setExtraParagraphSpace(5f);
				Plan.setBackgroundColor(BaseColor.GRAY);
				table.addCell(Plan);

				PdfPCell status = new PdfPCell(new Paragraph(c.getPlanStatus(), font));
				status.setBorderColor(BaseColor.BLACK);
				status.setPaddingLeft(10f);
				status.setHorizontalAlignment(Element.ALIGN_CENTER);
				status.setVerticalAlignment(Element.ALIGN_CENTER);
				status.setExtraParagraphSpace(5f);
				status.setBackgroundColor(BaseColor.GRAY);
				table.addCell(status);

			}
			document.add(table);
			document.close();
			pdfWriter.close();

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		
		

	}

}
