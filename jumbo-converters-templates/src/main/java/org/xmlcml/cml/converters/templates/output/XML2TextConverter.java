package org.xmlcml.cml.converters.templates.output;

import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;

public abstract class XML2TextConverter extends AbstractConverter {
	private static final Logger LOG = Logger.getLogger(XML2TextConverter.class);

	public MimeType getInputType() {
		return MimeType.XML;
	}
	
	public MimeType getOutputType() {
		return MimeType.TXT;
	}
	
	public XML2TextConverter() {
	}
	
	@Override
	public List<String> convertToText(Element xmlInput) {
		List<String> stringList = null;
		return stringList;
	}

	
}
