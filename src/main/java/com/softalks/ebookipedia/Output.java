package com.softalks.ebookipedia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Output {

	private Output() {
		// SONAR mandatory
	}
	
	private static final File ROOT = new File(".java-generated");
	
	public static void print(String variable, Object value) {
		print(new File(ROOT, variable), value.toString());
	}
	
	private static void print(File file, String content) {
		file.getParentFile().mkdirs();
		try (OutputStream out = new FileOutputStream(file)) {
			out.write(content.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	
}