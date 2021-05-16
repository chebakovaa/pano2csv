package com.bisoft.interfaces;

import com.bisoft.models.OpenedFile;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public interface ISaveTarget{
	IOpenedFile savedFormat(String name) throws FileNotFoundException, UnsupportedEncodingException;

	public class Fake implements ISaveTarget {

		private final OutputStreamWriter stream;
		private final ISavedFormat format;

		public Fake(OutputStreamWriter os, ISavedFormat sf){
			this.stream = os;
			this.format = sf;
		}

		@Override
		public IOpenedFile savedFormat(String name) throws FileNotFoundException, UnsupportedEncodingException {
			return new OpenedFile(
				stream, format
			);
		}
	}
}
