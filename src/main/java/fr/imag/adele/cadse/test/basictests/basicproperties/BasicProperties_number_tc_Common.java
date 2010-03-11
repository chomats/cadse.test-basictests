package fr.imag.adele.cadse.test.basictests.basicproperties;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;

public class BasicProperties_number_tc_Common extends GTCadseTestCase {

	/**
	 * Used by all numerical attributes.
	 */
	public abstract class BasicProperties_Common_data {

		// ======== //
		// PREFIX //
		// ======== //

		/** A prefix, to compute the attributes name */
		private final String attr_prefix = "attr_";
		/** A prefix, to compute the item type name */
		private final String it_prefix = "it_" + getTypeUnderTest();
		/** A prefix, to compute the instances name */
		private final String instance_prefix = "instance_";

		// ================== //
		// CADSE DEFINITION //
		// ================== //

		/** The CADSE definition name */
		protected final String cadse_name = "CADSE_BasicProperties_" + getTypeUnderTest();
		/** A path to the CADSE definition */
		protected final GTTreePath cadse_model = new GTTreePath(cadse_name);
		/** A path to the data model */
		protected final GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);

		// =========================== //
		// PROPERTIES POSSIBLE VALUES //
		// =========================== //

		/** Set of values for the Show in Creation Page property */
		protected final boolean[] sicpValues = { true, false };
		/** Set of values for the Show in Modification Page property */
		protected final boolean[] simpValues = { true, false };
		/** Set of values for the Cannot be undefined property */
		protected final boolean[] cbuValues = { true, false };
		/** Set of values for the List property */
		protected final boolean[] listValues = { /* true, */false }; // NOT LIST
		/** Set of values for the displayed default value */
		protected ArrayList<String> defValGraphicValues = new ArrayList<String>();
		/** Set of values for the modeled default value */
		protected ArrayList<Object> defValModelValues = new ArrayList<Object>();
		/** Set of values for the displayed new value */
		protected ArrayList<String> newValGraphicValues = new ArrayList<String>();
		/** Set of values for the modeled new value */
		protected ArrayList<Object> newValModelValues = new ArrayList<Object>();

		// ======================================================== //
		// TABLES USED FOR PROPERTIES VALUES FOR ALL THE INSTANCES //
		// ======================================================== //

		/** The Show in Creation Page property value for all the instances */
		protected final ArrayList<Boolean> sicpTab = new ArrayList<Boolean>();
		/** The Show in Modification Page property value for all the instances */
		protected final ArrayList<Boolean> simpTab = new ArrayList<Boolean>();
		/** The Cannot be undefined property value for all the instances */
		protected final ArrayList<Boolean> cbuTab = new ArrayList<Boolean>();
		/** The List property value for all the instances */
		protected final ArrayList<Boolean> listTab = new ArrayList<Boolean>();

		// ======================================================= //
		// TABLES USED FOR COMPUTED VALUES FOR ALL THE INSTANCES //
		// ======================================================= //

		/** The displayed default value for all the instances */
		protected final ArrayList<String> defValGraphicTab = new ArrayList<String>();
		/** The model default value for all the instances */
		protected final ArrayList<Object> defValModelTab = new ArrayList<Object>();
		/** The displayed new value for all the instances */
		protected final ArrayList<String> newValGraphicTab = new ArrayList<String>();
		/** The model new value for all the instances */
		protected final ArrayList<Object> newValModelTab = new ArrayList<Object>();

		/**
		 * Returns the type under test.
		 * 
		 * @return the type under test
		 */
		abstract public String getTypeUnderTest();

		abstract public ItemType getItemTypeUnderTest();

		public String getAttributeName() {
			return attr_prefix + getTypeUnderTest();
		}

		public String getItName(int i) {
			return it_prefix + i;
		}

		public String getInstanceName(int i) {
			return instance_prefix + i;
		}

		public String getInitialVisualValue(int i) {
			if (sicpTab.get(i)) {
				return defValGraphicTab.get(i);
			}
			else {
				throw new WidgetNotFoundException("No field in this dialog");
			}
		}

		public Object getInitialModelValue(int i) {
			return defValModelTab.get(i);
		}

		public Object getFinalModelValue(int i) {
			if (sicpTab.get(i)) {
				return newValModelTab.get(i);
			}
			else {
				return defValModelTab.get(i);
			}
		}

		public String getFinalGraphicalValue(int i) {
			if (sicpTab.get(i)) {
				if (newValGraphicTab.get(i) != null) {
					return newValGraphicTab.get(i);
				}
				else {
					return defValGraphicTab.get(i); // no new value
				}
			}
			else {
				throw new WidgetNotFoundException("No field in this dialog");
			}
		}

		public boolean isOkButtonActivated(int i) {
			return cbuTab.get(i) ? (getFinalModelValue(i) != null) : true;
		}

		public String getPropertiesGraphicalValue(int i) {

			if (simpTab.get(i)) {
				if (sicpTab.get(i)) {
					return getFinalGraphicalValue(i);
				}
				else {
					return defValGraphicTab.get(i);
				}
			}
			else {
				throw new WidgetNotFoundException("No field in this dialog");
			}
		}

		public Object getPropertiesModelValue(int i) {

			if (sicpTab.get(i)) {
				return getFinalModelValue(i);
			}
			else {
				return defValModelTab.get(i);
			}
		}

		public void initializeTables() {

			for (int j = 0; j < defValGraphicValues.size(); j++) {
				for (int k = 0; k < newValGraphicValues.size(); k++) {
					for (boolean sicp : sicpValues) {
						for (boolean simp : simpValues) {
							for (boolean cbu : cbuValues) {
								for (boolean list : listValues) {
									defValModelTab.add(defValModelValues.get(j));
									defValGraphicTab.add(defValGraphicValues.get(j));
									newValModelTab.add(newValModelValues.get(k));
									newValGraphicTab.add(newValGraphicValues.get(k));
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

		public void createAll() {

			// Creates a new CADSE
			createCadseDefinition(cadse_name, "model." + cadse_name);

			for (int i = 0; i < defValGraphicTab.size(); i++) {

				/* Item type creation */
				GTTreePath it_path = data_model.concat(getItName(i));
				createItemType(data_model, getItName(i), null, false, true, null);

				/* Attribute creation */
				GTTreePath attr_path = it_path.concat(getAttributeName());
				createBasicAttribute(it_path, getItemTypeUnderTest(), getAttributeName(), null,
						defValGraphicTab.get(i), sicpTab.get(i), simpTab.get(i), cbuTab.get(i), listTab.get(i));

				/* Assert item has been created */
				workspaceView.selectNode(attr_path);
			}
		}

		public void testExecution() {
			for (int i = 0; i < sicpTab.size(); i++) {
				testExecution(i);
			}
		}

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
				assertEquals("Initial visual value error for #" + i, getInitialVisualValue(i), GTCadseFactory
						.findCadseField(shell, getAttributeName()).getValue());
			}

			// initial model value
			if (fieldInCP) {
				assertEquals("Initial model value error for #" + i, getInitialModelValue(i), GTCadseFactory
						.findCadseField(shell, getAttributeName()).getModelValue());
			}

			// New Attribute Value
			if (fieldInCP && newValGraphicTab.get(i) != null) {
				GTCadseFactory.findCadseField(shell, getAttributeName()).typeText(newValGraphicTab.get(i));
			}

			// name + CHANGES FOCUS!!!
			GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(getInstanceName(i));

			// final visual value
			if (fieldInCP && newValGraphicTab.get(i) != null) {
				assertEquals("Error with final visual value for #" + i, getFinalGraphicalValue(i), GTCadseFactory
						.findCadseField(shell, getAttributeName()).getText());
			}

			// final model value (okButtonActivated is important! if the value is not correct, the previous correct
			// model value (default value) is locked even if the field displays another value.
			if (fieldInCP && newValGraphicTab.get(i) != null && isOkButtonActivated(i)) {
				assertEquals("Final model value error for #" + i, newValModelTab.get(i), GTCadseFactory.findCadseField(
						shell, getAttributeName()).getModelValue());
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
				assertEquals(getPropertiesGraphicalValue(i), GTCadseFactory.findCadseField(propertiesView,
						getAttributeName()).getText());
				assertEquals(getPropertiesModelValue(i), GTCadseFactory.findCadseField(propertiesView,
						getAttributeName()).getModelValue());
			}
		}
	}

	public class BasicProperties_double_data extends BasicProperties_Common_data {

		public BasicProperties_double_data() {

			defValGraphicValues.add("");
			defValGraphicValues.add("123.0");
			defValModelValues.add(null);
			defValModelValues.add(123d);

			newValGraphicValues.add("");
			newValGraphicValues.add("456.0");
			newValGraphicValues.add(null); // null stands for leave unchanged
			newValModelValues.add(null);
			newValModelValues.add(456d);
			newValModelValues.add(null);

			initializeTables();
		}

		@Override
		public String getTypeUnderTest() {
			return "double";
		}

		@Override
		public ItemType getItemTypeUnderTest() {
			return CadseGCST.DOUBLE;
		}
	}
}