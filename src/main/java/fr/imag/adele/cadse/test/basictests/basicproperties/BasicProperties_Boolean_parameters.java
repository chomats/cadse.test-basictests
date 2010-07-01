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
import fr.imag.adele.cadse.test.basictests.common.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.common.GTCommonParameters;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class BasicProperties_Boolean_parameters extends GTCommonParameters {

	public BasicProperties_Boolean_parameters() {
		ctp = initCTP();
	}

	@Override
	public ItemType getAttributeTypeUnderTest() {
		return CadseGCST.BOOLEAN;
	}

	@Override
	public String getAttributeNameUnderTest() {
		return "Boolean";
	}

	@Override
	protected GTCollectionTestParameter initCTP() {

		GTCollectionTestParameter ctp = new GTCollectionTestParameter(getAttributeTypeUnderTest(),
				getAttributeNameUnderTest(), getAttributeName());

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv(), notSicpKv() };
		KeyValue[] simpValues = { simpKv(), notSimpKv() };
		KeyValue[] cbuValues = { cbuKv(), notCbuKv() };
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Boolean(true));
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Boolean(false));
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : new value */
		KeyValue kv21 = new KeyValue(getAttributeName(), new Boolean(true));
		KeyValue kv22 = new KeyValue(getAttributeName(), new Boolean(false));
		KeyValue kv23 = new KeyValue(getAttributeName(), null);
		KeyValue[] newVal = new KeyValue[] { kv21, kv22, kv23 };

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