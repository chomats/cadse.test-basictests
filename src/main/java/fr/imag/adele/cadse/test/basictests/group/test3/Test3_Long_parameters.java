package fr.imag.adele.cadse.test.basictests.group.test3;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.basictests.common.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.common.type.GTLongParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test3_Long_parameters extends Test3_Common_parameters {

	public Test3_Long_parameters() {
		ctp = initCTP();
		typeParameter = new GTLongParameter();
	}

	@Override
	protected GTCollectionTestParameter initCTP() {

		GTCollectionTestParameter ctp = new GTCollectionTestParameter(typeParameter, getAttributeName(), getTestName());

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv(), notSicpKv() };
		KeyValue[] simpValues = { simpKv(), notSimpKv() };
		KeyValue[] cbuValues = { cbuKv(), notCbuKv() };
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Long(123));
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "");
		KeyValue kv32 = new KeyValue(getAttributeName(), new Long(456));
		KeyValue kv33 = new KeyValue(getAttributeName(), null);
		KeyValue[] newVal = new KeyValue[] { kv31, kv32, kv33 };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		ctp.addParameter("cbu", cbuValues);
		ctp.addParameter("list", listValues);

		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue", newVal);

		return ctp;
	}
}
