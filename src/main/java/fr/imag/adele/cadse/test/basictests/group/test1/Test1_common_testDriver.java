package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.cbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class Test1_common_testDriver extends GTCadseTestCase {

	// ====== //
	// PREFIX //
	// ====== //

	/** Item type prefix used to compute the names */
	private final String itPrefix = "it";

	/** Attribute prefix used to compute the names */
	private final String attrPrefix = "attr_";

	/** Link prefix used to compute the names */
	private final String linkName = "link";

	/** Instance prefix used to compute the names */
	private final String instancePrefix = "instance";

	// ====== //
	// VALUES //
	// ====== //

	protected KeyValue defaultValue;
	protected KeyValue newValue1;
	protected KeyValue newValue2;
	protected KeyValue newValue3;

	// ================ //
	// CADSE DEFINITION //
	// ================ //

	/** The CADSE definition name */
	private final String cadseName = "CADSE_group_test1_" + getTypeUnderTest();

	/** A path to the CADSE definition */
	private final GTTreePath cadseModel = new GTTreePath(cadseName);

	/** A path to the data model */
	private final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

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
	 * Performs table initializations
	 */
	protected void initializeTables() {
		for (KeyValue sicp : sicpValues) {
			for (KeyValue simp : simpValues) {
				for (KeyValue cbu : cbuValues) {
					for (KeyValue list : listValues) {
						sicpTab.add(sicp);
						simpTab.add(simp);
						cbuTab.add(cbu);
						listTab.add(list);
					}
				}
			}
		}
	}

	/**
	 * Gets the item type under test.
	 * 
	 * @return the item type under test
	 */
	abstract public ItemType getItemTypeUnderTest();

	/**
	 * Returns name of the type under test.
	 * 
	 * @return the type under test
	 */
	public String getTypeUnderTest() {
		return getItemTypeUnderTest().getName();
	}

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	public String getAttributeName(int i) {
		return attrPrefix + getTypeUnderTest();
	}

	/**
	 * Gets the it name, for a given instance
	 * 
	 * @param i
	 *            the instance number
	 * @return the it name
	 */
	public String getItSrcName(int i) {
		return itPrefix + "Src" + i;
	}

	/**
	 * Gets the link name.
	 * 
	 * @param i
	 *            the instance number
	 * @return the link name
	 */
	public String getLinkName(int i) {
		return linkName;
	}

	/**
	 * Gets the it name, for a given instance
	 * 
	 * @param i
	 *            the instance number
	 * @return the it name
	 */
	public String getItDstName(int i) {
		return itPrefix + "Dst" + i;
	}

	/**
	 * Gets the instance name.
	 * 
	 * @param i
	 *            the instance number
	 * @return the instance name
	 */
	public String getInstanceSrcName(int i) {
		return instancePrefix + "Src" + i;
	}

	/**
	 * Gets the instance name.
	 * 
	 * @param i
	 *            the instance number
	 * @return the instance name
	 */
	public String getInstanceDstName(int i) {
		return instancePrefix + "Dst" + i;
	}

	/**
	 * Gets the default value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the default value
	 */
	public KeyValue getDefaultValue(int i) {
		return new KeyValue(getAttributeName(i), defaultValue.visualValue, defaultValue.modelValue);
	}

	/**
	 * Gets the first new value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the first new value
	 */
	public KeyValue getNewValue1(int i) {

		if (sicpTab.get(i).getBoolean()) {
			return new KeyValue(getAttributeName(i), newValue1.visualValue, newValue1.modelValue);
		}
		else {
			return getDefaultValue(i);
		}
	}

	/**
	 * Gets the second new value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the second new value
	 */
	public KeyValue getNewValue2(int i) {

		if (simpTab.get(i).getBoolean()) {
			return new KeyValue(getAttributeName(i), newValue2.visualValue, newValue2.modelValue);
		}
		else {
			return getNewValue1(i);
		}
	}

	/**
	 * Gets the third new value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the third new value
	 */
	public KeyValue getNewValue3(int i) {
		return null;
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 */
	public void testCreation() {

		/* Creates the CADSE */
		testCreateCadse();

		/* Creates the item types and attributes */
		for (int i = 0; i < sicpTab.size(); i++) {
			testCreation(i);
		}
	}

	/**
	 * Creates the CADSE.
	 */
	public void testCreateCadse() {
		createCadseDefinition(cadseName, "model." + cadseName);
	}

	/**
	 * Creates the attribute number i.
	 * 
	 * @param i
	 *            the attribute number to be created.
	 */
	public void testCreation(int i) {

		System.out.println("Starting CADSEg #" + i);

		/* Pre create */
		preCreate(i);

		/* Item type and link creation */
		GTTreePath src = dataModel.concat(getItSrcName(i));
		GTTreePath dst = dataModel.concat(getItDstName(i));

		createItemType(dataModel, src.getDestinationName(), notAbstractKv, rootKv);
		createItemType(dataModel, dst.getDestinationName(), notAbstractKv, rootKv);
		createLinkType(getLinkName(i), src, dst, null, null, KeyValue.groupKv);

		/* Attribute creation */
		createBasicAttribute(src, getItemTypeUnderTest(), getAttributeName(i), getAttributeCreationKeyValues(i));

		/* Post create */
		GTTreePath attr = src.concat(getAttributeName(i));
		postCreate(i, src, attr);
	}

	/**
	 * Gets a list of key values used to create the attribute.
	 * 
	 * @param i
	 *            the i
	 * @return the attribute creation key values
	 */
	public KeyValue[] getAttributeCreationKeyValues(int i) {

		KeyValue dv = getDefaultValue(i);
		KeyValue kv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, dv.visualValue, dv.modelValue);

		return new KeyValue[] { sicpTab.get(i), simpTab.get(i), cbuTab.get(i), listTab.get(i), kv };
	}

	/**
	 * Performs actions before the item creation.
	 * 
	 * @param i
	 *            the number of the item to be created
	 */
	protected void preCreate(int i) {
	}

	/**
	 * Performs actions after the item creation.
	 * 
	 * @param i
	 *            the number of the item to be created
	 * @param it_path
	 *            the item type path
	 * @param attr_path
	 *            the attribute path
	 */
	protected void postCreate(int i, GTTreePath it_path, GTTreePath attr_path) {
	}

	/**
	 * At runtime, test if the CADSE has the correct behavior. Excludes the tests with numbers in the list in parameter.
	 * 
	 * @param exclude
	 */
	public void testExecution(ArrayList<Integer> exclude) {
		for (int i = 0; i < sicpTab.size(); i++) {
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
	 * At runtime, test if an item type has the correct behavior.
	 */
	public void testExecution(int i) {

		boolean fieldInCP = sicpTab.get(i).getBoolean();
		boolean fieldInMP = simpTab.get(i).getBoolean();

		/* ============= */
		/* Head creation */
		/* ============= */

		workspaceView.contextMenuNew(getItSrcName(i)).click();
		GTCadseShell shell = new GTCadseShell(getItSrcName(i));

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeName(i));
		assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInCP, isFieldPresent);

		// initial value
		if (fieldInCP) {
			KeyValue expected = getDefaultValue(i);

			Object actualVisual = shell.findCadseField(getAttributeName(i)).getValue();
			assertEqualsListValues("Initial visual value error for #" + i, expected.visualValue, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName(i)).getModelValue();
			assertEqualsListValues("Initial model value error for #" + i, expected.modelValue, actualModel);
		}

		// New Value 1
		if (fieldInCP) {
			shell.setValue(getNewValue1(i));
		}

		// name + CHANGES FOCUS!!!
		shell.findCadseFieldName().typeText(getInstanceSrcName(i));

		// final visual value
		if (fieldInCP) {
			KeyValue expected = getNewValue1(i);

			Object actualVisual = shell.findCadseField(getAttributeName(i)).getValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName(i)).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualModel);
		}

		// Waits until refresh
		GTPreferences.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);

		shell.close();

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(getInstanceSrcName(i));
		propertiesView.showTab(getItSrcName(i));

		// Name
		assertEquals(getInstanceSrcName(i), propertiesView.findCadseFieldName().getText());

		// Field value checking
		if (fieldInMP) {
			KeyValue expected = getNewValue1(i);

			Object actualVisual = propertiesView.findCadseField(getAttributeName(i)).getValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName(i)).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualModel);
		}

		// Field value modification
		if (fieldInMP) {
			propertiesView.setValue(getNewValue2(i));
		}

		/* =============== */
		/* Member creation */
		/* =============== */

		System.out.println("Create a member!!!");
		while (true) {
			;
		}

		/* ======================= */
		/* Head value modification */
		/* ======================= */

		/* ================================= */
		/* Member modification page checking */
		/* ================================= */

	}

	/**
	 * Assert visual values are String or String[] and are equals.
	 * 
	 * @param message
	 *            a failing message
	 * @param expected
	 * @param actual
	 */
	private void assertEqualsListValues(String message, Object expected, Object actual) {

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
