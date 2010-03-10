package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
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
	 * @param tab_hicp
	 *            a tab containing a set of hidden in computed pages values
	 * @param tab_mbi
	 *            a tab containing a set of must be initialized at creation time values
	 * @param tab_cbu
	 *            a tab containing a set of cannot be undefined values
	 */
	protected void createItems(GTTreePath data_model, ItemType attr, String prefix, String[] tab_defVal,
			boolean[] tab_hicp, boolean[] tab_mbi, boolean[] tab_cbu) {

		int i = 1;
		for (String defVal : tab_defVal) {
			for (boolean hicp : tab_hicp) {
				for (boolean mbi : tab_mbi) {
					for (boolean cbu : tab_cbu) {

						/* Item type creation */
						String it_name = "my_" + prefix + i;
						GTTreePath it_path = data_model.concat(it_name);
						createItemType(data_model, it_name, null, notAbstract, root, defaultContent);

						/* Attribute creation */
						String attr_name = prefix + "_attr";
						GTTreePath attr_path = it_path.concat(attr_name);
						createBasicAttribute(it_path, attr, attr_name, null, defVal, hicp, mbi, notList);

						/* Cannot be undefined attribute */
						try {
							propertiesView.showTab(attr.getDisplayName());
						}
						catch (Exception e) {
							workspaceView.selectNode(attr_path);
							propertiesView.showTab(attr.getDisplayName());
						}
						GTCadseFactory.findCadseField(propertiesView, CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_)
								.check(cbu);

						/* Increment */
						i++;
					}
				}
			}
		}
	}
}
