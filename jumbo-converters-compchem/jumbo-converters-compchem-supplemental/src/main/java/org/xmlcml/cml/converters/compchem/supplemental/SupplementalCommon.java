package org.xmlcml.cml.converters.compchem.supplemental;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;

public class SupplementalCommon {

	public static final String SUPPLEMENTAL_LOG = "supplemental-log";
	public static final String SUPPLEMENTAL_XML = "supplemental-xml";
	public static final MimeType LOG_TYPE = new MimeType("chemical/x-dalton-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-dalton-log-xml", ObjectType.XML, "log.xml");
}
