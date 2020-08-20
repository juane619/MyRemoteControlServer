package com.juane.utils;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JOptionPane;

public class Utils {
	public static String parseRawVolumeData(final int rawVolumeData) {
		final float multiplier = (float) (rawVolumeData * 0.01);
		return String.valueOf(multiplier * 65535);
	}

	public static Image getImage(final String pathAndFileName) {
		final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	public void showErrorOnGUI(final Component component, final String errMessage) {
		JOptionPane.showMessageDialog(component, errMessage);
	}
}
