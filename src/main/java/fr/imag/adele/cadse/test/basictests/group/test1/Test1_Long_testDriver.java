package fr.imag.adele.cadse.test.basictests.group.test1;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_Long_testDriver extends Test1_common_testDriver {

	/**
	 * Instantiates a new test1_ integer_test driver.
	 */
	public Test1_Long_testDriver() {
		defaultValue = new KeyValue(new String(), 10l, 10l);
		newValue1 = new KeyValue(new String(), 11l, 11l);
		newValue2 = new KeyValue(new String(), 12l, 12l);
		newValue3 = new KeyValue(new String(), 13l, 13l);

		initializeTables();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.LONG;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "Long";
	}
}
