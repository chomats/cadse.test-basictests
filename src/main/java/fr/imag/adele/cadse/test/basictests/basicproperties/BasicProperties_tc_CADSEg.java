package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name  = "CADSE_BasicProperties";
	
	protected final GTTreePath cadse_model   = new GTTreePath(cadse_name);
	protected final GTTreePath build_model   = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected final GTTreePath data_model    = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected final GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);
	
	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_selection() throws Exception {

		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_item_creation() throws Exception {
	
		// Values
		final Boolean notAbstract = false;
		final Boolean root = true;
		final Boolean defaultContent = null;
		final Boolean hidden = true;
		final Boolean notHidden = false;
		final Boolean mustBeInitialized = true;
		final Boolean notInitialized = false;
		final Boolean isList = true;
		final Boolean notList = false;
		
		final String it_name = "my_item_type";
		final GTTreePath it_path = data_model.concat(it_name);
		
		// Creates a new CADSE and a new item type
		createCadseDefinition(cadse_name, "model." + cadse_name);
		createItemType(data_model, it_name, null, notAbstract, root, defaultContent);
		
		// Default value
		createString(it_path, "no_default_value",   null,      notHidden, mustBeInitialized, notList);
		createString(it_path, "with_default_value", "def_val", notHidden, mustBeInitialized, notList);

		// Hidden in computed pages and must be initialized
		createString(it_path, "notHid_beInit",  null, notHidden, mustBeInitialized, notList);
		createString(it_path, "notHid_notInit", null, notHidden, notInitialized,    notList);
		createString(it_path, "hid_beInit",     null, hidden,    mustBeInitialized, notList);
		createString(it_path, "hid_notInit",    null, hidden,    notInitialized,    notList);
		
		// List
		createString(it_path, "list",     null, notHidden, mustBeInitialized, isList);
		createString(it_path, "not_list", null, notHidden, mustBeInitialized, notList);
	}
}