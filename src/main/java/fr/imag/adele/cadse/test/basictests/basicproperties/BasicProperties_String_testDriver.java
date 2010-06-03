package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class BasicProperties_String_testDriver extends BasicProperties_Common_testDriver {

	/**
	 * Instantiates a new testDriver
	 */
	public BasicProperties_String_testDriver() {
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
		KeyValue[] cbuValues = { cbuKv, notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "my_dv");
		KeyValue[] defVal = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "");
		KeyValue kv32 = new KeyValue(getAttributeName(), "new_val");
		KeyValue kv33 = null; // null stands for leave unchanged
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
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	protected ItemType getAttributeTypeUnderTest() {
		return CadseGCST.STRING;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	protected String getAttributeNameUnderTest() {
		return "String";
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
	protected boolean isOkButtonActivated(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean cbu = tp.getBoolean("cbu");
		boolean notEmpty = tp.getBoolean("notEmpty");

		if (isList) {
			return true;
		}
		else {

			// Checking cbu constraint
			if (cbu && getFinalModelValue(tp) == null) {
				return false;
			}

			// Checking not empty constraint
			if (notEmpty && getFinalModelValue(tp) != null && getFinalModelValue(tp).equals("")) {
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
	protected boolean setNewGraphicalValue(GTTestParameter tp, GTCadseShell shell) {

		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");
		String newValue = tp.getString("newValue");

		if (isList) {
			boolean expectedSuccess = notEmpty ? !newValue.equals("") : true;

			if (expectedSuccess) {
				shell.findCadseField(getAttributeName()).addValue(newValue);
			}
			else {
				try {
					shell.findCadseField(getAttributeName()).addValue(newValue, GTPreferences.FAILING_ASSERT_TIMEOUT);
					fail("It should be impossible to fill \"" + newValue + "\" for #" + tp.testNumber);
				}
				catch (Exception e) {
					// success
				}
			}
		}
		else {
			shell.findCadseField(getAttributeName()).typeText(newValue);
		}

		return true; // success
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getFinalGraphicalValue(
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected Object getFinalGraphicalValue(GTTestParameter tp) {

		boolean fieldInCP = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");
		KeyValue newKv = tp.getValue("newValue");
		KeyValue defValKv = tp.getValue("defVal");
		Object newValue = (newKv == null) ? null : newKv.value;
		Object defVal = (defValKv == null) ? null : defValKv.value;

		if (fieldInCP) {
			if (isList) { // default value is ignored with list
				if (newKv != null) {
					if (!newValue.equals("") || !notEmpty) {
						return new String[] { newValue.toString() };
					}
					else {
						return new String[] {};
					}
				}
				else {
					return new String[] {};
				}
			}
			else {
				if (newKv != null) {
					return newValue;
				}
				else {
					return defVal; // no new value
				}
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
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
	protected Object getFinalModelValue(GTTestParameter tp) {

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
