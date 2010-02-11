package fr.imag.adele.cadse.test.basictests.isabstract;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;



public class IsAbstract_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_IsAbstract");
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_running() throws Exception {

		/* This items creation should be possible */
		createBasicItem(workspaceView, null, "itNa",   "itNa",   "instance-itNa",   new GTTreePath("instance-itNa"));
		propertiesView.showTab("itNa");
		assertEquals("default_value_itNa", propertiesView.findTextWithLabel("string_attribute_itNa").getText());
		
		createBasicItem(workspaceView, null, "itANa",  "itANa", "instance-itANa", new GTTreePath("instance-itANa"));
		propertiesView.showTab("itANa");
		assertEquals("default_value_itA", propertiesView.findTextWithLabel("string_attribute_itA").getText());
		assertEquals("default_value_itANa", propertiesView.findTextWithLabel("string_attribute_itANa").getText());
		
		createBasicItem(workspaceView, null, "itNaNa", "itNaNa",  "instance-itNaNa",  new GTTreePath("instance-itNaNa"));
		propertiesView.showTab("itNaNa");
		assertEquals("default_value_itNa", propertiesView.findTextWithLabel("string_attribute_itNa").getText());
		assertEquals("default_value_itNaNa", propertiesView.findTextWithLabel("string_attribute_itNaNa").getText());
		
		
		/* This items creation shouldn't be available */
		assertItemCantbeCreated(workspaceView, null, "itA",   3000);
		assertItemCantbeCreated(workspaceView, null, "itNaA", 3000);
		assertItemCantbeCreated(workspaceView, null, "itAA",  3000);
	}

}
