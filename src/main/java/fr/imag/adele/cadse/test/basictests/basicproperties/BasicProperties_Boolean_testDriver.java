package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class BasicProperties_Boolean_testDriver extends BasicProperties_Common_testDriver {

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#adaptedValue(fr.imag.adele
	 * .cadse.test.basictests.testdriver.GTTestParameter,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue)
	 */
	@Override
	protected KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {

		boolean cbu = tp.getBoolean("cbu");

		if (cbu == true && kv.value == null) {
			return new KeyValue("", false);
		}
		else {
			return kv;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#isSettableValue(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter, fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue)
	 */
	@Override
	protected boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");
		boolean cbu = tp.getBoolean("cbu");

		if (isList) {
			return kv != null && kv.value != null && !kv.value.toString().equals("");
		}
		else {
			if (cbu && kv != null && kv.value == null) {
				return false;
			}
			else {
				return true;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#isAttributeCreationSuccess(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isAttributeCreationSuccess(GTTestParameter tp) {
		boolean cbu = tp.getBoolean("cbu");
		Boolean defVal = tp.getBoolean("defVal");
		boolean isList = tp.getBoolean("list");

		if (defVal == null && cbu == true && !isList) {
			return false;
		}
		else {
			return true;
		}
	}
}
