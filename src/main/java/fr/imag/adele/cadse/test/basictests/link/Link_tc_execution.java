package fr.imag.adele.cadse.test.basictests.link;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;



public class Link_tc_execution extends GTCadseTestCase {

	protected final long failingAssertTimeout = 3000;
	
	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_IsAbstract");
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_running() throws Exception {
	}

}
