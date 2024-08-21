package code.usa.Listner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import code.usa.myReport.ExtentReport;

public class code_Listner implements ITestListener
{

	@Override
	public void onFinish(ITestContext arg0) 
	{
		
		ExtentReport.report.endTest(ExtentReport.logger);
		
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		ExtentReport.logger= ExtentReport.report.startTest(arg0.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		ExtentReport.logger= ExtentReport.report.startTest(arg0.getName());
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		code.usa.myReport.LogStatus.fail("Test case failed");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		code.usa.myReport.LogStatus.fail("Test case failed");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
		code.usa.myReport.LogStatus.pass("Test Case method "+arg0.getMethod().getMethodName()+" started");
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		code.usa.myReport.LogStatus.pass("Test Case successfully Passed");
	}

}
