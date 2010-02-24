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
		
		// Link creation and deletion
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", "annotation_src1", new GTTreePath("annotation_src1"));
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", "annotation_dst1", new GTTreePath("annotation_dst1"));
		addLink("annotation_src1", "annotation_src", "annotation_dst1", "link_annotation");
		assertLinkCantBeAdded("annotation_src1", "annotation_src", "annotation_dst1", "link_annotation", failingAssertTimeout);
		
		// FIXME Tester la suppression du lien et sa re creation --> Voir avec Stéphane la spec
		
		// Source deletion
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", "annotation_src2", new GTTreePath("annotation_src2"));
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", "annotation_dst2", new GTTreePath("annotation_dst2"));
		addLink("annotation_src2", "annotation_src", "annotation_dst2", "link_annotation");
		
		deleteBasicItem(workspaceView, new GTTreePath("annotation_src2"));
		workspaceView.selectNode("annotation_dst2");
				
		// Destination deletion
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", "annotation_src3", new GTTreePath("annotation_src3"));
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", "annotation_dst3", new GTTreePath("annotation_dst3"));
		addLink("annotation_src3", "annotation_src", "annotation_dst3", "link_annotation");
		deleteBasicItem(workspaceView, new GTTreePath("annotation_dst3"));
		assertItemDoesNotExists(workspaceView, new GTTreePath("annotation_src3"), failingAssertTimeout);
		
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
