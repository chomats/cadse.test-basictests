package fr.imag.adele.cadse.test.basictests.common;

import fr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter;

public abstract class GTCommonParameters {

	/** The GTCollectionTestParameter, containing all the tests */
	protected GTCollectionTestParameter ctp;

	/** The type parameter */
	protected GTTypeParameter typeParameter;

	/** Attribute prefix used to compute the attributes names. */
	public final String attrPrefix = "attr";

	/**
	 * Performs ctp initialization.
	 * 
	 * @return the ctp
	 */
	protected abstract GTCollectionTestParameter initCTP();

	/**
	 * The test name. Used to compute CADSE definition name
	 * 
	 * @return the test name
	 */
	protected abstract String getTestName();

	/**
	 * The number of tests used to test the attribute.
	 * 
	 * @return the number of tests
	 */
	public int numberOfTests() {
		return ctp.numberOfTests();
	}

	/**
	 * gets the GTTestParameter from the instance number.
	 * 
	 * @param instanceNumber
	 *            the instance number
	 * @return the test parameters
	 */
	public GTTestParameter getTestParameters(int instanceNumber) {
		return ctp.getTestParameters(instanceNumber);
	}

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	protected String getAttributeName() {
		return attrPrefix + typeParameter.getAttributeName();
	}
}
