package fr.imag.adele.cadse.test.interfaces;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
	CheckCadsegPages_tc.class
})

public class CheckCadsegPages_ts {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(CheckCadsegPages_ts.class);
	}
}
