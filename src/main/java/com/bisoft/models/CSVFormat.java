package com.bisoft.models;

import com.bisoft.interfaces.IFileFormat;
import com.bisoft.interfaces.ISavedFormat;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CSVFormat implements ISavedFormat {
	
	private final String delimiter;
	private OutputStreamWriter out;

	public CSVFormat(String delimiter){
		this.delimiter = delimiter;
	}

	@Override
	public void save(OutputStreamWriter out, Iterable<String> row) throws IOException {
		  out.write(StreamSupport.stream(row.spliterator(), false).collect(Collectors.joining(delimiter)) + "\r\n");
	}

}
