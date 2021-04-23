package com.bisoft.models;

import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public class WorkFolderTest {
	
	private final String directoryName = "dir_for_test";
	private final File directory = new File(Paths.get(System.getProperty("java.io.tmpdir"), directoryName).toUri());
	private FileReader reader;
	
	@BeforeGroups("throw")
	public void beforeThrowCase(){
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
	
	@AfterGroups("throw")
	public void afterThrowCase(){
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
	
	
	@Test(groups = {"throw"})
	public void shouldBeThrows() throws Exception {
		assertThat(new WorkFolder(directory))
			.isNotNull();
		assertThat(new WorkFolder(directory).prepare())
			.withFailMessage("Not all file were deleted!");
	}
	
	@BeforeGroups("normal")
	public void beforeNormal(){
		if (! directory.exists()){
			directory.mkdir();
		}
		try {
			File.createTempFile("tst","clear_1", directory);
			File.createTempFile("tst","clear_2", directory);
			File.createTempFile("tst","clear_3", directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterGroups("normal")
	public void afterNormal(){
		String[] children = directory.list();
		for (int i=0; i<children.length; i++) {
			new File(directory, children[i]).deleteOnExit();
		}
		directory.deleteOnExit();
	}
	
	
	@Test(groups = {"normal"})
	public void shouldWorkCorrectly() throws Exception {
		assertThat(new WorkFolder(directory).prepare())
			.isEqualTo(3);
	}
	
	
}
