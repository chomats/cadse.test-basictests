package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.graphictests.test.GTPreferences;

public class BasicProperties_boolean_testDriver extends BasicProperties_common_testDriver {

	public BasicProperties_boolean_testDriver() {

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, true, true);
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null, null);
		defValCADSEgValues = new KeyValue[] { kv11, kv12 };

		/* Execution : value at start up */
		KeyValue kv21 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, true, true);
		KeyValue kv22 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null, null);
		executionOldValues = new KeyValue[] { kv21, kv22 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, false, false);
		KeyValue kv32 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null, null);
		KeyValue kv33 = null; // null stands for leave unchanged
		executionNewValues = new KeyValue[] { kv31, kv32, kv33 };

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

	/**
	 * Sets the new graphical value.
	 * 
	 * @param i
	 *            the instance number
	 * @param shell
	 *            the shell
	 */
	@Override
	public boolean setNewGraphicalValue(int i, GTShell shell) {

		Boolean newValue = (Boolean) executionNewTab.get(i).graphicalValue;
		boolean isList = listTab.get(i).getBoolean();
		boolean cbu = cbuTab.get(i).getBoolean();

		if (isList) {

			if (newValue != null) {
				boolean expectedSuccess = !newValue.equals("");

				if (expectedSuccess) {
					findCadseField(shell, getAttributeName()).addValue(newValue.toString());
				}
				else {
					try {
						findCadseField(shell, getAttributeName()).addValue(newValue.toString(),
								GTPreferences.FAILING_ASSERT_TIMEOUT);
						fail("It should be impossible to fill \"" + newValue + "\" for #" + i);
					}
					catch (Exception e) {
						// success
					}
				}
			}
		}
		else {
			boolean ok;

			try {
				findCadseField(shell, getAttributeName()).check(newValue);
				ok = true;
			}
			catch (Exception e) {
				ok = false;
			}

			if (ok == false) {
				if (newValue == null && cbu == true) {
					return false;
				}
				else {
					throw new WidgetNotFoundException("It should be possible to fill \"" + newValue + "\" for #" + i);
				}
			}
		}

		return true; // success
	}

	@Override
	public KeyValue getCorrectedDefVal(int i) {

		boolean cbu = cbuTab.get(i).getBoolean();
		KeyValue defVal = executionOldTab.get(i);

		if (cbu == true && defVal.modelValue == null) {
			return new KeyValue("", false, false);
		}
		else {
			return defVal;
		}
	}
}
