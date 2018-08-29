package com.databricks;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class GroupByMap {
	private ArrayList<String> filePaths = null;
	private int chunkSize = 0;
	private Iterator<Map.Entry<String, String>> input;
	private TreeMap<String, List<String>> hashMap = null;

	public GroupByMap(int chunkSize, Iterator<Map.Entry<String, String>> input) {
		this.chunkSize = chunkSize;
		this.input = input;
	}

	public void run() {
		TreeMap<String, List<String>> chunk = new TreeMap<String, List<String>>();
		int recordProcessed = 0;
		while (input.hasNext()) {
			Map.Entry<String, String> record = input.next();
			String key = record.getKey();
			String value = record.getValue();
			if (chunk.containsKey(key)) {
				chunk.get(key).add(value);
			} else {
				LinkedList<String> list = new LinkedList<String>();
				list.add(value);
				chunk.put(key, list);
			}
			recordProcessed++;
			if (recordProcessed >= chunkSize) {
				saveToFile(chunk);
				recordProcessed = 0;
			}
		}

		// all records can be processed in memory
		if (filePaths == null) {
			hashMap = chunk;
		} else if (!chunk.isEmpty()) {
			saveToFile(chunk);
		}
	}

	public ArrayList<String> getFilePaths() {
		return filePaths;
	}

	public TreeMap<String, List<String>> getInMemoryResult() {
		return hashMap;
	}

	private void saveToFile(TreeMap<String, List<String>> chunk) {
		if (filePaths == null) {
			filePaths = new ArrayList<String>();
		}
		try {
			Path tempFile = Files.createTempFile(null, ".txt");
			BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.US_ASCII);
			for (Map.Entry<String, List<String>> item : chunk.entrySet()) {
				String key = item.getKey();
				List<String> values = item.getValue();
				writer.write(key + '\t' + Arrays.toString(values.toArray()));
				writer.newLine();
			}
			filePaths.add(tempFile.toAbsolutePath().toString());
			chunk.clear();
			writer.close();
		} catch (IOException x) {
			System.err.format("Failed to write data to temp file");
		}
	}
}
