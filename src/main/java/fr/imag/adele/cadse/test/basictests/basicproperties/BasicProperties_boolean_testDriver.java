package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.test.KeyValue;

public class BasicProperties_boolean_testDriver extends BasicProperties_common_testDriver {

	public BasicProperties_boolean_testDriver() {

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, true, true);
		defValCADSEgValues = new KeyValue[] { kv11 };

		/* Execution : value at start up */
		KeyValue kv21 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, true, true);
		executionOldValues = new KeyValue[] { kv21 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, false, false);
		KeyValue kv32 = null; // null stands for leave unchanged
		executionNewValues = new KeyValue[] { kv31, kv32 };

		initializeTables();
	}

	@Override
	public String getTypeUnderTest() {
		return "boolean";
	}

	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.BOOLEAN;
	}
}
