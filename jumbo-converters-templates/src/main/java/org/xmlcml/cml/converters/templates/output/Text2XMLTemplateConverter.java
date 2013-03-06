package org.xmlcml.cml.converters.templates.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;

/** 
 * TODO document difference between this and superclass
 * * @author pm286
 *
 */
public class Text2XMLTemplateConverter extends Text2XMLConverter {

	protected Template template;

	public Text2XMLTemplateConverter() {
		super();
	}

	public Text2XMLTemplateConverter(Element templateElement) {
		init(templateElement);
	}

	public Text2XMLTemplateConverter(InputStream templateStream) {
		init(makeTemplateElement(templateStream));
	}

	public Text2XMLTemplateConverter(File templateFile) {
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
		this.template = template;
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


	@Override
	public Element convertToXML(List<String> lines) {
		lines = convertCharactersInLines(lines);
		TemplateProcessor glp = new TemplateProcessor(template);
		Element cmlElement = glp.applyMarkup(lines);
		// because we may have added parents
		Element cmlTop = (Element) cmlElement.query("ancestor-or-self::*").get(0);
		return cmlTop;
	
	}
	
	@Override
	public MimeType getInputType() {
		return null;
	}
	
	@Override
	public MimeType getOutputType() {
		return null;
	}
	
	@Override
	public String getDescription() {
		return "null";
	}

}
