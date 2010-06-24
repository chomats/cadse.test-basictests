package fr.imag.adele.cadse.test.basictests.metrics;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;

@RunWith(value = Parameterized.class)
public class Metrics_CADSEdefinition extends Metrics_common {

	/** Number of runs */
	protected final static int numberOfRuns = 1000;
	/** Instance number */
	protected final int instanceNumber;

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object[]> params = new ArrayList<Object[]>();
		for (int i = 0; i < numberOfRuns; i++) {
			params.add(new Object[] { i });
		}
		return params;
	}

	/**
	 * Constructor.
	 * 
	 * @param number
	 *        the instance number.
	 */
	public Metrics_CADSEdefinition(int number) {
		instanceNumber = number;
	}

	/**
	 * Creates the test context.
	 */
	@BeforeClass
	public static void createContext() {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Performs the test itself.
	 */
	@Test
	public void testRunner() {
		String cadse_name = "CADSE" + instanceNumber;
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}
}
