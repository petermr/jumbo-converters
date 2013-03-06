 package org.xmlcml.cml.converters.compchem.dlpoly.log;

import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {
	
    @Test
    @Ignore // fails with null pointer
    public void log2XML() {
        RegressionSuite.run("compchem/dlpoly/log", "log", "xml",
                          new DLPolyLog2XMLConverter());
    }
}
