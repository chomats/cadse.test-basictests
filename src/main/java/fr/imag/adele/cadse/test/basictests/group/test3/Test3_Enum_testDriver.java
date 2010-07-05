package fr.imag.adele.cadse.test.basictests.group.test3;

import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;

public class Test3_Enum_testDriver extends Test3_Common_testDriver {
	/** Enum type name. */
	String enumTypeName = "myEnum";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#preCreate
	 * (fr.imag.adele.cadse.test.basictests .testdriver.GTTestParameter)
	 */
	@Override
	protected void preCreate(GTTestParameter tp) {
		if (tp.testNumber == 0) {
			GTCadseHelperMethods.createEnumType(getDataModel(tp), enumTypeName, "one", "two", "three", "four", "five");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.basicproperties.
	 * BasicProperties_Common_testDriver#getCreationKeyValues(fr
	 * .imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("enumType", "defVal", "sicp", "simp", /* "cbu", */"list");
	}
}
