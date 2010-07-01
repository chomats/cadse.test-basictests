package fr.imag.adele.cadse.test.basictests.common;

import fr.imag.adele.cadse.core.ItemType;

public abstract class GTCommonParameters {

	protected GTCollectionTestParameter ctp;

	/** Attribute prefix used to compute the attributes names. */
	public final String attrPrefix = "attr";

	protected abstract ItemType getAttributeTypeUnderTest();

	protected abstract String getAttributeNameUnderTest();

	protected abstract GTCollectionTestParameter initCTP();

	public int numberOfTests() {
		return ctp.numberOfTests();
	}

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
