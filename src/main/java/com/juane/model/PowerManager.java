/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juane.model;

import java.io.IOException;

/**
 *
 * @author juane
 */
public class PowerManager {

	private final Runtime runtime;
	private final String os;

	public PowerManager() {
		runtime = Runtime.getRuntime();
		os = System.getProperty("os.name").toLowerCase();
	}

	public void powerOff(final String minuts) {
		String power_off = "";

		if (isWindows()) {
			power_off += "shutdown -s -t " + minuts;
		} else if (isUnix()) {
			power_off += "shutdown -h " + minuts;
		} else if (isMac()) {
			power_off += "shutdown -h " + minuts;
		}

		try {
			runtime.exec(power_off);
		} catch (final IOException ex) {
			System.err.println("ERROR!");
		}
	}

	public void restart(final String minuts) {
		String restart = "";

		if (isWindows()) {
			restart += "shutdown -r -t " + minuts;
		} else if (isUnix()) {
			restart += "shutdown -r " + minuts;
		} else if (isMac()) {
			restart += "shutdown -r " + minuts;
		}

		try {
			runtime.exec(restart);
		} catch (final IOException ex) {
			System.err.println("ERROR!");
		}
	}

	public void sleep(final String minuts) {
		// String sleep = "";
		//
		// if (isWindows()) {
		// sleep += "shutdown -h -t " + minuts;
		// } else if (isUnix()) {
		// sleep += "timeout /t " + minuts +" && rundll32.exe powrprof.dll,SetSuspendState Sleep";
		// } else if (isMac()) {
		// sleep += "shutdown -s " + minuts;
		// }
		//
		// try {
		// _runtime.exec(sleep);
		// } catch (IOException ex) {
		// System.err.println("ERROR!");
		// }
	}

	public void lock(final String minuts) {
		// String lock = "";
		//
		// if (isWindows()) {
		// lock += "shutdown -h -t " + minuts;
		// } else if (isUnix()) {
		// lock += "timeout /t " + minuts +" && rundll32.exe powrprof.dll,SetSuspendState Sleep";
		// } else if (isMac()) {
		// lock += "shutdown -s " + minuts;
		// }
		//
		// try {
		// _runtime.exec(lock);
		// } catch (IOException ex) {
		// System.err.println("ERROR!");
		// }
	}

	public void cancel() {
		String cancel = "";

		if (isWindows()) {
			cancel += "shutdown -a";
		} else if (isUnix()) {
			cancel += "shutdown -c";
		} else if (isMac()) {
			cancel += "shutdown -c";
		}

		try {
			runtime.exec(cancel);
		} catch (final IOException ex) {
			System.err.println("ERROR!");
		}
	}

	private boolean isWindows() {
		return (os.contains("win"));
	}

	private boolean isMac() {
		return (os.contains("mac"));
	}

	private boolean isUnix() {
		return (os.contains("nix") || os.contains("nux") || os.contains("aix"));
	}
}
