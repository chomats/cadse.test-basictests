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
		createBasicItem(workspaceView, null, "javaproject_content_src", "javaproject_content_src", "instance_src", new GTTreePath("instance_src"));
		packageExplorerView.selectNode("instance_src", sourceFolder);
		createBasicItem(workspaceView, null, "javaproject_content_nosrc", "javaproject_content_nosrc", "instance_nosrc", new GTTreePath("instance_nosrc"));
		packageExplorerView.selectNode("instance_nosrc");
		assertNodeDoesNotExists(packageExplorerView, new GTTreePath("instance_nosrc", sourceFolder), 3000);
		
		//  Java project content model Name template
		createBasicItem(workspaceView, null, "javaproject_content_template1", "javaproject_content_template1", "instance_template1", new GTTreePath("instance_template1"));
		packageExplorerView.selectNode("instance_template1_test");
		createBasicItem(workspaceView, null, "javaproject_content_template2", "javaproject_content_template2", "instance_template2", new GTTreePath("instance_template2"));
		packageExplorerView.selectNode("javaproject_content_template2_test");

		
		// ======================= //
		//                         //
		// ======================= //

		


		
		
		while (true);
	}
}
