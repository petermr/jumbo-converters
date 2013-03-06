package org.xmlcml.cml.converters.registry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.euclid.Util;


public class ModuleResource {
	
	/**
<resourceList name="nwchem">
  <prefix>nwchem</prefix>
  <namespace>http://wwmm.ch.cam.ac.uk/dict/nwchem</namespace>
  <typeList>
    <type name"input" mime="chemical/x-nwchem-input" objectType="TEXT">
      <suffix>inp</suffix>
    </type>
    <type name="log" mime="chemical/x-nwchem-log" objectType="TEXT">
      <suffix>log</suffix>
    </type>
    <type name="logXml" mime="chemical/x-nwchem-log-xml" objectType="XML">
      <suffix>log.xml</suffix>
      <suffix>xml</suffix>
    </type>
  </typeList>
  <dictionaryList>
    <dictionary resource="org/xmlcml/cml/converters/compchem/nwchem/nwchemDictionary.xml"
      url="http://no.where.yet"/>
  </dictionaryList>
  <converterList>
    <converter name="org.xmlcml.cml.converters.compchem.nwchem.input.CML2NWChemInputConverter"
      input="cml" output="input" description="converts cml to NWChem input"/>
    <converter name="org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2XMLConverter"
      input="log" output="logXml" description="converts log file to xmlCml"
      templateDir="org/xmlcml/cml/converters/compchem/nwchem/log/templates"
      template="topTemplate.dir"/>
    <converter name="org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2CompchemConverter"
      input="log" output="cml" description="converts log to comchem:CML"/>
  </converterList>
</resourceList>
	 */
	// fo
	private static final String NAME = "name";
	private static final String MODULE_RESOURCE = "moduleResource.xml";
	
	private Element moduleElement;
	private String prefix;
	private String namespace;

    public ModuleResource() {
    }

	protected void createModuleResource() {
		String packageName = this.getClass().getPackage().getName().replaceAll(Util.S_BACKSLASH+Util.S_PERIOD, Util.S_SLASH);
		try {
			moduleElement = CMLUtil.parseQuietlyToDocument(Util.getInputStreamFromResource(packageName+Util.S_SLASH+MODULE_RESOURCE)).getRootElement();
		} catch (Exception e) {
			throw new RuntimeException("Cannot find/read module resource", e);
		}
		processPrefix();
		processNamespace();
		processTypes();
		processDictionaries();
		processConverters();
	}

	/**
  <prefix>nwchem</prefix>
	 */
	private void processPrefix() {
		prefix = getStringContent("prefix");
	}

	/**
	  <namespace>http://wwmm.ch.cam.ac.uk/dict/nwchem</namespace>
		 */
	private void processNamespace() {
		namespace = getStringContent("namespace");
	}

	/**
    <type name"input" mime="chemical/x-nwchem-input" objectType="TEXT">
      <suffix>inp</suffix>
    </type>
	 */
	private void processTypes() {
		Nodes types = moduleElement.query("typelist/type");
		for (int i = 0; i < types.size(); i++) {
			Element typeElement = (Element) types.get(i);
			MimeType type = new MimeType(typeElement);
		}
	}

	/**
  <dictionaryList>
    <dictionary resource="org/xmlcml/cml/converters/compchem/nwchem/nwchemDictionary.xml"
      url="http://no.where.yet"/>
  </dictionaryList>
	 */
	private void processDictionaries() {
		// TODO Auto-generated method stub
		
	}

	/**
  <converterList>
    <converter name="org.xmlcml.cml.converters.compchem.nwchem.input.CML2NWChemInputConverter"
      input="cml" output="input" description="converts cml to NWChem input"/>
    <converter name="org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2XMLConverter"
      input="log" output="logXml" description="converts log file to xmlCml"
      templateDir="org/xmlcml/cml/converters/compchem/nwchem/log/templates"
      template="topTemplate.dir"/>
    <converter name="org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2CompchemConverter"
      input="log" output="cml" description="converts log to comchem:CML"/>
  </converterList>
	 */
	private void processConverters() {
		// TODO Auto-generated method stub
		
	}

	private String getStringContent(String xpath) {
		Element element = CMLUtil.getSingleElement(moduleElement, xpath);
		return element == null ? null : element.getValue();
	}
	
}
