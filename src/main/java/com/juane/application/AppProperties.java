package com.juane.application;

import java.io.IOException;
import java.util.Properties;

public class AppProperties {
	private static final String PROPERTIES_FILE_NAME = "app.properties";
	public static final String VERSION_PROPERTY = "app.version";

	public static Properties configuration = null;

	static {
		try {
			configuration = new Properties();
			configuration
					.load(MyRemoteControlApplication.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private AppProperties() {
	}

	public static String getProperty(final String property) {
		return configuration.getProperty(property);
	}
}