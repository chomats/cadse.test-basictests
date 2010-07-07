package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.basictests.common.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.common.type.GTBooleanParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_Boolean_parameters extends Test1_Common_parameters {

	/** Constructor. */
	public Test1_Boolean_parameters() {
		typeParameter = new GTBooleanParameter();
		ctp = initCTP();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.GTCommonParameters#initCTP()
	 */
	@Override
	protected GTCollectionTestParameter initCTP() {

		GTCollectionTestParameter ctp = new GTCollectionTestParameter(typeParameter, getAttributeName(), getTestName());

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv(), /* notSicpKv() */};
		KeyValue[] simpValues = { simpKv(), notSimpKv() };
		KeyValue[] cbuValues = { cbuKv(), notCbuKv() };
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Default value given into CADSEg */
		KeyValue defValKv1 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, Boolean.TRUE);
		KeyValue defValKv2 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { defValKv1, defValKv2 };

		/* Value used for head creation */
		KeyValue newValue1Kv1 = new KeyValue(getAttributeName(), Boolean.FALSE);
		KeyValue newValue1Kv2 = new KeyValue(getAttributeName(), null);
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv1, newValue1Kv2 };

		/* Value used for member creation */
		KeyValue newValue2Kv1 = new KeyValue(getAttributeName(), Boolean.TRUE);
		KeyValue newValue2Kv2 = new KeyValue(getAttributeName(), null);
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv1, newValue2Kv2 };

		/* Value used to modify head after member creation */
		KeyValue newValue3Kv1 = new KeyValue(getAttributeName(), Boolean.FALSE);
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

		return ctp;
	}
}
