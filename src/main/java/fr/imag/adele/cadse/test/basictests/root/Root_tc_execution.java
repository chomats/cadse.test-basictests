package fr.imag.adele.cadse.test.basictests.root;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Root_tc_execution extends GTCadseTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_Root");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_running() throws Exception {

		/* This items creation should be possible */
		createBasicItem(workspaceView, null, "itR", "instance-itR", new GTTreePath("instance-itR"));
		createBasicItem(workspaceView, null, "itNrR", "instance-itNrR", new GTTreePath("instance-itNrR"));
		createBasicItem(workspaceView, null, "itRR", "instance-itRR", new GTTreePath("instance-itRR"));

		/* This items creation shouldn't be available */
		assertItemCantbeCreated(workspaceView, null, "itNr", GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itNrNr", GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itRNr", GTPreferences.FAILING_ASSERT_TIMEOUT);
	}
}
