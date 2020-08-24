package com.juane.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.juane.model.MessagesType;
import com.juane.model.RemoteConstants;
import com.juane.utils.Utils;

public class RemoteCommandProcessManager {
	private static final Logger LOGGER = Logger.getLogger("RemoteCommandProcessManager");

	private static File externToolDirectory;
	private static String os;

	// right or left down
	private static double multiplier = 1.00;
	private static int times = 10;

	static {
		externToolDirectory = new File(RemoteConstants.EXTERNAL_TOOL_COMMANDS_DIRECTORY);
		os = System.getProperty("os.name").toLowerCase();
	}

	public static void processCommand(final int messageType, final String dataReceived) {
		switch (messageType) {
		case MessagesType.VOLUME_MESSAGE:
			final String volume = dataReceived;

			final String realVolume = Utils.parseRawVolumeData(Integer.valueOf(volume));

			RemoteCommandProcessManager.processCommand(RemoteConstants.EXTERN_TOOL_COMMAND,
					RemoteConstants.SYSVOLUME_COMMAND, realVolume);

			break;
		case MessagesType.KEYLEFT_MESSAGE:
			resetMultiplierBackwardForward();
			RemoteCommandProcessManager.processCommand(RemoteConstants.EXTERN_TOOL_COMMAND,
					RemoteConstants.KEYPRESS_COMMAND, RemoteConstants.LEFT_KEY_PARAM);
			break;
		case MessagesType.KEYRIGHT_MESSAGE:
			resetMultiplierBackwardForward();
			RemoteCommandProcessManager.processCommand(RemoteConstants.EXTERN_TOOL_COMMAND,
					RemoteConstants.KEYPRESS_COMMAND, RemoteConstants.RIGHT_KEY_PARAM);
			break;
		case MessagesType.KEYLEFT_LONG_MESSAGE:
			times = calculateTimesKeysDown();
			do {
				times--;
				RemoteCommandProcessManager.processCommand(RemoteConstants.EXTERN_TOOL_COMMAND,
						RemoteConstants.SENDKEY_COMMAND, RemoteConstants.LEFT_KEY_PARAM, RemoteConstants.DOWN_PARAM);
			} while (times > 0);
			break;
		case MessagesType.KEYRIGHT_LONG_MESSAGE:
			times = calculateTimesKeysDown();
			do {
				times--;
				RemoteCommandProcessManager.processCommand(RemoteConstants.EXTERN_TOOL_COMMAND,
						RemoteConstants.SENDKEY_COMMAND, RemoteConstants.RIGHT_KEY_PARAM, RemoteConstants.DOWN_PARAM);
			} while (times > 0);
			break;
		case MessagesType.KEYSPACE_MESSAGE:
			RemoteCommandProcessManager.processCommand(RemoteConstants.EXTERN_TOOL_COMMAND,
					RemoteConstants.KEYPRESS_COMMAND, RemoteConstants.SPC_KEY_PARAM);
			break;
		case MessagesType.POWER_OFF_MESSAGE:
			final String secondsToPower = String.valueOf(Long.valueOf(dataReceived) / 1000);
			PowerManager.powerOff(secondsToPower);
			break;
		case MessagesType.SUSPEND_OFF_MESSAGE:
			final String secondsToSleep = String.valueOf(Integer.valueOf(dataReceived) / 1000);
			PowerManager.sleep(secondsToSleep);
			break;
		case MessagesType.CANCEL_POWER_ACTION_MESSAGE:
			PowerManager.cancel();
			break;
		default:
			break;
		}
	}

	public static void processCommand(final String... commands) {
		final List<String> commandsProcessed = new ArrayList<>();

		if (isWindows()) {
			commandsProcessed.add(RemoteConstants.CMD_COMMAND);
			commandsProcessed.add(RemoteConstants.CMD_C_PARAM);
		} else if (isUnix()) {
			commandsProcessed.add(RemoteConstants.BASH_COMMAND);
			commandsProcessed.add(RemoteConstants.BASH_C_PARAM);
		}

		Collections.addAll(commandsProcessed, commands);

		final ProcessBuilder pb = new ProcessBuilder(commandsProcessed);
		pb.directory(externToolDirectory);

		try {
			pb.start();
			LOGGER.log(Level.INFO, "Command executed: " + pb.command().toString());
		} catch (final IOException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
		}
	}

	public static boolean isWindows() {
		return (os.contains("win"));
	}

	public static boolean isMac() {
		return (os.contains("mac"));
	}

	public static boolean isUnix() {
		return (os.contains("nix") || os.contains("nux") || os.contains("aix"));
	}

	private static void resetMultiplierBackwardForward() {
		multiplier = 1.0;
	}

	// TO DO: more complex way of calculate times
	private static int calculateTimesKeysDown() {
		final int returnTimes = (int) (10 * multiplier);
		multiplier += 0.20;
		return returnTimes;
	}
}
