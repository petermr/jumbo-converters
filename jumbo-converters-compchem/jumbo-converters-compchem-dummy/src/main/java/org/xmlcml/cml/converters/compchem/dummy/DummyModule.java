package org.xmlcml.cml.converters.compchem.dummy;

import java.util.ArrayList;

import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.dummy.log.DummyLog2XMLConverter;
import org.xmlcml.cml.converters.compchem.dummy.mol.DummyMol2CMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author pm286
 */
public class DummyModule extends AbstractConverterModule {

	public static final MimeType LOG_TYPE = new MimeType("chemical/x-dummy-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-dummy-log-xml", ObjectType.XML, "log.xml");
	public static final MimeType MOL_TYPE = new MimeType("chemical/x-dummy-mol", ObjectType.TEXT, "mol");
	private static final String PREFIX = "dummy";
	
    public DummyModule() {
    	super();
    }

    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new DummyLog2XMLConverter());
		converterList.add(new DummyMol2CMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
			mimeTypeList.add(MOL_TYPE);
		}
		return mimeTypeList;
	}
	
}
