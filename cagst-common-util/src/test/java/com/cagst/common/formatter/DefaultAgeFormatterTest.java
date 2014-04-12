package com.cagst.common.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for the {@link DefaultAgeFormatter} class.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
@RunWith(JUnit4.class)
public class DefaultAgeFormatterTest {
	private final DefaultAgeFormatter formatter = new DefaultAgeFormatter();

	/**
	 * Test the formatAge method with DateTime fields.
	 */
	@Test
	public void testFormatAge_DateTime() {
		DateTime dob1 = new DateTime(1970, 4, 19, 5, 30);
		DateTime dob2 = new DateTime(2012, 3, 15, 5, 30);

		DateTime testDate1 = new DateTime(2012, 3, 18, 8, 30);
		DateTime testDate2 = new DateTime(2012, 3, 20, 8, 30);
		DateTime testDate3 = new DateTime(2012, 4, 20, 8, 30);
		DateTime testDate4 = new DateTime(2012, 5, 20, 8, 30);

		String age1 = formatter.formatAge(dob1, testDate1);
		assertEquals(age1, "41y 10m");

		String age2 = formatter.formatAge(dob1, testDate2);
		assertEquals(age2, "41y 11m");

		String age3 = formatter.formatAge(dob1, testDate3);
		assertEquals(age3, "42y");

		String age4 = formatter.formatAge(dob1, testDate4);
		assertEquals(age4, "42y 1m");

		String age5 = formatter.formatAge(dob2, testDate1);
		assertEquals(age5, "3d");

		String age6 = formatter.formatAge(dob2, testDate2);
		assertEquals(age6, "5d");

		String age7 = formatter.formatAge(dob2, testDate3);
		assertEquals(age7, "1m 5d");

		String age8 = formatter.formatAge(dob2, testDate4);
		assertEquals(age8, "2m 5d");
	}

	/**
	 * Test the formatAge method with Date fields.
	 */
	@Test
	public void testFormatAge_Date() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(1970, 4, 19);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2012, 3, 15);

		Date dob1 = cal1.getTime();
		Date dob2 = cal2.getTime();

		Calendar test1 = Calendar.getInstance();
		test1.set(2012, 3, 18);

		Calendar test2 = Calendar.getInstance();
		test2.set(2012, 3, 20);

		Calendar test3 = Calendar.getInstance();
		test3.set(2012, 4, 20);

		Calendar test4 = Calendar.getInstance();
		test4.set(2012, 5, 20);

		Date testDate1 = test1.getTime();
		Date testDate2 = test2.getTime();
		Date testDate3 = test3.getTime();
		Date testDate4 = test4.getTime();

		String age1 = formatter.formatAge(dob1, testDate1);
		assertEquals(age1, "41y 10m");

		String age2 = formatter.formatAge(dob1, testDate2);
		assertEquals(age2, "41y 11m");

		String age3 = formatter.formatAge(dob1, testDate3);
		assertEquals(age3, "42y");

		String age4 = formatter.formatAge(dob1, testDate4);
		assertEquals(age4, "42y 1m");

		String age5 = formatter.formatAge(dob2, testDate1);
		assertEquals(age5, "3d");

		String age6 = formatter.formatAge(dob2, testDate2);
		assertEquals(age6, "5d");

		String age7 = formatter.formatAge(dob2, testDate3);
		assertEquals(age7, "1m 5d");

		String age8 = formatter.formatAge(dob2, testDate4);
		assertEquals(age8, "2m 5d");
	}
}
