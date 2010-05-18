package fr.imag.adele.cadse.test.basictests.basicproperties;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { BasicProperties_Boolean_tc_execution.class })
public class BasicProperties_Boolean_ts_execution {
	public static Test suite() {
		return new JUnit4TestAdapter(BasicProperties_Boolean_ts_execution.class);
	}
}
