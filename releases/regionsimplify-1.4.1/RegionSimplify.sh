#!/usr/bin/env bash

mkdir -p test_out

for dataset in "indonesia" "chile" "bangladesh" "china_mainland" "panama" "philippines"
do
	for scaleM in "5" "10"
	do
    	echo "Generalisation for "$dataset" - 1:"$scaleM"000000"
		java -jar RegionSimplify.jar -i "test_data/"$dataset".gpkg" -o "test_out/"$dataset"-"$scaleM"M.gpkg" -s $scaleM"000000"
	done
done
