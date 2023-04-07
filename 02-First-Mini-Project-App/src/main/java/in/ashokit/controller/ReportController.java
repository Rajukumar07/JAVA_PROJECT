package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

		List<String> planName = serviceImpl.getByPlanName();
		List<String> planStatus = serviceImpl.getByPlanStatus();

		m.addAttribute("searchRequest", new SearchRequest());
		m.addAttribute("planItems", planName);
		m.addAttribute("statusItems", planStatus);

		return "index";
	}
	
	@PostMapping("/search")
	public String searchHandleBtn(SearchRequest s,Model m) {
		
		List<String> planName = serviceImpl.getByPlanName();
		List<String> planStatus = serviceImpl.getByPlanStatus();

		m.addAttribute("searchRequest", new SearchRequest());
		m.addAttribute("planItems", planName);
		m.addAttribute("statusItems", planStatus);
		
		List<CitizenPlan> report = serviceImpl.handleSearchBtn(s);
		m.addAttribute("report", report);
		
		return "index";
		
	}

}
