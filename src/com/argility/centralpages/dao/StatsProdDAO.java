package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.StatsProd;
//TODO - add javadoc
public interface StatsProdDAO {

	public List<StatsProd> getAllStatsProd();
	
	public List<StatsProd> getSwImportFailedList();
	
	public List<StatsProd> getSwManyToImportList();
	
	public List<StatsProd> getReplicateAndNotImportedList();
	
	public List<StatsProd> getSwNotImportedForDaysList();
	
	public List<StatsProd> getCentralProcCrashedList();
	
	public List<StatsProd> getXoutReceivedNotProcessedList();
	
	public List<StatsProd> getNotReplicatedForDays();
}
