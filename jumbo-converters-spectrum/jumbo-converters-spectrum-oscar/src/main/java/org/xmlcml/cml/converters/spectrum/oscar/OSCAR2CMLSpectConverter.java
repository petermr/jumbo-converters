package org.xmlcml.cml.converters.spectrum.oscar;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.spectrum.SpectrumCommon;
import org.xmlcml.cml.element.CMLCml;
import org.xmlcml.cml.element.CMLSpectrum;
import org.xmlcml.cml.element.CMLSpectrum.SpectrumType;

public class OSCAR2CMLSpectConverter extends AbstractConverter implements
		Converter {

	private static final Logger LOG = Logger.getLogger(OSCAR2CMLSpectConverter.class);
	static {
		LOG.setLevel(Level.INFO);
	};
	public final static String JDX_PREFIX = "jdx";
	
	private CMLSpectrum cmlSpectrum = null;
	private SpectrumType spectrumType = null;
	
	
	/**
	 * 
	 * 
	 * @param oscarSpectrum
	 * @return spectrum
	 */
	public Element convertToXML(Element oscarSpectrum) {
		CMLCml cml = null;
		OSCAR2CMLSpectHelper oscarHelper = new OSCAR2CMLSpectHelper();
		CMLSpectrum spectrum = oscarHelper.parse(oscarSpectrum);
		if (spectrum != null) {
			cml = new CMLCml();
			cml.appendChild(spectrum);
		}
		return cml;
	}

	public MimeType getInputType() {
		return SpectrumCommon.OSCAR_XML_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return "convert OSCAR-DATA to CMLSpect (peaks)";
	}

}
