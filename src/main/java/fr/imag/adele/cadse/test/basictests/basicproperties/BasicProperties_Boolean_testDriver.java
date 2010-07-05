package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class BasicProperties_Boolean_testDriver extends
		BasicProperties_Common_testDriver {

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.basicproperties.
	 * BasicProperties_Common_testDriver#adaptedValue(fr.imag.adele
	 * .cadse.test.basictests.testdriver.GTTestParameter,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue)
	 */
	@Override
	protected KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {

		boolean cbu = tp.getBoolean("cbu");

		if (cbu == true && kv.value == null) {
			return new KeyValue("", false);
		} else {
			return kv;
		}
	}

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
