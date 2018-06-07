package com.magic.listners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {


	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
	{
		//System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();

		if (retry == null)	{
			System.out.println("dddddddddddddddddddddddddddddd");
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}


	}
}