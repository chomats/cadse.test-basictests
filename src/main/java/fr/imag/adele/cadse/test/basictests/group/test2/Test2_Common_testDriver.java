package fr.imag.adele.cadse.test.basictests.group.test2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.groupKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicHead;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import fr.imag.adele.cadse.test.basictests.common.GTSimpleTestDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test2_Common_testDriver extends GTSimpleTestDriver {

	protected String getItSrcName(GTTestParameter tp) {
		String sufix = getItName(tp).substring(0, 1).toUpperCase() + getItName(tp).substring(1);
		return "parent" + sufix;
	}

	@Override
	protected GTTreePath createTypes(GTTestParameter tp) {
		GTTreePath src = getDataModel(tp).concat(getItSrcName(tp));
		GTTreePath dst = getDataModel(tp).concat(getItName(tp));

		createItemType(getDataModel(tp), src.getDestinationName(), notAbstractKv(), rootKv());
		createItemType(getDataModel(tp), dst.getDestinationName(), notAbstractKv(), rootKv());
		createLinkType("groupLink", src, dst, null, null, groupKv());

		return getDataModel(tp).concat(getItName(tp));
	}

	@Override
	protected GTCadseShell initCreationPage(GTTestParameter tp) {
		workspaceView.contextMenuNewMember(new GTTreePath(getInstanceSrcName(tp)), getItName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItName(tp));
		return shell;
	}

	protected String getInstanceSrcName(GTTestParameter tp) {
		return "groupHead" + tp.testNumber;
	}

	@Override
	protected void preExecute(GTTestParameter tp) {

		/* Head creation */
		GTTreePath headPath = new GTTreePath(getInstanceSrcName(tp));
		createBasicHead(workspaceView, null, getItSrcName(tp), getInstanceSrcName(tp), headPath);
	}
}
