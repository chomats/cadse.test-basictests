package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
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

public class BasicProperties_Double_testDriver extends BasicProperties_Common_testDriver {

	/**
	 * Instantiates a new testDriver
	 */
	public BasicProperties_Double_testDriver() {
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

		// Common parameters
		KeyValue[] sicpValues = { sicpKv, notSicpKv };
		KeyValue[] simpValues = { simpKv, notSimpKv };
		KeyValue[] cbuValues = { cbuKv, notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Double(123));
		KeyValue[] defVal = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "");
		KeyValue kv32 = new KeyValue(getAttributeName(), null);
		KeyValue kv33 = new KeyValue(getAttributeName(), new Double(456));
		KeyValue kv34 = null; // null stands for leave unchanged
		KeyValue[] newVal = new KeyValue[] { kv31, kv32, kv33, kv34 };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		ctp.addParameter("cbu", cbuValues);
		ctp.addParameter("list", listValues);

		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue", newVal);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	protected ItemType getAttributeTypeUnderTest() {
		return CadseGCST.DOUBLE;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	protected String getAttributeNameUnderTest() {
		return "Double";
	}
}
