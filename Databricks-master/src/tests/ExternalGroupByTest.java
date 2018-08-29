package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import com.databricks.*;
import java.util.AbstractMap.SimpleEntry;

public class ExternalGroupByTest {
	public static void main(String args[]) {
		ExternalGroupByTest tester = new ExternalGroupByTest();
		System.out.println("Testing in-memory data processing:");
		tester.testInMomeryProcess();

		System.out.println("\nTesting external data processing:");
		tester.testExternalProcess();
	}

	private void testExternalProcess() {
		try {
			Path filePath = Paths.get("src/tests", "input.txt");
			BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
			LinkedList<Map.Entry<String, String>> data = new LinkedList<Map.Entry<String, String>>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] pair = line.split("\t");
				data.add(new SimpleEntry<String, String>(pair[0], pair[1]));
			}
			System.out.format("Total input records: \t%d\n", data.size());
			ExternalGroupBy externalProcessor = new ExternalGroupBy(5000, 10);
			FileIterator it = (FileIterator) externalProcessor.groupBy(data.iterator());
			int totalRecords = 0;
			int totalDataPoints = 0;
			while (it.hasNext()) {
				totalRecords++;
				Map.Entry<String, List<String>> item = it.next();
				List<String> values = item.getValue();
				totalDataPoints += values.size();
			}
			System.out.format("Output file:\t%s\n", it.getSourceFilePath());
			System.out.format("Total records:\t%d\n", totalRecords);
			System.out.format("Total data points in output:\t%d\n", totalDataPoints);
		} catch (IOException ex) {
			System.err.format("Failed to read data file: %s\n", ex);
		}
	}

	private void testInMomeryProcess() {
		ExternalGroupBy inMemoryProcessor = new ExternalGroupBy(50, 10);
		LinkedList<Map.Entry<String, String>> data = new LinkedList<Map.Entry<String, String>>();
		data.add(new SimpleEntry<String, String>("Charlie", "value 1"));
		data.add(new SimpleEntry<String, String>("Charlie", "value 2"));
		data.add(new SimpleEntry<String, String>("Charlie", "value 3"));
		data.add(new SimpleEntry<String, String>("Charlie", "value 4"));
		data.add(new SimpleEntry<String, String>("Charlie", "value 5"));
		data.add(new SimpleEntry<String, String>("Alice", "value 6"));
		data.add(new SimpleEntry<String, String>("Alice", "value 7"));
		data.add(new SimpleEntry<String, String>("Alice", "value 8"));
		data.add(new SimpleEntry<String, String>("Bob", "value 9"));
		data.add(new SimpleEntry<String, String>("Bob", "value 10"));
		data.add(new SimpleEntry<String, String>("Bob", "value 11"));
		data.add(new SimpleEntry<String, String>("Bob", "value 12"));
		data.add(new SimpleEntry<String, String>("David", "value 13"));
		data.add(new SimpleEntry<String, String>("David", "value 14"));
		data.add(new SimpleEntry<String, String>("David", "value 15"));
		data.add(new SimpleEntry<String, String>("David", "value 16"));
		data.add(new SimpleEntry<String, String>("David", "value 17"));
		data.add(new SimpleEntry<String, String>("David", "value 18"));

		Iterator<Map.Entry<String, List<String>>> it = inMemoryProcessor.groupBy(data.iterator());
		while (it.hasNext()) {
			Map.Entry<String, List<String>> item = it.next();
			String key = item.getKey();
			List<String> values = item.getValue();
			System.out.println(key + '\t' + Arrays.toString(values.toArray()));
		}
	}
}
