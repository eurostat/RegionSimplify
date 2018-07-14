# Region Simplify

Use [RegionSimplify](regionsimplify.md) to simplify your regions like [that](img/ex_lbl.gif?raw=true):

[![Before](img/ini_.png)](img/ex_lbl.gif?raw=true) [![After](img/fin_5M_.png)](img/ex_lbl.gif?raw=true)

## Quick start

1. Download [regionsimplify.zip](regionsimplify/regionsimplify-1.3.1.zip?raw=true) and unzip somewhere.
2. Run: `java -jar RegionSimplify.jar -i pathTo/myRegions.shp` where `pathTo/myRegions.shp` is the path to the input regions. You can alternativelly edit and execute *RegionSimplify.bat* (or *RegionSimplify.sh* for Linux users).

## Usage

### Requirements

Java 1.8 or higher is required. The java version installed, if any, can be found with `java --version` command. Recent versions of Java can be installed from [here](https://www.java.com/).

### Input data

* Input data must be a set of polygonal or multipolygonal features with no overlap between them.
* Only [SHP format](https://en.wikipedia.org/wiki/Shapefile) is currently supported. For any other format, use a converter such as [GDAL](http://www.gdal.org/) or [QGIS](https://www.qgis.org/).
* The projection of your input file must be specified. Both geographical and cartographic projections are supported.
* Some valid test datasets are provided in *test_data* folder.

### Input parameters

The help is displayed with `java -jar RegionSimplify.jar -h` command.

| Parameter | Required | Description | Default value |
| ------------- | ------------- |-------------| ------|
| -h | | Show the help message |  |
| -i, --inputFile *file* | * | Input file (SHP format) | |
| -o, --outputFile *file* | | Output file (SHP format) | out.shp |
| -s, --scaleDenominator *double* || The scale denominator for the target data. For a simplification to scale 1:1'000'000, the value should be 1000000. For a correspondance between zoom level and scale, see [here](https://gis.stackexchange.com/questions/7430/what-ratio-scales-do-google-maps-zoom-levels-correspond-to). | 50000|
| -inb, --roundNb *int* || Number of iterations of the process. A small value returns a result faster, while a high value returns better quality result. | 10 |
| -mcn, --maxCoordinatesNumber *int* || The maximum number of vertices of the input dataset. Above this value, the simplification will be applied automatically on a decomposition. See section below for more info. | 1000000 |
| -omcn, --objMaxCoordinateNumber *int* || The maximum number of vertices of each region dataset. Above this value, the simplification will be applied automatically on a decomposition of the input dataset. See section below for more info. | 1000 |

### Dealing with large datasets

[RegionSimplify](regionsimplify.md) can handle large datasets thanks to an automatic partionning mechanism. The principle is to decompose recursivelly the input dataset if it is too large, apply the simplification to the parts, and finally recompose the results. The partitionning is based on a quadtree structure as illustrated [here](img/gen_eurostat.pdf).

To use [RegionSimplify](regionsimplify.md) on large datasets, you should thus:
* Increase the memory allocated to the program with *Xmx* and *Xmx* parameters, such as: `java -Xmx12g -Xms4g -jar RegionSimplify.jar -i pathTo/myRegions.shp`
* Ajust the parameters *-mcn* and *-omcn* described in the table above. Low value mean intensive decomposition but fast simplifications. High values mean little decomposition but potentially time-consuming simplifications.

## Showcase

[RegionSimplify](regionsimplify.md) is used at [Eurostat-GISCO](http://ec.europa.eu/eurostat/web/gisco) for the production of [statistical and administrative unit datasets](http://ec.europa.eu/eurostat/web/gisco/geodata/reference-data/administrative-units-statistical-units) at different scales, such as [the NUTS dataset](http://ec.europa.eu/eurostat/web/gisco/geodata/reference-data/administrative-units-statistical-units/nuts). For more information, see [here](img/gen_eurostat.pdf).

## Support and contribution

Feel free to [ask support](https://github.com/eurostat/EuroGen/issues/new), fork the project or simply star it (it's always a pleasure). The source code is currently stored as part of [OpenCarto](https://github.com/jgaffuri/OpenCarto) repository.

<TODO make file chooser parameter config file>
<TODO Publish topology checker and topology corrector>
<TODO describe principles> <TODO show comparisons with others>
