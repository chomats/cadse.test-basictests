package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 */
public class BasicProperties_string_tc_execution extends GTCadseTestCase {

	/** The test driver */
	BasicProperties_string_testDriver driver = new BasicProperties_string_testDriver();

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace." + driver.cadseName);
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_string() throws Exception {
		driver.testExecution();
	}
}
