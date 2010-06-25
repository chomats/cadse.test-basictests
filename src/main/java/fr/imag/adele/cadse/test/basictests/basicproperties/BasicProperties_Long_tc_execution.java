package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 */
@RunWith(JUnit4.class)
public class BasicProperties_Long_tc_execution extends GTCadseTestCase {

	/** The test driver */
	protected static BasicProperties_Long_testDriver driver = new BasicProperties_Long_testDriver();

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		driver.selectCadse();
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_execution() throws Exception {
		driver.testExecution();
	}
}
