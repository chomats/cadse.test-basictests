package fr.imag.adele.cadse.test.basictests.bug1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createInteger;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Bug1_tc_execution extends Bug1_Common {

	@Test
	public void test_execution() throws Exception {
		selectCadses("Cadse Model.Workspace." + cadseName);
		welcomeView.close();
		workspaceView.show();

		createBasicItem(workspaceView, null, "ItSrc", "s1", new GTTreePath("s1"));
		createBasicItem(workspaceView, null, "ItSrc", "s2", new GTTreePath("s2"));

		createInteger(new GTTreePath("s1"), "intAttr");
		createInteger(new GTTreePath("s2"), "intAttr");
	}
}