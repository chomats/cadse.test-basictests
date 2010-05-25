package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_String_testDriver extends Test1_Common_testDriver {

	/**
	 * Instantiates a new test1_ integer_test driver.
	 */
	public Test1_String_testDriver() {
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
		KeyValue[] sicpValues = { sicpKv, notSicpKv };
		KeyValue[] simpValues = { simpKv, notSimpKv };
		KeyValue[] cbuValues = { /* cbuKv, */notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		KeyValue defValKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "defaultStr", "defaultStr");
		KeyValue newValue1Kv = new KeyValue(new String(), "strOne", "strOne");
		KeyValue newValue2Kv = new KeyValue(new String(), "strTwo", "strTwo");
		KeyValue newValue3Kv = new KeyValue(new String(), "strThree", "strThree");

		KeyValue[] defVal = new KeyValue[] { defValKv };
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv };
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv };
		KeyValue[] newValue3 = new KeyValue[] { newValue3Kv };

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
