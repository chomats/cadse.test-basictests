package fr.imag.adele.cadse.test.basictests.testdriver;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;

import java.util.ArrayList;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

/**
 * The Class GTCommonTestDriver.
 */
public abstract class GTCommonTestDriver extends GTCadseTestCase {

	/** The set of tests parameters. */
	protected GTCollectionTestParameter ctp = new GTCollectionTestParameter();

	// ====== //
	// PREFIX //
	// ====== //

	/** CADSE prefix, used to compute CADSE name. */
	public final String CADSEPrefix = "CADSE";

	/** Item type prefix used to compute the item types names. */
	public final String itPrefix = "it";

	/** Attribute prefix used to compute the attributes names. */
	public final String attrPrefix = "attr";

	/** Instance prefix used to compute the names. */
	public final String instancePrefix = "instance";

	// ================ //
	// CADSE DEFINITION //
	// ================ //

	/** The CADSE name. */
	public final String cadseName = CADSEPrefix + getTestName() + getTypeNameUnderTest();

	/** A path to the CADSE definition. */
	public final GTTreePath cadseModel = new GTTreePath(cadseName);

	/** A path to the data model. */
	public final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

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
	 * Returns name of the type under test. This value musn't be computed from the CADSEG constant, because it will be
	 * used for selecting the CADSE at startup.
	 * 
	 * @return the name of the type under test
	 */
	protected abstract String getTypeNameUnderTest();

	/**
	 * Initialize test parameters.
	 */
	protected abstract void initializeTestParameters();

	/**
	 * Creates the configuration #i.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	public abstract void testCreation(GTTestParameter tp);

	/**
	 * At runtime, test the configuration #i.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	public abstract void testExecution(GTTestParameter tp);

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
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	protected String getAttributeName() {
		return attrPrefix + getTypeNameUnderTest();
	}

	/**
	 * Gets the nb test.
	 * 
	 * @return the nb test
	 */
	public int getNbTest() {
		return ctp.numberOfTests();
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 * 
	 * @param exclude
	 *            the exclude
	 */
	public void testCreation(ArrayList<Integer> exclude) {

		/* Creates the CADSE */
		createCadse();

		/* Creates the item types and attributes */
		for (int i = 0; i < getNbTest(); i++) {
			if (!exclude.contains(new Integer(i))) {
				GTTestParameter tp = ctp.getTestParameters(i);
				System.out.println("CADSEg - running " + ctp.numberOfTests() + " tests.");
				System.out.println(tp.toString());
				testCreation(tp);
			}
		}
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 */
	public void testCreation() {
		ArrayList<Integer> exclude = new ArrayList<Integer>();
		testCreation(exclude);
	}

	/**
	 * At runtime, test if the CADSE has the correct behavior. Excludes the tests with numbers in the list in parameter.
	 * 
	 * @param exclude
	 *            the exclude
	 */
	public void testExecution(ArrayList<Integer> exclude) {
		for (int i = 0; i < getNbTest(); i++) {
			if (!exclude.contains(new Integer(i))) {
				GTTestParameter tp = ctp.getTestParameters(i);
				System.out.println("Execution - running " + ctp.numberOfTests() + " tests.");
				System.out.println(tp.toString());
				testExecution(tp);
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
	 * At runtime, test if the CADSE has the correct behavior with only the subtest which number is given into
	 * parameter.
	 * 
	 * @param i
	 *            the sub test number
	 */
	public void testExecution(int i) {
		testExecution(ctp.getTestParameters(i));
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
