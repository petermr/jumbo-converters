package org.xmlcml.cml.converters.chemdraw;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.chemdraw.CDXML2CMLObject;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;

public class CDXML2CMLConverter extends AbstractConverter implements
		Converter {

	private static final Logger LOG = Logger.getLogger(CDXML2CMLConverter.class);
	public static final String REG_MESSAGE = "Chemdraw: CDXML to CML conversion";
	static {
		LOG.setLevel(Level.INFO);
	}
	
	private boolean cleanMolecules = false;
	private boolean flatten = true;
	private boolean rescale = false;
	private boolean removeCDXAttributes = true;
	
	/**
	 * converts a CDXML object to CML. returns cml:cml
	 * 
	 * @param cdxml
	 */
	@Override
	public Element convertToXML(Element cdxml) {
		LOG.debug("CDXML2CML");
		CDXML2CMLObject cd = new CDXML2CMLObject();
		cd.setCleanMolecules(cleanMolecules);
		cd.setFlatten(flatten);
		cd.setRescale(rescale);
		cd.setRemoveCDXAttributes(removeCDXAttributes);
		try {
			cd.convertParsedXMLToCML(cdxml);
		} catch (Exception e) {
			throw new RuntimeException("Cannot parse CDXML", e);
		}
		CMLElement cml = cd.getCML();
		if (LOG.isDebugEnabled()) {
			CMLUtil.debug(cml, "final CML");
		}
		return cml;
	}
	
	public MimeType getInputType() {
		return ChemdrawModule.CDXML_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return REG_MESSAGE;
	}



}
