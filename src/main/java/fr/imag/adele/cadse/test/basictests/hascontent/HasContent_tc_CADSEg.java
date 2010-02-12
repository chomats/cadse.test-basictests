package fr.imag.adele.cadse.test.basictests.hascontent;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class HasContent_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name  = "CADSE_HasContent";
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

		// Misc
		String managerSufix = "-manager";
		
		// Default values
		Boolean isAbstract = false;
		Boolean isRoot = true;
		String file_cm = "FileContentModel";
		String folder_cm = "FolderContentModel";
		String javaFile_cm = "JavaFileContentModel";
		String javaProject_cm = "JavaProjectContentModel";
		
		// Creates a new cadse
		createCadseDefinition(cadse_name, "model." + cadse_name);

		
		// ==================== //
		// Item without content //
		// ==================== //

		createItemType(data_model, "no_content", null, isAbstract, isRoot, false);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), file_cm, 3000);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), folder_cm, 3000);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), javaFile_cm, 3000);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), javaProject_cm, 3000);
	
		
		// ======================= //
		// JavaProjectContentModel //
		// ======================= //
		
		// Java project content model with and without source folder
		createItemType(data_model, "javaproject_content_src",   null, isAbstract, isRoot, true);
		createItemType(data_model, "javaproject_content_nosrc", null, isAbstract, isRoot, true);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_src" + managerSufix),   true,  null, null);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_nosrc" + managerSufix), false, null, null);
		
		//  Java project content model Name template
		createItemType(data_model, "javaproject_content_template1", null, isAbstract, isRoot, true);
		createItemType(data_model, "javaproject_content_template2", null, isAbstract, isRoot, true);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_template1" + managerSufix), null, "${#qualified-name}_test", null);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_template2" + managerSufix), null, "${#type-name}_test", null);


		// ======================= //
		//                         //
		// ======================= //

		
//		createItemType(data_model, "file_content",        null, isAbstract, isRoot, true);
//		createItemType(data_model, "folder_content",      null, isAbstract, isRoot, true);
//		createItemType(data_model, "javafile_content",    null, isAbstract, isRoot, true);
//		createItemType(data_model, "javaproject_content", null, isAbstract, isRoot, true);

		

		
		
		//while (true);
	}

}
