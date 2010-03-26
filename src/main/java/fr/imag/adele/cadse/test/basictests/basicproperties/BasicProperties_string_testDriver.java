package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.Literals.sicpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.Literals.simpKv;

import java.util.ArrayList;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_string_testDriver extends BasicProperties_common_testDriver {

	/** Set of values for the notEmpty property */
	protected final boolean[] notEmptyValues = { true, false };
	/** The notEmpty property value for all the instances */
	protected final ArrayList<Boolean> notEmptyTab = new ArrayList<Boolean>();

	public BasicProperties_string_testDriver() {

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "my_dv");
		defValCADSEgValues = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : value at start up */
		KeyValue kv21 = new KeyValue(getAttributeName(), null, null);
		KeyValue kv22 = new KeyValue(getAttributeName(), "", "");
		KeyValue kv23 = new KeyValue(getAttributeName(), "my_dv", "my_dv");
		executionOldValues = new KeyValue[] { kv21, kv22, kv23 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "", "");
		KeyValue kv32 = new KeyValue(getAttributeName(), "new_val", "new_val");
		KeyValue kv33 = null; // null stands for leave unchanged
		executionNewValues = new KeyValue[] { kv31, kv32, kv33 };

		sicpValues = new KeyValue[] { sicpKv };
		simpValues = new KeyValue[] { simpKv };

		initializeTables();
	}

	/**
	 * Returns the type under test.
	 * 
	 * @return the type under test
	 */
	@Override
	public String getTypeUnderTest() {
		return "string";
	}

	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.STRING;
	}

	/**
	 * Performs actions after the item creation.
	 * 
	 * @param i
	 *            the number of the item to be created
	 * @param it_path
	 *            the item type path
	 * @param attr_path
	 *            the attribute path
	 */
	@Override
	protected void postCreate(int i, GTTreePath it_path, GTTreePath attr_path) {

		/* selects the node in the workspace view */
		workspaceView.selectNode(attr_path);

		propertiesView.showTab("String");
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.STRING_at_NOT_EMPTY_).check(notEmptyTab.get(i));
	}
}
