package org.xmlcml.cml.converters.compchem.gaussian;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.compchem.gaussian.archive.GaussianArchiveXML2CMLConverter;
import org.xmlcml.cml.converters.compchem.gaussian.log.GaussianLog2CompchemConverter;
import org.xmlcml.cml.converters.compchem.gaussian.log.GaussianLog2XMLConverter;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class GaussianModule extends AbstractConverterModule {

	public static final MimeType ARCHIVE_TYPE = new MimeType("chemical/x-gaussian-arc", ObjectType.TEXT, "arc");
	public static final MimeType ARCHIVE_XML_TYPE = new MimeType("chemical/x-gaussian-arc-xml", ObjectType.XML, "arc.xml");
	public static final MimeType INPUT_TYPE = new MimeType("chemical/x-gaussian-inp", ObjectType.TEXT, "inp");
	public static final MimeType LOG_TYPE = new MimeType("chemical/x-gaussian-log", ObjectType.TEXT, "log");
	public static final MimeType LOG_XML_TYPE = new MimeType("chemical/x-gaussian-log-xml", ObjectType.XML, "log.xml");
	private static final String PREFIX = "gaussian";

    public GaussianModule() {
    	super();
    }

    public String getPrefix() {
    	return PREFIX;
    }

	public List<Converter> getConverterList() {
		converterList.add(new GaussianArchiveXML2CMLConverter());
		converterList.add(new GaussianLog2CompchemConverter());
		converterList.add(new GaussianLog2XMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(LOG_TYPE);
			mimeTypeList.add(LOG_XML_TYPE);
			mimeTypeList.add(INPUT_TYPE);
			mimeTypeList.add(ARCHIVE_TYPE);
			mimeTypeList.add(ARCHIVE_XML_TYPE);
		}
		return mimeTypeList;
	}
	
}
