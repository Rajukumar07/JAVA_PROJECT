package in.ashokit.services;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ashokit.dto.SearchRequest;
import in.ashokit.entity.CitizenPlan;

public interface ReportService {

	public List<String> getByPlanName();
	
	public List<String> getByPlanStatus( );
	
	public List<CitizenPlan> handleSearchBtn(SearchRequest searchRequest);
	
	public void exportPDF(ServletContext context, HttpServletResponse response, HttpServletRequest request);
	
	public void getReportInExcel();
	
	
	
}
