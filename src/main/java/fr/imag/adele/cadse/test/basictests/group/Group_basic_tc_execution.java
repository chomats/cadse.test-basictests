package fr.imag.adele.cadse.test.basictests.group;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 */
public class Group_basic_tc_execution extends Group_basic_testDriver {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses(GTPreferences.TIMEOUT, "Cadse Model.Workspace." + cadse_name);
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_basic() throws Exception {

		GTCadseShell shell;

		for (int i = 0; i < sicpTab.size(); i++) {

			KeyValue sicp = sicpTab.get(i);
			KeyValue simp = simpTab.get(i);

			// ============== //
			// First part : A //
			// ============== //

			String attr = "attr_src" + i;
			String itSrcA = "it_srcA" + i;
			String itDstA = "it_dstA" + i;
			String headA = "headA" + i;
			String memberA = "memberA" + i;
			String newValA = "new_val" + i;

			// head creation
			workspaceView.contextMenuNewHead(itSrcA);
			shell = new GTCadseShell(itSrcA);
			GTCadseFactory.findCadseFieldName(shell).typeText(headA);
			String visual = GTCadseFactory.findCadseField(shell, attr).getText();
			String expected = (String) srcDefVal.graphicalValue;
			assertEquals("#" + i, expected, visual);
			GTCadseFactory.findCadseField(shell, attr).typeText(newValA);
			shell.close();

			// looking into properties pages
			workspaceView.selectNode(headA);
			propertiesView.showTab(itSrcA);
			visual = GTCadseFactory.findCadseField(propertiesView, attr).getText();
			expected = newValA;
			assertEquals("#" + i, expected, visual);

			// member creation
			workspaceView.contextMenuNewMember(new GTTreePath(headA), headA, itDstA);
			shell = new GTCadseShell(itDstA);
			GTCadseFactory.findCadseFieldName(shell).typeText(memberA);
			shell.waitUntilButtonEnabled(GTEclipseConstants.NEXT_BUTTON, GTPreferences.TIMEOUT);
			shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
			visual = GTCadseFactory.findCadseField(shell, attr).getText();
			expected = newValA;
			assertEquals("#" + i, expected, visual);
			shell.close();

			// looking into properties pages
			workspaceView.selectNode(memberA);
			propertiesView.showTab(itSrcA);
			visual = GTCadseFactory.findCadseField(propertiesView, attr).getText();
			expected = newValA;
			assertEquals("#" + i, expected, visual);
		}
	}
}
