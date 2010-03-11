package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

/**
 */
public class BasicProperties_double_tc_execution extends BasicProperties_number_tc_Common {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties_double");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_double() throws Exception {

		BasicProperties_double_data data = new BasicProperties_double_data();
		data.testExecution();
	}
}
