package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop = new Properties();

	public static void loadConfig() {
		try {
			InputStream input = ConfigReader.class.getClassLoader()
					.getResourceAsStream("environment/config.properties");
//            FileInputStream input  = new FileInputStream("/src/test/resources/environment/config.properties");
			if (input == null) {
				throw new IOException("config.properties not found in classpath");
			}
			prop.load(input);
		} catch (IOException e) {
			System.out.println("❌ Error loading config.properties");
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return prop.getProperty(key);
	}
}