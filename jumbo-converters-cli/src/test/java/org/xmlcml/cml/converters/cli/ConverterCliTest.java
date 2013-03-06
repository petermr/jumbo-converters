package org.xmlcml.cml.converters.cli;

import java.io.File;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.testutil.JumboTestUtils;

@Ignore
public class ConverterCliTest {

	private static String CIF_TYPE = "chemical/x-cif";
	private static String CML_TYPE = "chemical/x-cml";	
	private static String MOL_TYPE = "chemical/x-mdl-molfile";
	
	@Test
	public void dummy() {

	}

	@Test
    public void testList() {
    	String[] args = {"-i", "junk",  "junk",  "-o", "grot", "grot"};
    	try {
    		ConverterCli.main(args);
    		Assert.fail("Should throw exception");
    	} catch (Exception e) {
    	}

    }

    @Test
    public void testList1() {
    	String[] args = {"-i junk junk -o grot grot"};
    	try {
    		ConverterCli.main(args);
    		Assert.fail("Should throw exception");
    	} catch (Exception e) {
    	}
    }


    @Test
    public void testMol() {
    	String infile = "src/test/resources/examples/simple.mol";
    	String outfile = "src/test/resources/examples/simple.cml";
    	String reffile = "src/test/resources/examples/simple.ref.cml";
    	String[] args = {"-it "+MOL_TYPE+" -i "+infile +
    			         " -ot "+CML_TYPE+" -o "+outfile};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("fromMdl", 
				CMLUtil.parseQuietlyIntoCML(new File(outfile)), 
				CMLUtil.parseQuietlyIntoCML(new File(reffile)), 
				true, 0.00000001);
    }

    @Test
    public void testMolRoundtrip() {
    	String infile = "src/test/resources/examples/simple.cml";
    	String outfile = "src/test/resources/examples/simple.roundtrip.mol";
    	String[] args = {"-i "+infile+" -o "+outfile};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		Assert.fail("Should not throw "+ e);
    	}
//		JumboTestUtils.assertEqualsIncludingFloat("toMdl", 
//				CMLUtil.parseQuietlyIntoCML(new File(infile)), 
//				CMLUtil.parseQuietlyIntoCML(new File(outfile)), 
//				true, 0.00000001);
    }

    @Test
    public void testCMLLite() {
    	String infile = "src/test/resources/examples/simple.cml";
    	String outfile = "src/test/resources/examples/simple.lite.cml";
    	String reffile = "src/test/resources/examples/simple.lite.ref.cml";
    	String converter = "org.xmlcml.cml.converters.cml.CML2CMLLiteConverter";
    	String[] args = {"-i "+infile+" -o "+outfile+" -c "+converter};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("cmlLite", 
				CMLUtil.parseQuietlyIntoCML(new File(outfile)), 
				CMLUtil.parseQuietlyIntoCML(new File(reffile)), 
				true, 0.00000001);
    }

    @Test
    public void testEdit() {
    	String infile = "src/test/resources/examples/simple.cml";
    	String outfile = "src/test/resources/examples/simple.edited.cml";
    	String reffile = "src/test/resources/examples/simple.edited.ref.cml";
    	String[] args = {"-i "+infile+" -o "+outfile+" -e bonds"};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("edit", 
				CMLUtil.parseQuietlyIntoCML(new File(outfile)), 
				CMLUtil.parseQuietlyIntoCML(new File(reffile)), 
				true, 0.00000001);
    }

    @Test
    public void testEdit1() {
    	String infile = "src/test/resources/examples/simple.cml";
    	String outfile = "src/test/resources/examples/simple.edited1.cml";
    	String reffile = "src/test/resources/examples/simple.edited1.ref.cml";
    	String[] args = {"-i "+infile+" -o "+outfile+" -e orders addh3d"};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("edit", 
				CMLUtil.parseQuietlyIntoCML(new File(outfile)), 
				CMLUtil.parseQuietlyIntoCML(new File(reffile)), 
				true, 0.00000001);
    }

    @Test
    public void testCDX() {
    	String infile = "src/test/resources/examples/cdx/r19.cdx";
    	String outfile = "src/test/resources/examples/cdx/r19.cdxml";
    	String reffile = "src/test/resources/examples/cdx/r19.ref.cdxml";
    	String[] args = {"-i "+infile+" -o "+outfile};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("CDX", 
				CMLUtil.parseQuietlyToDocument(new File(outfile)).getRootElement(), 
				CMLUtil.parseQuietlyToDocument(new File(reffile)).getRootElement(), 
				true, 0.00000001);
    }

    @Test
    public void testCDXML() {
    	// C00006 fails with standard CDXML library
    	String infile = "src/test/resources/examples/cdx/r19.cdxml";
    	String outfile = "src/test/resources/examples/cdx/r19.cml";
    	String reffile = "src/test/resources/examples/cdx/r19.ref.cml";
    	String[] args = {"-i "+infile+" -o "+outfile};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("CDXML", 
				CMLUtil.parseQuietlyToDocument(new File(outfile)).getRootElement(), 
				CMLUtil.parseQuietlyToDocument(new File(reffile)).getRootElement(), 
				true, 0.00000001);
    }

    @Test
    public void testCIFUrl() {
    	// C00006 fails with standard CDXML library
    	String infile = "http://scripts.iucr.org/cgi-bin/sendcif?aa2004sup1";
    	String outfile = "src/test/resources/examples/cif/aa2004.cml";
    	String reffile = "src/test/resources/examples/cif/aa2004.ref.cml";
    	String[] args = {"-u "+infile+" -it "+CIF_TYPE+" -o "+outfile};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail("Should not throw "+ e);
    	}
		JumboTestUtils.assertEqualsIncludingFloat("CIF", 
				CMLUtil.parseQuietlyToDocument(new File(outfile)).getRootElement(), 
				CMLUtil.parseQuietlyToDocument(new File(reffile)).getRootElement(), 
				true, 0.00000001);
    }

    	
    @Test
    public void testWildCard() {
    	String infile = "*.mol";
    	String startDir = "src/test/resources/examples/mdl";
    	String outname = "*.cml";
    	String[] args = {"-i "+infile+" -o "+outname+" -d "+startDir};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    		Assert.fail("Should not throw "+ e);
    	}
    }


    @Test
    @Ignore
    public void testJCamp() {
    	String[] args = {"-i", "jcamp-dx",  "materials/in/spectrum.jdx",  "-o", "cml", "materials/out/spectrum.cml"};
    	try {
    		ConverterCli.main(args);
    	} catch (Exception e) {
    		Assert.fail("Should not throw "+ e);
    	}
    }
    
    @Test
    public void testExtension() {
    	Set<MimeType> types = ConverterCli.getTypesFromFilename("foo.cml");
    	Assert.assertNotNull("non-null list", types);
    }
    
}
