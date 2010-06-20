package fr.imag.adele.cadse.test.basictests.ui;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.*;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
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
		
		GTCadseHelperMethods.addImportOnManifest("Model.Workspace.CADSE_DefaultInstanceName", 
				"fr.imag.adele.cadse.core.impl.ui", "fr.imag.adele.cadse.core.ui", 
				"fr.imag.adele.cadse.cadseg.pages.ic",
				"fr.imag.adele.cadse.cadseg.pages.mc",
				"fr.imag.adele.cadse.core.impl.ui.mc",
				"fr.imag.adele.cadse.core.impl.ui.ic",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.mc",
				"fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui");
		
		workspaceView.findTree().doubleClick(data_model.concat("it_B"));
		
		GTTextEditor editor = new GTTextEditor("It_BManager.java");
		editor.show();
		editor.selectAll();
		editor.typeText(getText(getClass().getResource("itB_manager_java.txt")));
		editor.save();
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("Model.Workspace.CADSE_DefaultInstanceName");
		assertNotNull(project);
		IJavaProject jp = JavaCore.create(project);
		assertNotNull(jp);
		GTCadseHelperMethods.checkError(jp, null);
		
	}

	private String getText(URL url) throws IOException {
           // Read all of the text returned by the HTTP server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder text = new StringBuilder();
            String str ;
            while ((str = in.readLine()) != null) {
               text.append(str);
               text.append("\n");
            }

            in.close();
            return text.toString();
       
	}
	
}
