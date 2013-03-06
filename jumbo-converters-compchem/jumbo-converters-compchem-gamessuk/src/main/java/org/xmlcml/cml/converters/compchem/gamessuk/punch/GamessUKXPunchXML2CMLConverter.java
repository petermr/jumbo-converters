package org.xmlcml.cml.converters.compchem.gamessuk.punch;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.RawXML2CMLProcessor;
import org.xmlcml.cml.converters.compchem.AbstractCompchem2CMLConverter;
import org.xmlcml.cml.converters.compchem.gamessuk.GamessUKModule;

public class GamessUKXPunchXML2CMLConverter extends AbstractCompchem2CMLConverter{
	private static final Logger LOG = Logger.getLogger(GamessUKXPunchXML2CMLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}

	public GamessUKXPunchXML2CMLConverter() {

	}

	/**
	 * converts an MDL object to CML. returns cml:cml/cml:molecule
	 *
	 * @param xml
	 */
	public Element convertToXML(Element xml) {
		RawXML2CMLProcessor converter = new GamessUKXPunchXMLProcessor();
		converter.process(xml);
		CMLElement cml = converter.getCMLElement();
		addNamespaces(cml);
		return cml;
	}

	public MimeType getInputType() {
		return GamessUKModule.PUNCH_TYPE;
	}

	public MimeType getOutputType() {
		return GamessUKModule.PUNCH_XML_TYPE;
	}

	public String getDescription() {
		return "GamessUK Punch output to XML";
	}

}
