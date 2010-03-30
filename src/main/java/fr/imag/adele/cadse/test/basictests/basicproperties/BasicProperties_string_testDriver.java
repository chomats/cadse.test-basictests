package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.test.KeyValue.simpKv;

import java.util.ArrayList;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_string_testDriver extends BasicProperties_common_testDriver {

	/* Not Empty attribute : True value */
	KeyValue notEmptyTrue = new KeyValue(CadseGCST.STRING_at_NOT_EMPTY_, true, true);
	/* Not Empty attribute : False value */
	KeyValue notEmptyFalse = new KeyValue(CadseGCST.STRING_at_NOT_EMPTY_, false, false);
	/** Set of values for the notEmpty property */
	protected final KeyValue[] notEmptyValues = { notEmptyTrue, notEmptyFalse };

	/** The notEmpty property value for all the instances */
	protected final ArrayList<KeyValue> notEmptyTab = new ArrayList<KeyValue>();

	public BasicProperties_string_testDriver() {

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null, null);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "", "");
		KeyValue kv13 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "my_dv", "my_dv");
		defValCADSEgValues = new KeyValue[] { kv11, kv12, kv13 };

		/* Execution : value at start up */
		KeyValue kv21 = new KeyValue(getAttributeName(), "", "");
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
	 * Performs table initializations
	 */
	@Override
	public void initializeTables() {

		for (KeyValue notEmpty : notEmptyValues) {
			for (int j = 0; j < defValCADSEgValues.length; j++) {
				for (int k = 0; k < executionNewValues.length; k++) {
					for (KeyValue sicp : sicpValues) {
						for (KeyValue simp : simpValues) {
							for (KeyValue cbu : cbuValues) {
								for (KeyValue list : listValues) {
									defValCADSEgTab.add(defValCADSEgValues[j]);
									executionOldTab.add(executionOldValues[j]);
									executionNewTab.add(executionNewValues[k]);
									sicpTab.add(sicp);
									simpTab.add(simp);
									cbuTab.add(cbu);
									listTab.add(list);
									notEmptyTab.add(notEmpty);
								}
							}
						}
					}
				}
			}
		}
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
		notEmptyTab.get(i).checkBox(propertiesView);
	}

	/**
	 * Checks if is ok button is activated.
	 * 
	 * @param i
	 *            the instance number
	 * @return true, if is ok button is activated
	 */
	@Override
	public boolean isOkButtonActivated(int i) {
		if (listTab.get(i).getBoolean()) {
			return true;
		}
		else {
			boolean cbu = cbuTab.get(i).getBoolean();
			boolean notEmpty = notEmptyTab.get(i).getBoolean();

			boolean cbuResult = cbu ? (getFinalModelValue(i) != null) : true;
			boolean notEmptyResult = notEmpty ? (!getFinalModelValue(i).equals("")) : true;

			return cbuResult && notEmptyResult;
		}
	}
}
