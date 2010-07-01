package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_String_testDriver extends BasicProperties_Common_testDriver {

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
