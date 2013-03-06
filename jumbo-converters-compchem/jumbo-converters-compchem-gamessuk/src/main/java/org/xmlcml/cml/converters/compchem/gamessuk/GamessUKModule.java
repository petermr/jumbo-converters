package org.xmlcml.cml.converters.compchem.gamessuk;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.gamessuk.log.GamessUKXLog2XMLConverter;
import org.xmlcml.cml.converters.compchem.gamessuk.punch.GamessUKXPunchXML2CMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class GamessUKModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-gamessuk-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-gamessuk-log-xml", ObjectType.XML, "log.xml");
	public static final MimeType PUNCH_TYPE = new MimeType("chemical/x-gamessuk-punch", ObjectType.TEXT, "punch");
	public static final MimeType PUNCH_XML_TYPE = new MimeType("chemical/x-gamessuk-punch-xml", ObjectType.XML, "punch.xml");
	private static final String PREFIX = "gamessuk";

	
    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new GamessUKXPunchXML2CMLConverter());
		converterList.add(new GamessUKXLog2XMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
			mimeTypeList.add(PUNCH_TYPE);
			mimeTypeList.add(PUNCH_XML_TYPE);
		}
		return mimeTypeList;
	}
	

	
}
