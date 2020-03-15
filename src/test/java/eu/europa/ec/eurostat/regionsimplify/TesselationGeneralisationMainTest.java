package eu.europa.ec.eurostat.regionsimplify;

import junit.framework.TestCase;

/**
 * @author julien Gaffuri
 *
 */
public class TesselationGeneralisationMainTest extends TestCase {


	public static void main(String[] args) {
		junit.textui.TestRunner.run(TesselationGeneralisationMainTest.class);
	}

	public void test() {
		for(String s : new String[] {"5","10"})
			for(String inFormat : new String[] {"gpkg","shp"})
				for(String outFormat : new String[] {"gpkg","shp","geojson"})
					for(String ds : new String[] {"bangladesh", "chile", "china_mainland", "indonesia", "panama", "philippines"})
						TesselationGeneralisationMain.main(new String[] {
								"-i", "src/test/resources/"+ds+"."+inFormat,
								"-o", "target/testout/"+ds+"_"+s+"_"+inFormat+"."+outFormat,
								"-s", s+"000000"});
	}

}
