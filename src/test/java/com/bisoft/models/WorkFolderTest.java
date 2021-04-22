package com.bisoft.models;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkFolderTest {
	
	private final String directoryName = "dir_for_test";
	private final File directory = new File(Paths.get(System.getProperty("java.io.tmpdir"), directoryName).toUri());
	private FileReader reader;
	
	@BeforeEach
	public void before(){
		if (! directory.exists()){
			directory.mkdir();
		}
		try {
			File.createTempFile("tst","clear_1", directory);
			File.createTempFile("tst","clear_2", directory);
			File file = File.createTempFile("tst","clear_3", directory);
			reader = new FileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterEach
	public void after(){
		try {
			reader.close();
			String[] children = directory.list();
			for (int i=0; i<children.length; i++) {
				new File(directory, children[i]).deleteOnExit();
			}
			directory.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void shouldThrows()
	{
		 Exception	exception = assertThrows(Exception.class, () ->  new WorkFolder(directory).prepare());
		 assertTrue(exception.getMessage().equals("Not all file were deleted!"));
	}
}
