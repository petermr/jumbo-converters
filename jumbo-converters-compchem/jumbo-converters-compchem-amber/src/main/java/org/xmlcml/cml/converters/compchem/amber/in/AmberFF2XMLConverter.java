package org.xmlcml.cml.converters.compchem.amber.in;

import java.util.List;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.amber.AmberModule;
import org.xmlcml.cml.converters.templates.output.Text2XMLConverter;

public class AmberFF2XMLConverter extends Text2XMLConverter {
	
    public static final String AMBER_FF_TO_AMBER_FF_XML = "Amber-FF to Amber-FF-XML";
	
	public AmberFF2XMLConverter() {
		super();
	}
	
	@Override
	public Element convertToXML(List<String> lines) {
		// 
		Element element = super.convertToXML(lines);
		return element;
	}

	
	public MimeType getInputType() {
		return AmberModule.FF_TYPE;
	}
	
	public MimeType getOutputType() {
		return AmberModule.FF_XML_TYPE;
	}
	
	public String getDescription() {
		return AMBER_FF_TO_AMBER_FF_XML;
	}

}
