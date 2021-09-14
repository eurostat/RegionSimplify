/**
 * 
 */
package eu.europa.ec.eurostat.regionsimplify;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.measure.Unit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.geotools.referencing.util.CRSUtilities;
import org.locationtech.jts.geom.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.jgiscotools.io.geo.GeoData;
import eu.europa.ec.eurostat.jgiscotools.regionsimplify.TesselationGeneralisation;
import eu.europa.ec.eurostat.jgiscotools.util.CRSType;

/**
 * @author julien Gaffuri
 *
 */
public class TesselationGeneralisationMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Options options = new Options();
		options.addOption(Option.builder("i").longOpt("inputFile").desc("Input file. The supported formats are GeoPackage (*.gpkg extension), SHP (*.shp extension) and GeoJSON (*.geojson extension).")
				.hasArg().argName("file").build());
		options.addOption(Option.builder("o").longOpt("outputFile").desc("Optional. Output file (format: GPKG, GeoJSON or SHP). Default: 'out.gpkg'.")
				.hasArg().argName("file").build());
		options.addOption(Option.builder("ip").longOpt("inputPointFile").desc("Optional. Input file for points (format: GPKG, GeoJSON or SHP).")
				.hasArg().argName("file").build());
		options.addOption(Option.builder("id").desc("Optional. Id property to link the units and the points.")
				.hasArg().argName("string").build());
		options.addOption(Option.builder("s").longOpt("scaleDenominator").desc("Optional. The scale denominator for the target data. Default: 50000")
				.hasArg().argName("double").build());
		options.addOption(Option.builder("p").longOpt("parallel").desc("Optional. Set to 1 to use multiple processors in parallel. Set to 0 otherwise. Default: 1.")
				.hasArg().argName("int").build());
		options.addOption(Option.builder("inb").longOpt("roundNb").desc("Optional. Number of iterations of the process. Default: 10.")
				.hasArg().argName("int").build());
		options.addOption(Option.builder("mcn").longOpt("Optional. maxCoordinatesNumber").desc("Default: 1000000.")
				.hasArg().argName("int").build());
		options.addOption(Option.builder("omcn").longOpt("Optional. objMaxCoordinateNumber").desc("Default: 1000.")
				.hasArg().argName("int").build());
		options.addOption(Option.builder("h").desc("Show this help message").build());

		CommandLine cmd = null;
		try { cmd = new DefaultParser().parse( options, args); } catch (ParseException e) {
			System.err.println( "Parsing failed.  Reason: " + e.getMessage() );
			return;
		}

		//help statement
		if(cmd.hasOption("h")) {
			new HelpFormatter().printHelp("java -jar opencarto-XXX.jar", options);
			return;
		}

		//String inFile = "src/test/resources/testTesselationGeneralisation.shp";
		String inFile = cmd.getOptionValue("i");
		if(inFile==null) {
			System.err.println("An input file should be specified with -i option. Use -h option to show the help message.");
			return;
		} else if(!new File(inFile).exists()) {
			System.err.println("Input file does not exist: "+inFile);
			return;
		}
		String outFile = cmd.getOptionValue("o");
		if(outFile == null) outFile = Paths.get("").toAbsolutePath().toString() + "/out.gpkg";
		String inPtFile = cmd.getOptionValue("ip");
		String idProp = cmd.getOptionValue("id");
		double scaleDenominator = cmd.getOptionValue("s") != null? Integer.parseInt(cmd.getOptionValue("s")) : 50000;
		String parallel_ = cmd.getOptionValue("p");
		boolean parallel = (parallel_ == null || !parallel_.equals("0"));
		int roundNb = cmd.getOptionValue("inb") != null? Integer.parseInt(cmd.getOptionValue("inb")) : 10;
		int maxCoordinatesNumber = cmd.getOptionValue("mcn") != null? Integer.parseInt(cmd.getOptionValue("mcn")) : 1000000;
		int objMaxCoordinateNumber = cmd.getOptionValue("omcn") != null? Integer.parseInt(cmd.getOptionValue("omcn")) : 1000;


		System.out.println("Load data from "+inFile);
		Collection<Feature> units = GeoData.getFeatures(inFile);
		if(idProp != null && !"".equals(idProp)) for(Feature unit : units) unit.setID( unit.getAttribute(idProp).toString() );

		HashMap<String, Collection<Point>> pointsInd = null;
		if(inPtFile != null && !"".equals(inPtFile)) {
			System.out.println("Load point data from "+inPtFile);
			ArrayList<Feature> points = GeoData.getFeatures(inPtFile);
			pointsInd = TesselationGeneralisation.indexPoints(points, idProp);
		}

		System.out.println("Launch generalisation");
		CRSType crsType = getCRSType(GeoData.getCRS(inFile));
		units = TesselationGeneralisation.runGeneralisation(units, pointsInd, crsType, scaleDenominator, parallel, roundNb, maxCoordinatesNumber, objMaxCoordinateNumber);

		System.out.println("Save output to "+outFile);
		GeoData.save(units, outFile, GeoData.getCRS(inFile));

		System.out.println("End");
	}




	//TODO use projectionutil instead
	private static CRSType getCRSType(Unit<?> unit) {
		if(unit == null) return CRSType.UNKNOWN;
		switch (unit.toString()) {
		case "": return CRSType.UNKNOWN;
		case "°": return CRSType.GEOG;
		case "deg": return CRSType.GEOG;
		case "dms": return CRSType.GEOG;
		case "degree": return CRSType.GEOG;
		case "m": return CRSType.CARTO;
		default:
			System.err.println("Unexpected unit of measure for projection: "+unit);
			return CRSType.UNKNOWN;
		}
	}

	//TODO use projectionutil instead
	private static CRSType getCRSType(CoordinateReferenceSystem crs) {
		return getCRSType(CRSUtilities.getUnit(crs.getCoordinateSystem()));
	}

}
