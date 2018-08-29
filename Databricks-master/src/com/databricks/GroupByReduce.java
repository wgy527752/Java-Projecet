package com.databricks;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class GroupByReduce extends Thread {
	private String fileName1 = null;
	private String fileName2 = null;
	private String outputFile = null;

	public GroupByReduce(String fileName1, String fileName2) {
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
	}

	@Override
	public void run() {
		try {
			FileIterator it1 = new FileIterator(fileName1);
			FileIterator it2 = new FileIterator(fileName2);
			Path tempFile = Files.createTempFile(null, ".txt");
			BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.US_ASCII);

			Map.Entry<String, List<String>> item1 = null;
			Map.Entry<String, List<String>> item2 = null;
			while (it1.hasNext() || it2.hasNext()) {
				if (item1 == null && it1.hasNext()) {
					item1 = it1.next();
				}
				if (item2 == null && it2.hasNext()) {
					item2 = it2.next();
				}
				String line = null;
				if (item1 == null || (item2 != null && item1.getKey().compareTo(item2.getKey()) > 0)) {
					line = item2.getKey() + '\t' + Arrays.toString(item2.getValue().toArray());
					item2 = null;
				} else if (item2 == null || (item1 != null && item2.getKey().compareTo(item1.getKey()) > 0)) {
					line = item1.getKey() + '\t' + Arrays.toString(item1.getValue().toArray());
					item1 = null;
				} else {
					// item1 and item2 have the same key
					List<String> temp = new ArrayList<String>();
					temp.addAll(item1.getValue());
					temp.addAll(item2.getValue());
					line = item1.getKey() + '\t' + Arrays.toString(temp.toArray());
					item1 = null;
					item2 = null;
				}
				writer.write(line);
				writer.newLine();
			}
			outputFile = tempFile.toAbsolutePath().toString();
			writer.close();
		} catch (IOException x) {
			System.err.format("Failed to write data to temp file");
		}
	}

	public String getInputFile1() {
		return fileName1;
	}

	public String getInputFile2() {
		return fileName2;
	}

	public String getOutputFilePath() {
		return outputFile;
	}
}
