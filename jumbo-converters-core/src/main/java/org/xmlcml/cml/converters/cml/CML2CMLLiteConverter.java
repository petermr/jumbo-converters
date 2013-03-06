package org.xmlcml.cml.converters.cml;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLBuilder;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.element.CMLCml;

public class CML2CMLLiteConverter extends AbstractConverter implements
		Converter {
	private static final Logger LOG = Logger.getLogger(CML2CMLLiteConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}
	
	/**
	 * converts a CML object to CMLLite. returns cml:cml
	 * 
	 */
	public Element convertToXML(Element element) {
		CMLElement cmlIn = CMLBuilder.ensureCML(element);
		CMLLiteHelper converter = new CMLLiteHelper(cmlIn);
		CMLCml cml = converter.getCML();
		return cml;
	}

	public MimeType getInputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return "normalizes a CML document to conform to CMLLite";
	}

}
