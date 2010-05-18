package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.test.GTPreferences;

public class BasicProperties_Boolean_testDriver extends BasicProperties_Common_testDriver {

	/**
	 * Instantiates a new basic properties_ boolean_test driver.
	 */
	public BasicProperties_Boolean_testDriver() {

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

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "Boolean";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#setNewGraphicalValue(int,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell)
	 */
	@Override
	protected boolean setNewGraphicalValue(int i, GTCadseShell shell) {

		Boolean newValue = (Boolean) executionNewTab.get(i).visualValue;
		boolean isList = listTab.get(i).getBoolean();
		boolean cbu = cbuTab.get(i).getBoolean();

		if (isList) {

			if (newValue != null) {
				boolean expectedSuccess = !newValue.equals("");

				if (expectedSuccess) {
					shell.findCadseField(getAttributeName()).addValue(newValue.toString());
				}
				else {
					try {
						shell.findCadseField(getAttributeName()).addValue(newValue.toString(),
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
				shell.findCadseField(getAttributeName()).check(newValue);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getCorrectedDefVal(int)
	 */
	@Override
	protected KeyValue getCorrectedDefVal(int i) {

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
