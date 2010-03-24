package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.util.ArrayList;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_string_testDriver extends BasicProperties_common_testDriver {

	/** Set of values for the notEmpty property */
	protected final boolean[] notEmptyValues = { true, false };
	/** The notEmpty property value for all the instances */
	protected final ArrayList<Boolean> notEmptyTab = new ArrayList<Boolean>();

	public BasicProperties_string_testDriver() {

		defValGraphicValues = new String[] { null, "", "my_dv" };
		defValModelValues = new Object[] { null, "", "my_dv" };

		newValGraphicValues = new String[] { null, "", "new_dv" }; // null stands for leave unchanged
		newValModelValues = new Object[] { null, "", "new_dv" };

		sicpValues = new boolean[] { true };
		simpValues = new boolean[] { true };

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

		for (String defValGraphic : defValGraphicValues) {
			for (String newValGraphic : newValGraphicValues) {
				for (boolean sicp : sicpValues) {
					for (boolean simp : simpValues) {
						for (boolean cbu : cbuValues) {
							for (boolean list : listValues) {
								for (boolean notEmpty : notEmptyValues) {
									defValGraphicTab.add(defValGraphic);
									newValGraphicTab.add(newValGraphic);

									defValModelTab.add(defValGraphic); // graphic value equals the model one
									newValModelTab.add(newValGraphic); // graphic value equals the model one

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
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.STRING_at_NOT_EMPTY_).check(notEmptyTab.get(i));
	}
}
