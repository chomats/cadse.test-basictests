package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test1_String_testDriver extends Test1_Common_testDriver {

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
}
