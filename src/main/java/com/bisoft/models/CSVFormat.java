package com.bisoft.models;

import com.bisoft.interfaces.ISavedFormat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFormat implements ISavedFormat {
	
	private final String delimiter;
	
	public CSVFormat(String delimiter){
		this.delimiter = delimiter;
	}
	
	@Override
	public void save(List<String> row) {
		output.write(row.stream().collect(Collectors.joining(delimiter)) + "\r\n");
	}
}
