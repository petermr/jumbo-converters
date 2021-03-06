package org.xmlcml.cml.converters.compchem.molcas.log;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.TestUtils;
import org.xmlcml.cml.converters.templates.output.Text2XMLTemplateConverter;
import org.xmlcml.cml.converters.testutils.RegressionSuite;
import org.xmlcml.euclid.Util;

public class ConverterTest {
	private static Logger LOG = Logger.getLogger(ConverterTest.class);

	private static final String IN_SUFFIX = ".in";
	private static final String XML_SUFFIX = ".xml";

	private String codeType = "molcas";
	private String fileType = "log";

	@Test
	public void dummy() {
		
	}
	
	// tests fail because of whitespace somewhere. Needs an evening's work
	@Ignore
	@Test   public void testInternuc()        {testConverter("internuc");}

    @Test
    @Ignore // need whitespace comparison
    public void molcasOut2XML() {
		Text2XMLTemplateConverter converter = createConverter("org/xmlcml/cml/converters/compchem/molcas/log/topTemplate.xml");
        RegressionSuite.run("compchem/molcas/log", "out", "xml", converter, true);
    }
   
	private void testConverter(String name) {
		String templateXML = "org/xmlcml/cml/converters/compchem/molcas/log/topTemplate.xml";
		Text2XMLTemplateConverter converter = createConverter(templateXML);
		TestUtils.runConverterTest(converter, 
				"compchem/molcas/log/templates/"+name+IN_SUFFIX, 
				"compchem/molcas/log/templates/"+name+XML_SUFFIX,
				true);
	}
		
	private Text2XMLTemplateConverter createConverter(String templateXML) {
		Text2XMLTemplateConverter converter = null;
		try {
			InputStream templateStream = Util.getInputStreamFromResource(templateXML);
			converter = CompchemText2XMLTemplateConverter.createTemplateConverter(templateStream, "molcas", "log");
		} catch (Exception e) {
			throw new RuntimeException("Cannot make template ", e);
		}
		return converter;
	}

}
