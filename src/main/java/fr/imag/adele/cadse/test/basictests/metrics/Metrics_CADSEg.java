package fr.imag.adele.cadse.test.basictests.metrics;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;

public class Metrics_CADSEg extends Metrics_common {

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.metrics.Metrics_common#getNbTests()
	 */
	@Override
	public int getNbTests() {
		return 1000;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.metrics.Metrics_common#executionTest(int)
	 */
	@Override
	public void executionTest(int i) {
		String cadse_name = "CADSE" + i;
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}

	/**
	 * Performs initializations.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPreparation() throws Exception {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Starts the test.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRun() throws Exception {
		executionTest();
	}
}
