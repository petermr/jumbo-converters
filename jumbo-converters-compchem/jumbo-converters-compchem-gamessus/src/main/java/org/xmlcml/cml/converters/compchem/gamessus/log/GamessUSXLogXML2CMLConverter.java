package org.xmlcml.cml.converters.compchem.gamessus.log;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.AbstractCompchem2CMLConverter;
import org.xmlcml.cml.converters.compchem.gamessus.GamessUSModule;
import org.xmlcml.cml.converters.compchem.gamessus.punch.GamessUSPunchXMLProcessor;

public class GamessUSXLogXML2CMLConverter extends AbstractCompchem2CMLConverter{
	private static final Logger LOG = Logger.getLogger(GamessUSXLogXML2CMLConverter.class);
	private static final String GAMESSUS_LOG_XML_TO_CML = "GamessUS log to CML";
	static {
		LOG.setLevel(Level.INFO);
	}	
	
	public GamessUSXLogXML2CMLConverter() {
	}

	/**
	 * @param xml
	 */
	public Element convertToXML(Element xml) {
		rawXml2CmlProcessor = new GamessUSPunchXMLProcessor();
		return convert(xml);
	}

	public MimeType getInputType() {
		return GamessUSModule.LOG_XML_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return GAMESSUS_LOG_XML_TO_CML;
	}
}
