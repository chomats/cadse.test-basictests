package fr.imag.adele.cadse.test.basictests.testdriver;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;

import java.util.ArrayList;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public abstract class GTCommonTestDriver extends GTCadseTestCase {

	// ================ //
	// CADSE DEFINITION //
	// ================ //

	/** The CADSE definition name */
	public final String cadseName = "CADSE_" + getTestName() + "_" + getTypeUnderTest();

	/** A path to the CADSE definition */
	public final GTTreePath cadseModel = new GTTreePath(cadseName);

	/** A path to the data model */
	public final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

	// ====== //
	// PREFIX //
	// ====== //

	/** Item type prefix used to compute the names */
	protected final String itPrefix = "it";

	/** Attribute prefix used to compute the names */
	protected final String attrPrefix = "attr_";

	/** Instance prefix used to compute the names */
	protected final String instancePrefix = "instance";

	// ========================== //
	// PROPERTIES POSSIBLE VALUES //
	// ========================== //

	/** Set of values for the Show in Creation Page property */
	protected KeyValue[] sicpValues = { sicpKv, notSicpKv };

	/** Set of values for the Show in Modification Page property */
	protected KeyValue[] simpValues = { simpKv, notSimpKv };

	/** Set of values for the Cannot be undefined property */
	protected KeyValue[] cbuValues = { cbuKv, notCbuKv };

	/** Set of values for the List property */
	protected KeyValue[] listValues = { notListKv, listKv };

	// ======================================================= //
	// TABLES USED FOR PROPERTIES VALUES FOR ALL THE INSTANCES //
	// ======================================================= //

	/** The Show in Creation Page property value for all the instances */
	protected final ArrayList<KeyValue> sicpTab = new ArrayList<KeyValue>();

	/** The Show in Modification Page property value for all the instances */
	protected final ArrayList<KeyValue> simpTab = new ArrayList<KeyValue>();

	/** The Cannot be undefined property value for all the instances */
	protected final ArrayList<KeyValue> cbuTab = new ArrayList<KeyValue>();

	/** The List property value for all the instances */
	protected final ArrayList<KeyValue> listTab = new ArrayList<KeyValue>();

	public void selectCadse() {
		selectCadses("Cadse Model.Workspace." + cadseName);
	}

	/**
	 * Gets the item type under test.
	 * 
	 * @return the item type under test
	 */
	protected abstract String getTestName();

	/**
	 * Gets the item type under test.
	 * 
	 * @return the item type under test
	 */
	protected abstract ItemType getItemTypeUnderTest();

	/**
	 * Returns name of the type under test.
	 * 
	 * @return the type under test
	 */
	protected String getTypeUnderTest() {
		return getItemTypeUnderTest().getName();
	}

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	protected String getAttributeName() {
		return attrPrefix + getTypeUnderTest();
	}

	/**
	 * Assert visual values are String or String[] and are equals.
	 * 
	 * @param message
	 *            a failing message
	 * @param expected
	 * @param actual
	 */
	protected void assertEqualsListValues(String message, Object expected, Object actual) {

		ArrayList<Object> tab1 = generateArray(expected);
		ArrayList<Object> tab2 = generateArray(actual);

		if (expected == null && actual == null) {
			return;
		}

		if (tab1.size() != tab2.size()) {
			fail(message);
		}

		for (int i = 0; i < tab1.size(); i++) {
			assertEquals(tab1.get(i), tab2.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Object> generateArray(Object object) {

		ArrayList<Object> tab = new ArrayList<Object>();

		if (object == null) {
			return tab;
		}

		if (object instanceof ArrayList<?>) {
			for (Object o : (ArrayList) object) {
				if (o instanceof Enum) {
					tab.add(o.toString());
				}
				else {
					tab.add(o);
				}
			}
		}
		else if (object instanceof Object[]) {
			Object[] t = (Object[]) object;
			for (int i = 0; i < t.length; i++) {
				tab.add(t[i]);
			}
		}
		else if (object instanceof Enum) {
			tab.add(object.toString());
		}
		else {
			tab.add(object);
		}
		return tab;
	}
}
