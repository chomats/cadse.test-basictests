package fr.imag.adele.cadse.test.basictests.isabstract;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.assertItemCantbeCreated;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTTestCase;
import fr.imag.adele.graphictests.test.GTTestParameters;

public class IsAbstract_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_IsAbstract");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_running() throws Exception {

		/* This items creation should be possible */
		createBasicItem(workspaceView, null, "itNa", "instance-itNa", new GTTreePath("instance-itNa"));
		propertiesView.showTab("itNa");
		assertEquals("default_value_itNa", propertiesView.findTextWithLabel("string_attribute_itNa").getText());

		createBasicItem(workspaceView, null, "itANa", "instance-itANa", new GTTreePath("instance-itANa"));
		propertiesView.showTab("itANa");
		assertEquals("default_value_itA", propertiesView.findTextWithLabel("string_attribute_itA").getText());
		assertEquals("default_value_itANa", propertiesView.findTextWithLabel("string_attribute_itANa").getText());

		createBasicItem(workspaceView, null, "itNaNa", "instance-itNaNa", new GTTreePath("instance-itNaNa"));
		propertiesView.showTab("itNaNa");
		assertEquals("default_value_itNa", propertiesView.findTextWithLabel("string_attribute_itNa").getText());
		assertEquals("default_value_itNaNa", propertiesView.findTextWithLabel("string_attribute_itNaNa").getText());

		/* This items creation shouldn't be available */
		assertItemCantbeCreated(workspaceView, null, "itA", GTTestParameters.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itNaA", GTTestParameters.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itAA", GTTestParameters.FAILING_ASSERT_TIMEOUT);
	}

}
