package fr.imag.adele.cadse.test.basictests.link;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.addLink;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.removeLink;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

@RunWith(JUnit4.class)
public class Link_tc_execution extends GTCadseTestCase {

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses("Cadse Model.Workspace.CADSE_Link");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_minMax() throws Exception {

		// Instances
		String instance_prefix = "instance_min_max_";
		String src1_name = instance_prefix + "s1";
		String dst1_name = instance_prefix + "d1";
		String dst2_name = instance_prefix + "d2";
		String dst3_name = instance_prefix + "d3";
		GTTreePath src1_path = new GTTreePath(src1_name);
		GTTreePath dst1_path = new GTTreePath(dst1_name);
		GTTreePath dst2_path = new GTTreePath(dst2_name);
		GTTreePath dst3_path = new GTTreePath(dst3_name);

		// Types
		String type_prefix = "min_max_";
		String s1_type_name = type_prefix + "s1";
		String d1_type_name = type_prefix + "d1";
		String link_type_name = type_prefix + "link";

		// New attributes
		createBasicItem(workspaceView, null, s1_type_name, src1_name, src1_path);
		createBasicItem(workspaceView, null, d1_type_name, dst1_name, dst1_path);
		createBasicItem(workspaceView, null, d1_type_name, dst2_name, dst2_path);
		createBasicItem(workspaceView, null, d1_type_name, dst3_name, dst3_path);

		// Should be allowed
		addLink(workspaceView, src1_path, dst1_name, link_type_name);
		addLink(workspaceView, src1_path, dst2_name, link_type_name);

		// Should not be allowed
		addLink(workspaceView, src1_path, dst3_name, link_type_name);
		for (String str : propertiesView.findCadseField(link_type_name).getRoots()) {
			if (str.equals(link_type_name)) {
				fail("The link shouldn't have been added");
			}
		}
	}

	@Test
	public void test_src_dst() throws Exception {

		// Instances
		String instance_prefix = "instance_src_dst_";
		String src1_name = instance_prefix + "s1";
		String src2_name = instance_prefix + "s2";
		String dst1a_name = instance_prefix + "d1a";
		String dst1b_name = instance_prefix + "d1b";
		String dst2a_name = instance_prefix + "d2a";
		String dst2b_name = instance_prefix + "d2b";
		GTTreePath src1_path = new GTTreePath(src1_name);
		GTTreePath src2_path = new GTTreePath(src2_name);
		GTTreePath dst1a_path = new GTTreePath(dst1a_name);
		GTTreePath dst1b_path = new GTTreePath(dst1b_name);
		GTTreePath dst2a_path = new GTTreePath(dst2a_name);
		GTTreePath dst2b_path = new GTTreePath(dst2b_name);

		// Types
		String type_prefix = "src_dst_";
		String s1_type_name = type_prefix + "s1";
		String s2_type_name = type_prefix + "s2";
		String d1_type_name = type_prefix + "d1";
		String d2_type_name = type_prefix + "d2";
		String l11_link_type_name = type_prefix + "l11";
		String l12_link_type_name = type_prefix + "l12";
		String l21_link_type_name = type_prefix + "l21";
		String l22_link_type_name = type_prefix + "l22";

		// New attributes
		createBasicItem(workspaceView, null, s1_type_name, src1_name, src1_path);
		createBasicItem(workspaceView, null, s2_type_name, src2_name, src2_path);
		createBasicItem(workspaceView, null, d1_type_name, dst1a_name, dst1a_path);
		createBasicItem(workspaceView, null, d1_type_name, dst1b_name, dst1b_path);
		createBasicItem(workspaceView, null, d2_type_name, dst2a_name, dst2a_path);
		createBasicItem(workspaceView, null, d2_type_name, dst2b_name, dst2b_path);

		// Should be allowed
		addLink(workspaceView, src1_path, dst1a_name, l11_link_type_name);

		addLink(workspaceView, src1_path, dst1b_name, l11_link_type_name);

		addLink(workspaceView, src2_path, dst1a_name, l11_link_type_name);
		addLink(workspaceView, src2_path, dst1a_name, l21_link_type_name);

		addLink(workspaceView, src2_path, dst1b_name, l11_link_type_name);
		addLink(workspaceView, src2_path, dst1b_name, l21_link_type_name);

		addLink(workspaceView, src1_path, dst2a_name, l11_link_type_name);
		addLink(workspaceView, src1_path, dst2a_name, l12_link_type_name);

		addLink(workspaceView, src1_path, dst2b_name, l11_link_type_name);
		addLink(workspaceView, src1_path, dst2b_name, l12_link_type_name);

		addLink(workspaceView, src2_path, dst2a_name, l11_link_type_name);
		addLink(workspaceView, src2_path, dst2a_name, l12_link_type_name);
		addLink(workspaceView, src2_path, dst2a_name, l21_link_type_name);
		addLink(workspaceView, src2_path, dst2a_name, l22_link_type_name);

		addLink(workspaceView, src2_path, dst2b_name, l11_link_type_name);
		addLink(workspaceView, src2_path, dst2b_name, l12_link_type_name);
		addLink(workspaceView, src2_path, dst2b_name, l21_link_type_name);
		addLink(workspaceView, src2_path, dst2b_name, l22_link_type_name);

		// Should not be allowed

		assertLinkCantBeAdded(workspaceView, src1_path, dst1a_name, l12_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src1_path, dst1a_name, l21_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src1_path, dst1a_name, l22_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		assertLinkCantBeAdded(workspaceView, src1_path, dst1b_name, l12_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src1_path, dst1b_name, l21_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src1_path, dst1b_name, l22_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		assertLinkCantBeAdded(workspaceView, src2_path, dst1a_name, l12_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src2_path, dst1a_name, l22_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		assertLinkCantBeAdded(workspaceView, src2_path, dst1b_name, l12_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src2_path, dst1b_name, l22_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		assertLinkCantBeAdded(workspaceView, src1_path, dst2a_name, l21_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src1_path, dst2a_name, l22_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		assertLinkCantBeAdded(workspaceView, src1_path, dst2b_name, l21_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertLinkCantBeAdded(workspaceView, src1_path, dst2b_name, l22_link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);
	}

	@Test
	public void test_annotation() throws Exception {

		// Instances
		String instance_prefix = "instance_annotation_";
		String src1_name = instance_prefix + "s1";
		String src2_name = instance_prefix + "s2";
		String src3_name = instance_prefix + "s3";
		String dst1_name = instance_prefix + "d1";
		String dst2_name = instance_prefix + "d2";
		String dst3_name = instance_prefix + "d3";
		GTTreePath src1_path = new GTTreePath(src1_name);
		GTTreePath src2_path = new GTTreePath(src2_name);
		GTTreePath src3_path = new GTTreePath(src3_name);
		GTTreePath dst1_path = new GTTreePath(dst1_name);
		GTTreePath dst2_path = new GTTreePath(dst2_name);
		GTTreePath dst3_path = new GTTreePath(dst3_name);

		// Types
		String type_prefix = "annotation_";
		String link_type_name = type_prefix + "link";
		String src_type_name = type_prefix + "src";
		String dst_type_name = type_prefix + "dst";

		// Link creation and deletion
		createBasicItem(workspaceView, null, src_type_name, src1_name, src1_path);
		createBasicItem(workspaceView, null, dst_type_name, dst1_name, dst1_path);
		addLink(workspaceView, src1_path, dst1_name, link_type_name);

		assertLinkCantBeAdded(workspaceView, src1_path, dst1_name, link_type_name, GTPreferences.FAILING_ASSERT_TIMEOUT);

		// Link deletion
		removeLink(workspaceView, src1_path, dst1_name, link_type_name);
		assertNodeDoesNotExists(workspaceView, src1_path, GTPreferences.FAILING_ASSERT_TIMEOUT);

		// Re-creation
		createBasicItem(workspaceView, null, src_type_name, src1_name, src1_path);
		addLink(workspaceView, src1_path, dst1_name, link_type_name);

		// Source deletion
		createBasicItem(workspaceView, null, src_type_name, src2_name, src2_path);
		createBasicItem(workspaceView, null, dst_type_name, dst2_name, dst2_path);
		addLink(workspaceView, src2_path, dst2_name, link_type_name);

		deleteBasicItem(workspaceView, src2_path);
		workspaceView.selectNode(dst2_name);

		// Destination deletion
		createBasicItem(workspaceView, null, src_type_name, src3_name, src3_path);
		createBasicItem(workspaceView, null, dst_type_name, dst3_name, dst3_path);
		addLink(workspaceView, src3_path, dst3_name, link_type_name);

		deleteBasicItem(workspaceView, dst3_path);
		assertNodeDoesNotExists(workspaceView, src3_path, GTPreferences.FAILING_ASSERT_TIMEOUT);
	}

	@Test
	public void test_part() throws Exception {

		// Instances
		String instance_prefix = "instance_part_";
		String src1_name = instance_prefix + "s1";
		String dst1_name = instance_prefix + "d1";
		String dst2_name = instance_prefix + "d2";
		String dst3_name = instance_prefix + "d3";
		GTTreePath src1_path = new GTTreePath(src1_name);
		GTTreePath dst1_path = new GTTreePath(dst1_name);
		GTTreePath dst2_path = new GTTreePath(dst2_name);
		GTTreePath dst3_path = new GTTreePath(dst3_name);

		// Types
		String type_prefix = "part_";
		String link_type_name = type_prefix + "link";
		String src_type_name = type_prefix + "src";
		String dst_type_name = type_prefix + "dst";

		// source and destination creation
		assertItemCantbeCreated(workspaceView, null, dst_type_name, GTPreferences.FAILING_ASSERT_TIMEOUT);
		createBasicItem(workspaceView, null, src_type_name, src1_name, src1_path);
		createBasicItem(workspaceView, src1_path, dst_type_name, dst1_name, dst1_path);
		assertLinkCantBeRemoved(workspaceView, src1_path, dst1_name, link_type_name,
				GTPreferences.FAILING_ASSERT_TIMEOUT);

		// assert destination can be deleted
		deleteBasicItem(workspaceView, dst1_path);
		workspaceView.selectNode(src1_path);

		// source deletion
		deleteBasicItem(workspaceView, src1_path);

		// source and multiple destination creation
		createBasicItem(workspaceView, null, src_type_name, src1_name, src1_path);
		createBasicItem(workspaceView, src1_path, dst_type_name, dst1_name, dst1_path);
		createBasicItem(workspaceView, src1_path, dst_type_name, dst2_name, dst2_path);
		createBasicItem(workspaceView, src1_path, dst_type_name, dst3_name, dst3_path);

		deleteBasicItem(workspaceView, src1_path);
		assertNodeDoesNotExists(workspaceView, dst1_path, GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertNodeDoesNotExists(workspaceView, dst2_path, GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertNodeDoesNotExists(workspaceView, dst3_path, GTPreferences.FAILING_ASSERT_TIMEOUT);
	}
}
