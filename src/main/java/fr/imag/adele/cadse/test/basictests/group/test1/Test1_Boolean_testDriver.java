package fr.imag.adele.cadse.test.basictests.group.test1;

import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;

public class Test1_Boolean_testDriver extends Test1_Common_testDriver {

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#
	 * isAttributeCreationSuccess(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isAttributeCreationSuccess(GTTestParameter tp) {
		boolean cbu = tp.getBoolean("cbu");
		Boolean defVal = tp.getBoolean("defVal");
		boolean isList = tp.getBoolean("list");

		if (defVal == null && cbu == true && !isList) {
			return false;
		} else {
			return true;
		}
	}
}
