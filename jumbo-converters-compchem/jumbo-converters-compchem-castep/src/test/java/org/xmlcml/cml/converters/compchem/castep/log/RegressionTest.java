 package org.xmlcml.cml.converters.compchem.castep.log;

import org.junit.Test;

import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {

    @Test
    public void castepLog2XML() {
        RegressionSuite.run("compchem/castep/log", "log", "xml",
                          new CastepLog2XMLConverter());
    }
}
