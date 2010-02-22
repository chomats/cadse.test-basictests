package fr.imag.adele.cadse.test.basictests.link;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;



public class Link_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_Link");
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_source_destination() throws Exception {
		while(true);
	}

}
