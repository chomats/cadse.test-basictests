package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.test.basictests.common.GTCommonParameters;
import fr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

/**
 * Tests basic properties for double attributes. CADSEg part.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
@RunWith(value = Parameterized.class)
public class BasicProperties_Double_tc_CADSEg extends GTCadseTestCase {

	/** The test instance number. (Test is parameterized) */
	protected int instanceNumber;

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();

		GTTestParameter param = getParameters().getTestParameters(0);
		getDriver().createCadse(param);
	}

	/**
	 * The test itself.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testCreation() throws Exception {
		GTTestParameter param = getParameters().getTestParameters(instanceNumber);
		getDriver().testCreation(param);
	}

	/**
	 * Constructor.
	 * 
	 * @param i
	 *            the instance number.
	 */
	public BasicProperties_Double_tc_CADSEg(int i) {
		instanceNumber = i;
	}

	/**
	 * Compute parameters for this test.
	 * 
	 * @return an array with all the parameters.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		GTCommonParameters param = getParameters();
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		for (int i = 0; i < param.numberOfTests(); i++) {
			params.add(new Object[] { i });
		}
		return params;
	}

	/**
	 * Gets the test driver.
	 * 
	 * @return the test driver
	 */
	protected static GTCommonTestDriver getDriver() {
		return new BasicProperties_Common_testDriver();
	}

	/**
	 * Gets the test parameters.
	 * 
	 * @return the test parameters
	 */
	protected static GTCommonParameters getParameters() {
		return new BasicProperties_Double_parameters();
	}
}
