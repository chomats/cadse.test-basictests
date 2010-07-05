package fr.imag.adele.cadse.test.basictests.group.test1;

import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;

public class Test1_Enum_testDriver extends Test1_Common_testDriver {

	/** Enum Type name */
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
	 * @see
	 * fr.imag.adele.cadse.test.basictests.group.test1.Test1_Common_testDriver
	 * #getCreationKeyValues(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	public KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("enumType", "sicp", "simp", /* "cbu", */"list", "defVal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#
	 * attributeCreationSuccess(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isAttributeCreationSuccess(GTTestParameter tp) {

		String defVal = tp.getString("defVal");
		if (defVal == null || defVal.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
