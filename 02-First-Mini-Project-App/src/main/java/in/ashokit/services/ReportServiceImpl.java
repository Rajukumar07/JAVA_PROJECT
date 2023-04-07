package in.ashokit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.SearchRequest;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.repositories.CitizenPlanRepo;

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
	public List<CitizenPlan> handleSearchBtn(SearchRequest searchRequest) {

		
		List<CitizenPlan> findAll = repo.findAll();
		
		return findAll;
	}

	@Override
	public void getReportInPDF() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getReportInExcel() {
		// TODO Auto-generated method stub

	}

}
