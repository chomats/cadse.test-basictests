package fr.imag.adele.cadse.test.basictests.link;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;



public class Link_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse "/*Model.Workspace.CADSE_Link"*/);
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_annotation() throws Exception {
		
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", "annotation1", new GTTreePath("annotation1"));
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", "annotation2", new GTTreePath("annotation2"));
		
		addLink("annotation1", "annotation_src", "annotation2", "link_annotation");
		assertLinkCantBeAdded("annotation1", "annotation_src", "annotation2", "link_annotation", failingAssertTimeout);
		
		
		
		
		while(true);
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
		addLink("instance_s1", "s1", "instance_d1a", "l11");
				
		addLink("instance_s1", "s1", "instance_d1b", "l11");
		
		addLink("instance_s2", "s2", "instance_d1a", "l11");
		addLink("instance_s2", "s2", "instance_d1a", "l21");
				
		addLink("instance_s2", "s2", "instance_d1b", "l11");
		addLink("instance_s2", "s2", "instance_d1b", "l21");
				
		addLink("instance_s1", "s1", "instance_d2a", "l11");
		addLink("instance_s1", "s1", "instance_d2a", "l12");
				
		addLink("instance_s1", "s1", "instance_d2b", "l11");
		addLink("instance_s1", "s1", "instance_d2b", "l12");
		
		addLink("instance_s2", "s2", "instance_d2a", "l11");
		addLink("instance_s2", "s2", "instance_d2a", "l12");
		addLink("instance_s2", "s2", "instance_d2a", "l21");
		addLink("instance_s2", "s2", "instance_d2a", "l22");
		
		addLink("instance_s2", "s2", "instance_d2b", "l11");
		addLink("instance_s2", "s2", "instance_d2b", "l12");
		addLink("instance_s2", "s2", "instance_d2b", "l21");
		addLink("instance_s2", "s2", "instance_d2b", "l22");
		
		
		/* Should not be allowed */ 
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d1a", "l12", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d1a", "l21", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d1a", "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d1b", "l12", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d1b", "l21", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d1b", "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded("instance_s2", "s2", "instance_d1a", "l12", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s2", "s2", "instance_d1a", "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded("instance_s2", "s2", "instance_d1b", "l12", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s2", "s2", "instance_d1b", "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d2a", "l21", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d2a", "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d2b", "l21", failingAssertTimeout);
		assertLinkCantBeAdded("instance_s1", "s1", "instance_d2b", "l22", failingAssertTimeout);
	}
}
