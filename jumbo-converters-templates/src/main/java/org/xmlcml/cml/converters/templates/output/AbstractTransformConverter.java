package org.xmlcml.cml.converters.templates.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import nu.xom.Builder;
import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;

/** 
 * Superclass of TransformConverter
 * provides templates for transformation
 * 
 * @author pm286
 *
 */
public abstract class AbstractTransformConverter extends AbstractConverter {

	private static Logger LOG = Logger.getLogger(AbstractTransformConverter.class);

	protected static Template transformTemplate = null;
	
	public MimeType getInputType() {
		return MimeType.XML;
	}
	
	public MimeType getOutputType() {
		return MimeType.XML;
	}
	

	public AbstractTransformConverter() {
		super();
	}

	public AbstractTransformConverter(Element templateElement) {
		init(templateElement);
	}

	public AbstractTransformConverter(InputStream templateStream) {
		init(makeTemplateElement(templateStream));
	}

	public AbstractTransformConverter(File templateFile) {
		try {
			FileInputStream templateStream = new FileInputStream(templateFile);
			init(makeTemplateElement(templateStream));
		} catch (Exception e) {
			throw new RuntimeException("Cannot create template", e);
		}
	}
	
	private void init(Element templateElement) {
		this.setTemplate(new Template(templateElement));
	}

	protected void setTemplate(Template template) {
		this.transformTemplate = template;
	}


	private Element makeTemplateElement(InputStream templateStream) {
		try {
			Element templateElement = new Builder().build(templateStream).getRootElement();
			init(templateElement);
			return templateElement;
		} catch (Exception e) {
			throw new RuntimeException("Cannot build template: ", e);
		}
	}
}
