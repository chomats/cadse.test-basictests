package fr.imag.adele.cadse.test.basictests.defaultinstancename;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class DefaultInstanceName_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name  = "CADSE_DefaultInstanceName";
	protected GTTreePath cadse_model   = new GTTreePath(cadse_name);
	protected GTTreePath build_model   = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model    = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);
	
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
	
		// Creates a new cadse
		createCadseDefinition(cadse_name, "model." + cadse_name);

		// Creates item type with default instance name
		createItemType(data_model, "my_item_type", null, false, true, null);
		workspaceView.selectNode(data_model.concat("my_item_type"));
		propertiesView.showTab("Item type");
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_DEFAULT_INSTANCE_NAME_).typeText("my_default_name");		
	}
}