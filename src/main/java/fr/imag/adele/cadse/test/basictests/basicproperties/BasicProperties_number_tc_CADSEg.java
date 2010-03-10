package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_number_tc_CADSEg extends GTCadseTestCase {

	/**
	 * A very powerful method used to create attributes combining values available in tables.
	 * 
	 * @param attr
	 *            the type of the attribute to be created
	 * @param prefix
	 *            prefix, used to compute the attribute name.
	 * @param tab_defVal
	 *            a tab containing a set of default values
	 * @param tab_sicp
	 *            a tab containing values for the show in default creation page parameter
	 * @param tab_simp
	 *            a tab containing values for the show in default modification page parameter
	 * @param tab_cbu
	 *            a tab containing a set of cannot be undefined values
	 */
	protected void createItems(GTTreePath data_model, ItemType attr, String prefix, String[] tab_defVal,
			boolean[] tab_sicp, boolean[] tab_simp, boolean[] tab_cbu) {

		int i = 1;
		for (String defVal : tab_defVal) {
			for (boolean sicp : tab_sicp) {
				for (boolean simp : tab_simp) {
					for (boolean cbu : tab_cbu) {

						/* Item type creation */
						String it_name = "my_" + prefix + i;
						GTTreePath it_path = data_model.concat(it_name);
						createItemType(data_model, it_name, null, notAbstract, root, defaultContent);

						/* Attribute creation */
						String attr_name = prefix + "_attr";
						GTTreePath attr_path = it_path.concat(attr_name);
						createBasicAttribute(it_path, attr, attr_name, null, defVal, sicp, simp, cbu, notList);

						/* Assert item has been created */
						workspaceView.selectNode(attr_path);

						/* Increment */
						i++;
					}
				}
			}
		}
	}
}
