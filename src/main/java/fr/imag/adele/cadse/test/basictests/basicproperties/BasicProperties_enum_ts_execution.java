package fr.imag.adele.cadse.test.basictests.basicproperties;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { BasicProperties_enum_tc_execution.class })
public class BasicProperties_enum_ts_execution {
	public static Test suite() {
		return new JUnit4TestAdapter(BasicProperties_enum_ts_execution.class);
	}
}