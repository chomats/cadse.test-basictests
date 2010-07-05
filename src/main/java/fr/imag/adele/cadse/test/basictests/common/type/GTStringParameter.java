package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class GTStringParameter extends GTTypeParameter {

	@Override
	public ItemType getAttributeType() {
		return CadseGCST.STRING;
	}

	@Override
	public String getAttributeName() {
		return "String";
	}

	@Override
	public boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");
		boolean notEmpty = tp.getBoolean("notEmpty");

		if (isList) {
			if (kv.value == null) {
				return false;
			} else {
				return notEmpty ? !kv.getString().equals("") : true;
			}
		} else {
			return true;
		}
	}
}
