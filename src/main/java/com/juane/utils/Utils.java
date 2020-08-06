package com.juane.utils;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
		pb.directory(new File("mylibs"));

		try {
			pb.start();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Image getImage(final String pathAndFileName) {
		final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	public void showErrorOnGUI(final Component component, final String errMessage) {
		JOptionPane.showMessageDialog(component, errMessage);
	}
}
