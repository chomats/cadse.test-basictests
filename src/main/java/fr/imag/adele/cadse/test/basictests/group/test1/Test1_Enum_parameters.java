package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.basictests.common.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.common.type.GTEnumParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test1_Enum_parameters extends Test1_Common_parameters {

	/** Enum Type name */
	String enumTypeName = "myEnum";

	public Test1_Enum_parameters() {
		ctp = initCTP();
		typeParameter = new GTEnumParameter();
	}

	@Override
	protected GTCollectionTestParameter initCTP() {

		GTCollectionTestParameter ctp = new GTCollectionTestParameter(typeParameter, getAttributeName(), getTestName());

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv(), /* notSicpKv() */};
		KeyValue[] simpValues = { simpKv(), notSimpKv() };
		/*
		 * KeyValue[] cbuValues = { cbuKv(), notCbuKv() }; CBU = this attribute
		 * does not exists with enum type
		 */
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Default value given into CADSEg */
		KeyValue defValKv1 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue defValKv2 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "two");
		KeyValue defValKv3 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { defValKv1, defValKv2, defValKv3 };

		/* Value used for head creation */
		KeyValue newValue1Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue1Kv2 = new KeyValue(getAttributeName(), "three");
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv1, newValue1Kv2 };

		/* Value used for member creation */
		KeyValue newValue2Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue2Kv2 = new KeyValue(getAttributeName(), "four");
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv1, newValue2Kv2 };

		/* Value used to modify head after member creation */
		KeyValue newValue3Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue3Kv2 = new KeyValue(getAttributeName(), "five");
		KeyValue[] newValue3 = new KeyValue[] { newValue3Kv1, newValue3Kv2 };

		/* Enum Type */
		KeyValue enumTypeKv = new KeyValue(CadseGCST.ENUM_lt_ENUM_TYPE, new GTTreePath(enumTypeName));
		KeyValue[] enumType = new KeyValue[] { enumTypeKv };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		/*
		 * ctp.addParameter("cbu", cbuValues); CBU = this attribute does not
		 * exists with enum type
		 */
		ctp.addParameter("list", listValues);

		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue1", newValue1);
		ctp.addParameter("newValue2", newValue2);
		ctp.addParameter("newValue3", newValue3);

		ctp.addParameter("enumType", enumType);

		return ctp;
	}
}
