package org.xmlcml.cml.converters.spectrum.jdx.jdx2cml;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.jcamp.math.AxisMap;
import org.jcamp.parser.JCAMPException;
import org.jcamp.parser.JCAMPReader;
import org.jcamp.spectrum.IDataArray1D;
import org.jcamp.spectrum.IOrderedDataArray1D;
import org.jcamp.spectrum.ISpectrumIdentifier;
import org.jcamp.spectrum.Spectrum;
import org.jcamp.spectrum.Spectrum1D;
import org.jcamp.spectrum.Spectrum2D;
import org.jcamp.spectrum.notes.Note;
import org.xmlcml.cml.base.CC;
import org.xmlcml.cml.element.CMLArray;
import org.xmlcml.cml.element.CMLCml;
import org.xmlcml.cml.element.CMLParameter;
import org.xmlcml.cml.element.CMLParameterList;
import org.xmlcml.cml.element.CMLScalar;
import org.xmlcml.cml.element.CMLSpectrum;
import org.xmlcml.cml.element.CMLSpectrum.SpectrumType;
import org.xmlcml.cml.element.CMLSpectrumData;
import org.xmlcml.cml.element.CMLXaxis;
import org.xmlcml.cml.element.CMLYaxis;

public class JDX2CMLParser {


	/** maps from JDX librarry to modern CML
	 *
	 */
	private static HashMap<String, ScalarType> note2cmlmap = new HashMap<String, ScalarType>();
	static {
		note2cmlmap.put("jcamp$datatype", new ScalarType("dataType", CC.XSD_STRING));
		note2cmlmap.put("jcamp$dataclass", new ScalarType("dataClass", CC.XSD_STRING));
		note2cmlmap.put("origin", new ScalarType("origin", CC.XSD_STRING));
		note2cmlmap.put("jcamp$owner", new ScalarType("owner", CC.XSD_STRING));
		note2cmlmap.put("nmr$frequency", new ScalarType("observeFrequency", CC.XSD_DOUBLE));
		note2cmlmap.put("nmr$nucleus", new ScalarType("observeNucleus", CC.XSD_STRING));
		note2cmlmap.put("nmr$acquisition-mode", new ScalarType("acquisitionMode", CC.XSD_STRING));
		note2cmlmap.put("nmr$averages", new ScalarType("digitiser", CC.XSD_STRING));
		note2cmlmap.put("nmr$digitiser-res", new ScalarType("digitiserRes", CC.XSD_STRING));
	
	}
	private CMLSpectrum cmlSpectrum = null;
	private SpectrumType spectrumType = null;
	public final static String JDX_PREFIX = "jdx";

	
	public JDX2CMLParser() {
		
	}
	public CMLCml convertToCML(List<String> lines) {
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line + CC.S_NEWLINE);
		}
		return convertToCML(sb.toString());
	}

	/**
	 * @param jcampString
	 * @throws RuntimeException
	 */
	public CMLCml convertToCML(String jcampString) throws RuntimeException {
		CMLCml cml = null;
		JCAMPReader jcamp = JCAMPReader.getInstance();
		Spectrum jcampSpectrum = null;
		try {
			jcampSpectrum = jcamp.createSpectrum(jcampString);
		} catch (JCAMPException e) {
			throw new RuntimeException("Cannot parse JCAMP", e);
		}
		cml = new CMLCml();
		cmlSpectrum = new CMLSpectrum();
		cml.appendChild(cmlSpectrum);
		createSpectrumFromJCamp(jcampSpectrum);
		return cml;
	}

	/**
	 * @param jcampSpectrum
	 * @throws RuntimeException
	 */
	private void addNotes(Spectrum jcampSpectrum) throws RuntimeException {
		// vendor specific and other metadata
		Collection<?> notes = jcampSpectrum.getNotes();
		if (notes.size() > 0) {
			CMLParameterList parameterList = new CMLParameterList();
			for (Object obj : notes) {
				Note note = (Note) obj;
				CMLParameter parameter = createParameter(note);
				parameterList.addParameter(parameter);
			}
			cmlSpectrum.addParameterList(parameterList);
		}
	}
	private CMLParameter createParameter(Note note) {
		CMLParameter parameter = new CMLParameter();
		String noteDescriptor = note.getDescriptor().toString().trim();
		ScalarType scalarType = note2cmlmap.get(noteDescriptor);
		String xsdType = (scalarType == null) ? CC.XSD_STRING : scalarType.xsdDataType;
		String value = note.getValue().toString().trim();
		CMLScalar scalar = (xsdType.equals(CC.XSD_DOUBLE)) ? new CMLScalar(new Double(value)) : new CMLScalar(value);
		String dictRef = (scalarType == null) ? noteDescriptor : scalarType.dictRef;
		String prefix = (scalarType == null) ? "foo" : JDX_PREFIX;
		parameter.setDictRef(prefix+CC.S_COLON+dictRef);
		parameter.appendChild(scalar);
		return parameter;
	}

	/**
		 * @param jcampSpectrum
		 */
		private void createSpectrumFromJCamp(Spectrum jcampSpectrum) {
			getSpectrumType(jcampSpectrum);
			cmlSpectrum.setType(spectrumType.toString());
			
			String xLabel = jcampSpectrum.getXAxisLabel();
			if ("m/z".equals(xLabel)) {
				xLabel = "M/Z";          // this seems to be variable case on different runs?
			}
			String yLabel = jcampSpectrum.getYAxisLabel();
			// not quite sure what these do...
			@SuppressWarnings("unused")
			AxisMap xAxisMap = jcampSpectrum.getXAxisMap();
			@SuppressWarnings("unused")
			AxisMap yAxisMap = jcampSpectrum.getYAxisMap();
			
			if (jcampSpectrum instanceof Spectrum1D) {
				CMLXaxis xaxis = new CMLXaxis();
				CMLYaxis yaxis = new CMLYaxis();
				Spectrum1D spectrum1d = (Spectrum1D) jcampSpectrum;
				CMLSpectrumData spectrumData = new CMLSpectrumData();
				cmlSpectrum.addSpectrumData(spectrumData);
				
				IOrderedDataArray1D xData = spectrum1d.getXData();
				CMLArray cmlXData = new CMLArray(xData.toArray());
				spectrumData.addXaxis(xaxis);
				xaxis.addArray(cmlXData);
				xaxis.setTitle(xLabel);
				
				IDataArray1D yData = spectrum1d.getYData();
				CMLArray cmlYData = new CMLArray(yData.toArray());
				spectrumData.addYaxis(yaxis);
				yaxis.addArray(cmlYData);
				yaxis.setTitle(yLabel);
				
			} else if (jcampSpectrum instanceof Spectrum2D) {
				throw new RuntimeException("Cannot support 2D spectrum");
			}
			
	//		origin is business process not coordinate
	//		System.out.println("ORIGIN (business) "+jcampSpectrum.getOrigin());
	//		System.out.println("OWNER "+jcampSpectrum.getOwner());
			
			String title = jcampSpectrum.getTitle();
			cmlSpectrum.setTitle(title);
			addNotes(jcampSpectrum);
		}

	private SpectrumType getSpectrumType(Spectrum jcampSpectrum) {
			int identifier = jcampSpectrum.getIdentifier();
	/**		
	 * 			from JCAMP
			   public final static int UNKNOWN = 0;
			    public final static int INFRARED = 2 << 0;
			    public final static int IR = 2 << 0;
			    public final static int RAMAN = 2 << 1;
			    public final static int ULTRAVIOLET = 2 << 2;
			    public final static int UV = 2 << 2;
			    public final static int FLUORESCENCE = 2 << 3;
			    public final static int NMR = 2 << 4;
			    public final static int MASS = 2 << 5;
			    public final static int MS = 2 << 5;
			    public final static int CHROMATOGRAM = 2 << 16;
			    public final static int GC = 2 << 17 | CHROMATOGRAM;
			    public final static int LC = 2 << 18 | CHROMATOGRAM;
			    public final static int FID = 2 << 23;
			    public final static int SPEC2D = 2 << 24;
			    public final static int NMRFID = NMR | FID;
			    public final static int NMR2D = NMR | SPEC2D;
			    public final static int FLUORESCENCE2D = FLUORESCENCE | SPEC2D;
			    public final static int GCMS = MS | GC;
			    public final static int LCMS = MS | LC;
	*/		
			spectrumType = SpectrumType.UNKNOWN;
			if (identifier == ISpectrumIdentifier.UNKNOWN) {
				//
			} else if (identifier == ISpectrumIdentifier.IR) {
				spectrumType = SpectrumType.IR;
			} else if (identifier == ISpectrumIdentifier.RAMAN) {
				spectrumType = SpectrumType.RAMAN;
			} else if (identifier == ISpectrumIdentifier.ULTRAVIOLET) {
				spectrumType = SpectrumType.UV;
			} else if (identifier == ISpectrumIdentifier.FLUORESCENCE) {
				spectrumType = SpectrumType.FLUORESCENCE;
			} else if (identifier == ISpectrumIdentifier.NMR) {
				spectrumType = SpectrumType.NMR;
			} else if (identifier == ISpectrumIdentifier.MASS) {
				spectrumType = SpectrumType.MASS;
			} else if (identifier == ISpectrumIdentifier.CHROMATOGRAM ||
				identifier == ISpectrumIdentifier.GC ||
				identifier == ISpectrumIdentifier.LC) {
				spectrumType = SpectrumType.CHROMATOGRAM;
			} else {
				spectrumType = SpectrumType.UNSUPPORTED;
			}
			return spectrumType;
		}
	@SuppressWarnings("unused")
	private void debug(AxisMap axisMap) {
		System.out.println(axisMap.getData());
		System.out.println(axisMap.getDataRange());
		System.out.println(axisMap.getFullViewRange());
		System.out.println(axisMap.getGrid());
		System.out.println(axisMap.getMapRange());
		System.out.println(axisMap.getZoomRange());
	}
	/**
		 * @param jcampSpectrum
		 */
		@SuppressWarnings("unused")
		private void debug(Spectrum jcampSpectrum) {
			System.out.println("XLAB "+jcampSpectrum.getXAxisLabel());
			AxisMap xAxisMap = jcampSpectrum.getXAxisMap();
			System.out.println("XAXIS "+xAxisMap);
			System.out.println("YLAB "+jcampSpectrum.getYAxisLabel());
			AxisMap yAxisMap = jcampSpectrum.getYAxisMap();
			System.out.println("YAXIS "+yAxisMap);
			System.out.println("OR "+jcampSpectrum.getOrigin());
			System.out.println("TITLE "+jcampSpectrum.getTitle());
			System.out.println("ID "+jcampSpectrum.getIdentifier());
			Collection<?> notes = jcampSpectrum.getNotes();
			for (Object obj : notes) {
				System.out.println("NOTE "+jcampSpectrum.getOwner());
			}
			System.out.println("OWNER "+jcampSpectrum.getOwner());
			if (jcampSpectrum instanceof Spectrum1D) {
				Spectrum1D spectrum1d = (Spectrum1D) jcampSpectrum;
				IOrderedDataArray1D xData = spectrum1d.getXData();
				CMLArray cmlXData = new CMLArray(xData.toArray());
	//			cmlXData.debug("XDATA");
				IDataArray1D yData = spectrum1d.getYData();
				CMLArray cmlYData = new CMLArray(yData.toArray());
	//			cmlXData.debug("YDATA");
			}
		}

}
