package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class Test1_Common_testDriver extends GTCommonTestDriver {

	/** Link prefix used to compute the names */
	protected final String linkName = "link";

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTestName()
	 */
	@Override
	public String getTestName() {
		return "group_test1";
	}

	/**
	 * Gets the source it name, for a given instance
	 * 
	 * @param i
	 *            the instance number
	 * @return the source it name
	 */
	public String getItSrcName(GTTestParameter tp) {
		return itPrefix + "Src" + tp.testNumber;
	}

	/**
	 * Gets the link name, for a given instance
	 * 
	 * @param i
	 *            the instance number
	 * @return the link name
	 */
	public String getLinkName(GTTestParameter tp) {
		return linkName;
	}

	/**
	 * Gets the it destination name, for a given instance
	 * 
	 * @param i
	 *            the instance number
	 * @return the destination it name
	 */
	public String getItDstName(GTTestParameter tp) {
		return itPrefix + "Dst" + tp.testNumber;
	}

	/**
	 * Gets the source instance name.
	 * 
	 * @param i
	 *            the instance number
	 * @return the source instance name
	 */
	public String getInstanceSrcName(GTTestParameter tp) {
		return instancePrefix + "Src" + tp.testNumber;
	}

	/**
	 * Gets the destination instance name.
	 * 
	 * @param i
	 *            the instance number
	 * @return the destination instance name
	 */
	public String getInstanceDstName(GTTestParameter tp) {
		return instancePrefix + "Dst" + tp.testNumber;
	}

	/**
	 * Gets the default value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the default value
	 */
	public KeyValue getDefaultValue(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return new KeyValue(getAttributeName(), new String[] {}, new String[] {});
		}
		else {
			return new KeyValue(getAttributeName(), tp.getValue("defVal").visualValue, tp.getValue("defVal").modelValue);
		}
	}

	/**
	 * Gets the first new value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the first new value
	 */
	public KeyValue getNewValue1(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean sicp = tp.getBoolean("sicp");

		if (sicp) {
			if (isList) {
				return new KeyValue(getAttributeName(),
						new String[] { tp.getValue("newValue1").visualValue.toString() }, new String[] { tp
								.getValue("newValue1").modelValue.toString() });
			}
			else {
				return new KeyValue(getAttributeName(), tp.getValue("newValue1").visualValue,
						tp.getValue("newValue1").modelValue);
			}
		}
		else {
			return getDefaultValue(tp);
		}
	}

	/**
	 * Gets the second new value.
	 * 
	 * @param i
	 *            the instance number.
	 * @return the second new value
	 */
	public KeyValue getNewValue2(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean simp = tp.getBoolean("simp");

		if (isList) {

			KeyValue kv = getNewValue1(tp);

			ArrayList<String> visual = new ArrayList<String>();
			ArrayList<String> model = new ArrayList<String>();

			for (int j = 0; j < ((String[]) kv.modelValue).length; j++) {
				visual.add(((String[]) kv.modelValue)[j]);
				model.add(((String[]) kv.visualValue)[j]);
			}

			if (simp) {
				visual.add(tp.getValue("newValue2").visualValue.toString());
				model.add(tp.getValue("newValue2").modelValue.toString());
			}

			return new KeyValue(getAttributeName(), visual.toArray(new String[] {}), model.toArray(new String[] {}));
		}

		// not list
		else {
			if (simp) {
				return new KeyValue(getAttributeName(), tp.getValue("newValue2").visualValue,
						tp.getValue("newValue2").modelValue);
			}
			else {
				return getNewValue1(tp);
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
	public KeyValue getNewValue3(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean simp = tp.getBoolean("simp");

		if (isList) {

			KeyValue kv = getNewValue2(tp);

			ArrayList<String> visual = new ArrayList<String>();
			ArrayList<String> model = new ArrayList<String>();

			for (int j = 0; j < ((String[]) kv.modelValue).length; j++) {
				visual.add(((String[]) kv.modelValue)[j]);
				model.add(((String[]) kv.visualValue)[j]);
			}

			if (simp) {
				visual.add(tp.getValue("newValue3").visualValue.toString());
				model.add(tp.getValue("newValue3").modelValue.toString());
			}

			return new KeyValue(getAttributeName(), visual.toArray(new String[] {}), model.toArray(new String[] {}));
		}

		// not list
		else {
			if (simp) {
				return new KeyValue(getAttributeName(), tp.getValue("newValue3").visualValue,
						tp.getValue("newValue3").modelValue);
			}
			else {
				return getNewValue2(tp);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#testCreation(int)
	 */
	@Override
	public void testCreation(GTTestParameter tp) {

		System.out.println("Starting CADSEg #" + tp.testNumber);

		/* Pre create */
		preCreate(tp);

		/* Item type and link creation */
		GTTreePath src = dataModel.concat(getItSrcName(tp));
		GTTreePath dst = dataModel.concat(getItDstName(tp));

		createItemType(dataModel, src.getDestinationName(), notAbstractKv, rootKv);
		createItemType(dataModel, dst.getDestinationName(), notAbstractKv, rootKv);
		createLinkType(getLinkName(tp), src, dst, null, null, KeyValue.groupKv);

		/* Attribute creation */
		createBasicAttribute(src, getItemTypeUnderTest(), getAttributeName(), getAttributeCreationKeyValues(tp));

		/* Post create */
		GTTreePath attr = src.concat(getAttributeName());
		postCreate(tp, src, attr);
	}

	/**
	 * Gets a list of key values used to create the attribute.
	 * 
	 * @param i
	 *            the i
	 * @return the attribute creation key values
	 */
	public KeyValue[] getAttributeCreationKeyValues(GTTestParameter tp) {

		KeyValue dv = tp.getValue("defVal");
		KeyValue defVal = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, dv.visualValue, dv.modelValue);

		KeyValue sicp = tp.getValue("sicp");
		KeyValue simp = tp.getValue("simp");
		KeyValue cbu = tp.getValue("cbu");
		KeyValue isList = tp.getValue("list");

		return new KeyValue[] { sicp, simp, cbu, isList, defVal };
	}

	/**
	 * Performs actions before the item creation.
	 * 
	 * @param i
	 *            the number of the item to be created
	 */
	protected void preCreate(GTTestParameter tp) {
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
	protected void postCreate(GTTestParameter tp, GTTreePath it_path, GTTreePath attr_path) {
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#testExecution(int)
	 */
	@Override
	public void testExecution(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean simp = tp.getBoolean("simp");

		// Values used to set fields
		KeyValue val1 = new KeyValue(getAttributeName(), tp.getValue("newValue1").visualValue,
				tp.getValue("newValue1").modelValue);
		KeyValue val2 = new KeyValue(getAttributeName(), tp.getValue("newValue2").visualValue,
				tp.getValue("newValue2").modelValue);
		KeyValue val3 = new KeyValue(getAttributeName(), tp.getValue("newValue3").visualValue,
				tp.getValue("newValue3").modelValue);

		/* ============= */
		/* Head creation */
		/* ============= */

		workspaceView.contextMenuNewHead(getItSrcName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItSrcName(tp));

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, sicp,
				isFieldPresent);

		// GET initial value
		if (sicp) {
			KeyValue expected = getDefaultValue(tp);

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Initial visual value error for #" + tp.testNumber, expected.visualValue,
					actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Initial model value error for #" + tp.testNumber, expected.modelValue, actualModel);
		}

		// SET New Value 1
		if (sicp) {
			shell.setValue(val1);
		}

		// name + CHANGES FOCUS!!!
		shell.findCadseFieldName().typeText(getInstanceSrcName(tp));

		// Waits until refresh
		GTPreferences.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);

		shell.close();

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(getInstanceSrcName(tp));
		propertiesView.showTab(getItSrcName(tp));

		// Name
		assertEquals(getInstanceSrcName(tp), propertiesView.findCadseFieldName().getText());

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, simp,
				isFieldPresent);

		// GET New Value 1
		if (simp) {
			KeyValue expected = getNewValue1(tp);

			Object actualVisual = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.visualValue,
					actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.visualValue,
					actualModel);
		}

		// SET New Value 2
		if (simp) {
			propertiesView.setValue(val2);
		}

		/* =============== */
		/* Member creation */
		/* =============== */

		workspaceView.contextMenuNewMember(new GTTreePath(getInstanceSrcName(tp)), getItDstName(tp)).click();
		shell = new GTCadseShell(getItDstName(tp));
		shell.findCadseFieldName().typeText(getInstanceDstName(tp));

		// GET NewValue 2
		if (sicp) {

			shell.next();

			KeyValue expected = getNewValue2(tp);

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.visualValue,
					actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.visualValue,
					actualModel);
		}
		else {
			assertCadseFieldDoesNotExist(shell, getAttributeName(), 0);
			assertButtonDoesNotExist(shell, GTEclipseConstants.NEXT_BUTTON, 0);
		}

		shell.close();

		/* ======================= */
		/* Head value modification */
		/* ======================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, simp,
				isFieldPresent);

		// SET New Value 3
		if (simp) {
			workspaceView.selectNode(getInstanceSrcName(tp));
			propertiesView.showTab(getItSrcName(tp));
			propertiesView.setValue(val3);
		}

		/* ================================= */
		/* Member modification page checking */
		/* ================================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, simp,
				isFieldPresent);

		// GET New Value 3
		if (simp) {

			workspaceView.selectNode(getInstanceDstName(tp));
			propertiesView.showTab(getInstanceSrcName(tp));
			KeyValue expected = getNewValue3(tp);

			Object actualVisual = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.visualValue,
					actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.visualValue,
					actualModel);
		}
	}
}
