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

		// Creates item types (A => Abstract, Na => Non abstract)
		createItemType(data_model, "itA", null, true, root, defaultContent);
		createString(data_model.concat("itA"), "string_attribute_itA", "default_value_itA", notHidden,
				mustBeInitialized, notList);
		createItemType(data_model, "itNa", null, false, root, defaultContent);
		createString(data_model.concat("itNa"), "string_attribute_itNa", "default_value_itNa", notHidden,
				mustBeInitialized, notList);

		createItemType(data_model, "itAA", data_model.concat("itA"), true, root, defaultContent);
		createString(data_model.concat("itAA"), "string_attribute_itAA", "default_value_itAA", notHidden,
				mustBeInitialized, notList);
		createItemType(data_model, "itANa", data_model.concat("itA"), false, root, defaultContent);
		createString(data_model.concat("itANa"), "string_attribute_itANa", "default_value_itANa", notHidden,
				mustBeInitialized, notList);
		createItemType(data_model, "itNaA", data_model.concat("itNa"), true, root, defaultContent);
		createString(data_model.concat("itNaA"), "string_attribute_itNaA", "default_value_itNaA", notHidden,
				mustBeInitialized, notList);
		createItemType(data_model, "itNaNa", data_model.concat("itNa"), false, root, defaultContent);
		createString(data_model.concat("itNaNa"), "string_attribute_itNaNa", "default_value_itNaNa", notHidden,
				mustBeInitialized, notList);
	}

	@Test
	public void test_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model);
	}
}
