package org.xmlcml.cml.converters.templates.input;

import org.xmlcml.cml.converters.templates.output.XSLTTextConverter;

import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLConstants;

public class InputGenerator extends XSLTTextConverter {

	private static Logger LOG = Logger.getLogger(InputGenerator.class);
	protected static final String INPUT_PARAMETERS = "inputParameters";

	public InputGenerator(Document xsltDocument, String inputParameterFilename) {
		super(xsltDocument);
		super.setParameter(INPUT_PARAMETERS, inputParameterFilename);
	}

	public List<String> convertToText(Element moleculeElement) {
		String s = this.transform(moleculeElement.getDocument());
		String[] ss = s.split(CMLConstants.S_WHITEREGEX);
		return Arrays.asList(ss);
	}

	private String transform(Document document) {
		try {
			Nodes nodes = transform.transform(document);
			return nodes.get(0).getValue();
		} catch (Exception e) {
			throw new RuntimeException("cannot transform", e);
		}
	}
	
}
