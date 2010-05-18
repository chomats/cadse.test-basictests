package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_enum_testDriver extends BasicProperties_common_testDriver {

	String enumTypeName = "my_enum";

	public BasicProperties_enum_testDriver() {

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "", null);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "two", "two");
		defValCADSEgValues = new KeyValue[] { kv11, kv12 };

		/* Execution : value at start up */
		KeyValue kv21 = new KeyValue(getAttributeName(), "", null);
		KeyValue kv22 = new KeyValue(getAttributeName(), "two", "two");
		executionOldValues = new KeyValue[] { kv21, kv22 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(getAttributeName(), "", null);
		KeyValue kv32 = new KeyValue(getAttributeName(), "one", "one");
		KeyValue kv33 = null; // null stands for leave unchanged
		executionNewValues = new KeyValue[] { kv31, kv32, kv33 };

		/* CBU = this attributes does not exists with enum type */
		cbuValues = new KeyValue[] { cbuKv };

		initializeTables();
	}

	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.ENUM;
	}

	/**
	 * Performs actions before the item creation.
	 * 
	 * @param i
	 *            the number of the item to be created
	 */
	@Override
	protected void preCreate(int i) {
		if (i == 0) {
			GTCadseHelperMethods.createEnumType(dataModel, enumTypeName, "one", "two", "three", "four");
		}
	}

	/**
	 * Gets key values to fill creation page.
	 * 
	 * @param i
	 *            the i
	 * @return the creation key values
	 */
	@Override
	protected KeyValue[] getCreationKeyValues(int i) {
		KeyValue enumTypeKv = new KeyValue(CadseGCST.ENUM_lt_ENUM_TYPE, new GTTreePath(enumTypeName));
		return new KeyValue[] { enumTypeKv, defValCADSEgTab.get(i), sicpTab.get(i), simpTab.get(i), listTab.get(i) };
	}

	/**
	 * Returns true if the attribute can be created, false otherwise.
	 * 
	 * @param i
	 * @return true if the attribute can be created, false otherwise.
	 */
	@Override
	protected boolean attributeCreationSuccess(int i) {
		String defVal = (String) defValCADSEgTab.get(i).visualValue;
		if (defVal.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}
}
