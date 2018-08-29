package com.databricks;

import java.util.Iterator;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;
import java.util.AbstractMap.SimpleEntry;

public final class FileIterator implements Iterator<Map.Entry<String, List<String>>> {
	private BufferedReader reader = null;
	private Map.Entry<String, List<String>> itemBuffer = null;
	private String fileName = null;

	public FileIterator(String fileName) {
		try {
			this.fileName = fileName;
			Path filePath = Paths.get(fileName);
			reader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
		} catch (IOException ex) {
			System.err.format("Failed to read file: %s\n%s\n", fileName, ex);
		}
	}

	@Override
	public boolean hasNext() {
		if (itemBuffer == null) {
			itemBuffer = parseNextLine();
		}

		if (itemBuffer != null) {
			return true;
		} else {
			try {
				reader.close();
			} catch (IOException ex) {
				System.err.format("Failed to close file");
			}
			return false;
		}
	}

	@Override
	public Map.Entry<String, List<String>> next() {
		Map.Entry<String, List<String>> nextItem = null;
		if (itemBuffer != null) {
			nextItem = itemBuffer;
			itemBuffer = null;
		} else {
			nextItem = parseNextLine();
		}
		return nextItem;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public String getSourceFilePath() {
		return this.fileName;
	}

	private Map.Entry<String, List<String>> parseNextLine() {
		try {
			String line = reader.readLine();
			if (line == null || line.isEmpty()) {
				return null;
			}
			String[] pair = line.split("\t");
			String key = pair[0];
			String values = pair[1].substring(1, pair[1].length() - 1);
			StringTokenizer tokens = new StringTokenizer(values, ",");
			List<String> value_list = new LinkedList<String>();
			while (tokens.hasMoreTokens()) {
				value_list.add(tokens.nextToken());
			}
			return new SimpleEntry<String, List<String>>(key, value_list);
		} catch (IOException ex) {
			System.err.format("Failed to read the next line from file");
			return null;
		}
	}
}