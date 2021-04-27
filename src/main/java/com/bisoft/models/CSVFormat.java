package com.bisoft.models;

import com.bisoft.interfaces.IFileFormat;
import com.bisoft.interfaces.ISavedFormat;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFormat implements ISavedFormat, IFileFormat {
	
	private final String delimiter;
	private OutputStreamWriter out;

	public CSVFormat(String delimiter){
		this.delimiter = delimiter;
	}

	@Override
	public void saveStart(File file) throws FileNotFoundException, UnsupportedEncodingException {
		out = new OutputStreamWriter(new FileOutputStream(file.toString()+".csv"), "UTF8");
	}

	@Override
	public void saveEnd() throws IOException {
		out.close();
	}

	@Override
	public void saveNext(List<String> row) throws IOException {
		out.write(row.stream().collect(Collectors.joining(delimiter)) + "\r\n");
	}
}
