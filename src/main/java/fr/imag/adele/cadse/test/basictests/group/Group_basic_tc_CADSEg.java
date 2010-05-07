package fr.imag.adele.cadse.test.basictests.group;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.waitUntilWorkspaceStarted;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
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

	@Test
	public void test_create_cadse() throws Exception {

		createCadseDefinition(cadse_name, cadse_name);
		waitUntilWorkspaceStarted();

		for (int i = 1; i <= 3; i++) {

			GTTreePath it_src = dataModel.concat("it_src" + i);
			GTTreePath it_dst = dataModel.concat("it_dst" + i);
			KeyValue defVal = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "def_val" + i);
			createITandLink(it_src, it_dst);

			if (i == 1) {
				createString(it_src, "attr" + i, defVal, KeyValue.sicpKv);
			}

			if (i == 2) {
				createString(it_dst, "attr" + i, defVal, KeyValue.sicpKv);
			}
		}
	}

	public void createITandLink(GTTreePath it_src, GTTreePath it_dst) {

		createItemType(dataModel, it_src.getDestinationName(), KeyValue.rootKv);
		createItemType(dataModel, it_dst.getDestinationName(), KeyValue.rootKv);
		createLinkType("link", it_src, it_dst, null, null, KeyValue.groupKv);
	}
}
