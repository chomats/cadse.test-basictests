package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_String_testDriver extends BasicProperties_Common_testDriver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#postCreate
	 * (fr.imag.adele.cadse.test.basictests .testdriver.GTTestParameter,
	 * fr.imag.adele.graphictests.gttree.GTTreePath,
	 * fr.imag.adele.graphictests.gttree.GTTreePath)
	 */
	@Override
	protected void postCreate(GTTestParameter tp, GTTreePath itPath, GTTreePath attrPath) {
		workspaceView.selectNode(attrPath);
		propertiesView.showTab("String");
		propertiesView.setValue(tp.getValue("notEmpty"));
	}

	@Override
	protected KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {
		return kv;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.basicproperties.
	 * BasicProperties_Common_testDriver#getFinalModelValue(fr.imag
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
				} else {
					return new Object[] { newValue };
				}
			} else {
				return new Object[] {};
			}
		} else {
			if (fieldInCP) {
				if (newKv != null) { // in case graphic = "" and model = null
					return newValue;
				} else {
					return defVal;
				}
			} else {
				return defVal;
			}
		}
	}
}
