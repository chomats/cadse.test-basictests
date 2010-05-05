package fr.imag.adele.cadse.test.basictests.group;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.waitUntilWorkspaceStarted;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;

/**
 */
public class Group_basic_tc_execution extends Group_basic_testDriver {

	GTCadseShell shell;

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace." + cadse_name);
		welcomeView.close();
		workspaceView.show();
		waitUntilWorkspaceStarted();
	}

	@Test
	public void test1() throws Exception {

		final int i = 1;

		String attr = "attr" + i;
		String itSrc = "it_src" + i;
		String itDst = "it_dst" + i;
		String head = "head" + i;
		String member = "member" + i;

		KeyValue defVal = new KeyValue(attr, "def_val" + i, "def_val" + i);
		KeyValue newVal1 = new KeyValue(attr, "new_val1" + i, "new_val1" + i);
		KeyValue newVal2 = new KeyValue(attr, "new_val2" + i, "new_val2" + i);

		// head creation
		workspaceView.contextMenuNewHead(itSrc).click();
		shell = new GTCadseShell(itSrc);
		defVal.assertEqualsValues("default value", shell);
		newVal1.setValue(shell);
		GTCadseFactory.findCadseFieldName(shell).typeText(head);
		newVal1.assertEqualsValues("New value in creation page", shell);
		shell.close();

		// looking into properties pages and changing value
		workspaceView.selectNode(head);
		propertiesView.showTab(itSrc);
		newVal1.assertEqualsValues("New value in modification page", propertiesView);
		newVal2.setValue(propertiesView);

		// member creation
		workspaceView.contextMenuNewMember(new GTTreePath(head), head, itDst).click();
		shell = new GTCadseShell(itDst);
		GTCadseFactory.findCadseFieldName(shell).typeText(member);
		shell.waitUntilButtonEnabled(GTEclipseConstants.NEXT_BUTTON);
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		newVal2.assertEqualsValues("New value in modification page", shell);
		shell.close();

		// looking into properties pages
		workspaceView.selectNode(member);
		propertiesView.showTab(head);
		newVal2.assertEqualsValues("New value in modification page", propertiesView);

		// Value modification in the head

		// Assert value has changed in the member

		// Adding field in the head

		// ...

	}

}
