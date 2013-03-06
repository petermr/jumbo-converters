package org.xmlcml.cml.converters.compchem.gaussian.archive;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.AbstractCompchem2CMLConverter;
import org.xmlcml.cml.converters.compchem.gaussian.GaussianModule;

public class GaussianArchiveXML2CMLConverter extends AbstractCompchem2CMLConverter{
	private static final Logger LOG = Logger.getLogger(GaussianArchiveXML2CMLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	}

	public GaussianArchiveXML2CMLConverter() {
	}

	/**
	 * @param xml
	 */
	public Element convertToXML(Element xml) {
		rawXml2CmlProcessor = new GaussianArchiveXMLProcessor();
		return convert(xml);
	}

	public MimeType getInputType() {
		return GaussianModule.ARCHIVE_XML_TYPE;
	}

	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}

	public String getDescription() {
		return "Gaussian archive XML to CML";
	}

}
