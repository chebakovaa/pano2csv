package com.bisoft.models;

import com.bisoft.App;
import org.assertj.core.api.Condition;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.bisoft.helpers.SqlHelper.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

public class TableCollectionTest {
	
	private Connection connection;
	private String queryTables;
	private final String directoryName = "dir1_for_test";
	private final File directory = new File(Paths.get(System.getProperty("java.io.tmpdir"), directoryName).toUri());
	
	@BeforeMethod
	public void before() {
		if (! directory.exists()){
			directory.mkdir();
		}
		Properties property = new Properties();
		InputStream is = App.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			property.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String DB_URL = property.getProperty("jdbc.url"); // "jdbc:postgresql://192.168.1.60:1105/pitc";
		final String USER = property.getProperty("jdbc.username"); //"postgres";
		final String PASS = property.getProperty("jdbc.password");
		connection = getConnection(DB_URL, USER, PASS);
		
		InputStream queryStream = App.class.getClassLoader().getResourceAsStream("get_all_tables.sql");
		queryTables = new BufferedReader(
			new InputStreamReader(queryStream, StandardCharsets.UTF_8))
			.lines()
			.collect(Collectors.joining("\n"));
		
	}

	@Test
	public void shouldGetTableContent() throws SQLException, IOException {

		TableCollection tlb = new TableCollection(connection, queryTables);

		tlb.save(directory, new CSVFormat(";"));
		
		File[] files = directory.listFiles();
		assertThat(files).as("list of files").isNotEmpty();
		assertThat(files.length).as("count files").isGreaterThan(1);
		assertThat(files).as("list of files").doesNotHaveDuplicates();
		assertThat(Files.readString(files[0].toPath())
			.equals(Files.readString(files[1].toPath()))).isFalse();
		
	}
	
	@AfterMethod
	public void after() {
		String[] children = directory.list();
		for (int i=0; i<children.length; i++) {
			new File(directory, children[i]).deleteOnExit();
		}
		directory.deleteOnExit();
	}
	
}
