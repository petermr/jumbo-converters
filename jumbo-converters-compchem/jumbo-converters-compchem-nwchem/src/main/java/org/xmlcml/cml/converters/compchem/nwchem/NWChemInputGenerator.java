package org.xmlcml.cml.converters.compchem.nwchem;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.templates.input.InputGenerator;

public class NWChemInputGenerator extends InputGenerator {

	private static Logger LOG = Logger.getLogger(NWChemInputGenerator.class);
	
	public NWChemInputGenerator(Document xsltDocument, String inputParameterFilename) {
		super(xsltDocument, inputParameterFilename);
	}
	
	/**
	 * 
	 * @param args molecule 0 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String moleculeFile =   (args.length > 0) ? args[0] : 
   		    "src/main/resources/org/xmlcml/cml/converters/compchem/nwchem/in/inputMolecule1.xml";
		String outFile =        (args.length > 1) ? args[1] : "test/test1.out";
		String inputParamFile = (args.length > 2) ? args[2] :
			"src/main/resources/org/xmlcml/cml/converters/compchem/nwchem/in/inputParams.xml";
		String xslFile =        (args.length > 3) ? args[3] :
			"src/main/resources/org/xmlcml/cml/converters/compchem/nwchem/in/createInput.xsl";
		Document xsltDocument = new Builder().build(new FileInputStream(xslFile));
		Element moleculeElement = new Builder().build(new FileInputStream(moleculeFile)).getRootElement();
		InputGenerator generator = new NWChemInputGenerator(xsltDocument, inputParamFile);
		List<String> lines = generator.convertToText(moleculeElement);
		FileUtils.writeLines(new File(outFile), lines);
	}


}
