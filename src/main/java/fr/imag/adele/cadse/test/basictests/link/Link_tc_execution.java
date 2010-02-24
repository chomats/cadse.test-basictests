package fr.imag.adele.cadse.test.basictests.link;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;



public class Link_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		// FIXME regarder ça avec stéphane.
		selectCadses("Cadse "/*Model.Workspace.CADSE_Link"*/);
		welcomeView.close();
		workspaceView.show();
	}
	
	@Test
	public void test_source_destination() throws Exception {
		
		String prefix = "instance_sd_";
		
		// source 1
		String source1_name = prefix + "s1";
		GTTreePath source1_path = new GTTreePath(source1_name);
		// source 2
		String source2_name = prefix + "s2";
		GTTreePath source2_path = new GTTreePath(source2_name);
		// destination 1a
		String dest1a_name = prefix + "d1a";
		GTTreePath dest1a_path = new GTTreePath(dest1a_name);
		// destination 1b
		String dest1b_name = prefix + "d1b";
		GTTreePath dest1b_path = new GTTreePath(dest1b_name);
		// destination 2a
		String dest2a_name = prefix + "d2a";
		GTTreePath dest2a_path = new GTTreePath(dest2a_name);
		// destination 2b
		String dest2b_name = prefix + "d2b";
		GTTreePath dest2b_path = new GTTreePath(dest2b_name);
		
		
		/* New attributes */
		createBasicItem(workspaceView, null, "s1", "s1", source1_name, source1_path);
		createBasicItem(workspaceView, null, "s2", "s2", source2_name, source2_path);
		createBasicItem(workspaceView, null, "d1", "d1", dest1a_name, dest1a_path);
		createBasicItem(workspaceView, null, "d1", "d1", dest1b_name, dest1b_path);
		createBasicItem(workspaceView, null, "d2", "d2", dest2a_name, dest2a_path);
		createBasicItem(workspaceView, null, "d2", "d2", dest2b_name, dest2b_path);
		
	
		/* Should be allowed */ 
		addLink(workspaceView, source1_path, dest1a_name, "l11");
				
		addLink(workspaceView, source1_path, dest1b_name, "l11");
		
		addLink(workspaceView, source2_path, dest1a_name, "l11");
		addLink(workspaceView, source2_path, dest1a_name, "l21");
				
		addLink(workspaceView, source2_path, dest1b_name, "l11");
		addLink(workspaceView, source2_path, dest1b_name, "l21");
				
		addLink(workspaceView, source1_path, dest2a_name, "l11");
		addLink(workspaceView, source1_path, dest2a_name, "l12");
				
		addLink(workspaceView, source1_path, dest2b_name, "l11");
		addLink(workspaceView, source1_path, dest2b_name, "l12");
		
		addLink(workspaceView, source2_path, dest2a_name, "l11");
		addLink(workspaceView, source2_path, dest2a_name, "l12");
		addLink(workspaceView, source2_path, dest2a_name, "l21");
		addLink(workspaceView, source2_path, dest2a_name, "l22");
		
		addLink(workspaceView, source2_path, dest2b_name, "l11");
		addLink(workspaceView, source2_path, dest2b_name, "l12");
		addLink(workspaceView, source2_path, dest2b_name, "l21");
		addLink(workspaceView, source2_path, dest2b_name, "l22");
		
		
		/* Should not be allowed */
		
		assertLinkCantBeAdded(workspaceView, source1_path, dest1a_name, "l12", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source1_path, dest1a_name, "l21", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source1_path, dest1a_name, "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded(workspaceView, source1_path, dest1b_name, "l12", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source1_path, dest1b_name, "l21", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source1_path, dest1b_name, "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded(workspaceView, source2_path, dest1a_name, "l12", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source2_path, dest1a_name, "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded(workspaceView, source2_path, dest1b_name, "l12", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source2_path, dest1b_name, "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded(workspaceView, source1_path, dest2a_name, "l21", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source1_path, dest2a_name, "l22", failingAssertTimeout);
		
		assertLinkCantBeAdded(workspaceView, source1_path, dest2b_name, "l21", failingAssertTimeout);
		assertLinkCantBeAdded(workspaceView, source1_path, dest2b_name, "l22", failingAssertTimeout);	
	}
	
	@Test
	public void test_annotation() throws Exception {
		
		String prefix = "instance_annotation_";
		
		// source 1
		String source1_name = prefix + "s1";
		GTTreePath source1_path = new GTTreePath(source1_name);
		// source 2
		String source2_name = prefix + "s2";
		GTTreePath source2_path = new GTTreePath(source2_name);
		// source 3
		String source3_name = prefix + "s3";
		GTTreePath source3_path = new GTTreePath(source3_name);
		// destination 1
		String dest1_name = prefix + "d1";
		GTTreePath dest1_path = new GTTreePath(dest1_name);
		// destination 2
		String dest2_name = prefix + "d2";
		GTTreePath dest2_path = new GTTreePath(dest2_name);
		// destination 3
		String dest3_name = prefix + "d3";
		GTTreePath dest3_path = new GTTreePath(dest3_name);
		
		
			
		// Link creation and deletion
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", source1_name, source1_path);
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", dest1_name,   dest1_path);
		addLink(workspaceView, source1_path, dest1_name, "link_annotation");
		
		assertLinkCantBeAdded(workspaceView, source1_path, dest1_name, "link_annotation", failingAssertTimeout);
		
		// FIXME Tester la suppression du lien et sa re creation --> Voir avec Stéphane la spec
		
		// Source deletion
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", source2_name, source2_path);
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", dest2_name, dest2_path);
		addLink(workspaceView, source2_path, dest2_name, "link_annotation");
		
		deleteBasicItem(workspaceView, source2_path);
		workspaceView.selectNode(dest2_name);
				
		// Destination deletion
		createBasicItem(workspaceView, null, "annotation_src", "annotation_src", source3_name, source3_path);
		createBasicItem(workspaceView, null, "annotation_dst", "annotation_dst", dest3_name, dest3_path);
		addLink(workspaceView, source3_path, dest3_name, "link_annotation");
		
		deleteBasicItem(workspaceView, dest3_path);
		assertItemDoesNotExists(workspaceView, source3_path, failingAssertTimeout);
	}
}
