package fr.imag.adele.cadse.test.basictests.cannotbeundefined;



import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(value={
		CannotBeUndefined_tc_execution.class
})


public class CannotBeUndefined_ts_execution {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(CannotBeUndefined_ts_execution.class);
	}
}
