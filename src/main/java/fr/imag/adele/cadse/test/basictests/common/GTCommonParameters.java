package fr.imag.adele.cadse.test.basictests.common;

import fr.imag.adele.cadse.core.ItemType;

public abstract class GTCommonParameters {

	/** The GTCollectionTestParameter, containing all the tests */
	protected GTCollectionTestParameter ctp;

	/** Attribute prefix used to compute the attributes names. */
	public final String attrPrefix = "attr";

	/**
	 * The attribute item type which is under test.
	 * 
	 * @return the attribute type under test
	 */
	protected abstract ItemType getAttributeTypeUnderTest();

	/**
	 * The attribute name which is under test.
	 * 
	 * @return the attribute name under test
	 */
	protected abstract String getAttributeNameUnderTest();

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
	 *        the instance number
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
		return attrPrefix + getAttributeNameUnderTest();
	}
}
