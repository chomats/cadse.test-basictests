package fr.imag.adele.cadse.test.basictests.defaultinstancename;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

@RunWith(JUnit4.class)
public class DefaultInstanceName_tc_execution extends GTCadseTestCase {

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses("Cadse Model.Workspace.CADSE_DefaultInstanceName");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_running() throws Exception {

		/* Creates attribute */
		workspaceView.contextMenuNew("my_item_type").click();
		GTCadseShell shell = new GTCadseShell("my_item_type");
		assertEquals("my_default_name", shell.findCadseFieldName().getText());
		shell.close();

		/* Assert item has been displayed */
		workspaceView.selectNode("my_default_name");
	}
}
