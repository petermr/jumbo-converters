/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xmlcml.cml.converters.spectrum.oscar;

import org.junit.Test;
import org.xmlcml.cml.converters.spectrum.SpectrumCommon;
import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {

   @Test
   public void oscar2cml() {
      RegressionSuite.run(SpectrumCommon.OSCAR_DIR, "xml", "cml",
                            new OSCAR2CMLSpectConverter());
   }

}
