package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class GTBooleanParameter extends GTTypeParameter {

	@Override
	public ItemType getAttributeType() {
		return CadseGCST.BOOLEAN;
	}

	@Override
	public String getAttributeName() {
		return "Boolean";
	}

	@Override
	public boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");
		boolean cbu = tp.getBoolean("cbu");

		if (isList) {
			return kv != null && kv.value != null && !kv.value.toString().equals("");
		} else {
			if (cbu && kv != null && kv.value == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public boolean isAttributeCreationSuccess(GTTestParameter tp) {
		boolean cbu = tp.getBoolean("cbu");
		Boolean defVal = tp.getBoolean("defVal");
		boolean isList = tp.getBoolean("list");

		if (defVal == null && cbu == true && !isList) {
			return false;
		} else {
			return true;
		}
	}
}
