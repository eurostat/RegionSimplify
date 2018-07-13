# Region Simplify

Use RegionSimplify to simplify like:
![Before](https://github.com/eurostat/EuroGen/raw/master/img/ini_.png) ![After](https://github.com/eurostat/EuroGen/raw/master/img/fin_5M_.png)

## Quick start

[Download][1], unzip somewhere, double click on RegionSimplify.bat (windows) or RegionSimplify.sh (linux), select your input file, and save the output.
TODO: implement file chooser

## Advanced use

### Install detail

RegionSimplify requires Java 1.7 or higher to be installed.
TODO: utility to check java is installed?
You can get Java from: (link)

### Input data

Input data should be a set of polygonal or multipolygonal features with no overlap between them. To check the integrity of the input file, you can use <TODO>.
<TODO> can be used to fix the input file.
Currently, only SHP files are supported.

### Execution and parameters

RegionSimplify can be executed by double clicking on the execution file, as described in the quick start section. This execution will use default parameters.

To lanch simplifications with special parameters, launch command such as:
`java -jar RegionSimplify.jar ...`

| -h            | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

[1]: https://github.com/eurostat/EuroGen/blob/master/regionsimplify/regionsimplify-1.3.1.zip?raw=true
