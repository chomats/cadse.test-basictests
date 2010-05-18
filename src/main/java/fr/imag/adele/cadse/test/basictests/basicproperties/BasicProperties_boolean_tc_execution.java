package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 */
public class BasicProperties_boolean_tc_execution extends GTCadseTestCase {

	/** The test driver */
	BasicProperties_boolean_testDriver driver = new BasicProperties_boolean_testDriver();

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {
		driver.selectCadse();
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_boolean() throws Exception {
		driver.testExecution();
	}
}
