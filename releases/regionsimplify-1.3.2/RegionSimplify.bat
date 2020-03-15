mkdir test_out
java -jar RegionSimplify.jar -i test_data/bangladesh.shp -o test_out/bangladesh-5M.shp -s 5000000
java -jar RegionSimplify.jar -i test_data/bangladesh.shp -o test_out/bangladesh-10M.shp -s 10000000
