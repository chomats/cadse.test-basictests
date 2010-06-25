package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 * Tests basic properties for enum attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
@RunWith(JUnit4.class)
public class BasicProperties_Enum_tc_CADSEg extends GTCadseTestCase {

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Creates a set of enum attributes.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_enum() throws Exception {
		BasicProperties_Enum_testDriver data = new BasicProperties_Enum_testDriver();
		data.testCreation();
	}
}
