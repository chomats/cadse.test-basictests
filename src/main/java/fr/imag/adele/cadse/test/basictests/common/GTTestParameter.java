package fr.imag.adele.cadse.test.basictests.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class GTTestParameter {

	/** The attributes values */
	protected HashMap<String, KeyValue> params;

	/** The test number */
	public final int testNumber;

	/** The attribute item type which is under test. */
	protected final GTTypeParameter typeParameter;

	/** The name of the attribute */
	private final String attributeName;

	/** The test name. Used to compute CADSE definition name */
	private final String testName;

	/**
	 * Instantiates a new GTTestParameter.
	 * 
	 * @param params
	 *            the attributes values.
	 * @param testNumber
	 *            the test number
	 */
	public GTTestParameter(int testNumber, HashMap<String, KeyValue> params, GTTypeParameter typeParameter,
			String attributeName, String testName) {
		this.testNumber = testNumber;
		this.params = params;
		this.typeParameter = typeParameter;
		this.attributeName = attributeName;
		this.testName = testName;
	}

	/**
	 * Gets the ItemType of the attribute under test.
	 * 
	 * @return the ItemType of the attribute type under test
	 */
	public ItemType getAttributeTypeUnderTest() {
		return typeParameter.getAttributeType();
	}

	/**
	 * Gets the name of the attribute under test.
	 * 
	 * @return the name of the attribute type under test
	 */
	public String getAttributeNameUnderTest() {
		return typeParameter.getAttributeName();
	}

	/**
	 * Checks if it should be possible to set a value.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return true, if the value can be set
	 */
	public boolean isSettableValue(KeyValue value) {
		return typeParameter.isSettableValue(this, value);
	}

	/**
	 * Checks if a value is valid, depending on the context.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return true, if is ok button is activated
	 */
	public boolean isValidValue(Object value) {
		return typeParameter.isValidValue(this, value);
	}

	/**
	 * Returns true if the attribute can be created, false otherwise.
	 * 
	 * @return true if the attribute can be created, false otherwise.
	 */
	public boolean isAttributeCreationSuccess() {
		return typeParameter.isAttributeCreationSuccess(this);
	}

	public KeyValue adaptedValue(KeyValue kv) {
		return typeParameter.adaptedValue(this, kv);
	}

	/**
	 * Performs actions after the item creation.
	 * 
	 * @param attrPath
	 *            the attribute path
	 */
	public void setAdvancedAttributeProperties(GTTreePath attrPath) {
		typeParameter.setAdvancedAttributeProperties(this, attrPath);
	}

	/**
	 * Gets the key values used in creation page for creating the attribute.
	 * 
	 * @return the creation key values
	 */
	public KeyValue[] getCreationKeyValues() {
		return typeParameter.getCreationKeyValues(this);
	}

	/**
	 * Actions to be performed before attribute creation.
	 * 
	 * @param dataModel
	 *            path to the data model
	 */
	public void preCreate(GTTreePath dataModel) {
		typeParameter.preCreate(this, dataModel);
	}

	/**
	 * Gets the name of the test. This is a general name, without reference to
	 * any type, such as boolean.
	 * 
	 * @return the name of the test.
	 */
	public String getTestName() {
		return testName;
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
	 * Returns true if the TestParametes contains a value for a given key.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public boolean containsKey(String key) {
		return params.containsKey(key);
	}

	/**
	 * ToString method. The exact details of the representation are unspecified
	 * and subject to change.
	 * 
	 * @return a string representing the class.
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
			} else if (value.value == null) {
				sb.append("    - [" + key + "] " + "<<NULL VALUE>>" + "\n");
			} else if (value.value.toString().isEmpty()) {
				sb.append("    - [" + key + "] " + "<<EMPTY>>" + "\n");
			} else {
				sb.append("    - [" + key + "] " + value.value.toString() + "\n");
			}
		}
		return sb.toString();
	}

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	public String getAttributeName() {
		return attributeName;
	}
}
