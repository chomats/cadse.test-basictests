package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class Test1_common_testDriver extends GTCommonTestDriver {

	/** Link prefix used to compute the names */
	protected final String linkName = "link";

	// ====== //
	// VALUES //
	// ====== //

	protected KeyValue defaultValue;
	protected KeyValue newValue1;
	protected KeyValue newValue2;
	protected KeyValue newValue3;

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

	@Override
	public String getTestName() {
		return "group_test1";
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

		if (listTab.get(i) == KeyValue.listKv) {
			return new KeyValue(getAttributeName(), new String[] {}, new String[] {});
		}
		else {
			return new KeyValue(getAttributeName(), defaultValue.visualValue, defaultValue.modelValue);
		}
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

			if (listTab.get(i) == KeyValue.listKv) {
				return new KeyValue(getAttributeName(), new String[] { newValue1.visualValue.toString() },
						new String[] { newValue1.modelValue.toString() });
			}
			else {
				return new KeyValue(getAttributeName(), newValue1.visualValue, newValue1.modelValue);
			}
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

		if (listTab.get(i) == KeyValue.listKv) {

			KeyValue kv = getNewValue1(i);

			ArrayList<String> visual = new ArrayList<String>();
			ArrayList<String> model = new ArrayList<String>();

			for (int j = 0; j < ((String[]) kv.modelValue).length; j++) {
				visual.add(((String[]) kv.modelValue)[j]);
				model.add(((String[]) kv.visualValue)[j]);
			}

			if (simpTab.get(i) == KeyValue.simpKv) {
				visual.add(newValue2.visualValue.toString());
				model.add(newValue2.modelValue.toString());
			}

			return new KeyValue(getAttributeName(), visual.toArray(new String[] {}), model.toArray(new String[] {}));
		}

		// not list
		else {
			if (simpTab.get(i).getBoolean()) {
				return new KeyValue(getAttributeName(), newValue2.visualValue, newValue2.modelValue);
			}
			else {
				return getNewValue1(i);
			}
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

		if (listTab.get(i) == KeyValue.listKv) {

			KeyValue kv = getNewValue2(i);

			ArrayList<String> visual = new ArrayList<String>();
			ArrayList<String> model = new ArrayList<String>();

			for (int j = 0; j < ((String[]) kv.modelValue).length; j++) {
				visual.add(((String[]) kv.modelValue)[j]);
				model.add(((String[]) kv.visualValue)[j]);
			}

			if (simpTab.get(i) == KeyValue.simpKv) {
				visual.add(newValue3.visualValue.toString());
				model.add(newValue3.modelValue.toString());
			}

			return new KeyValue(getAttributeName(), visual.toArray(new String[] {}), model.toArray(new String[] {}));
		}

		// not list
		else {
			if (simpTab.get(i).getBoolean()) {
				return new KeyValue(getAttributeName(), newValue3.visualValue, newValue3.modelValue);
			}
			else {
				return getNewValue2(i);
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
		createBasicAttribute(src, getItemTypeUnderTest(), getAttributeName(), getAttributeCreationKeyValues(i));

		/* Post create */
		GTTreePath attr = src.concat(getAttributeName());
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

		// Values used to set fields
		KeyValue val1 = new KeyValue(getAttributeName(), newValue1.visualValue, newValue1.modelValue);
		KeyValue val2 = new KeyValue(getAttributeName(), newValue2.visualValue, newValue2.modelValue);
		KeyValue val3 = new KeyValue(getAttributeName(), newValue3.visualValue, newValue3.modelValue);

		/* ============= */
		/* Head creation */
		/* ============= */

		workspaceView.contextMenuNewHead(getItSrcName(i)).click();
		GTCadseShell shell = new GTCadseShell(getItSrcName(i));

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInCP, isFieldPresent);

		// GET initial value
		if (fieldInCP) {
			KeyValue expected = getDefaultValue(i);

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Initial visual value error for #" + i, expected.visualValue, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Initial model value error for #" + i, expected.modelValue, actualModel);
		}

		// SET New Value 1
		if (fieldInCP) {
			shell.setValue(val1);
		}

		// name + CHANGES FOCUS!!!
		shell.findCadseFieldName().typeText(getInstanceSrcName(i));

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

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInMP, isFieldPresent);

		// GET New Value 1
		if (fieldInMP) {
			KeyValue expected = getNewValue1(i);

			Object actualVisual = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualModel);
		}

		// SET New Value 2
		if (fieldInMP) {
			propertiesView.setValue(val2);
		}

		/* =============== */
		/* Member creation */
		/* =============== */

		workspaceView.contextMenuNewMember(new GTTreePath(getInstanceSrcName(i)), getItDstName(i)).click();
		shell = new GTCadseShell(getItDstName(i));
		shell.findCadseFieldName().typeText(getInstanceDstName(i));

		// GET NewValue 2
		if (fieldInCP) {

			shell.next();

			KeyValue expected = getNewValue2(i);

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualModel);
		}
		else {

			try {
				shell.next();
				fail("It should be impossible to find and click on next button");
			}
			catch (Exception e) {
				// success
			}
		}

		shell.close();

		/* ======================= */
		/* Head value modification */
		/* ======================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInMP, isFieldPresent);

		// SET New Value 3
		if (fieldInMP) {
			workspaceView.selectNode(getInstanceSrcName(i));
			propertiesView.showTab(getItSrcName(i));
			propertiesView.setValue(val3);
		}

		/* ================================= */
		/* Member modification page checking */
		/* ================================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + i, fieldInMP, isFieldPresent);

		// GET New Value 3
		if (fieldInMP) {

			workspaceView.selectNode(getInstanceDstName(i));
			propertiesView.showTab(getInstanceSrcName(i));
			KeyValue expected = getNewValue3(i);

			Object actualVisual = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + i, expected.visualValue, actualModel);
		}
	}
}
