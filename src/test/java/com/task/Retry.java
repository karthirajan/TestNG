package com.task;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int minCount = 0;
	int maxCount = 3;

	public boolean retry(ITestResult result) {
		
		if(minCount < maxCount){
			
			minCount++;
			
			System.out.println("Rerun the failure test :"+result.getName());
			return true;
		}
		
		return false;
	}

}
