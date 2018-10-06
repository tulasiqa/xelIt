package vibe.reports.tests;

import org.testng.annotations.Test;
import common.Priority;
import vibe.calendar2.tests.CalendarMethods;
import vibe.tasks.tests.TaskMethods;


@Priority(134)
public class ReportsTest extends ReportsMethods{
	
	TaskMethods tk = new TaskMethods();		
	CalendarMethods cal = new CalendarMethods();
	@Test(priority=13401)
	public void validateAllTablesWithTitlesOfReports() throws Exception{
		logInfo("Entered into validateAllTablesWithTitlesOfReports() test");			
		navigate2Report();	
		 typesofReports();		
	}
	
	//Reports- Select Report from table , Send bulk emails to all users. 
	@Test(priority=13402)
	public void sendBulkmailFromReports() throws Exception{
		 logInfo("Entered into sendBulkmailFromReports test");
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 exportList("Send Bulk Email");
		 sendBulkMessage(subjectBulk);
		 confirmationMessage("Email sent");
	}
	
	//Reports- Select Report from table , createEvent to all users. 
	@Test(priority=13403)
	public void createEventFromReport() throws Exception{
		logInfo("Entered into createEventFromReport() test");	
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 exportList("Create Event");
		 createEvent(eventText, 0);
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 exportList("Create Event");
		 selectEventAndInvitation(eventText);		 
		 cal.go2CalendarPage();
		 cal.selectCalendarEventInMonthView(eventText);
		 cal.deleteEvent();
		 
	}
	
	@Test(priority=13404)
	public void sendDirectMessage() throws Exception{
		logInfo("Entered into sendDirectMessage() test");	
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 exportList("Send Direct Message");
		 directMessage();		 
	}
	
	
	@Test(priority=13405)
	public void downLoadCSV() throws Exception{
		logInfo("Entered into sendDirectMessage() test");	
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 downLoadSelection("Csv");
		 downloadConfirmation();
		 
	}
	
	
	@Test(priority=13406)
	public void downLoadPdf() throws Exception{
		logInfo("Entered into downLoadPdf() test");	
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 downLoadSelection("Pdf");
		 downloadConfirmation();
		 
	}
	
	@Test(priority=13404)
	public void downLoadTabDelimited() throws Exception{
		logInfo("Entered into downLoadTabDelimited() test");	
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 downLoadSelection("Tab Delimited");
		 downloadConfirmation();		 
	}
	
	@Test(priority=13404)
	public void downLoadlabels() throws Exception{
		logInfo("Entered into downLoadlabels() test");	
		 navigate2Report();			
		 reportstable(prop.getLocatorForEnvironment(appUrl,"reportName")) ;
		 downLoadSelection("Labels");
		 downloadConfirmation();
	}
	
	
	


}
