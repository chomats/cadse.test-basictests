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

	@Override
	public boolean isValidValue(GTTestParameter tp, Object value) {

		boolean isList = tp.getBoolean("list");
		boolean cbu = tp.getBoolean("cbu");
		boolean notEmpty = tp.getBoolean("notEmpty");

		if (isList) {
			return true;
		} else {

			// Checking cbu constraint
			if (cbu && value == null) {
				return false;
			}

			// Checking not empty constraint
			if (notEmpty && value != null && value.toString().equals("")) {
				return false;
			}

			return true;
		}
	}

	@Override
	public KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {
		return kv;
	}
}
