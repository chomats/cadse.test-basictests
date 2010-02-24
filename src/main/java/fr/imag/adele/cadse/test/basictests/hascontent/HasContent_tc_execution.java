package fr.imag.adele.cadse.test.basictests.hascontent;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;



public class HasContent_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_HasContent");
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_running() throws Exception {
		
		// Default values
		String sourceFolder = "sources";
		
		// ======================= //
		// JavaProjectContentModel //
		// ======================= //
		
		// Java project content model with and without source folder
		createBasicItem(workspaceView, null, "javaproject_content_src", "instance_src", new GTTreePath("instance_src"));
		packageExplorerView.selectNode("instance_src", sourceFolder);
		createBasicItem(workspaceView, null, "javaproject_content_nosrc", "instance_nosrc", new GTTreePath("instance_nosrc"));
		packageExplorerView.selectNode("instance_nosrc");
		assertNodeDoesNotExists(packageExplorerView, new GTTreePath("instance_nosrc", sourceFolder), failingAssertTimeout);
		
		//  Java project content model Name template
		createBasicItem(workspaceView, null, "javaproject_content_template1", "instance_template1", new GTTreePath("instance_template1"));
		packageExplorerView.selectNode("instance_template1_test");
		createBasicItem(workspaceView, null, "javaproject_content_template2", "instance_template2", new GTTreePath("instance_template2"));
		packageExplorerView.selectNode("javaproject_content_template2_test");

		
		// =================== //
		// Common root project //
		// =================== //
		
		GTTreePath root_project_node = new GTTreePath("my_root_project");
		createBasicItem(workspaceView, null, "root_project", "my_root_project", root_project_node);
		packageExplorerView.selectNode("my_root_project");

		
		// ================ //
		// FileContentModel //
		// ================ //
		
		createBasicItem(workspaceView, root_project_node, "file_content", "my_file_content", root_project_node.concat("my_file_content"));
		packageExplorerView.selectNode(root_project_node.concat("my_file_content.txt"));
			
		
		// ================== //
		// FolderContentModel //
		// ================== //
		
		createBasicItem(workspaceView, root_project_node, "folder_content", "my_folder_content", root_project_node.concat("my_folder_content"));
		packageExplorerView.selectNode(root_project_node.concat("my_folder_content"));
		
		
		// ==================== //
		// JavaFileContentModel //
		// ==================== //
		
		createBasicItem(workspaceView, root_project_node, "javaFile_content", "my_javaFile_content", root_project_node.concat("my_javaFile_content"));
		packageExplorerView.selectNode(root_project_node.concat("sources", "fr.imag.adele.my_javaFile_content", "my_javaFile_content.java"));
	}
}
