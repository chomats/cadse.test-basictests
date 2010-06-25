package fr.imag.adele.cadse.test.basictests.metrics;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

public abstract class Metrics_common extends GTCadseTestCase {

	/** The list of all execution times */
	protected static ArrayList<Long> time = new ArrayList<Long>();

	protected Long startTime;

	/** Displays information on the console */
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		System.out.println("Starting test #" + time.size());
		startTime = System.currentTimeMillis();
	}

	/** Displays test result on the console */
	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		long duration = System.currentTimeMillis() - startTime;
		time.add(duration);
		System.out.println("Test ended (" + duration + " ms)");
	}

	/** Displays test result on the console */
	@AfterClass
	public static void afterClass() {
		System.out.println(getStatistics());
	}

	public static String getStatistics() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number of tests : ");
		sb.append(time.size());
		sb.append("\n");
		for (int i = 0; i < time.size(); i++) {
			sb.append("#");
			sb.append(i);
			sb.append(" : ");
			sb.append(time.get(i));
			sb.append(" ms\n");
		}

		return sb.toString();
	}
}
