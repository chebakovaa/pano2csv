package com.bisoft.models;

import com.bisoft.interfaces.IModelObject;
import com.bisoft.interfaces.IModelSource;
import com.bisoft.interfaces.ISaveTarget;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ObjectStructureTest {

  @Test
	public void shouldWorkCorrectly() {
		Map<String,ByteArrayOutputStream> baosMap = Map.of("1", new ByteArrayOutputStream(), "2", new ByteArrayOutputStream());
		
		assertThatCode(() ->  new ObjectStructure(new IModelSource.Fake(
				List.of(
						new IModelObject.Fake("1", List.of("11", "12", "13"), List.of(List.of("111", "112", "113"), List.of("121", "122", "123")))
						, new IModelObject.Fake("2", List.of("21", "22", "23"), List.of(List.of("211", "212", "213"), List.of("221", "222", "223")))
						)
				),
				new ISaveTarget.Fake(
						baosMap
						, new CSVFormat(";")
				)).save())
				.doesNotThrowAnyException();
		assertThat(baosMap.get("1").toString().contains("121;122;123")).isTrue();
		assertThat(baosMap.get("2")).hasToString("21;22;23\r\n211;212;213\r\n221;222;223\r\n");
	}

}
