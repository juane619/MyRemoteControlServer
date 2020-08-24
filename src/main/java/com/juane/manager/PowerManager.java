/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juane.manager;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author juane
 */
public abstract class PowerManager {
	private static int interval;
	private static Timer timer = null;
	private static boolean running = false;

	public static void powerOff(final String seconds) {
		if (!running) {
			String command = "";

			if (RemoteCommandProcessManager.isWindows()) {
				command += "shutdown -s -t " + seconds;
			} else if (RemoteCommandProcessManager.isUnix()) {
				command += "shutdown -h " + seconds;
			} else if (RemoteCommandProcessManager.isMac()) {
				command += "shutdown -h " + seconds;
			}

			RemoteCommandProcessManager.processCommand(command);
			running = true;
		}
	}

	public static void restart(final String seconds) {
		// String command = "";
		//
		// if (RemoteCommandProcessManager.isWindows()) {
		// command += "shutdown -r -t " + seconds;
		// } else if (RemoteCommandProcessManager.isUnix()) {
		// command += "shutdown -r " + seconds;
		// } else if (RemoteCommandProcessManager.isMac()) {
		// command += "shutdown -r " + seconds;
		// }

		// try {
		// //runtime.exec(command);
		// } catch (final IOException ex) {
		// LOGGER.log(Level.SEVERE, "Error exucuting command: " + command);
		// }
	}

	public static void sleep(final String seconds) {
		if (!running) {
			String command = "";

			if (RemoteCommandProcessManager.isWindows()) {
				command += "%windir%\\System32\\rundll32.exe powrprof.dll,SetSuspendState Sleep";
			} else if (RemoteCommandProcessManager.isUnix()) {
				command += "shutdown -h -t " + seconds;
			} else if (RemoteCommandProcessManager.isMac()) {
				command += "shutdown -s " + seconds;
			}

			final String builtCommand = command;
			interval = Integer.parseInt(seconds);

			timer = new Timer();

			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					interval = setInterval(builtCommand);
				}
			}, 0, 1000);
			running = true;
		}
	}

	public static void lock(final String seconds) {
		// String lock = "";
		//
		// if (isWindows()) {
		// lock += "shutdown -h -t " + seconds;
		// } else if (isUnix()) {
		// lock += "timeout /t " + seconds +" && rundll32.exe powrprof.dll,SetSuspendState Sleep";
		// } else if (isMac()) {
		// lock += "shutdown -s " + seconds;
		// }
		//
		// try {
		// _runtime.exec(lock);
		// } catch (IOException ex) {
		// System.err.println("ERROR!");
		// }
	}

	public static void cancel() {
		if (running) {
			String command = "";

			if (RemoteCommandProcessManager.isWindows()) {
				command += "shutdown -a";
			} else if (RemoteCommandProcessManager.isUnix()) {
				command += "shutdown -c";
			} else if (RemoteCommandProcessManager.isMac()) {
				command += "shutdown -c";
			}

			if (timer != null) {
				timer.cancel();
			}

			RemoteCommandProcessManager.processCommand(command);
			running = false;
		}
	}

	private static final int setInterval(final String command) {
		if (interval == 1) {
			doTimerAction(command);
			timer.cancel();
		}
		return --interval;
	}

	private static void doTimerAction(final String command) {
		RemoteCommandProcessManager.processCommand(command);
	}

}
