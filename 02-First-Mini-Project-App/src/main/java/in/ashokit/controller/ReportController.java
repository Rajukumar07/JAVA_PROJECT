package in.ashokit.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import in.ashokit.dto.SearchRequest;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.services.ReportServiceImpl;

@Controller
public class ReportController {

	@Autowired
	private ReportServiceImpl serviceImpl;

	@GetMapping(value = { "/", "/search" })
	public String loadForm(Model m) {
		m.addAttribute("searchRequest", new SearchRequest());
		init(m);

		return "index";
	}

	private void init(Model m) {
		List<String> planName = serviceImpl.getByPlanName();
		List<String> planStatus = serviceImpl.getByPlanStatus();

		m.addAttribute("planItems", planName);
		m.addAttribute("statusItems", planStatus);
	}

	@PostMapping("/search")
	public String searchHandleBtn(@ModelAttribute("searchRequest") SearchRequest s, Model m) {

		System.out.println(s);
		init(m);

		List<CitizenPlan> report = serviceImpl.handleSearchBtn(s);
		m.addAttribute("report", report);

		return "index";

	}

	@GetMapping("/downloadpdf")
	public void exportPDF(ServletContext context, HttpServletResponse response, HttpServletRequest request) {

		serviceImpl.exportPDF(context, response, request);
	}

	/*
	 * @GetMapping("/getpdf") public void exportToPDF(HttpServletResponse response)
	 * throws DocumentException, IOException {
	 * response.setContentType("application/pdf"); DateFormat dateFormatter = new
	 * SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
	 * dateFormatter.format(new Date());
	 * 
	 * String headerKey = "Content-Disposition"; String headerValue =
	 * "attachment; filename=users_" + currentDateTime + ".pdf";
	 * response.setHeader(headerKey, headerValue);
	 * 
	 * List<CitizenPlan> findAll = repo.findAll();
	 * 
	 * PDFExporter exporter = new PDFExporter(findAll); exporter.export(response);
	 * 
	 * 
	 * }
	 */

}
