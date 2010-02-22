package fr.imag.adele.cadse.test.basictests.basicproperties;


import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;



public class BasicProperties_tc_execution extends GTCadseTestCase {
	
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
		shell.findTextWithLabel("notHid_beInit").typeText("found!");
		assertTextFieldDoesNotExist(shell, "notHid_notInit", failingAssertTimeout);
		assertTextFieldDoesNotExist(shell, "hid_notInit",    failingAssertTimeout);
		assertTextFieldDoesNotExist(shell, "hid_beInit",     failingAssertTimeout);

		// List
		shell.findButton("Add...").click();
		GTShell shell2 = new GTShell("");
		shell2.findText().typeText("value1");
		shell2.close();
		shell.findTextWithLabel("not_list").typeText("not list");

		shell.close();
		
		
		/* Property page */
		workspaceView.selectNode("my_instance");
		propertiesView.showTab("my_item_type");
	
		// Name
		assertEquals("my_instance", GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_NAME_).getText());
		
		// Default value
		assertEquals("", propertiesView.findTextWithLabel("no_default_value").getText());
		assertEquals("def_val", propertiesView.findTextWithLabel("with_default_value").getText());
		
		// Hidden in computed pages and must be initialized
		assertEquals("found!", propertiesView.findTextWithLabel("notHid_beInit").getText());
		propertiesView.findTextWithLabel("notHid_notInit").typeText("found!");
		assertTextFieldDoesNotExist(propertiesView, "hid_notInit", failingAssertTimeout);
		assertTextFieldDoesNotExist(propertiesView, "hid_beInit",  failingAssertTimeout);
		
		// List
		assertEquals("not list", propertiesView.findTextWithLabel("not_list").getText());
		propertiesView.findTree().selectNode("value1");
	}
}