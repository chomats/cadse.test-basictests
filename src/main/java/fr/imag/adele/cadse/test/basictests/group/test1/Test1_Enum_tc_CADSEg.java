package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

@RunWith(JUnit4.class)
public class Test1_Enum_tc_CADSEg extends GTCadseTestCase {

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Creates a set of integer attributes.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_Enum() throws Exception {
		Test1_Enum_testDriver data = new Test1_Enum_testDriver();
		data.testCreation();
	}
}
