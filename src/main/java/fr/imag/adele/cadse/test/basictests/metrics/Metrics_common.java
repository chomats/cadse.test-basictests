package fr.imag.adele.cadse.test.basictests.metrics;

import java.util.ArrayList;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

public abstract class Metrics_common extends GTCadseTestCase {

	/** The list of all execution times */
	ArrayList<Long> time = new ArrayList<Long>();

	/** Method called before all the tests */
	public void beforeTests() {
	}

	/** Method called after all the tests */
	public void afterTests() {
		System.out.println(toString());
	}

	/**
	 * Method called before each test.
	 * 
	 * @param i
	 *        the test number.
	 */
	public void beforeTest(int i) {
		System.out.println("Starting test #" + i);
	}

	/**
	 * Method called afetr each test.
	 * 
	 * @param i
	 *        the test number.
	 */
	public void afterTest(int i) {
		System.out.println("Test #" + i + " ended (" + time.get(i) + " ms)");
	}

	/**
	 * The test content.
	 * 
	 * @param i
	 *        the test number.
	 */
	public abstract void executionTest(int i);

	/** Number of tests to be started */
	public abstract int getNbTests();

	/**
	 * Runs all the tests.
	 */
	public void executionTest() {

		beforeTests();
		for (int i = 0; i < getNbTests(); i++) {
			beforeTest(i);
			long startTime = System.currentTimeMillis();
			executionTest(i);
			long endTime = System.currentTimeMillis();
			time.add(endTime - startTime);
			afterTest(i);
		}
		afterTests();
	}

	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number of tests : ");
		sb.append(getNbTests());
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
