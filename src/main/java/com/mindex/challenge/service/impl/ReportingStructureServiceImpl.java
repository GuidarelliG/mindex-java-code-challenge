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

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Overload
    private int numberOfReportingIds(String top_id){
	    //While[newids.size > 0]
	    //	old_ids, prev_ids, new_ids
	    //	prev_ids = new_ids
	    //	clear new_ids
	    //	for id in prev_ids
	    //		reporters = getemployee(ids).getreporters()
	    //		for reporter in reporters
	    //			if reporter is not in old_ids
	    //				new_ids += reporter
	    //				old_ids += reporter
	    //
	    //return old_ids.size() - 1 (because I should probably include the original id from the get-go 
	    // to save a small amount of iterations :) )
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
		    prev_ids.addAll(new_ids)
		    new_ids.clear();
		    for(String id : prev_ids)
		    {
			    reporters.clear();
			    reporters.addAll(Arrays.asList(employeeRepository.findByEmployeeId(id).getDirectReports()));
			    //should also take into account that the ids may not be real employees...
			    //This may result in an error as null does not have 'getDirectReports' functionality
			    for(String reporter : reporters)
			    {
				    if(!old_ids.contains(reporter))
				    {
					    old_ids.add(reporter);
					    new_ids.add(reporter);
				    }
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
	reportingStructure.setEmployeeId(id);
	reportingStructure.setNumberOfReports(numberOfReportingIds(ids))


        return reportingStructure;
    }

}
