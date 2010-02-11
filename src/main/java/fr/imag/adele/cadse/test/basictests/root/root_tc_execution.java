package fr.imag.adele.cadse.test.basictests.root;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;


public class root_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		selectCadses("Cadse Model.Workspace.CADSE_root");
		welcomeView.close();
	}
	
	
	@Test
	public void test_running() throws Exception {

		/* This items creation should be possible */
		createBasicItem(workspaceView, null, "itR",   "itR",   "instance-itR",   new GTTreePath("instance-itR"));
		createBasicItem(workspaceView, null, "itNrR", "itNrR", "instance-itNrR", new GTTreePath("instance-itNrR"));
		createBasicItem(workspaceView, null, "itRR",  "itRR",  "instance-itRR",  new GTTreePath("instance-itRR"));
		
		/* This items creation shouldn't be available */
		assertItemCantbeCreated(workspaceView, null, "itNr",   3000);
		assertItemCantbeCreated(workspaceView, null, "itNrNr", 3000);
		assertItemCantbeCreated(workspaceView, null, "itRNr",  3000);
	}
}
