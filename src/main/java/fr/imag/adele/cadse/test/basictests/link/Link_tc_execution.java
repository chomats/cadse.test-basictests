package fr.imag.adele.cadse.test.basictests.link;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;



public class Link_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse "/*Model.Workspace.CADSE_Link"*/);
		welcomeView.close();
		workspaceView.show();
	}
	
	
	@Test
	public void test_source_destination() throws Exception {
		
		/* New attributes */
		createBasicItem(workspaceView, null, "s1", "s1", "instance_s1", new GTTreePath("instance_s1"));
		createBasicItem(workspaceView, null, "s2", "s2", "instance_s2", new GTTreePath("instance_s2"));
		createBasicItem(workspaceView, null, "d1", "d1", "instance_d1a", new GTTreePath("instance_d1a"));
		createBasicItem(workspaceView, null, "d1", "d1", "instance_d1b", new GTTreePath("instance_d1b"));
		createBasicItem(workspaceView, null, "d2", "d2", "instance_d2a", new GTTreePath("instance_d2a"));
		createBasicItem(workspaceView, null, "d2", "d2", "instance_d2b", new GTTreePath("instance_d2b"));
		
	
		/* Should be allowed */ 
		createLink("instance_s1", "s1", "instance_d1a", "l11");
				
		createLink("instance_s1", "s1", "instance_d1b", "l11");
		
		createLink("instance_s2", "s2", "instance_d1a", "l11");
		createLink("instance_s2", "s2", "instance_d1a", "l21");
				
		createLink("instance_s2", "s2", "instance_d1b", "l11");
		createLink("instance_s2", "s2", "instance_d1b", "l21");
				
		createLink("instance_s1", "s1", "instance_d2a", "l11");
		createLink("instance_s1", "s1", "instance_d2a", "l12");
				
		createLink("instance_s1", "s1", "instance_d2b", "l11");
		createLink("instance_s1", "s1", "instance_d2b", "l12");
		
		createLink("instance_s2", "s2", "instance_d2a", "l11");
		createLink("instance_s2", "s2", "instance_d2a", "l12");
		createLink("instance_s2", "s2", "instance_d2a", "l21");
		createLink("instance_s2", "s2", "instance_d2a", "l22");
		
		createLink("instance_s2", "s2", "instance_d2b", "l11");
		createLink("instance_s2", "s2", "instance_d2b", "l12");
		createLink("instance_s2", "s2", "instance_d2b", "l21");
		createLink("instance_s2", "s2", "instance_d2b", "l22");
		
		
		/* Should not be allowed */ 
		createLinkFail("instance_s1", "s1", "instance_d1a", "l12", failingAssertTimeout);
		createLinkFail("instance_s1", "s1", "instance_d1a", "l21", failingAssertTimeout);
		createLinkFail("instance_s1", "s1", "instance_d1a", "l22", failingAssertTimeout);
		
		createLinkFail("instance_s1", "s1", "instance_d1b", "l12", failingAssertTimeout);
		createLinkFail("instance_s1", "s1", "instance_d1b", "l21", failingAssertTimeout);
		createLinkFail("instance_s1", "s1", "instance_d1b", "l22", failingAssertTimeout);
		
		createLinkFail("instance_s2", "s2", "instance_d1a", "l12", failingAssertTimeout);
		createLinkFail("instance_s2", "s2", "instance_d1a", "l22", failingAssertTimeout);
		
		createLinkFail("instance_s2", "s2", "instance_d1b", "l12", failingAssertTimeout);
		createLinkFail("instance_s2", "s2", "instance_d1b", "l22", failingAssertTimeout);
		
		createLinkFail("instance_s1", "s1", "instance_d2a", "l21", failingAssertTimeout);
		createLinkFail("instance_s1", "s1", "instance_d2a", "l22", failingAssertTimeout);
		
		createLinkFail("instance_s1", "s1", "instance_d2b", "l21", failingAssertTimeout);
		createLinkFail("instance_s1", "s1", "instance_d2b", "l22", failingAssertTimeout);
		
		while(true);
	}


	protected void createLink(String source, String sourceType, String destination, String linkName) {
		createLink(source, sourceType, destination, linkName, SWTBotPreferences.TIMEOUT);
	}
	
	protected void createLinkFail(String source, String sourceType, String destination, String linkName, long timeout) {
		try {
			createLink(source, sourceType, destination, linkName, timeout);
			fail("This creation should have fail");
		}
		catch (Exception e) {
			// SUCCESS
			System.out.println("SUCCESS : link creation " + linkName + " from " + source + " to " + destination + " has failed as is was expected");			
		}
	}
	
	protected void createLink(String source, String sourceType, String destination, String linkName, long timeout) {	
		long old_timeout = SWTBotPreferences.TIMEOUT;
		SWTBotPreferences.TIMEOUT = timeout;
		
		workspaceView.selectNode(source);
		propertiesView.showTab(sourceType);
				
		GTCadseFactory.findCadseField(propertiesView, linkName).add(destination);
		
		SWTBotPreferences.TIMEOUT = old_timeout;
	}
}
