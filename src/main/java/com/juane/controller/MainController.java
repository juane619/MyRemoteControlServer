/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juane.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.swing.AbstractButton;

import com.juane.manager.PowerManager;
import com.juane.network.ServerThread;
import com.juane.utils.Constants;
import com.juane.view.MainFrame;

/**
 *
 * @author juane
 */
public class MainController implements ActionListener {
	private final Logger LOGGER = Logger.getLogger("MainController");

	MainFrame mainFrame;
	ServerThread serverThread;

	public MainController(final MainFrame mainFrame, ServerThread serverThread) {
		this.mainFrame = mainFrame;
		this.serverThread = serverThread;
	}

	public void start() {
		mainFrame.setTitle(Constants.APP_TITLE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.mainPanel.runButton.addActionListener(this);
		mainFrame.mainPanel.cancelButton.addActionListener(this);

		// Start server functions to manage clients connections
		this.serverThread.start();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object src = e.getSource();
		final Enumeration<AbstractButton> buttonsOpt = mainFrame.mainPanel.buttonGroup_options.getElements();

		if (src == mainFrame.mainPanel.runButton) {
			String option = null;

			while (buttonsOpt.hasMoreElements()) {
				final AbstractButton button = buttonsOpt.nextElement();

				if (button.isSelected()) {
					option = button.getName();
					break;
				}
			}

			if (option != null) {
				final String timeSeconds = getTimeSelectedInSeconds();

				runSelectedAction(option, timeSeconds);
			}
		} else if (src == mainFrame.mainPanel.cancelButton) {
			PowerManager.cancel();
		}
	}

	private void runSelectedAction(final String option, String timeSeconds) {
		if (timeSeconds.contentEquals("0")) {
			timeSeconds = "10";
		}

		switch (option) {
		case "poweroff":
			PowerManager.powerOff(timeSeconds);
			break;
		case "restart":
			PowerManager.restart(timeSeconds);
			break;
		case "sleep":
			PowerManager.sleep(timeSeconds);
			break;
		case "lock":
			PowerManager.lock(timeSeconds);
			break;
		default:
			break;
		}
	}

	private String getTimeSelectedInSeconds() {
		final int hours = Integer.parseInt(mainFrame.mainPanel.getjSpinner_count_h()) * 3600;
		final int minutes = Integer.parseInt(mainFrame.mainPanel.getjSpinner_count_m()) * 60;
		final int seconds = Integer.parseInt(mainFrame.mainPanel.getjSpinner_count_s());

		return String.valueOf(hours + minutes + seconds);
	}

}
