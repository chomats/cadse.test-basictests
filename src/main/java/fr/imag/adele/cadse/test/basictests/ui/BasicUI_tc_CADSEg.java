package fr.imag.adele.cadse.test.basictests.ui;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.*;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttext.GTText;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;

/**
 * Tests basic properties for boolean attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
//@RunWith(Parameterized.class)
public class BasicUI_tc_CADSEg extends GTCadseTestCase {

//	@Parameters
//    public static Collection<Object[]> data() {
//    	gtca
//            return Arrays.asList(new Object[][] 
//                            { { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 },
//                                            { 6, 8 } } );
//    }

    
	
	
	protected final String cadse_name = "CADSE_DefaultInstanceName";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);

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
		KeyValue superItA = new KeyValue(
				CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, data_model.concat("it_A"));
		
		// Creates item type with default instance name
		createItemType(data_model, "it_A", notAbstractKv, rootKv);
		workspaceView.selectNode(data_model.concat("it_A"));
		createString(data_model.concat("it_A"), "attr");
		
		createItemType(data_model, "it_B", notAbstractKv, rootKv, superItA);
		propertiesView.showTab("Item type");
		propertiesView.findCadseField(CadseGCST.ITEM_TYPE_at_DEFAULT_INSTANCE_NAME_).typeText("my_default_name");
		
		workspaceView.findTree().doubleClick(data_model.concat("it_B"));
		GTTextEditor editor = new GTTextEditor("it_BManager.java");
		editor.show();
		editor.typeText();
		
	}
	
	/**
	 * Creates a set of boolean attributes.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_boolean() throws Exception {
		String cadseName = "UI";
		createCadseDefinition(cadseName , "model.ui");
	}
}
