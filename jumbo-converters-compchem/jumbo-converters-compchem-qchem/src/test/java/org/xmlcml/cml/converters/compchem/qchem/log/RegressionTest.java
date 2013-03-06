 package org.xmlcml.cml.converters.compchem.qchem.log;

import org.junit.Test;

import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {

    @Test
    public void qchemLog2XML() {
        RegressionSuite.run("compchem/qchem/log", "log", "xml",
                          new QChemLog2XMLConverter());
    }
}
