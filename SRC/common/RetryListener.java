package common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer,ITestListener  {

	@Override
	public void transform(ITestAnnotation testannotation, Class testClass,
			Constructor testConstructor, Method testMethod)	{
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

		if (retry == null)	{
			testannotation.setRetryAnalyzer(Retry.class);
		}

	}

		 @Override
			public void onFinish(ITestContext context) {
				Set<ITestResult> failedTests = context.getSkippedTests().getAllResults();
				for (ITestResult temp : failedTests) {
					ITestNGMethod method = temp.getMethod();
					if (context.getFailedTests().getResults(method).size() > 1) {
						failedTests.remove(temp);
					} else {
						if (context.getPassedTests().getResults(method).size() > 0) {
							failedTests.remove(temp);
						}else{
							if(context.getSkippedTests().getResults(method).size()>0){
								failedTests.remove(temp);
							}
						}
					}
				}
			}
		


	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
}