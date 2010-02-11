package fr.imag.adele.cadse.test.basictests.root;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTTestParameters;


public class root_tc_execution extends GTCadseTestCase {

	@Test
	public void test_selection() throws Exception {

		GTTestParameters.banner();
		System.out.println("trace1");
		GTTestParameters.banner();
		
		selectCadses("Cadse Model.Workspace.CADSE_root");
		welcomeView.close();
		
		GTTestParameters.banner();
		System.out.println("trace2");
		GTTestParameters.banner();

	}
	
	
	@Test
	public void test_feature() throws Exception {

		GTTestParameters.banner();
		System.out.println("trace3");
		GTTestParameters.banner();

		workspaceView.contextMenuNew("itR").click();
		shell = new GTCadseShell("itR");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("instance-itNrR");
		shell.close();
		workspaceView.selectNode(new GTTreePath("instance-itNrR"));
		
		//workspaceView.contextMenuNew("itNrR").click();
		//workspaceView.contextMenuNew("itRR").click();
				
		while (true)
			;
	}
	
	
}
