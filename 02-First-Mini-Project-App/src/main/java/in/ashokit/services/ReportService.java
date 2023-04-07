package in.ashokit.services;

import java.util.List;

import in.ashokit.dto.SearchRequest;
import in.ashokit.entity.CitizenPlan;

public interface ReportService {

	public List<String> getByPlanName();
	
	public List<String> getByPlanStatus( );
	
	public List<CitizenPlan> handleSearchBtn(SearchRequest searchRequest);
	
	public void getReportInPDF();
	
	public void getReportInExcel();
	
	
	
}
