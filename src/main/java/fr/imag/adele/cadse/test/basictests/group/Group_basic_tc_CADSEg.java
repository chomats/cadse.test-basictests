package fr.imag.adele.cadse.test.basictests.group;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

/**
 * Tests basic properties for string attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
public class Group_basic_tc_CADSEg extends Group_basic_testDriver {

	/** A path to the CADSE definition */
	protected final GTTreePath cadseModel = new GTTreePath(cadse_name);
	/** A path to the data model */
	protected final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

	/**
	 * Makes a few things before the test starts.
	 * <ul>
	 * <li>Starts CADSEg</li>
	 * <li>Closes unless views</li>
	 * <li>Creates a new CADSE</li>
	 * </ul>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_preparation() throws Exception {

		// Starts CADSEg
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);

		// Closes unless views
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Creates a set of string attributes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_basic() throws Exception {

		createCadseDefinition(cadse_name, cadse_name);
		initializeTables();

		for (int i = 0; i < sicpTab.size(); i++) {

			GTTreePath it_srcA = dataModel.concat("it_srcA" + i);
			GTTreePath it_dstA = dataModel.concat("it_dstA" + i);
			GTTreePath it_srcB = dataModel.concat("it_srcB" + i);
			GTTreePath it_dstB = dataModel.concat("it_dstB" + i);

			createItemType(dataModel, it_srcA.getDestinationName(), KeyValue.rootKv);
			createItemType(dataModel, it_dstA.getDestinationName(), KeyValue.rootKv);
			createLinkType("linkA", it_srcA, it_dstA, null, null, new KeyValue(CadseGCST.LINK_TYPE_at_GROUP, true));

			createItemType(dataModel, it_srcB.getDestinationName(), KeyValue.rootKv);
			createItemType(dataModel, it_dstB.getDestinationName(), KeyValue.rootKv);
			createLinkType("linkB", it_srcB, it_dstB, null, null, new KeyValue(CadseGCST.LINK_TYPE_at_GROUP, true));

			createString(it_srcA, "attr_src" + i, sicpTab.get(i), simpTab.get(i), CST.srcDefVal);
			createString(it_dstB, "attr_dst" + i, sicpTab.get(i), simpTab.get(i), CST.dstDefVal);
		}
	}
}
