package fr.imag.adele.cadse.test.basictests.isabstract;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class IsAbstract_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_IsAbstract";
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

		// Default values
		Boolean isRoot = true;
		Boolean hasContent = null; // keep default
		Boolean isHidden = false;
		Boolean mustBeInitialized = true;
		Boolean isList = false;
		
		// Creates a new cadse
		createCadseDefinition(cadse_name, "model." + cadse_name);
		
		// Creates item types (A => Abstract, Na => Non abstract)
		createItemType(data_model, "itA",  null, true,  isRoot, hasContent);
		createString(data_model.concat("itA"), "string_attribute_itA", "default_value_itA", isHidden, mustBeInitialized, isList);
		createItemType(data_model, "itNa", null, false, isRoot, hasContent);
		createString(data_model.concat("itNa"), "string_attribute_itNa", "default_value_itNa", isHidden, mustBeInitialized, isList);
	
		createItemType(data_model, "itAA",   data_model.concat("itA"),  true,  isRoot, hasContent);
		createString(data_model.concat("itAA"), "string_attribute_itAA", "default_value_itAA", isHidden, mustBeInitialized, isList);
		createItemType(data_model, "itANa",  data_model.concat("itA"),  false, isRoot, hasContent);
		createString(data_model.concat("itANa"), "string_attribute_itANa", "default_value_itANa", isHidden, mustBeInitialized, isList);
		createItemType(data_model, "itNaA",  data_model.concat("itNa"), true,  isRoot, hasContent);
		createString(data_model.concat("itNaA"), "string_attribute_itNaA", "default_value_itNaA", isHidden, mustBeInitialized, isList);
		createItemType(data_model, "itNaNa", data_model.concat("itNa"), false, isRoot, hasContent);
		createString(data_model.concat("itNaNa"), "string_attribute_itNaNa", "default_value_itNaNa", isHidden, mustBeInitialized, isList);
	}
}
