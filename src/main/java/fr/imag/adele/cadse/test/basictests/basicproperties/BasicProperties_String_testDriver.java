package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_String_testDriver extends BasicProperties_Common_testDriver {

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	public ItemType getAttributeTypeUnderTest() {
		return CadseGCST.STRING;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	public String getAttributeNameUnderTest() {
		return "String";
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getCTP()
	 */
	@Override
	public GTCollectionTestParameter getCTP() {

		GTCollectionTestParameter ctp = new GTCollectionTestParameter();

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv() /* , notSicpKv() */};
		KeyValue[] simpValues = { simpKv(), notSimpKv() };
		KeyValue[] cbuValues = { cbuKv(), notCbuKv() };
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "my_dv");
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "");
		KeyValue kv32 = new KeyValue(getAttributeName(), "new_val");
		KeyValue kv33 = new KeyValue(getAttributeName(), null);
		KeyValue[] newVal = new KeyValue[] { kv31, kv32, kv33 };

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
		ctp.addParameter("newValue", newVal);
		ctp.addParameter("notEmpty", notEmpty);

		return ctp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#postCreate(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter, fr.imag.adele.graphictests.gttree.GTTreePath,
	 * fr.imag.adele.graphictests.gttree.GTTreePath)
	 */
	@Override
	protected void postCreate(GTTestParameter tp, GTTreePath itPath, GTTreePath attrPath) {
		workspaceView.selectNode(attrPath);
		propertiesView.showTab("String");
		propertiesView.setValue(tp.getValue("notEmpty"));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#isOkButtonActivated(fr.
	 * imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isValidValue(GTTestParameter tp, Object value) {

		boolean isList = tp.getBoolean("list");
		boolean cbu = tp.getBoolean("cbu");
		boolean notEmpty = tp.getBoolean("notEmpty");

		if (isList) {
			return true;
		}
		else {

			// Checking cbu constraint
			if (cbu && value == null) {
				return false;
			}

			// Checking not empty constraint
			if (notEmpty && value != null && value.toString().equals("")) {
				return false;
			}

			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#setNewGraphicalValue(fr
	 * .imag.adele.cadse.test.basictests.testdriver.GTTestParameter,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell)
	 */
	@Override
	protected boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");

		if (isList) {
			if (kv.value == null) {
				return false;
			}
			else {
				return notEmpty ? !kv.getString().equals("") : true;
			}
		}
		else {
			return true;
		}
	}

	@Override
	protected KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {
		return kv;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getFinalModelValue(fr.imag
	 * .adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected Object getFinalValue(GTTestParameter tp) {

		boolean fieldInCP = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");
		KeyValue newKv = tp.getValue("newValue");
		KeyValue defValKv = tp.getValue("defVal");

		Object newValue = (newKv == null) ? null : newKv.value;
		Object defVal = (defValKv == null) ? null : defValKv.value;

		if (isList) { // def val is ignored with list attributes
			if (fieldInCP && newKv != null && newValue != null) {

				if (notEmpty && newValue.equals("")) {
					return new Object[] {};
				}
				else {
					return new Object[] { newValue };
				}
			}
			else {
				return new Object[] {};
			}
		}
		else {
			if (fieldInCP) {
				if (newKv != null) { // in case graphic = "" and model = null
					return newValue;
				}
				else {
					return defVal;
				}
			}
			else {
				return defVal;
			}
		}
	}
}
