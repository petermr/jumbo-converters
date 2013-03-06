package org.xmlcml.cml.converters.compchem.qchem.log;

import org.junit.Test;
import org.xmlcml.cml.converters.testutils.TemplateTester;

public class TemplateTest {

	private TemplateTester templateTester = new TemplateTester("qchem", "log", ".log", this.getClass());

	@Test
	public void testCommentExamples() {
		templateTester.runCommentExamples();
	}

	@Test	public void testMulliken()             {templateTester.runTemplateCommentExamples("mulliken");}

	@Test	public void testQChemLogFile()         {templateTester.runTestOnFile("qchem");}

	@Test	public void testStandardNuclear()             {templateTester.runTemplateCommentExamples("stdnucorient");}


}
