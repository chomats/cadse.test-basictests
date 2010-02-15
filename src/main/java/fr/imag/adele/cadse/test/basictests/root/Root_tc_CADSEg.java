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
	protected GTTreePath data_model  = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	
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

		// default values
		Boolean isAbstract = false;
		Boolean hasContent = null;
		
		// Creates a new cadse
		createCadseDefinition(cadse_name, "model." + cadse_name);
		
		// Creates item types (R => Root, Nr => Non root)
		createItemType(data_model, "itR",  null, isAbstract, true,  hasContent);
		createItemType(data_model, "itNr", null, isAbstract, false, hasContent);
	
		createItemType(data_model, "itRR",   data_model.concat("itR"),  isAbstract, true,  hasContent);
		createItemType(data_model, "itRNr",  data_model.concat("itR"),  isAbstract, false, hasContent);
		createItemType(data_model, "itNrR",  data_model.concat("itNr"), isAbstract, true,  hasContent);
		createItemType(data_model, "itNrNr", data_model.concat("itNr"), isAbstract, false, hasContent);
	}
	
	@Test
	public void test_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model);
	}
}
