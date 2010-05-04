package fr.imag.adele.cadse.test.basictests.defaultinstancename;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseFieldName;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.graphictests.test.GTPreferences;
import fr.imag.adele.graphictests.test.GTTestCase;

public class DefaultInstanceName_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses(GTPreferences.TIMEOUT, "Cadse Model.Workspace.CADSE_DefaultInstanceName");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_running() throws Exception {

		/* Creates attribute */
		workspaceView.contextMenuNew("my_item_type").click();
		GTShell shell = new GTShell("my_item_type");
		assertEquals("my_default_name", findCadseFieldName(shell).getText());
		shell.close();

		/* Assert item has been displayed */
		workspaceView.selectNode("my_default_name");
	}
}
