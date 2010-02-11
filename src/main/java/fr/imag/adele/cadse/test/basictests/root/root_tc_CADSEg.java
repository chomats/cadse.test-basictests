package fr.imag.adele.cadse.test.basictests.root;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class root_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_root";
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

		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		shell.close();
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_item_creation() throws Exception {

		// Creates a new cadse
		createCadseDefinition(cadse_name, "model." + cadse_name);
		
		// Creates item types (R => Root, Nr => Non root)
		createItemType(data_model, "itR", null, null, true, null);
		createItemType(data_model, "itNr", null, null, false, null);
	
		createItemType(data_model, "itRR",   data_model.concat("itR"),  null, true,  null);
		createItemType(data_model, "itRNr",  data_model.concat("itR"),  null, false, null);
		createItemType(data_model, "itNrR",  data_model.concat("itNr"), null, true,  null);
		createItemType(data_model, "itNrNr", data_model.concat("itNr"), null, false, null);
	}	
}
