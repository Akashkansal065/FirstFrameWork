package com.magic.listners;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.magic.utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class CustomAssert {

	public static Map<Long,Boolean> map = new HashMap<Long,Boolean>();
	
	/*public static final ThreadLocal<Boolean> threadId = new ThreadLocal<Boolean>()
	{
		@Override 
		protected Boolean initialValue() 
		{
			return new Boolean(true); 
		}	
	};*/

	
	private static void addVerificationFailure(final Throwable e) {
		map.put(Thread.currentThread().getId(), false);
		ExtentTestManager.getTest().log(LogStatus.FAIL,"!!!FAILURE ALERT!!! - Assertion Failure: " + e.getMessage());
		System.out.println("Inside Failure Assert");
	}

	private static void addVerificationFailure(final Throwable e,String Actual,String Expected) {
		map.put(Thread.currentThread().getId(), false);
		ExtentTestManager.getTest().log(LogStatus.FAIL,Actual+" Not Matched with "+Expected,"!!!FAILURE ALERT!!! - Assertion Failure: " + e.getMessage());
		System.out.println("Inside Failure Assert");
	}

	private static void addVerificationPass(String Message) {
		ExtentTestManager.getTest().log(LogStatus.PASS,Message);
	}

	public static void AssertEqual(String Actual,String Expected,String Statement)
	{
		try
		{
			Assert.assertEquals(Actual,Expected,Statement);
			addVerificationPass(Statement);
		}
		catch(Throwable e)
		{
			addVerificationFailure(e,Actual,Expected);
		}
	}
	public static void AssertEqual(String Actual,String Expected)
	{
		try
		{
			Assert.assertEquals(Actual,Expected);
			addVerificationPass(Actual+" are same as "+Expected);
		}
		catch(Throwable e)
		{
			addVerificationFailure(e);
		}
	}
	public static void AssertEquals(final boolean actual, final boolean expected, final String message) {

		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final byte actual, final byte expected, final String message) {

		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final byte[] actual, final byte[] expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}

	}

	public static void AssertEquals(final char actual, final char expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final double actual, final double expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final float actual, final float expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final int actual, final int expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final long actual, final long expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {

			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final Object actual, final Object expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final Object[] actual, final Object[] expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertEquals(final short actual, final short expected, final String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}
	public static void AssertFalse(final boolean condition, final String message) {
		try {
			Assert.assertFalse(condition, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertNotNull(final Object object, final String message) {
		try {
			Assert.assertNotNull(object, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertNotSame(final Object actual, final Object expected, final String message) {
		try {
			Assert.assertNotSame(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertNull(final Object object, final String message) {
		try {
			Assert.assertNull(object, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertSame(final Object actual, final Object expected, final String message) {
		try {
			Assert.assertSame(actual, expected, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

	public static void AssertTrue(final boolean condition, final String message) {
		try {
			Assert.assertTrue(condition, message);
			addVerificationPass(message);
		} catch (Throwable e) {
			addVerificationFailure(e);
		}
	}

}