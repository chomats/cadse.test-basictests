package fr.imag.adele.cadse.test.basictests.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class GTTestParameter {

	/** The attributes values */
	protected HashMap<String, KeyValue> params;

	/** The test number */
	public final int testNumber;

	/**
	 * Instantiates a new GTTestParameter.
	 * 
	 * @param params
	 *            the attributes values.
	 * @param testNumber
	 *            the test number
	 */
	public GTTestParameter(int testNumber, HashMap<String, KeyValue> params) {
		this.testNumber = testNumber;
		this.params = params;
	}

	/**
	 * Gets the KeyValue object from its key reference.
	 * 
	 * @param key
	 *            the key
	 * @return the KeyValue object.
	 */
	public KeyValue getValue(String key) {
		return params.get(key);
	}

	/**
	 * Gets the KeyValue object from its key reference.
	 * 
	 * @param key
	 *            the key
	 * @return the KeyValue object.
	 */
	public KeyValue[] getValues(String... keys) {

		ArrayList<KeyValue> list = new ArrayList<KeyValue>();
		for (String key : keys) {
			list.add(params.get(key));
		}

		return list.toArray(new KeyValue[0]);
	}

	/**
	 * Gets the KeyValue visual value and returns it as a Boolean.
	 * 
	 * @return the boolean
	 */
	public Boolean getBoolean(String key) {
		return params.get(key).getBoolean();
	}

	/**
	 * Gets the KeyValue visual value and returns it as a String.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public String getString(String key) {
		return params.get(key).getString();
	}

	/**
	 * Returns true uf the TestParametes contains a value for a given key.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public boolean containsKey(String key) {
		return params.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  + test #" + testNumber + "\n");
		for (Entry<String, KeyValue> e : params.entrySet()) {
			String key = e.getKey();
			KeyValue value = e.getValue();

			if (value == null) {
				sb.append("    - [" + key + "] " + "<<NULL KEY_VALUE>>" + "\n");
			}
			else if (value.value == null) {
				sb.append("    - [" + key + "] " + "<<NULL VALUE>>" + "\n");
			}
			else if (value.value.toString().isEmpty()) {
				sb.append("    - [" + key + "] " + "<<EMPTY>>" + "\n");
			}
			else {
				sb.append("    - [" + key + "] " + value.value.toString() + "\n");
			}
		}
		return sb.toString();
	}
}