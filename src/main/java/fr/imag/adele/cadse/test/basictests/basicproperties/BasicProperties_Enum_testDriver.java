package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;

/**
 * The Class BasicProperties_Enum_testDriver.
 */
public class BasicProperties_Enum_testDriver extends BasicProperties_Common_testDriver {

	/** Enum type name. */
	String enumTypeName = "myEnum";

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#preCreate(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter)
	 */
	@Override
	protected void preCreate(GTTestParameter tp) {
		if (tp.testNumber == 0) {
			GTCadseHelperMethods.createEnumType(getDataModel(tp), enumTypeName, "one", "two", "three", "four", "five");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getCreationKeyValues(fr
	 * .imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("enumType", "defVal", "sicp", "simp", /* "cbu", */"list");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#attributeCreationSuccess(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isAttributeCreationSuccess(GTTestParameter tp) {

		String defVal = tp.getString("defVal");
		if (defVal == null || defVal.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#isOkButtonActivated(fr.
	 * imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isValidValue(GTTestParameter tp, Object value) {

		String defVal = tp.getString("defVal");
		boolean sicp = tp.getBoolean("sicp");

		// Attribute haven't been created : no problem for creating the item type (!)
		// or attribute not displayed in Creation page
		if (!sicp || defVal == null || defVal.isEmpty()) {
			return true;
		}

		// Null or empty new value is forbidden
		else if (value == null || value.toString().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
}
