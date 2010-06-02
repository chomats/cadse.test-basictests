package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.test.GTPreferences;

public class BasicProperties_Boolean_testDriver extends BasicProperties_Common_testDriver {

	/**
	 * Instantiates a new testDriver
	 */
	public BasicProperties_Boolean_testDriver() {
		initializeTestParameters();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	protected ItemType getAttributeTypeUnderTest() {
		return CadseGCST.BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	protected String getAttributeNameUnderTest() {
		return "Boolean";
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#initializeTestParameters()
	 */
	@Override
	protected void initializeTestParameters() {

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv, notSicpKv };
		KeyValue[] simpValues = { simpKv, notSimpKv };
		KeyValue[] cbuValues = { cbuKv, notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		/* Values given into CADSEg */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Boolean(true));
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { kv11, kv12 };

		/* Execution : new value */
		KeyValue kv31 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, new Boolean(false));
		KeyValue kv32 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue kv33 = null; // null stands for leave unchanged
		KeyValue[] newVal = new KeyValue[] { kv31, kv32, kv33 };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		ctp.addParameter("cbu", cbuValues);
		ctp.addParameter("list", listValues);
		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue", newVal);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#setNewGraphicalValue(fr
	 * .imag.adele.cadse.test.basictests.testdriver.GTTestParameter,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell)
	 */
	@Override
	protected boolean setNewGraphicalValue(GTTestParameter tp, GTCadseShell shell) {

		Boolean newValue = tp.getBoolean("newValue");
		boolean isList = tp.getBoolean("list");
		boolean cbu = tp.getBoolean("cbu");

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
						fail("It should be impossible to fill \"" + newValue + "\" for #" + tp.testNumber);
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
					throw new WidgetNotFoundException("It should be possible to fill \"" + newValue + "\" for #"
							+ tp.testNumber);
				}
			}
		}

		return true; // success
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#adaptedValue(fr.imag.adele
	 * .cadse.test.basictests.testdriver.GTTestParameter,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue)
	 */
	@Override
	protected KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {

		boolean cbu = tp.getBoolean("cbu");

		if (cbu == true && kv.value == null) {
			return new KeyValue("", false);
		}
		else {
			return kv;
		}
	}
}
