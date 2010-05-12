package fr.imag.adele.cadse.test.basictests.basicproperties;

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

import java.util.ArrayList;
import java.util.UUID;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class BasicProperties_common_testDriver extends GTCadseTestCase {

	// ====== //
	// PREFIX //
	// ====== //

	/** A prefix, to compute the attributes name */
	private final String attr_prefix = "attr_";
	/** A prefix, to compute the item type name */
	private final String it_prefix = "it_" + getTypeUnderTest();
	/** A prefix, to compute the instances name */
	private final String instance_prefix = "instance_";

	// ================ //
	// CADSE DEFINITION //
	// ================ //

	/** The CADSE definition name */
	protected final String cadse_name = "CADSE_BasicProperties_" + getTypeUnderTest();
	/** A path to the CADSE definition */
	protected final GTTreePath cadse_model = new GTTreePath(cadse_name);
	/** A path to the data model */
	protected final GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);

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
	protected KeyValue[] listValues = { listKv, notListKv };

	/** Set of values for the default value in CADSEg */
	protected KeyValue[] defValCADSEgValues;
	/** Set of values for the pre-set value at execution time */
	protected KeyValue[] executionOldValues;
	/** Set of values for the new value at execution time */
	protected KeyValue[] executionNewValues;

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

	/** The default value for all the instances */
	protected final ArrayList<KeyValue> defValCADSEgTab = new ArrayList<KeyValue>();
	/** The pre-set value at execution time for all the instances */
	protected final ArrayList<KeyValue> executionOldTab = new ArrayList<KeyValue>();
	/** The new value at execution time for all the instances */
	protected final ArrayList<KeyValue> executionNewTab = new ArrayList<KeyValue>();

	/**
	 * Performs table initializations
	 */
	public void initializeTables() {

		for (int j = 0; j < defValCADSEgValues.length; j++) {
			for (int k = 0; k < executionNewValues.length; k++) {
				for (KeyValue sicp : sicpValues) {
					for (KeyValue simp : simpValues) {
						for (KeyValue cbu : cbuValues) {
							for (KeyValue list : listValues) {
								defValCADSEgTab.add(defValCADSEgValues[j]);
								executionOldTab.add(executionOldValues[j]);
								executionNewTab.add(executionNewValues[k]);
								sicpTab.add(sicp);
								simpTab.add(simp);
								cbuTab.add(cbu);
								listTab.add(list);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Returns the type under test.
	 * 
	 * @return the type under test
	 */
	abstract public String getTypeUnderTest();

	/**
	 * Gets the item type under test.
	 * 
	 * @return the item type under test
	 */
	abstract public ItemType getItemTypeUnderTest();

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	public String getAttributeName() {
		return attr_prefix + getTypeUnderTest();
	}

	/**
	 * Gets the it name, for a given instance
	 * 
	 * @param i
	 *            the instance number
	 * @return the it name
	 */
	public String getItName(int i) {
		return it_prefix + i;
	}

	/**
	 * Gets the instance name.
	 * 
	 * @param i
	 *            the instance number
	 * @return the instance name
	 */
	public String getInstanceName(int i) {
		return instance_prefix + i;
	}

	public KeyValue getCorrectedDefVal(int i) {
		return executionOldTab.get(i);
	}

	/**
	 * Gets the initial visual value.
	 * 
	 * @param i
	 *            the instance number
	 * @return a string or a list of string
	 */
	public KeyValue getInitialValue(int i) {

		boolean isList = listTab.get(i).getBoolean();
		KeyValue defVal = getCorrectedDefVal(i);

		if (isList) {
			return new KeyValue(getAttributeName(), new String[] {}, new ArrayList<Object>());
		}
		else {
			return defVal;
		}
	}

	/**
	 * Sets the new graphical value.
	 * 
	 * @param i
	 *            the instance number
	 * @param shell
	 *            the shell
	 */
	public boolean setNewGraphicalValue(int i, GTCadseShell shell) {

		String newValue = (String) executionNewTab.get(i).visualValue;
		boolean isList = listTab.get(i).getBoolean();

		if (isList) {

			boolean expectedSuccess = !newValue.equals("");

			if (expectedSuccess) {
				shell.findCadseField(getAttributeName()).addValue(newValue);
			}
			else {
				try {
					shell.findCadseField(getAttributeName()).addValue(newValue, GTPreferences.FAILING_ASSERT_TIMEOUT);
					fail("It should be impossible to fill \"" + newValue + "\" for #" + i);
				}
				catch (Exception e) {
					// success
				}
			}
		}
		else {
			shell.findCadseField(getAttributeName()).typeText(newValue);
		}

		return true; // success
	}

	/**
	 * Gets the final model value.
	 * 
	 * @param i
	 *            the instance number
	 * @return the final model value
	 */
	public Object getFinalModelValue(int i) {

		boolean fieldInCP = sicpTab.get(i).getBoolean();
		boolean isList = listTab.get(i).getBoolean();
		KeyValue newKv = executionNewTab.get(i);
		Object newModelValue = (newKv == null) ? null : newKv.modelValue;

		if (isList) { // def val is ignored with list attributes
			if (fieldInCP && newKv != null && newModelValue != null) {
				return new Object[] { newModelValue };
			}
			else {
				return new Object[] {};
			}
		}
		else {
			if (fieldInCP) {
				if (newKv != null) { // in case graphic = "" and model = null
					return newModelValue;
				}
				else {
					return getCorrectedDefVal(i).modelValue;
				}
			}
			else {
				return getCorrectedDefVal(i).modelValue;
			}
		}
	}

	/**
	 * Gets the final graphical value.
	 * 
	 * @param i
	 *            the instance number
	 * @return the final graphical value
	 */
	public Object getFinalGraphicalValue(int i) {

		boolean fieldInCP = sicpTab.get(i).getBoolean();
		boolean isList = listTab.get(i).getBoolean();

		KeyValue newKv = executionNewTab.get(i);
		Object newGraphicalValue = (newKv == null) ? null : newKv.visualValue;

		if (fieldInCP) {
			if (isList) { // default value is ignored with list
				if (newKv != null && newGraphicalValue != null && !newGraphicalValue.toString().equals("")) {
					return new String[] { newGraphicalValue.toString() };
				}
				else {
					return new String[] {};
				}
			}
			else {
				if (newKv != null) {
					return newGraphicalValue;
				}
				else {
					return getCorrectedDefVal(i).visualValue;
				}
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
		}
	}

	/**
	 * Checks if is ok button is activated.
	 * 
	 * @param i
	 *            the instance number
	 * @return true, if is ok button is activated
	 */
	public boolean isOkButtonActivated(int i) {

		boolean cbu = cbuTab.get(i).getBoolean();
		boolean isList = listTab.get(i).getBoolean();

		if (isList) {
			return true;
		}
		else {
			return cbu ? (getFinalModelValue(i) != null) : true;
		}
	}

	/**
	 * Gets the attribute graphical value in the property view.
	 * 
	 * @param i
	 *            the instance number
	 * @return the attribute graphical value in the property view.
	 */
	public Object getPropertiesGraphicalValue(int i) {

		boolean fieldInCP = sicpTab.get(i).getBoolean();
		boolean fieldInMP = simpTab.get(i).getBoolean();
		boolean isList = listTab.get(i).getBoolean();

		if (fieldInMP) {
			if (isList) {
				if (fieldInCP) {
					return getFinalGraphicalValue(i);
				}
				else {
					return new String[] {}; // the default value is ignored for list attributes
				}
			}
			else {
				if (fieldInCP) {
					return getFinalGraphicalValue(i);
				}
				else {
					return getCorrectedDefVal(i).visualValue;
				}
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
		}
	}

	/**
	 * Gets the attribute model value in the property view.
	 * 
	 * @param i
	 *            the instance number
	 * @return the attribute model value in the property view.
	 */
	public Object getPropertiesModelValue(int i) {

		boolean fieldInCP = sicpTab.get(i).getBoolean();
		boolean isList = listTab.get(i).getBoolean();

		if (fieldInCP) {
			return getFinalModelValue(i);
		}
		else {
			if (isList) {
				return new Object[] {}; // default value is ignored with list attributes
			}
			else {
				return getCorrectedDefVal(i).modelValue;
			}
		}
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 */
	public void testCreation() {

		/* Creates the CADSE */
		testCreateCadse();

		/* Creates the item types and attributes */
		for (int i = 0; i < defValCADSEgTab.size(); i++) {
			testCreation(i);
		}
	}

	/**
	 * Creates the CADSE.
	 */
	public void testCreateCadse() {
		createCadseDefinition(cadse_name, "model." + cadse_name);
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

		/* Item type creation */
		createItemType(data_model, getItName(i), notAbstractKv, rootKv);

		/* Attribute creation */
		GTTreePath it_path = data_model.concat(getItName(i));

		boolean success = true;
		try {
			createBasicAttribute(it_path, getItemTypeUnderTest(), getAttributeName(), getCreationKeyValues(i));

			/* Assert item has been created */
			GTTreePath attr_path = it_path.concat(getAttributeName());
			workspaceView.selectNode(attr_path);

			/* Post create */
			postCreate(i, it_path, attr_path);
		}
		catch (Exception e) {
			success = false;
		}

		boolean expected = attributeCreationSuccess(i);
		assertEquals("testCreation error with #" + i, expected, success);
	}

	/**
	 * Returns true if the attribute can be created, false otherwise.
	 * 
	 * @param i
	 * @return true if the attribute can be created, false otherwise.
	 */
	protected boolean attributeCreationSuccess(int i) {
		return true;
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
	 * Gets key values to fill creation page.
	 * 
	 * @param i
	 *            the i
	 * @return the creation key values
	 */
	protected KeyValue[] getCreationKeyValues(int i) {

		if (defValCADSEgTab.get(i) != null) {
			return new KeyValue[] { defValCADSEgTab.get(i), sicpTab.get(i), simpTab.get(i), cbuTab.get(i),
					listTab.get(i) };
		}
		else {
			return new KeyValue[] { sicpTab.get(i), simpTab.get(i), cbuTab.get(i), listTab.get(i) };
		}
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

		if (!attributeCreationSuccess(i)) {
			System.out.println("Attribute #" + i + " haven't been created. Test skiped.");
		}
		else {
			boolean fieldInCP = sicpTab.get(i).getBoolean();
			boolean fieldInMP = simpTab.get(i).getBoolean();
			KeyValue newValue = executionNewTab.get(i);

			/* ============= */
			/* Creation page */
			/* ============= */

			workspaceView.contextMenuNew(getItName(i)).click();
			GTCadseShell shell = new GTCadseShell(getItName(i));

			// is field present
			boolean isFieldPresent = shell.fieldExists(getAttributeName());
			assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInCP, isFieldPresent);

			// initial value
			if (fieldInCP) {
				KeyValue expected = getInitialValue(i);

				Object actual_visual = shell.findCadseField(getAttributeName()).getValue();
				assertEqualsListValues("Initial visual value error for #" + i, expected.visualValue, actual_visual);

				Object actual_model = shell.findCadseField(getAttributeName()).getModelValue();
				assertEqualsListValues("Initial model value error for #" + i, expected.modelValue, actual_model);
			}

			// New Attribute Value
			if (fieldInCP && newValue != null) {
				if (!setNewGraphicalValue(i, shell)) {
					// setting the new value has failed, as expected
					shell.close(GTEclipseConstants.CANCEL_BUTTON);
					return;
				}
			}

			// name + CHANGES FOCUS!!!
			shell.findCadseFieldName().typeText(getInstanceName(i));

			// final visual value
			if (fieldInCP && newValue != null) {
				Object expected = getFinalGraphicalValue(i);
				Object actual = shell.findCadseField(getAttributeName()).getValue();
				assertEqualsListValues("Error with final visual value for #" + i, expected, actual);
			}

			// final model value (okButtonActivated is important! if the value is not correct, the previous correct
			// model value (default value) is locked even if the field displays another value.
			if (fieldInCP && newValue != null && isOkButtonActivated(i)) {
				Object expected = getFinalModelValue(i);
				Object actual = shell.findCadseField(getAttributeName()).getModelValue();
				assertEqualsListValues("Final model value error for #" + i, expected, actual);
			}

			// Waits until refresh
			GTPreferences.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);

			// Gets the UUID
			UUID id = shell.findCadseFieldName().getRunningField().getSwtUiplatform().getItem().getId();

			// Closes shell
			if (isOkButtonActivated(i)) {
				shell.close();
			}
			else {
				try {
					shell.close(GTPreferences.FAILING_ASSERT_TIMEOUT);
					fail("OK button is activated whereas it shouldn't for #" + i);
				}
				catch (Exception e) {
					// SUCCESS
				}
				return;
			}

			/* ============== */
			/* Model checking */
			/* ============== */

			Item item = CadseCore.getLogicalWorkspace().getItem(id);
			assertNotNull(item);
			IAttributeType<?> attr = item.getType().getAttributeType(getAttributeName());
			assertNotNull(attr);
			Object model_actual = item.getAttribute(attr);
			Object model_expected = getFinalModelValue(i);
			assertEqualsListValues("Error in model checking for #" + i, model_expected, model_actual);

			/* ============= */
			/* Property page */
			/* ============= */

			workspaceView.selectNode(getInstanceName(i));
			propertiesView.showTab(getItName(i));

			// Name
			assertEquals(getInstanceName(i), propertiesView.findCadseFieldName().getText());

			// Field value
			if (fieldInMP) {
				Object graphicalExpected = getPropertiesGraphicalValue(i);
				Object graphicalActual = propertiesView.findCadseField(getAttributeName()).getValue();
				assertEqualsListValues("Error in graphical modification page value for #" + i, graphicalExpected,
						graphicalActual);

				Object modelExpected = getPropertiesModelValue(i);
				Object modelActual = propertiesView.findCadseField(getAttributeName()).getModelValue();
				assertEqualsListValues("Error in model modification page value for #" + i, modelExpected, modelActual);
			}
		}
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
