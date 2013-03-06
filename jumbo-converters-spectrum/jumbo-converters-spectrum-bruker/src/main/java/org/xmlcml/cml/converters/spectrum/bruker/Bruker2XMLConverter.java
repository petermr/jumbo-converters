package org.xmlcml.cml.converters.spectrum.bruker;

import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.spectrum.SpectrumCommon;
import org.xmlcml.cml.converters.spectrum.jdx.jdx2cml.JDX2CMLParser;
import org.xmlcml.cml.element.CMLCml;
import org.xmlcml.cml.element.CMLSpectrum;
import org.xmlcml.cml.element.CMLSpectrum.SpectrumType;

public class Bruker2XMLConverter extends AbstractConverter {

	private static final Logger LOG = Logger.getLogger(Bruker2XMLConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	};
	
	private CMLSpectrum cmlSpectrum = null;
	private SpectrumType spectrumType = null;

	private JDX2CMLParser jdx2cmlParser;
	private CMLCml topCml;
	public Bruker2XMLConverter() {
		this.jdx2cmlParser = new JDX2CMLParser();
	}
	
	/**
	 * converts an Bruker object to CMLSpect. returns cml:cml
	 * 
	 * @param lines JCAMP in any ASCII format
	 */
	public Element convertToXML(List<String> lines) {
		topCml = new CMLCml();
		BrukerProcessor brukerProcessor = new BrukerProcessor(topCml);
		lines = brukerProcessor.extractAndProcessBrukerFiles(lines);
		CMLCml cml = jdx2cmlParser.convertToCML(lines);
		topCml.appendChild(cml);
		return topCml;
	}

	public MimeType getInputType() {
		return SpectrumCommon.BRUKER_JDX_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return "convert Bruker JCAMP-DX to XML";
	}


}
