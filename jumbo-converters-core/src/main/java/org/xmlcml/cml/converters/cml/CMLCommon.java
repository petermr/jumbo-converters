package org.xmlcml.cml.converters.cml;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;

public class CMLCommon {

	public static final String CML = "cml";
	public static final String PNG = "png";
	public static final String SVG = "svg";
	public static final String TXT = "txt";
	public static final String XML = "xml";
	
	public static final MimeType CML_TYPE = new MimeType("chemical/x-cml", ObjectType.XML, "cml");
	public static final MimeType HTML_TYPE = new MimeType("text/xhtml", ObjectType.XML, "html");
	public static final MimeType SVG_TYPE = new MimeType("image/svg", ObjectType.XML, "svg");

}
