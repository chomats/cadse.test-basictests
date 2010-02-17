package fr.imag.adele.cadse.test.basictests.basicproperties;


import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;



public class BasicProperties_tc_execution extends GTCadseTestCase {

	protected final long failingAssertTimeout = 3000;
	
	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties");
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_running() throws Exception {
		
		/* Creates attribute */
		workspaceView.contextMenuNew("my_item_type").click();
		GTShell shell = new GTShell("my_item_type");

		// name
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("my_instance");		

		// Default value
		assertEquals("", shell.findTextWithLabel("no_default_value").getText());
		assertEquals("def_val", shell.findTextWithLabel("with_default_value").getText());
		
		// Hidden in computed pages and must be initialized
		shell.findTextWithLabel("hid_beinit").typeText("found!");
		shell.findTextWithLabel("notHid_beInit").typeText("found!");
		assertTextFieldDoesNotExist(shell, "notHid_notInit", failingAssertTimeout);
		assertTextFieldDoesNotExist(shell, "hid_notInit",    failingAssertTimeout);

		// List
		shell.findButton("Add...").click();
		GTShell shell2 = new GTShell("");
		shell2.findText().typeText("value1");
		shell2.close();
		shell.findTextWithLabel("not_list").typeText("not list");

		shell.close();
		
		
		/* Assert item has been displayed */
		workspaceView.selectNode("my_instance");
		
		
		/* Property page */
		propertiesView.showTab("my_it");
		
		// Name
		assertEquals("my_instance", GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).getText());
		
		// Default value
		assertEquals("", shell.findTextWithLabel("no_default_value").getText());
		assertEquals("def_val", shell.findTextWithLabel("with_default_value").getText());
		
		// Hidden in computed pages and must be initialized
//		shell.findTextWithLabel("hid_beinit").typeText("found!");
//		shell.findTextWithLabel("notHid_beInit").typeText("found!");
//		assertTextFieldDoesNotExist(shell, "notHid_notInit", 3000);
//		assertTextFieldDoesNotExist(shell, "hid_notInit",    3000);
		
		// List
		assertEquals("not list", shell.findTextWithLabel("not_list").getText());
		shell.findTable().selectTableItem("value1");
		
		while(true);
	}
}