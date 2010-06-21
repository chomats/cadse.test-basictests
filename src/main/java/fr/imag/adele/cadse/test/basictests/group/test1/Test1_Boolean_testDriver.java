package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_Boolean_testDriver extends Test1_Common_testDriver {

	/**
	 * Instantiates a new testDriver
	 */
	public Test1_Boolean_testDriver() {
		initializeTestParameters();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#initializeTestParameters()
	 */
	@Override
	protected void initializeTestParameters() {

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv /* , notSicpKv */};
		KeyValue[] simpValues = { simpKv, notSimpKv };
		KeyValue[] cbuValues = { cbuKv, notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		/* Default value given into CADSEg */
		KeyValue defValKv1 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Boolean(true));
		KeyValue defValKv2 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { defValKv1, defValKv2 };

		/* Value used for head creation */
		KeyValue newValue1Kv1 = new KeyValue(getAttributeName(), new Boolean(false));
		KeyValue newValue1Kv2 = new KeyValue(getAttributeName(), null);
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv1, newValue1Kv2 };

		/* Value used for member creation */
		KeyValue newValue2Kv1 = new KeyValue(getAttributeName(), new Boolean(true));
		KeyValue newValue2Kv2 = new KeyValue(getAttributeName(), null);
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv1, newValue2Kv2 };

		/* Value used to modify head after member creation */
		KeyValue newValue3Kv1 = new KeyValue(getAttributeName(), new Boolean(false));
		KeyValue newValue3Kv2 = new KeyValue(getAttributeName(), null);
		KeyValue[] newValue3 = new KeyValue[] { newValue3Kv1, newValue3Kv2 };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		ctp.addParameter("cbu", cbuValues);
		ctp.addParameter("list", listValues);

		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue1", newValue1);
		ctp.addParameter("newValue2", newValue2);
		ctp.addParameter("newValue3", newValue3);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	protected ItemType getAttributeTypeUnderTest() {
		return CadseGCST.BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	protected String getAttributeNameUnderTest() {
		return "Boolean";
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
