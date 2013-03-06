package org.xmlcml.cml.converters.compchem.gamessus.punch;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.AbstractCompchem2CMLConverter;
import org.xmlcml.cml.converters.compchem.gamessus.GamessUSModule;

public class GamessUSPunchXML2CMLConverter extends AbstractCompchem2CMLConverter{
	private static final Logger LOG = Logger.getLogger(GamessUSPunchXML2CMLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}

	public GamessUSPunchXML2CMLConverter() {
	}

	/**
	 * @param xml
	 */
	public Element convertToXML(Element xml) {
		rawXml2CmlProcessor = new GamessUSPunchXMLProcessor();
		return convert(xml);
	}

	public MimeType getInputType() {
		return GamessUSModule.PUNCH_TYPE;
	}

	public MimeType getOutputType() {
		return GamessUSModule.PUNCH_XML_TYPE;
	}

	public String getDescription() {
		return "GamessUS punch to XML";
	}


}
