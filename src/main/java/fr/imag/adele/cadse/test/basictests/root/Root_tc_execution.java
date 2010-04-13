package fr.imag.adele.cadse.test.basictests.root;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.assertItemCantbeCreated;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTTestCase;
import fr.imag.adele.graphictests.test.GTTestParameters;

public class Root_tc_execution extends GTTestCase {

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
		assertItemCantbeCreated(workspaceView, null, "itNr", GTTestParameters.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itNrNr", GTTestParameters.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itRNr", GTTestParameters.FAILING_ASSERT_TIMEOUT);
	}
}
