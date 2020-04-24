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
		//TODO
		for(String inFormat : new String[] {/*"gpkg",*/"shp"})
			for(String outFormat : new String[] {"gpkg","shp","geojson"})
				for(String s : new String[] {"1","5","10","50"})
					for(String ds : new String[] {"chile", "bangladesh", "china_mainland", "indonesia", "panama", "philippines"}) {
						TesselationGeneralisationMain.main(new String[] {
								"-i", "src/test/resources/"+ds+"."+inFormat,
								"-o", "target/testout/"+ds+"_1_"+s+"_"+inFormat+"."+outFormat,
								"-inb", "3",
								"-s", s+"000000"});
						/*TesselationGeneralisationMain.main(new String[] {
								"-i", "src/test/resources/"+ds+"."+inFormat,
								"-p", "0",
								"-o", "target/testout/"+ds+"_0_"+s+"_"+inFormat+"."+outFormat,
								"-inb", "5",
								"-s", s+"000000"});*/
					}
	}

}
