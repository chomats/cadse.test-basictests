package fr.imag.adele.cadse.test.basictests.defaultinstancename;


import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;



public class DefaultInstanceName_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_DefaultInstanceName");
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_running() throws Exception {
		
		/* Creates attribute */
		workspaceView.contextMenuNew("my_item_type").click();
		GTShell shell = new GTShell("my_item_type");
		assertEquals("my_default_name", GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).getText());
		shell.close();
		
		/* Assert item has been displayed */
		workspaceView.selectNode("my_default_name");
	}
}
