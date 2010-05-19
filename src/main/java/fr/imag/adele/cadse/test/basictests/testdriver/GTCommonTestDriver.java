package fr.imag.adele.cadse.test.basictests.testdriver;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;

import java.util.ArrayList;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public abstract class GTCommonTestDriver extends GTCadseTestCase {

	// ====== //
	// PREFIX //
	// ====== //

	/** CADSE prefix, used to compute CADSE name */
	public final String CADSEPrefix = "CADSE";

	/** Item type prefix used to compute the item types names */
	public final String itPrefix = "it";

	/** Attribute prefix used to compute the attributes names */
	public final String attrPrefix = "attr";

	/** Instance prefix used to compute the names */
	public final String instancePrefix = "instance";

	// ================ //
	// CADSE DEFINITION //
	// ================ //

	/** The CADSE name */
	public final String cadseName = CADSEPrefix + getTestName() + getTypeNameUnderTest();

	/** A path to the CADSE definition */
	public final GTTreePath cadseModel = new GTTreePath(cadseName);

	/** A path to the data model */
	public final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

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

	/**
	 * Gets the name of the test. This is a general name, without reference to any type, such as boolean.
	 * 
	 * @return the name of the test.
	 */
	protected abstract String getTestName();

	/**
	 * Gets the item type under test.
	 * 
	 * @return the item type under test
	 */
	protected abstract ItemType getItemTypeUnderTest();

	/**
	 * Creates the configuration #i.
	 * 
	 * @param configuration
	 *            number
	 */
	public abstract void testCreation(int i);

	/**
	 * At runtime, test the configuration #i.
	 * 
	 * @param configuration
	 *            number
	 */
	public abstract void testExecution(int i);

	/**
	 * At startup, selects the CADSE created before.
	 */
	public void selectCadse() {
		selectCadses("Cadse Model.Workspace." + cadseName);
	}

	/**
	 * Creates the CADSE.
	 */
	private void createCadse() {
		createCadseDefinition(cadseName, "model." + cadseName);
	}

	/**
	 * Returns name of the type under test. This value musn't be computed from the CADSEG constant, because it will be
	 * used for selecting the CADSE at startup.
	 * 
	 * @return the name of the type under test
	 */
	protected abstract String getTypeNameUnderTest();

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	protected String getAttributeName() {
		return attrPrefix + getTypeNameUnderTest();
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 */
	public void testCreation() {

		/* Creates the CADSE */
		createCadse();

		/* Creates the item types and attributes */
		for (int i = 0; i < simpTab.size(); i++) {
			testCreation(i);
		}
	}

	/**
	 * At runtime, test if the CADSE has the correct behavior. Excludes the tests with numbers in the list in parameter.
	 * 
	 * @param exclude
	 */
	public void testExecution(ArrayList<Integer> exclude) {
		for (int i = 0; i < simpTab.size(); i++) {
			if (!exclude.contains(new Integer(i))) {
				System.out.println("Starting execution #" + i);
				testExecution(i);
			}
		}
	}

	/**
	 * At runtime, test if the CADSE has the correct behavior.
	 */
	public void testExecution() {
		ArrayList<Integer> exclude = new ArrayList<Integer>();
		testExecution(exclude);
	}

	/**
	 * Assert values are String or String[] and are equals.
	 * 
	 * @param message
	 *            a failing message
	 * @param expected
	 *            the expected list
	 * @param actual
	 *            the actual list
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
			Object val1 = tab1.get(i);
			Object val2 = tab2.get(i);

			if (val1 instanceof String || val2 instanceof String) {
				val1 = val1.toString();
				val2 = val2.toString();
			}

			assertEquals(val1, val2);
		}
	}

	/**
	 * Convert the object into parameter to an Array.
	 * 
	 * @param object
	 *            the source object
	 * @return an array list
	 */
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
