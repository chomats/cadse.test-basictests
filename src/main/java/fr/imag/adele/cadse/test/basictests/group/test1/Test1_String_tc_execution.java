package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

@RunWith(JUnit4.class)
public class Test1_String_tc_execution extends GTCadseTestCase {

	protected static Test1_String_testDriver driver = new Test1_String_testDriver();

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
