package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class BasicProperties_Integer_testDriver extends BasicProperties_Common_testDriver {

	/**
	 * Instantiates a new basic properties_ integer_test driver.
	 */
	public BasicProperties_Integer_testDriver() {

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "", null);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "123", 123);
		defValCADSEgValues = new KeyValue[] { kv11, kv12 };

		/* Execution : value at start up */
		KeyValue kv21 = new KeyValue(getAttributeName(), "", null);
		KeyValue kv22 = new KeyValue(getAttributeName(), "123", 123);
		executionOldValues = new KeyValue[] { kv21, kv22 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "", null);
		KeyValue kv32 = new KeyValue(getAttributeName(), "456", 456);
		KeyValue kv33 = null; // null stands for leave unchanged
		executionNewValues = new KeyValue[] { kv31, kv32, kv33 };

		initializeTables();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.INTEGER;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "Integer";
	}
}
