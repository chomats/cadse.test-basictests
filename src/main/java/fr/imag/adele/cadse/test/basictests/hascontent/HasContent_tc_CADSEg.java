package fr.imag.adele.cadse.test.basictests.hascontent;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.jpcmSrcFolderKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.noContentKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notJpcmSrcFolderKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notRootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.partKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.withContentKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createFileContentModel;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createFolderContentModel;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createJavaFileContentModel;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createJavaProjectContentModel;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

@RunWith(JUnit4.class)
public class HasContent_tc_CADSEg extends GTCadseTestCase {

	protected static final String cadse_name = "CADSE_HasContent";
	protected final GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected final GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected final GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected final GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
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

		createItemType(data_model, "no_content", notAbstractKv, rootKv, noContentKv);

		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), file_cm,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), folder_cm,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), javaFile_cm,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, mapping_model.concat("no_content" + managerSufix), javaProject_cm,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		// ======================= //
		// JavaProjectContentModel //
		// ======================= //

		// Java project content model with and without source folder
		createItemType(data_model, "javaproject_content_src", notAbstractKv, rootKv, withContentKv);
		createItemType(data_model, "javaproject_content_nosrc", notAbstractKv, rootKv, withContentKv);

		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_src" + managerSufix),
				jpcmSrcFolderKv);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_nosrc" + managerSufix),
				notJpcmSrcFolderKv);

		// Java project content model Name template
		createItemType(data_model, "javaproject_content_template1", notAbstractKv, rootKv, withContentKv);
		createItemType(data_model, "javaproject_content_template2", notAbstractKv, rootKv, withContentKv);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_template1"
				+ managerSufix), new KeyValue(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_,
				"${#qualified-name}_test"));
		createJavaProjectContentModel(workspaceView, mapping_model.concat("javaproject_content_template2"
				+ managerSufix), new KeyValue(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_, "${#type-name}_test"));

		// =================== //
		// Common root project //
		// =================== //

		createItemType(data_model, "root_project", notAbstractKv, rootKv, withContentKv);
		createJavaProjectContentModel(workspaceView, mapping_model.concat("root_project" + managerSufix),
				jpcmSrcFolderKv);

		// ================ //
		// FileContentModel //
		// ================ //

		createItemType(data_model, "file_content", notAbstractKv, notRootKv, withContentKv);
		createLinkType("link_file", data_model.concat("root_project"), data_model.concat("file_content"), "0",
				"unbounded", partKv);
		createFileContentModel(workspaceView, mapping_model.concat("file_content" + managerSufix), new KeyValue(
				CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "/${#short-name}.txt"));

		// ================== //
		// FolderContentModel //
		// ================== //

		createItemType(data_model, "folder_content", notAbstractKv, notRootKv, withContentKv);
		createLinkType("link_folder", data_model.concat("root_project"), data_model.concat("folder_content"), "0",
				"unbounded", partKv);
		createFolderContentModel(workspaceView, mapping_model.concat("folder_content" + managerSufix), new KeyValue(
				CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_, "/${#short-name}"));

		// ==================== //
		// JavaFileContentModel //
		// ==================== //

		createItemType(data_model, "javaFile_content", notAbstractKv, notRootKv, withContentKv);
		createLinkType("link_javaFile", data_model.concat("root_project"), data_model.concat("javaFile_content"), "0",
				"unbounded", partKv);
		createJavaFileContentModel(workspaceView, mapping_model.concat("javaFile_content" + managerSufix),
				new KeyValue(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_, "${#short-name}"), new KeyValue(
						CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_, "fr.imag.adele.${#short-name}"));
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
