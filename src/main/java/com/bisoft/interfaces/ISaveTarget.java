package com.bisoft.interfaces;

import com.bisoft.models.OpenedFile;
import com.bisoft.navi.common.interfaces.ISavedFormat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface ISaveTarget{
	IOpenedFile savedFormat(String name) throws FileNotFoundException, UnsupportedEncodingException;

	class Fake implements ISaveTarget {

		private final ISavedFormat format;
		private final Map<String, ByteArrayOutputStream> streams;
		
		public Fake(Map<String, ByteArrayOutputStream> os, ISavedFormat sf){
			this.streams = os;
			this.format = sf;
		}

		@Override
		public IOpenedFile savedFormat(String name) throws UnsupportedEncodingException {
			return new OpenedFile(
				new OutputStreamWriter(streams.get(name), "UTF8"), format
			);
		}
	}
}
