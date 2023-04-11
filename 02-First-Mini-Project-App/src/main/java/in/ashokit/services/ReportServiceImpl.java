package in.ashokit.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.dto.SearchRequest;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.repositories.CitizenPlanRepo;
import in.ashokit.utils.PDFGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo repo;

	@Override
	public List<String> getByPlanName() {
		List<String> name = repo.getPlanName();
		return name;
	}

	@Override
	public List<String> getByPlanStatus() {
		List<String> status = repo.getPlanStatus();
		return status;
	}

	@Override
	public List<CitizenPlan> handleSearchBtn(SearchRequest request) {

		System.out.println(request);

		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && "" != request.getPlanName()) {
			entity.setPlanName(request.getPlanName());
		}

		if (!"".equals(request.getPlanStatus()) && null != request.getPlanStatus()) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (!"".equals(request.getGender()) && null != request.getGender()) {
			entity.setGender(request.getGender());
		}

		if (!"".equals(request.getStartDate()) && null != request.getStartDate()) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(date);
		}

		if (!"".equals(request.getEndDate()) && null != request.getEndDate()) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(date);
		}

		List<CitizenPlan> findAll = repo.findAll(Example.of(entity));
		System.out.println(findAll);

		return findAll;
	}

	@Override
	public void exportPDF(ServletContext context, HttpServletResponse response, HttpServletRequest request) {

		List<CitizenPlan> findAll = repo.findAll();

		PDFGenerator p = new PDFGenerator();
		boolean isFlag = p.getReportInPDF(findAll, context, response, request);

		if (isFlag) {
			String realPath = request.getServletContext().getRealPath("D:/reportpdf/" + "citizens" + ".pdf");
			fileDownLoad(realPath, context, response, "citizen.pdf");
		}

	}

	private void fileDownLoad(String fullPath, ServletContext context, HttpServletResponse response, String filename) {
		File file = new File(fullPath);
		final int Buffer_Size = 4096;
		if (file.exists()) {

			try {
				FileInputStream fis = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + filename);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[Buffer_Size];
				int bytesRead = -1;
				while ((bytesRead = fis.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				fis.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void getReportInExcel() {
		// TODO Auto-generated method stub

	}

}
