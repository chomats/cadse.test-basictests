package fr.imag.adele.cadse.test.basictests.group.test3;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
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

@RunWith(value = Parameterized.class)
public class Test3_Enum_tc_execution {

	/** The test instance number. (Test is parameterized) */
	protected int instanceNumber;

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		GTTestParameter param = getParameters().getTestParameters(0);
		getDriver().selectCadse(param);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * The test itself.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void testExecution() throws Exception {
		GTTestParameter param = getParameters().getTestParameters(instanceNumber);
		getDriver().testExecution(param);
	}

	/**
	 * Constructor.
	 * 
	 * @param i
	 *        the instance number.
	 */
	public Test3_Enum_tc_execution(int i) {
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
		return new Test3_Enum_testDriver();
	}

	/**
	 * Gets the test parameters.
	 * 
	 * @return the test parameters
	 */
	protected static GTCommonParameters getParameters() {
		return new Test3_Enum_parameters();
	}
}
