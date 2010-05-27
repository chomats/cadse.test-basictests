package fr.imag.adele.cadse.test.basictests.testdriver;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;

import java.util.ArrayList;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
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
	public final String cadseName = CADSEPrefix + getTestName() + getAttributeNameUnderTest();

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
	 * Gets the attribute type under test.
	 * 
	 * @return the attribute item type.
	 */
	protected abstract ItemType getAttributeTypeUnderTest();

	/**
	 * Returns name of the attribute under test. This value musn't be computed from the CADSEG constant, because it will
	 * be used for selecting the CADSE at startup.
	 * 
	 * @return the name of the attribute type under test
	 */
	protected abstract String getAttributeNameUnderTest();

	/**
	 * This method is used to set properties into the GTCollectionTestParameter.
	 */
	protected abstract void initializeTestParameters();

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 */
	public void testCreation() {
		ArrayList<Integer> exclude = new ArrayList<Integer>();
		testCreation(exclude);
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 * 
	 * @param exclude
	 *            the set of test to be excluded from creation.
	 */
	public void testCreation(ArrayList<Integer> exclude) {

		boolean createCadse = true;

		/* Creates the item types and attributes */
		for (int i = 0; i < getNbTest(); i++) {
			if (!exclude.contains(new Integer(i))) {
				testCreation(i, createCadse);
				createCadse = false;
			}
		}
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 * 
	 * @param i
	 *            sub test number
	 */
	public void testCreation(int i) {
		testCreation(i, true);
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 * 
	 * @param i
	 *            sub test number.
	 * @param createCadse
	 *            true if the CADSE should be created, false otherwise.
	 */
	public void testCreation(int i, boolean createCadse) {

		/* Creates the CADSE */
		if (createCadse) {
			createCadse();
		}

		/* Debug information */
		GTTestParameter tp = ctp.getTestParameters(i);
		System.out.println("CADSEg - running " + ctp.numberOfTests() + " tests.");
		System.out.println(tp.toString());

		/* Creates the item types and attributes */
		testCreation(tp);
	}

	/**
	 * Creates a specific the configuration.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	protected void testCreation(GTTestParameter tp) {

		/* Pre create */
		preCreate(tp);

		/* Item type and link creation */
		GTTreePath typePath = createTypes(tp);

		/* Attributes creation */
		boolean success = createAttributes(tp, typePath);

		boolean expected = attributeCreationSuccess(tp);
		assertEquals("testCreation error with #" + tp.testNumber, expected, success);

		/* Post create */
		if (success) {
			GTTreePath attrPath = typePath.concat(getAttributeName());
			postCreate(tp, typePath, attrPath);
		}
	}

	/**
	 * Performs actions before the item creation.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	protected void preCreate(GTTestParameter tp) {
	}

	/**
	 * Creates the types.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return a path to the type from which attributes will be created
	 */
	abstract protected GTTreePath createTypes(GTTestParameter tp);

	/**
	 * Creates the attributes.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param typePath
	 *            a path to the type on which attributes should be created
	 * @return true, if successful
	 */
	protected boolean createAttributes(GTTestParameter tp, GTTreePath typePath) {
		try {
			createBasicAttribute(typePath, getAttributeTypeUnderTest(), getAttributeName(), getCreationKeyValues(tp));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets the creation key values.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the list of KeyValues for creating the attribute.
	 */
	abstract protected KeyValue[] getCreationKeyValues(GTTestParameter tp);

	/**
	 * Returns true if the attribute can be created, false otherwise.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return true if the attribute can be created, false otherwise.
	 */
	protected boolean attributeCreationSuccess(GTTestParameter tp) {
		return true;
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
	protected void postCreate(GTTestParameter tp, GTTreePath itPath, GTTreePath attrPath) {
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
		return attrPrefix + getAttributeNameUnderTest();
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
	 * At runtime, test the configuration tp.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	protected abstract void testExecution(GTTestParameter tp);

	/**
	 * At startup, selects the CADSE created before.
	 */
	public void selectCadse() {
		selectCadses("Cadse Model.Workspace." + cadseName);
	}

	/**
	 * At runtime, test if the CADSE has the correct behavior. Excludes the tests with numbers in the list in parameter.
	 * 
	 * @param exclude
	 *            the exclude list
	 */
	public void testExecution(ArrayList<Integer> exclude) {
		for (int i = 0; i < getNbTest(); i++) {
			if (!exclude.contains(new Integer(i))) {
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
	 * At runtime, test if the CADSE has the correct behavior only with the subtest which number is given into
	 * parameter.
	 * 
	 * @param i
	 *            sub test number
	 */
	public void testExecution(int i) {
		GTTestParameter tp = ctp.getTestParameters(i);
		System.out.println("Execution - running " + ctp.numberOfTests() + " tests.");
		System.out.println(tp.toString());
		testExecution(tp);
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
