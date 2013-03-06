package org.xmlcml.cml.converters.compchem.gamessus;

import java.util.ArrayList;

import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.gamessus.log.GamessUSXLog2XMLConverter;
import org.xmlcml.cml.converters.compchem.gamessus.log.GamessUSXLogXML2CMLConverter;
import org.xmlcml.cml.converters.compchem.gamessus.punch.GamessUSPunchXML2CMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class GamessUSModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-gamessus-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-gamessus-log-xml", ObjectType.XML, "log.xml");
	public static final MimeType PUNCH_TYPE = new MimeType("chemical/x-gamessus-punch", ObjectType.TEXT, "punch");
	public static final MimeType PUNCH_XML_TYPE = new MimeType("chemical/x-gamessus-punch-xml", ObjectType.XML, "punch.xml");
	private static final String PREFIX = "gamessus";
	
    public GamessUSModule() {
    	super();
    }

    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new GamessUSXLog2XMLConverter());
		converterList.add(new GamessUSPunchXML2CMLConverter());
		converterList.add(new GamessUSXLogXML2CMLConverter());
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
