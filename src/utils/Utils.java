package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static String parseRawVolumeData(final int rawVolumeData) {
		final float multiplier = (float) (rawVolumeData * 0.01);
		return String.valueOf(multiplier * 65535);
	}

	public static void processCommand(final String... commands) {
		final List<String> commandsProcessed = new ArrayList<String>();
		commandsProcessed.add("cmd");
		commandsProcessed.add("/c");

		for (final String c : commands) {
			commandsProcessed.add(c);
		}

		final ProcessBuilder pb = new ProcessBuilder(commandsProcessed);
		pb.directory(new File("lib"));

		try {
			pb.start();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
