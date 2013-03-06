package org.xmlcml.cml.converters.registry;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CML2CMLLiteConverter;

@Ignore
public class ConverterRegistryTest {

	String CML = "chemical/x-cml";
	String CDX = "chemical/x-cdx";
	String CDXML = "chemical/x-cdxml";
	TypePair PAIR_OK  = new TypePair(CDX, CDXML);
	TypePair PAIR_MISSING  = new TypePair(CDXML, CDX);
	
	@Test
	public void dummy() {
	}

    @Test
//    @Ignore
    public void testMap() {
    	Map<TypePair, List<Converter>> map = ConverterRegistry.getDefaultConverterRegistry().getMap();
    	Assert.assertNotNull(map);
    	// size will change as more are added
    	Assert.assertTrue(map.size() > 10);
    }

    @Test
    public void testList() {
    	List<Converter> converterList = ConverterRegistry.getDefaultConverterRegistry().getConverterList();
    	Assert.assertNotNull(converterList);
    	Assert.assertTrue(converterList.size() > 10);
    }

    @Test
    public void testList1() {
    	List<Converter> converterList = ConverterRegistry.getDefaultConverterRegistry().getConverterList();
    	boolean found = false;
    	for (Converter converter : converterList) {
    		if (CML2CMLLiteConverter.class.equals(converter.getClass())) {
    			found = true;
    			break;
    		}
    	}
    	Assert.assertTrue("converter", found);
    }

    @Test
    public void testMap1() {
    	Map<TypePair, List<Converter>> map = ConverterRegistry.getDefaultConverterRegistry().getMap();
    	Assert.assertTrue(map.containsKey(PAIR_OK));
    	Assert.assertFalse(map.containsKey(PAIR_MISSING));
    	for (TypePair typePair1 : map.keySet()) {
    		System.out.println(typePair1);
    	}
    }

    @Test
    public void testFindConverter() {
    	List<Converter> converters = ConverterRegistry.getDefaultConverterRegistry().findConverters(CDX, CDXML);
    	Assert.assertNotNull("cdx", converters);
    	Assert.assertEquals("cdx", 1, converters.size());
    	Assert.assertEquals("cdx", "CDX2CDXMLConverter", converters.get(0).getClass().getName());
    }

    @Test
    public void testFindConverter1() {
    	List<Converter> converters = ConverterRegistry.getDefaultConverterRegistry().findConverters(CML, CML);
    	Assert.assertNotNull("cml", converters);
    	for (Converter converter : converters) {
    		System.out.println(converter);
    	}
    	Assert.assertEquals("cml", 3, converters.size());
    }

	@Test
	public void testRegistryLoadsConverterList() {
		List<Converter> list = ConverterRegistry.getDefaultConverterRegistry().getConverterList();
		assertTrue(list.size()>0);
	}

	@Test
	public void testFindFoo2BarConverter() {
		List<Converter> converterList = ConverterRegistry.getDefaultConverterRegistry().findConverters("foo", "bar");
		assertNull(converterList);
	}

	@Test
	public void testFindTypesFromSuffix() {
		Set<MimeType> types = ConverterRegistry.getDefaultConverterRegistry().getTypes("cml");
		Assert.assertNotNull("get types", types);
		Assert.assertEquals("get types", 1, types.size());
		Assert.assertEquals("get types", "chemical/x-cml", ((MimeType)types.toArray()[0]).getMimeType());
	}

	@Test
	public void testFindTypesFromSuffix1() {
		Set<MimeType> types = ConverterRegistry.getDefaultConverterRegistry().getTypes("mol");
		Assert.assertNotNull("get types", types);
		Assert.assertEquals("types count", 1, types.size());
		Assert.assertEquals("type", "chemical/x-mdl-molfile", ((MimeType)types.toArray()[0]).getMimeType());
	}

	@Test
	public void testFindSingleTypeFromSuffix() {
		MimeType type = ConverterRegistry.getDefaultConverterRegistry().getSingleTypeFromSuffix("cml");
		Assert.assertNotNull("get type", type);
		Assert.assertEquals("get type", "chemical/x-cml", type.getMimeType());
	}
	
	@Test
	public void testSingletonConverterRegistry() {
		Assert.assertNotNull(ConverterRegistry.getDefaultConverterRegistry());
	}

	@Test
	public void testSingletonConverterRegistryList() {
		List<Converter> converterList = ConverterRegistry.getDefaultConverterRegistry().getConverterList();
		Assert.assertNotNull(converterList);
		Assert.assertEquals("converterList", 20, converterList.size());
	}

}
