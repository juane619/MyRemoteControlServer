package com.juane.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.juane.model.RemoteConstants;

public class RemoteCommandProcessManager {
	static File DIRECTORY;

	static {
		DIRECTORY = new File(RemoteConstants.EXTERNAL_TOOL_COMMANDS_DIRECTORY);
	}

	public static void processCommand(final String... commands) {
		final List<String> commandsProcessed = new ArrayList<>();

		commandsProcessed.add(RemoteConstants.CMD_COMMAND);
		commandsProcessed.add(RemoteConstants.CMD_C_PARAM);

		// for (final String c : commands) {
		// commandsProcessed.add(c);
		// }
		Collections.addAll(commandsProcessed, commands);

		final ProcessBuilder pb = new ProcessBuilder(commandsProcessed);
		pb.directory(DIRECTORY);

		try {
			pb.start();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
