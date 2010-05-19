package fr.imag.adele.cadse.test.basictests.group.test1;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_Boolean_testDriver extends Test1_common_testDriver {

	/**
	 * Instantiates a new test1_ integer_test driver.
	 */
	public Test1_Boolean_testDriver() {
		defaultValue = new KeyValue(new String(), true, true);
		newValue1 = new KeyValue(new String(), false, false);
		newValue2 = new KeyValue(new String(), null, null);
		newValue3 = new KeyValue(new String(), true, true);

		// Initializes CBU
		cbuValues = new KeyValue[] { KeyValue.notCbuKv };

		initializeTables();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "Boolean";
	}
}
