package fr.imag.adele.cadse.test.basictests.hascontent;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class HasContent_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_HasContent";
	protected final GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected final GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected final GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected final GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);

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

		// Misc
		String managerSufix = "-manager";

		// Default values

		String file_cm = "FileContentModel";
		String folder_cm = "FolderContentModel";
		String javaFile_cm = "JavaFileContentModel";
		String javaProject_cm = "JavaProjectContentModel";

		// ==================== //
		// Item without content //
		// ==================== //

		createItemType(data_model, "no_content", null, notAbstract, root, noContent);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), file_cm,
				failingAssertTimeout);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), folder_cm,
				failingAssertTimeout);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), javaFile_cm,
				failingAssertTimeout);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), javaProject_cm,
				failingAssertTimeout);

		// ======================= //
		// JavaProjectContentModel //
		// ======================= //

		// Java project content model with and without source folder
		createItemType(data_model, "javaproject_content_src", null, notAbstract, root, withContent);
		createItemType(data_model, "javaproject_content_nosrc", null, notAbstract, root, withContent);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_src" + managerSufix),
				true, null, null);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_nosrc" + managerSufix),
				false, null, null);

		// Java project content model Name template
		createItemType(data_model, "javaproject_content_template1", null, notAbstract, root, withContent);
		createItemType(data_model, "javaproject_content_template2", null, notAbstract, root, withContent);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_template1"
				+ managerSufix), null, "${#qualified-name}_test", null);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_template2"
				+ managerSufix), null, "${#type-name}_test", null);

		// =================== //
		// Common root project //
		// =================== //

		createItemType(data_model, "root_project", null, notAbstract, root, withContent);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("root_project" + managerSufix), true, null,
				null);

		// ================ //
		// FileContentModel //
		// ================ //

		createItemType(data_model, "file_content", null, notAbstract, false, withContent);
		createLinkType("link_file", data_model.concat("root_project"), data_model.concat("file_content"), "0",
				"unbounded", CadseGCST.LINK_TYPE_at_PART_, true);
		createFileContentModel(workspaceView, mapping_model.concat("file_content" + managerSufix),
				"/${#short-name}.txt", null);

		// ================== //
		// FolderContentModel //
		// ================== //

		createItemType(data_model, "folder_content", null, notAbstract, false, withContent);
		createLinkType("link_folder", data_model.concat("root_project"), data_model.concat("folder_content"), "0",
				"unbounded", CadseGCST.LINK_TYPE_at_PART_, true);
		createFolderContentModel(workspaceView, mapping_model.concat("folder_content" + managerSufix),
				"/${#short-name}", null);

		// ==================== //
		// JavaFileContentModel //
		// ==================== //

		createItemType(data_model, "javaFile_content", null, notAbstract, false, withContent);
		createLinkType("link_javaFile", data_model.concat("root_project"), data_model.concat("javaFile_content"), "0",
				"unbounded", CadseGCST.LINK_TYPE_at_PART_, true);
		createJavaFileContentModel(workspaceView, mapping_model.concat("javaFile_content" + managerSufix),
				"${#short-name}", "fr.imag.adele.${#short-name}", null);
	}

	/**
	 * Checks that the CADSE generated by this test has no compilation error.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model);
	}

}
