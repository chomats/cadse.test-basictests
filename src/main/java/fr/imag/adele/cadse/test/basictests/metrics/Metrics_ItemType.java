package fr.imag.adele.cadse.test.basictests.metrics;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Metrics_ItemType extends Metrics_common {

	/** Test name */
	String testName = "ItemType";

	/** The CADSE name. */
	String cadseName = "CADSE" + testName;

	/** A path to the CADSE definition. */
	public final GTTreePath cadseModel = new GTTreePath(cadseName);

	/** A path to the data model. */
	public final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.metrics.Metrics_common#getNbTests()
	 */
	@Override
	public int getNbTests() {
		return 500;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.metrics.Metrics_common#beforeTests()
	 */
	@Override
	public void beforeTests() {
		createCadseDefinition(cadseName, "model." + cadseName);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.metrics.Metrics_common#executionTest(int)
	 */
	@Override
	public void executionTest(int i) {
		createItemType(dataModel, testName + i);
	}

	/**
	 * Performs initializations.
	 * 
	 * @throws Exception
	 *         the exception
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
	 *         the exception
	 */
	@Test
	public void testRun() throws Exception {
		executionTest();
	}
}
