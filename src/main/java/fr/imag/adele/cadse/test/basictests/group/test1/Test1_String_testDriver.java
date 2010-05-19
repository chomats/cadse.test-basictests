package fr.imag.adele.cadse.test.basictests.group.test1;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_String_testDriver extends Test1_common_testDriver {

	/**
	 * Instantiates a new test1_ integer_test driver.
	 */
	public Test1_String_testDriver() {
		defaultValue = new KeyValue(new String(), "defaultStr", "defaultStr");
		newValue1 = new KeyValue(new String(), "strOne", "strOne");
		newValue2 = new KeyValue(new String(), "strTwo", "strTwo");
		newValue3 = new KeyValue(new String(), "strThree", "strThree");

		initializeTables();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.STRING;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "String";
	}
}
