package fr.imag.adele.cadse.test.basictests.testdriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class GTCollectionTestParameter {

	/** The list of available parameters */
	protected HashMap<String, KeyValue[]> parameters = new HashMap<String, KeyValue[]>();

	/** The list of parameters for each test */
	protected ArrayList<HashMap<String, KeyValue>> tests = new ArrayList<HashMap<String, KeyValue>>();

	/**
	 * Instantiates a new GTCommonTestParameter
	 */
	public GTCollectionTestParameter() {
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		// Displays parameters
		sb.append("Parameters:\n");
		for (Entry<String, KeyValue[]> e : parameters.entrySet()) {
			String key = e.getKey();
			sb.append("  + " + key + "\n");
			KeyValue[] values = e.getValue();
			for (KeyValue value : values) {
				sb.append("    - " + value.visualValue.toString() + "\n");
			}
		}
		sb.append("\n");

		// Displays Tests
		sb.append("Tests:\n");
		for (int i = 0; i < numberOfTests(); i++) {
			sb.append(getTestParameters(i).toString());
		}
		sb.append("\n");

		return sb.toString();
	}

	/**
	 * Adds a parameter which can takes different values.
	 * 
	 * @param key
	 *            the key
	 * @param values
	 *            the values
	 */
	public void addParameter(String key, KeyValue[] values) {
		parameters.put(key, values);
		recomputeTests();
	}

	/**
	 * Gets a test.
	 * 
	 * @param i
	 *            the test number
	 * @return the test
	 */
	public GTTestParameter getTestParameters(int i) {
		return new GTTestParameter(i, tests.get(i));
	}

	/**
	 * Gets the number of tests.
	 * 
	 * @return the number of tests.
	 */
	public int numberOfTests() {
		return tests.size();
	}

	/**
	 * Recompute tests parameters.
	 */
	@SuppressWarnings("unchecked")
	private void recomputeTests() {

		tests.clear();

		for (Entry<String, KeyValue[]> e : parameters.entrySet()) {
			String key = e.getKey();
			KeyValue[] values = e.getValue();

			// Test is empty
			if (tests.isEmpty()) {
				for (KeyValue kv : values) {
					HashMap<String, KeyValue> hm = new HashMap<String, KeyValue>();
					hm.put(key, kv);
					tests.add(hm);
				}
			}

			// Test is not empty
			else {
				ArrayList<HashMap<String, KeyValue>> result = new ArrayList<HashMap<String, KeyValue>>();

				for (KeyValue kv : values) {
					for (HashMap<String, KeyValue> test : tests) {
						HashMap<String, KeyValue> newHm = (HashMap<String, KeyValue>) test.clone();
						newHm.put(key, kv);
						result.add(newHm);
					}
				}
				tests = result;
			}
		}
	}
}
