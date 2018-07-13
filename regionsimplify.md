# Region Simplify

Use [RegionSimplify](https://github.com/eurostat/EuroGen/blob/master/regionsimplify.md) to simplify your regions like:

![Before](https://github.com/eurostat/EuroGen/raw/master/img/ini_.png) ![After](https://github.com/eurostat/EuroGen/raw/master/img/fin_5M_.png)

## Quick start

1. Java 1.7 or higher is required. If not already installed, get it from [here](https://www.java.com/)
2. Download [regionsimplify.zip](https://github.com/eurostat/EuroGen/blob/master/regionsimplify/regionsimplify-1.3.1.zip?raw=true)
3. Unzip somewhere.
4. Run: `java -jar RegionSimplify.jar -i pathTo/myRegions.shp` where `pathTo/myRegions.shp` is the path to the input regions. You can alternativelly edit and execute *RegionSimplify.bat* (*RegionSimplify.sh* for Linux users). The final result appears in a *out.shp* file or in a file specified via the `-o` option.

## Advanced use

### Input data

* Input data must be a set of polygonal or multipolygonal features with no overlap between them.
* Only [SHP format](https://en.wikipedia.org/wiki/Shapefile) is currently supported. For any other format, use a converter such as [GDAL](http://www.gdal.org/) or [QGIS](https://www.qgis.org/).
* The projection of your input file must be specified. Both geographical and cartographic projections are supported.

### Parameters

The parameters are described in the help: `java -jar RegionSimplify.jar -h`
Here they are:

| Parameter | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |


-h                                               Show this help message
 -i,--inputFile <file>                            Input file (SHP format).
 -id <string>                                     Optional. Id property to
                                                  link the units and the
                                                  points.
 -inb,--roundNb <int>                             Optional. Number of
                                                  iterations of the
                                                  process. Default: 10.
 -ip,--inputPointFile <file>                      Optional. Input file for
                                                  points (SHP format).
 -mcn,--Optional. maxCoordinatesNumber <int>      Default: 1000000.
 -o,--outputFile <file>                           Optional. Output file
                                                  (SHP format). Default:
                                                  'out.shp'.
 -omcn,--Optional. objMaxCoordinateNumber <int>   Default: 1000.
 -s,--scaleDenominator <double>                   Optional. The scale
                                                  denominator for the
                                                  target data. Default:
                                                  50000



| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |




<TODO make available file chooser>
<TODO parameter file>
<TODO Publish topology checker and topology corrector>
<TODO describe principles>
<TODO >