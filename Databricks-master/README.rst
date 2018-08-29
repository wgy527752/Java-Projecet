Databricks Code Assignment: External GroupBy
==============================================

Run Test:
------------------------------------
This project is developed with Eclipse.

``GroupByMap.java``: process the input. If all the input data can be processed in memory,
it will return the result directly. Otherwise, it will save partial results to temp files.

``GroupByReduce.java``: Similar to Reduce in MapReduce, it will process partial results in
temp files, and output the final result to a temp file. Then, a FileIterator is returned.

``FileIterator.java``: Iterate records in a file, parse them, and return them in the required format.

``ExternalGroupBy.java``: The main class to process input data. Two parameters are required:

* ``chunkSize``: specify the number of records that can be processed in memeory
* ``reduceSum``: specify the number of GroupByReduce threads that can be run in parallel.

Run Test:
------------------------------------
Overall test: 

.. code-block:: bash
	
	javac ExternalGroupByTest.java
	java ExternalGroupByTest
	
Overall test output: 

.. code-block:: bash

	Testing in-memory data processing:
	Alice	[value 6, value 7, value 8]
	Bob	[value 9, value 10, value 11, value 12]
	Charlie	[value 1, value 2, value 3, value 4, value 5]
	David	[value 13, value 14, value 15, value 16, value 17, value 18]

	Testing external data processing:
	Total input records: 	24360
	Output file:	/var/folders/3y/0fbxjxf914q4dt23_zttp07cgr1sgp/T/2789571709755756762.txt
	Total records: 	145
	Total data points in output: 	24360
	
FileIterator test:

.. code-block:: bash
	
	javac FileIteratorTest.java
	java FileIteratorTest
	
