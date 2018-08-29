package com.databricks;

import java.util.*;

public class ExternalGroupBy {
	private int chunkSize = 0;
	private int reduceNum = 0;

	public ExternalGroupBy(int chunkSize, int reduceNum) {
		this.chunkSize = chunkSize;
		this.reduceNum = reduceNum;
	}

	public Iterator<Map.Entry<String, List<String>>> groupBy(Iterator<Map.Entry<String, String>> input) {
		GroupByMap map = new GroupByMap(chunkSize, input);
		map.run();

		// All data can be processed in memory
		TreeMap<String, List<String>> inMemoryResult = map.getInMemoryResult();
		if (inMemoryResult != null) {
			return inMemoryResult.entrySet().iterator();
		}

		// External processing
		LinkedList<String> files = new LinkedList<String>(map.getFilePaths());
		while (files.size() > 1) {
			int reduceRunning = 0;
			LinkedList<GroupByReduce> tasks = new LinkedList<GroupByReduce>();
			while (files.size() > 1 && reduceRunning < reduceNum) {
				GroupByReduce task = new GroupByReduce(files.pollFirst(), files.pollFirst());
				task.start();
				tasks.add(task);
				reduceRunning++;
			}
			for (GroupByReduce task : tasks) {
				try {
					task.join();
					files.add(task.getOutputFilePath());
				} catch (InterruptedException ex) {
					System.err.format("One reduce thread faild:%s", ex);
					files.add(task.getInputFile1());
					files.add(task.getInputFile2());
				}
			}
		}
		return new FileIterator(files.pop());
	}
}
