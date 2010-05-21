package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
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
	 * Instantiates a new basic properties_ string_test driver.
	 */
	public BasicProperties_String_testDriver() {
		initializeTestParameters();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getItemTypeUnderTest()
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
		KeyValue[] simpValues = { simpKv /* , notSimpKv */};
		KeyValue[] cbuValues = { cbuKv, notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null, null);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "", "");
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "my_dv", "my_dv");
		KeyValue[] defVal = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "", "");
		KeyValue kv32 = new KeyValue(getAttributeName(), "new_val", "new_val");
		KeyValue kv33 = null; // null stands for leave unchanged
		KeyValue[] newVal = new KeyValue[] { kv31, kv32, kv33 };

		/* Not Empty Attribute */
		KeyValue notEmptyTrue = new KeyValue(CadseGCST.STRING_at_NOT_EMPTY_, true, true);
		KeyValue notEmptyFalse = new KeyValue(CadseGCST.STRING_at_NOT_EMPTY_, false, false);
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
	 * @see fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#postCreate(int,
	 * fr.imag.adele.graphictests.gttree.GTTreePath, fr.imag.adele.graphictests.gttree.GTTreePath)
	 */
	@Override
	protected void postCreate(GTTestParameter tp, GTTreePath it_path, GTTreePath attr_path) {

		/* selects the node in the workspace view */
		workspaceView.selectNode(attr_path);
		propertiesView.showTab("String");
		propertiesView.setValue(tp.getValue("notEmpty"));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#isOkButtonActivated(int)
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
			boolean cbuResult = cbu ? (getFinalModelValue(tp) != null) : true;
			boolean notEmptyResult = notEmpty ? (!getFinalModelValue(tp).equals("")) : true;

			return cbuResult && notEmptyResult;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#setNewGraphicalValue(int,
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
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getFinalGraphicalValue(int)
	 */
	@Override
	protected Object getFinalGraphicalValue(GTTestParameter tp) {

		boolean fieldInCP = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");
		KeyValue newKv = tp.getValue("newValue");
		KeyValue oldKv = tp.getValue("defVal"); // TODO ERROR it was OLD value!
		Object newGraphicalValue = (newKv == null) ? null : newKv.visualValue;
		Object oldGraphicalValue = (oldKv == null) ? null : oldKv.visualValue;

		if (fieldInCP) {
			if (isList) { // default value is ignored with list
				if (newKv != null) {
					if (!newGraphicalValue.equals("") || !notEmpty) {
						return new String[] { newGraphicalValue.toString() };
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
					return newGraphicalValue;
				}
				else {
					return oldGraphicalValue; // no new value
				}
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getFinalModelValue(int)
	 */
	@Override
	protected Object getFinalModelValue(GTTestParameter tp) {

		boolean fieldInCP = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");
		KeyValue newKv = tp.getValue("newValue");
		KeyValue oldKv = tp.getValue("defVal"); // TODO ERROR it was OLD value!

		Object newModelValue = (newKv == null) ? null : newKv.modelValue;
		Object oldModelValue = (oldKv == null) ? null : oldKv.modelValue;

		if (isList) { // def val is ignored with list attributes
			if (newKv != null && newModelValue != null) {

				if (notEmpty && newModelValue.equals("")) {
					return new Object[] {};
				}
				else {
					return new Object[] { newModelValue };
				}
			}
			else {
				return new Object[] {};
			}
		}
		else {
			if (fieldInCP) {
				if (newKv != null) { // in case graphic = "" and model = null
					return newModelValue;
				}
				else {
					return oldModelValue;
				}
			}
			else {
				return oldModelValue;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getCorrectedDefVal(fr.imag
	 * .adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected KeyValue getCorrectedDefVal(GTTestParameter tp) {

		KeyValue defValKv = tp.getValue("defVal");
		Boolean cbu = tp.getBoolean("cbu");

		if (cbu && (defValKv == null || defValKv.getString() == null || defValKv.getString().isEmpty())) {
			return new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "", "");
		}
		else {
			return defValKv;
		}
	}
}
