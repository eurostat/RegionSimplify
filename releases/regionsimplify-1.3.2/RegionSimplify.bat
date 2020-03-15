mkdir test_out
java -jar RegionSimplify.jar -i test_data/bangladesh.gpkg -o test_out/bangladesh-5M.gpkg -s 5000000
java -jar RegionSimplify.jar -i test_data/bangladesh.gpkg -o test_out/bangladesh-10M.gpkg -s 10000000
