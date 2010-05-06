package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 */
public class BasicProperties_integer_tc_execution extends GTCadseTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties_integer");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_integer() throws Exception {

		BasicProperties_integer_testDriver integerTest = new BasicProperties_integer_testDriver();
		integerTest.testExecution();
	}
}
