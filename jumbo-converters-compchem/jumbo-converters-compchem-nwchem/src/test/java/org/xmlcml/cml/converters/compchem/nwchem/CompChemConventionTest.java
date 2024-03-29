package org.xmlcml.cml.converters.compchem.nwchem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLog2XMLConverter;
import org.xmlcml.cml.converters.compchem.nwchem.log.NWChemLogXML2CompchemConverter;

public class CompChemConventionTest {

	private static Document doc;
	
	@BeforeClass
	public static void runConverters() throws Exception {
		InputStream in = CompChemConventionTest.class.
			getResourceAsStream("/compchem/nwchem/log/markjohn/fukuilite.log");
		
		NWChemLog2XMLConverter converter1 = NWChemLog2XMLConverter.createDefaultConverter();
		Element e1 = converter1.convertToXML(in);
		
		NWChemLogXML2CompchemConverter converter2 = NWChemLogXML2CompchemConverter.createDefaultConverter();
		
		Element e2 = converter2.convertToXML(e1);
		doc = CMLUtil.ensureDocument(e2);
		CMLUtil.debug(e2, new FileOutputStream("test/fukuilite.xml"), 1);
	}

	@AfterClass
	public static void cleanUp() {
		doc = null;
	}
	
	@Test
	public void testConversionRuns() {
		assertNotNull(doc);
	}

	/**
<module cmlx:templateRef="nwchem.log" 
convention="convention:compchem" 
xmlns="http://www.xml-cml.org/schema" 
xmlns:cmlx="http://www.xml-cml.org/schema/cmlx" 
xmlns:conventions="http://www.xml-cml.org/convention/" 
xmlns:compchem="http://www.xml-cml.org/dictionary/compchem/" 
xmlns:cc="http://www.xml-cml.org/dictionary/compchem/" 
xmlns:n="http://www.xml-cml.org/dictionary/nwchem/" 
xmlns:x="http://www.xml-cml.org/dictionary/cmlx/" 
xmlns:h="http://www.w3.org/1999/xhtml" 
xmlns:cml="http://www.xml-cml.org/schema" 
xmlns:convention="http://www.xml-cml.org/convention/" 
xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
xmlns:nonsi="http://www.xml-cml.org/unit/nonSi/">
 <module cmlx:lineCount="1" cmlx:templateRef="argument">
	 */
	@Test
	public void testRootModuleHasCompChempConvention() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']",
			CMLConstants.CML_XPATH);
		assertEquals("Root element", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/convention/", e.getNamespaceURI("convention"));
	}

	/**
 <module id="jobList1" dictRef="cc:jobList">
  <module id="job1" dictRef="cc:job">
   <module id="environment" dictRef="cc:environment">
    <parameterList/>
   </module>
  </module>
 </module>
	 */
	@Test
	public void testFindJobList() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']", CMLConstants.CML_XPATH);
		assertEquals("Job list", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/dictionary/compchem/", e.getNamespaceURI("cc"));
		assertNotNull(e.getAttribute("id"));
	}
	
	@Test
	public void testFindJob() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']", CMLConstants.CML_XPATH);
		assertEquals("Job", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/dictionary/compchem/", e.getNamespaceURI("cc"));
		assertNotNull(e.getAttribute("id"));
	}
	
	@Test
	public void testFindEnvironmentModule() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:environment']", CMLConstants.CML_XPATH);
		assertEquals("Environment", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/dictionary/compchem/", e.getNamespaceURI("cc"));
	}
	
	@Test
	public void testFindEnvironmentParameterList() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:environment']/cml:parameterList", CMLConstants.CML_XPATH);
		assertEquals("Environment parameter list", 1, nodes.size());
	}

	@Test
	public void testFindEnvironmentParameters() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:environment']/cml:parameterList/*", CMLConstants.CML_XPATH);
		assertFalse("Environment has parameters", nodes.isEmpty());
		for (Node node : nodes) {
			Element e = (Element) node;
			assertEquals("parameter", e.getLocalName());
		}
	}
	/**
 <module cmlx:lineCount="16" cmlx:templateRef="job">
  <scalar dataType="xsd:string" dictRef="compchem:hostname" cmlx:templateRef="host">cyclops</scalar>
  <scalar dataType="xsd:string" dictRef="compchem:executable" cmlx:templateRef="prog">/home/mw529/code/NWChem/nwchem-6.0/bin/LINUX64/nwchem</scalar>
  <array dataType="xsd:string" dictRef="compchem:date" cmlx:templateRef="date" size="5">Fri May 13 17:54:33 2011</array>
  <scalar dataType="xsd:string" dictRef="compchem:version" cmlx:templateRef="version">6.0</scalar>
  <scalar dataType="xsd:string" dictRef="compchem:input" cmlx:templateRef="input">fukui.nw</scalar>
  <scalar dataType="xsd:string" dictRef="compchem:nproc" cmlx:templateRef="nproc">1</scalar>
 </module>
	 */
	
	@Test
	public void testEnvironmentHostName() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:environment']/cml:parameterList/cml:parameter[@dictRef='compchem:hostname']/cml:scalar/text()", CMLConstants.CML_XPATH);
		assertFalse(nodes.isEmpty());
		assertEquals("cyclops", nodes.get(0).getValue());
	}
	
	@Test
	public void testEnvironmentVersion() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:environment']/cml:parameterList/cml:parameter[@dictRef='compchem:version']/cml:scalar/text()", CMLConstants.CML_XPATH);
		assertFalse(nodes.isEmpty());
		assertEquals("6.0", nodes.get(0).getValue());
	}

	
	
	@Test
	public void testFindInitializationModule() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:initialization']", CMLConstants.CML_XPATH);
		assertEquals("Initialization", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/dictionary/compchem/", e.getNamespaceURI("cc"));
	}
	
	@Test
	public void testFindInitializationParameterList() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:initialization']/cml:parameterList", CMLConstants.CML_XPATH);
		assertEquals("Initialization parameter list", 1, nodes.size());
	}

	@Test
	public void testFindInitializationParameters() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:initialization']/cml:parameterList/*", CMLConstants.CML_XPATH);
		assertFalse("Initialization has parameters", nodes.isEmpty());
		for (Node node : nodes) {
			Element e = (Element) node;
			assertEquals("parameter", e.getLocalName());
		}
	}
	
	@Test
	public void testInitializationSCFType() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:initialization']/cml:parameterList/cml:parameter[@dictRef='cc:scf.type']/cml:scalar/text()", CMLConstants.CML_XPATH);
		assertFalse(nodes.isEmpty());
		assertEquals("DFT", nodes.get(0).getValue());
	}
	
	@Ignore
	@Test
	public void testInitializationBasis() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:initialization']/cml:parameterList/cml:parameter[@dictRef='cc:basis']/cml:scalar/text()", CMLConstants.CML_XPATH);
		assertFalse(nodes.isEmpty());
		assertEquals("6-31G(d)", nodes.get(0).getValue());
	}
	
	@Test
	public void testInitializationMolecule() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:initialization']/cml:molecule", CMLConstants.CML_XPATH);
		assertEquals(1, nodes.size());
		
		Element molecule = (Element) nodes.get(0);
		assertEquals(1, CMLUtil.getQueryNodes(molecule, "./cml:atomArray", CMLConstants.CML_XPATH).size());
		assertEquals(1, CMLUtil.getQueryNodes(molecule, "./cml:bondArray", CMLConstants.CML_XPATH).size());
	}

	
	
	@Test
	public void testFindCalculationModule() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:calculation']", CMLConstants.CML_XPATH);
		assertEquals("Calculation", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/dictionary/compchem/", e.getNamespaceURI("cc"));
	}
	
	@Test
	public void testFindFinalizationModule() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:finalization']", CMLConstants.CML_XPATH);
		assertEquals("Environment", 1, nodes.size());
		Element e = (Element) nodes.get(0);
		assertEquals("http://www.xml-cml.org/dictionary/compchem/", e.getNamespaceURI("cc"));
	}

	@Test
	public void testFindFinalizationPropertyList() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:finalization']/cml:propertyList", CMLConstants.CML_XPATH);
		assertEquals("Finalization property list", 1, nodes.size());
	}

	@Ignore
	@Test
	public void testFindFinalizationProperties() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:finalization']/cml:propertyList/*", CMLConstants.CML_XPATH);
		assertFalse("Finalization has properties", nodes.isEmpty());
		for (Node node : nodes) {
			Element e = (Element) node;
			assertEquals("property", e.getLocalName());
		}
	}

	@Ignore
	@Test
	public void testFinalizationHFEnergy() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:finalization']/cml:propertyList/cml:property[@dictRef='cc:hfenergy']/cml:scalar/text()", CMLConstants.CML_XPATH);
		assertFalse(nodes.isEmpty());
		assertEquals("-40.5183892", nodes.get(0).getValue());
	}

	@Test
	public void testFinalizationMolecule() {
		List<Node> nodes = CMLUtil.getQueryNodes(doc, "/cml:*[@convention='convention:compchem']/cml:module[@dictRef='cc:jobList']/cml:module[@dictRef='cc:job']/cml:module[@dictRef='cc:finalization']/cml:molecule", CMLConstants.CML_XPATH);
		assertEquals(1, nodes.size());
		
		Element molecule = (Element) nodes.get(0);
		assertEquals(1, CMLUtil.getQueryNodes(molecule, "./cml:atomArray", CMLConstants.CML_XPATH).size());
		assertEquals(1, CMLUtil.getQueryNodes(molecule, "./cml:bondArray", CMLConstants.CML_XPATH).size());
	}

}
