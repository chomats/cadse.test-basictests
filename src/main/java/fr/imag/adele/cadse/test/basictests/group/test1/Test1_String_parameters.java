package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.basictests.common.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.common.type.GTStringParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_String_parameters extends Test1_Common_parameters {

	/** Constructor. */
	public Test1_String_parameters() {
		ctp = initCTP();
		typeParameter = new GTStringParameter();
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
		KeyValue[] sicpValues = { sicpKv(), notSicpKv() };
		KeyValue[] simpValues = { simpKv() /* , notSimpKv() */};
		KeyValue[] cbuValues = { cbuKv(), notCbuKv() };
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Default value given into CADSEg */
		KeyValue defValKv1 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue defValKv2 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "strDv");
		KeyValue defValKv3 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { defValKv1, defValKv2, defValKv3 };

		/* Value used for head creation */
		KeyValue newValue1Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue1Kv2 = new KeyValue(getAttributeName(), "str1");
		KeyValue newValue1Kv3 = new KeyValue(getAttributeName(), null);
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv1, newValue1Kv2, newValue1Kv3 };

		/* Value used for member creation */
		KeyValue newValue2Kv = new KeyValue(getAttributeName(), "str2");
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv };

		/* Value used to modify head after member creation */
		KeyValue newValue3Kv = new KeyValue(getAttributeName(), "str3");
		KeyValue[] newValue3 = new KeyValue[] { newValue3Kv };

		/* Not Empty Attribute */
		KeyValue notEmptyTrue = new KeyValue(CadseGCST.STRING_at_NOT_EMPTY_, true);
		KeyValue notEmptyFalse = new KeyValue(CadseGCST.STRING_at_NOT_EMPTY_, false);
		KeyValue[] notEmpty = { notEmptyTrue, notEmptyFalse };

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
		ctp.addParameter("notEmpty", notEmpty);

		return ctp;
	}
}
