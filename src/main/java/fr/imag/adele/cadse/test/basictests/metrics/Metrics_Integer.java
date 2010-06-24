package fr.imag.adele.cadse.test.basictests.metrics;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createInteger;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;

@RunWith(value = Parameterized.class)
public class Metrics_Integer extends Metrics_common {

	/** Test name */
	protected final static String testName = "Integer";
	/** The CADSE name. */
	protected final static String cadseName = "CADSE" + testName;
	/** The Item Type name */
	protected final static String itemTypeName = "ItemType";
	/** A path to the CADSE definition. */
	protected final static GTTreePath cadseModel = new GTTreePath(cadseName);
	/** A path to the data model. */
	protected final static GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);
	/** A path to the ItemType */
	protected final static GTTreePath itemType = dataModel.concat(itemTypeName);

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
	public Metrics_Integer(int number) {
		instanceNumber = number;
	}

	/**
	 * Creates the test context.
	 * <ul>
	 * <li>Creates the CADSE</li>
	 * <li>Creates the item type</li>
	 * </ul>
	 */
	@BeforeClass
	public static void createContext() {
		/* CADSEg initializations */
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();

		/* Test initializations */
		createCadseDefinition(cadseName, "model." + cadseName);
		createItemType(dataModel, itemTypeName);
	}

	/**
	 * Performs the test itself.
	 */
	@Test
	public void testRunner() {
		createInteger(itemType, "IntegerAttr" + instanceNumber);
	}
}
