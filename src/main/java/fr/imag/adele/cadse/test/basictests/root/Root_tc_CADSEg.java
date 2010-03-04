package fr.imag.adele.cadse.test.basictests.root;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Root_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_Root";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);

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

		// Creates a new CADSE
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}

	@Test
	public void test_item_creation() throws Exception {

		// values
		final Boolean notAbstract = false;
		final Boolean defaultContent = null;

		// Creates item types (R => Root, Nr => Non root)
		createItemType(data_model, "itR", null, notAbstract, true, defaultContent);
		createItemType(data_model, "itNr", null, notAbstract, false, defaultContent);

		createItemType(data_model, "itRR", data_model.concat("itR"), notAbstract, true, defaultContent);
		createItemType(data_model, "itRNr", data_model.concat("itR"), notAbstract, false, defaultContent);
		createItemType(data_model, "itNrR", data_model.concat("itNr"), notAbstract, true, defaultContent);
		createItemType(data_model, "itNrNr", data_model.concat("itNr"), notAbstract, false, defaultContent);
	}

	@Test
	public void test_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model);
	}
}
