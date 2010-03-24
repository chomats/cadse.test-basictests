package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.failingAssertTimeout;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTTestCase;

public abstract class BasicProperties_common_testDriver extends GTTestCase {

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
	protected boolean[] sicpValues = { true, false };
	/** Set of values for the Show in Modification Page property */
	protected boolean[] simpValues = { true, false };
	/** Set of values for the Cannot be undefined property */
	protected boolean[] cbuValues = { true, false };
	/** Set of values for the List property */
	protected boolean[] listValues = { true, false };

	/** Set of values for the displayed default value */
	protected String[] defValGraphicValues;
	/** Set of values for the modeled default value */
	protected Object[] defValModelValues;
	/** Set of values for the displayed new value */
	protected String[] newValGraphicValues;
	/** Set of values for the modeled new value */
	protected Object[] newValModelValues;

	// ======================================================= //
	// TABLES USED FOR PROPERTIES VALUES FOR ALL THE INSTANCES //
	// ======================================================= //

	/** The Show in Creation Page property value for all the instances */
	protected final ArrayList<Boolean> sicpTab = new ArrayList<Boolean>();
	/** The Show in Modification Page property value for all the instances */
	protected final ArrayList<Boolean> simpTab = new ArrayList<Boolean>();
	/** The Cannot be undefined property value for all the instances */
	protected final ArrayList<Boolean> cbuTab = new ArrayList<Boolean>();
	/** The List property value for all the instances */
	protected final ArrayList<Boolean> listTab = new ArrayList<Boolean>();
	/** The displayed default value for all the instances */

	protected final ArrayList<String> defValGraphicTab = new ArrayList<String>();
	/** The model default value for all the instances */
	protected final ArrayList<Object> defValModelTab = new ArrayList<Object>();
	/** The displayed new value for all the instances */
	protected final ArrayList<String> newValGraphicTab = new ArrayList<String>();
	/** The model new value for all the instances */
	protected final ArrayList<Object> newValModelTab = new ArrayList<Object>();

	/**
	 * Performs table initializations
	 */
	public void initializeTables() {

		for (int j = 0; j < defValGraphicValues.length; j++) {
			for (int k = 0; k < newValGraphicValues.length; k++) {
				for (boolean sicp : sicpValues) {
					for (boolean simp : simpValues) {
						for (boolean cbu : cbuValues) {
							for (boolean list : listValues) {
								defValModelTab.add(defValModelValues[j]);
								defValGraphicTab.add(defValGraphicValues[j]);
								newValModelTab.add(newValModelValues[k]);
								newValGraphicTab.add(newValGraphicValues[k]);
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

	/**
	 * Gets the initial visual value.
	 * 
	 * @param i
	 *            the instance number
	 * @return a string or a list of string
	 */
	public Object getInitialVisualValue(int i) {
		if (sicpTab.get(i)) {
			if (listTab.get(i)) {
				return new String[] {};
			}
			else {
				return defValGraphicTab.get(i);
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
		}
	}

	/**
	 * Gets the initial model value.
	 * 
	 * @param i
	 *            the instance number
	 * @return the initial model value
	 */
	public Object getInitialModelValue(int i) {
		if (listTab.get(i)) {
			return new ArrayList<Object>();
		}
		else {
			return defValModelTab.get(i);
		}
	}

	/**
	 * Gets the final model value.
	 * 
	 * @param i
	 *            the instance number
	 * @return the final model value
	 */
	public Object getFinalModelValue(int i) {
		if (listTab.get(i)) { // def val is ignored with list attributes
			if (newValModelTab.get(i) != null) {
				return new Object[] { newValModelTab.get(i) };
			}
			else {
				return new Object[] {};
			}
		}
		else {
			if (sicpTab.get(i)) {
				if (newValGraphicTab.get(i) != null) { // in case graphic = "" and model = null
					return newValModelTab.get(i);
				}
				else {
					return defValModelTab.get(i);
				}
			}
			else {
				return defValModelTab.get(i);
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
		if (sicpTab.get(i)) {
			if (listTab.get(i)) { // default value is ignored with list
				if (newValGraphicTab.get(i) != null && !newValGraphicTab.get(i).equals("")) {
					return new String[] { newValGraphicTab.get(i) };
				}
				else {
					return new String[] {};
				}
			}
			else {
				if (newValGraphicTab.get(i) != null) {
					return newValGraphicTab.get(i);
				}
				else {
					return defValGraphicTab.get(i); // no new value
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
		if (listTab.get(i)) {
			return true;
		}
		else {
			return cbuTab.get(i) ? (getFinalModelValue(i) != null) : true;
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

		if (simpTab.get(i)) {
			if (listTab.get(i)) {
				if (sicpTab.get(i)) {
					return getFinalGraphicalValue(i);
				}
				else {
					return new String[] {}; // the default value is ignored for list attributes
				}
			}
			else {

				if (sicpTab.get(i)) {
					return getFinalGraphicalValue(i);
				}
				else {
					return defValGraphicTab.get(i);
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

		if (sicpTab.get(i)) {
			return getFinalModelValue(i);
		}
		else {
			if (listTab.get(i)) {
				return new Object[] {}; // default value is ignored with list attributes
			}
			else {
				return defValModelTab.get(i);
			}
		}
	}

	/**
	 * Creates the CADSE, the items type and all the attributes in CADSEg.
	 */
	public void createAll() {

		// Creates a new CADSE
		createCadseDefinition(cadse_name, "model." + cadse_name);

		for (int i = 0; i < defValGraphicTab.size(); i++) {

			System.out.println("Starting CADSEg #" + i);

			/* Item type creation */
			GTTreePath it_path = data_model.concat(getItName(i));
			createItemType(data_model, getItName(i), null, false, true, null);

			/* Attribute creation */
			GTTreePath attr_path = it_path.concat(getAttributeName());
			createBasicAttribute(it_path, getItemTypeUnderTest(), getAttributeName(), null, defValGraphicTab.get(i),
					sicpTab.get(i), simpTab.get(i), cbuTab.get(i), listTab.get(i));

			/* Assert item has been created */
			workspaceView.selectNode(attr_path);

			/* Post create */
			postCreate(i, it_path, attr_path);
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
		for (int i = 0; i < sicpTab.size(); i++) {
			testExecution(i);
		}
	}

	/**
	 * At runtime, test if an item type has the correct behavior.
	 */
	public void testExecution(int i) {

		boolean fieldInCP = sicpTab.get(i);

		/* ============== */
		/* Creation page */
		/* ============== */

		workspaceView.contextMenuNew(getItName(i)).click();
		GTShell shell = new GTShell(getItName(i));

		// is field present
		boolean isFieldPresent = true;
		try {
			GTCadseFactory.findCadseField(shell, getAttributeName());
		}
		catch (Exception e) {
			isFieldPresent = false;
		}
		assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInCP, isFieldPresent);

		// initial visual value
		if (fieldInCP) {
			Object expected = getInitialVisualValue(i);
			Object actual = GTCadseFactory.findCadseField(shell, getAttributeName()).getValue();
			assertEqualsListValues("Initial visual value error for #" + i, expected, actual);
		}

		// initial model value
		if (fieldInCP) {
			assertEqualsListValues("Initial model value error for #" + i, getInitialModelValue(i), GTCadseFactory
					.findCadseField(shell, getAttributeName()).getModelValue());
		}

		// New Attribute Value
		if (fieldInCP && newValGraphicTab.get(i) != null) {
			if (listTab.get(i)) {
				if (newValGraphicTab.get(i).equals("")) {
					long old_timeout = SWTBotPreferences.TIMEOUT;
					try {
						SWTBotPreferences.TIMEOUT = failingAssertTimeout;
						GTCadseFactory.findCadseField(shell, getAttributeName()).addValue(newValGraphicTab.get(i));
						SWTBotPreferences.TIMEOUT = old_timeout;
						fail("It should be impossible to fill an empty value for #" + i);
					}
					catch (Exception e) {
						// SUCCESS : it's not possible to fill an empty value
						SWTBotPreferences.TIMEOUT = old_timeout;
						shell.close(GTEclipseConstants.CANCEL_BUTTON);
						return; // END of test for this item!
					}
				}
				else {
					GTCadseFactory.findCadseField(shell, getAttributeName()).addValue(newValGraphicTab.get(i));
				}
			}
			else {
				GTCadseFactory.findCadseField(shell, getAttributeName()).typeText(newValGraphicTab.get(i));
			}
		}

		// name + CHANGES FOCUS!!!
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(getInstanceName(i));

		// final visual value
		if (fieldInCP && newValGraphicTab.get(i) != null) {
			assertEqualsListValues("Error with final visual value for #" + i, getFinalGraphicalValue(i), GTCadseFactory
					.findCadseField(shell, getAttributeName()).getValue());
		}

		// final model value (okButtonActivated is important! if the value is not correct, the previous correct
		// model value (default value) is locked even if the field displays another value.
		if (fieldInCP && newValGraphicTab.get(i) != null && isOkButtonActivated(i)) {
			assertEqualsListValues("Final model value error for #" + i, newValModelTab.get(i), GTCadseFactory
					.findCadseField(shell, getAttributeName()).getModelValue());
		}

		// Waits until refresh
		try {
			Thread.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Closes shell
		if (isOkButtonActivated(i)) {
			shell.close();
		}
		else {
			try {
				shell.close(failingAssertTimeout);
				fail("OK button is activated whereas it shouldn't for #" + i);
			}
			catch (Exception e) {
				// SUCCESS
			}
			return;
		}

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(getInstanceName(i));
		propertiesView.showTab(getItName(i));

		// Name
		assertEquals(getInstanceName(i), GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_NAME_)
				.getText());

		// Field value
		if (simpTab.get(i)) {
			assertEqualsListValues("Error in graphical modification page value for #" + i,
					getPropertiesGraphicalValue(i), GTCadseFactory.findCadseField(propertiesView, getAttributeName())
							.getValue());
			assertEqualsListValues("Error in model modification page value for #" + i, getPropertiesModelValue(i),
					GTCadseFactory.findCadseField(propertiesView, getAttributeName()).getModelValue());
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
				tab.add(o);
			}
		}
		else if (object instanceof Object[]) {
			Object[] t = (Object[]) object;
			for (int i = 0; i < t.length; i++) {
				tab.add(t[i]);
			}
		}
		else {
			tab.add(object);
		}
		return tab;
	}

}
