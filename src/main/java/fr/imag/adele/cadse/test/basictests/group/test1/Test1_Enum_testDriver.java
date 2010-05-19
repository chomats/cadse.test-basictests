package fr.imag.adele.cadse.test.basictests.group.test1;

import java.util.ArrayList;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test1_Enum_testDriver extends Test1_common_testDriver {

	String enumTypeName = "myEnum";

	/**
	 * Instantiates a new test1_ integer_test driver.
	 */
	public Test1_Enum_testDriver() {
		defaultValue = new KeyValue(new String(), "four", "four");
		newValue1 = new KeyValue(new String(), "one", "one");
		newValue2 = new KeyValue(new String(), "two", "two");
		newValue3 = new KeyValue(new String(), "three", "three");

		initializeTables();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.ENUM;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "Enum";
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#preCreate(int)
	 */
	@Override
	protected void preCreate(int i) {
		if (i == 0) {
			GTCadseHelperMethods.createEnumType(dataModel, enumTypeName, "one", "two", "three", "four");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getAttributeCreationKeyValues(int)
	 */
	@Override
	public KeyValue[] getAttributeCreationKeyValues(int i) {

		KeyValue[] kvList = super.getAttributeCreationKeyValues(i);
		KeyValue enumTypeKv = new KeyValue(CadseGCST.ENUM_lt_ENUM_TYPE, new GTTreePath(enumTypeName));

		ArrayList<KeyValue> retval = new ArrayList<KeyValue>();
		retval.add(enumTypeKv);

		for (KeyValue kv : kvList) {
			retval.add(kv);
		}

		return (KeyValue[]) retval.toArray();
	}
}
