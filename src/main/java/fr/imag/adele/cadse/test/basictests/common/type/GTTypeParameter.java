package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public abstract class GTTypeParameter {

	public abstract ItemType getAttributeType();

	public abstract String getAttributeName();

	/**
	 * Checks if it should be possible to set a value.
	 * 
	 * @param tp
	 *            the tp
	 * @param value
	 *            the value
	 * @return true, if the value can be set
	 */
	public boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return kv != null && kv.value != null && !kv.value.toString().equals("");
		} else {
			return true;
		}
	}

	/**
	 * Checks if a value is valid, depending on the context.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param value
	 *            the value
	 * @return true, if is ok button is activated
	 */
	public boolean isValidValue(GTTestParameter tp, Object value) {

		boolean cbu = tp.getBoolean("cbu");
		boolean isList = tp.getBoolean("list");

		if (isList) {
			return true;
		} else {
			if (cbu == true && (value == null || value.toString().isEmpty())) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Returns true if the attribute can be created, false otherwise.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return true if the attribute can be created, false otherwise.
	 */
	public boolean isAttributeCreationSuccess(GTTestParameter tp) {
		return true;
	}

	public KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {
		if (kv.value instanceof String && kv.getString().isEmpty()) {
			return new KeyValue(kv, null);
		} else {
			return kv;
		}
	}

	/**
	 * Performs actions after the item creation.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param itPath
	 *            the item type path
	 * @param attrPath
	 *            the attribute path
	 */
	public void setAdvancedAttributeProperties(GTTestParameter tp, GTTreePath attrPath) {
	}
}
