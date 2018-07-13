# Region Simplify

Use [RegionSimplify](regionsimplify.md) to simplify your regions like:

![Before](img/ini_.png) ![After](img/fin_5M_.png)


[![Before](img/ini_.png)](img/ini.png)



## Quick start

1. Download [regionsimplify.zip](regionsimplify/regionsimplify-1.3.1.zip?raw=true) and unzip somewhere.
2. Run: `java -jar RegionSimplify.jar -i pathTo/myRegions.shp` where `pathTo/myRegions.shp` is the path to the input regions. You can alternativelly edit and execute *RegionSimplify.bat* (or *RegionSimplify.sh* for Linux users).

## Usage

### Requirements

Java 1.7 or higher is required. The java version installed, if any, can be found with `java --version` command. Latest versions are available from [here](https://www.java.com/).

### Input data

* Input data must be a set of polygonal or multipolygonal features with no overlap between them.
* Only [SHP format](https://en.wikipedia.org/wiki/Shapefile) is currently supported. For any other format, use a converter such as [GDAL](http://www.gdal.org/) or [QGIS](https://www.qgis.org/).
* The projection of your input file must be specified. Both geographical and cartographic projections are supported.
* Some valid test datasets are provided in *test_data* folder.

### Input parameters

The help can be displayed with: `java -jar RegionSimplify.jar -h`

| Parameter | Required | Description | Default value |
| ------------- | ------------- |-------------| ------|
| -h | | Show the help message |  |
| -i,--inputFile *file* | * | Input file (SHP format) | |
| -o,--outputFile *file* | | Output file (SHP format) | out.shp |
| -s,--scaleDenominator *double* || The scale denominator for the target data | 50000|
| -inb,--roundNb *int* || Number of iterations of the process. A small value returns a result faster. | 10 |
| -mcn,--maxCoordinatesNumber *int* || TODO | 1000000 |
| -omcn,--objMaxCoordinateNumber *int* || TODO | 1000 |

## Support and contribution

Feel free to [ask support](https://github.com/eurostat/EuroGen/issues/new), fork the project or simply star it (it's always a pleasure). The source code is currently stored as part of [OpenCarto](https://github.com/jgaffuri/OpenCarto) repository.

<TODO images large>
<TODO section on large datasets explain partitionning JVM parameters>
<TODO make file chooser parameter config file>
<TODO Publish topology checker and topology corrector>
<TODO describe principles>
<TODO show comparisons with others>
<TODO explain parameters in the table>
<TODO showcase>
