package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

public class Test1_String_tc_execution extends GTCadseTestCase {

	Test1_String_testDriver driver = new Test1_String_testDriver();

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
	public void test_execution() throws Exception {
		driver.testExecution();
	}
}