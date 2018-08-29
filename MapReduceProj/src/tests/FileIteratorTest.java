package tests;

import java.util.*;
import com.databricks.*;

public class FileIteratorTest {
	public static void main(String args[]) {
		FileIterator iterator = new FileIterator("src/tests/FileIteratorTestData.txt");
		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> item = iterator.next();
			String key = item.getKey();
			List<String> values = item.getValue();
			System.out.println(key + '\t' + Arrays.toString(values.toArray()));
		}
	}
}