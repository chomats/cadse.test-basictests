package fr.imag.adele.cadse.test.basictests.bug1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicHead;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createInteger;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.gttree.GTTreePath;

@RunWith(JUnit4.class)
public class Bug1_tc_execution extends Bug1_Common {

	@Test
	public void test_execution() throws Exception {
		selectCadses("Cadse Model.Workspace." + cadseName);
		welcomeView.close();
		workspaceView.show();

		createBasicHead(workspaceView, null, "ItSrc", "s1", new GTTreePath("s1"));
		createBasicHead(workspaceView, null, "ItSrc", "s2", new GTTreePath("s2"));

		createInteger(new GTTreePath("s1"), "intAttr");
		createInteger(new GTTreePath("s2"), "intAttr");
	}
}
