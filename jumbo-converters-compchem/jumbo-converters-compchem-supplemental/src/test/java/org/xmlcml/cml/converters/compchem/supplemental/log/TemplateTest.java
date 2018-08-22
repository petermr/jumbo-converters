package org.xmlcml.cml.converters.compchem.supplemental.log;

import org.junit.Test;
import org.xmlcml.cml.converters.testutils.TemplateTester;

public class TemplateTest {
	
	private TemplateTester templateTester = new TemplateTester("supplemental", "log", ".log", this.getClass());
	
	@Test
	public void testCommentExamples() {
		templateTester.runCommentExamples();
	}
	
	@Test	public void testSupplementalLogFile()         {templateTester.runTestOnFile("supplemental");}
	

}
