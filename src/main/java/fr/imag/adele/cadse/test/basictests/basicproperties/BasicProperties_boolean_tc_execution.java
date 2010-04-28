package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.test.GTPreferences;
import fr.imag.adele.graphictests.test.GTTestCase;

/**
 */
public class BasicProperties_boolean_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses(GTPreferences.TIMEOUT, "Cadse Model.Workspace.CADSE_BasicProperties_boolean");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_boolean() throws Exception {
		BasicProperties_boolean_testDriver booleanTest = new BasicProperties_boolean_testDriver();
		booleanTest.testExecution();
	}
}
