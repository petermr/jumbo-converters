package org.xmlcml.cml.converters.compchem.amber.mdout;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.AbstractCompchem2CMLConverter;
import org.xmlcml.cml.converters.compchem.amber.AmberModule;

public class AmberMdoutXML2CMLConverter extends AbstractCompchem2CMLConverter{
	private static final Logger LOG = Logger.getLogger(AmberMdoutXML2CMLConverter.class);
    public static final String AMBER_MD_XML_TO_AMBER_MD_CML = "Amber-Mdout-XML to Amber-Mdout-CML";
	static {
		LOG.setLevel(Level.INFO);
	}	
	
	public AmberMdoutXML2CMLConverter() {
	}

	/**
	 * converts an Foo to CML. returns cml:cml/cml:molecule
	 * 
	 * @param xml
	 */
	public Element convertToXML(Element xml) {
		rawXml2CmlProcessor = new AmberMdoutXMLProcessor();
		return convert(xml);
	}
	
	public MimeType getInputType() {
		return AmberModule.MDOUT_XML_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return AMBER_MD_XML_TO_AMBER_MD_CML;
	}

}
