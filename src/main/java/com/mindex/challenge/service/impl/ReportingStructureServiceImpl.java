package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

	private ArrayList<String> employeeListToEmployeeIDs(List<Employee> employees )
	{
		ArrayList<String> employeeIds = new ArrayList<String>();
		for(Employee employee : employees)
		{
			employeeIds.add(employee.getEmployeeId());
		}
		return employeeIds;
	}

    private int numberOfReportingIds(String top_id){
	    ArrayList<String> old_ids = new ArrayList<String>();
	    ArrayList<String> prev_ids = new ArrayList<String>();
	    ArrayList<String> new_ids = new ArrayList<String>();
	    ArrayList<String> reporters = new ArrayList<String>();
	    old_ids.add(top_id);
	    prev_ids.add(top_id);
	    new_ids.add(top_id);
	    while(new_ids.size() > 0)
	    {
		    prev_ids.clear();
		    prev_ids.addAll(new_ids);
		    new_ids.clear();
		    for(String id : prev_ids)
		    {
			    reporters.clear();
				LOG.debug("id: [{}]", id);
				try
				{
					reporters.addAll(employeeListToEmployeeIDs(employeeRepository.findByEmployeeId(id).getDirectReports()));
			    	for(String reporter : reporters)
			    	{
				    	if(!old_ids.contains(reporter))
				    	{
					    	old_ids.add(reporter);
							new_ids.add(reporter);
				    	}
			    	}
				}
				catch (Exception e)
				{

				}
		    }
	    }
	    return old_ids.size() - 1;
    }

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Calculating Number of reporters to id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
	//I am not sure how the function above works but that is fine...

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
	}

	ReportingStructure reportingStructure = new ReportingStructure();
	reportingStructure.setEmployee(employee);
	reportingStructure.setNumberOfReports(numberOfReportingIds(id));


        return reportingStructure;
    }

}
