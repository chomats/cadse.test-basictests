package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.test.basictests.testdriver.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

@RunWith(value = Parameterized.class)
public class Test1_String_tc_execution extends GTCadseTestCase {

	/** The test instance number. (Test is parameterized) */
	protected int instanceNumber;

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		getDriver().selectCadse();
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
		GTTestParameter param = getDriver().getCTP().getTestParameters(instanceNumber);
		getDriver().testExecution(param);
	}

	/**
	 * Constructor.
	 * 
	 * @param i
	 *        the instance number.
	 */
	public Test1_String_tc_execution(int i) {
		instanceNumber = i;
	}

	/**
	 * Compute parameters for this test.
	 * 
	 * @return an array with all the parameters.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		GTCollectionTestParameter ctp = getDriver().getCTP();
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		for (int i = 0; i < ctp.numberOfTests(); i++) {
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
		return new Test1_String_testDriver();
	}
}
