/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import model.PowerManager;
import network.ServerThread;
import utils.Constants;
import view.MainFrame;

/**
 *
 * @author juane
 */
public class MainController implements ActionListener {

	MainFrame mainView;
	PowerManager powerManage;
	ServerThread serverController;

	public MainController(final MainFrame mainFrame, final PowerManager powerManage) {
		mainView = mainFrame;
		this.powerManage = powerManage;
	}

	public void start() {
		mainView.setTitle(Constants.APP_TITLE);
		mainView.setLocationRelativeTo(null);
		mainView.mainPanel.runButton.addActionListener(this);
		mainView.mainPanel.cancelButton.addActionListener(this);

		// Start server functions to manage clients connections
		new ServerThread(mainView).start();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object src = e.getSource();
		final Enumeration<AbstractButton> buttonsOpt = mainView.mainPanel.buttonGroup_options.getElements();

		if (src == mainView.mainPanel.runButton) {
			String option = null;

			while (buttonsOpt.hasMoreElements()) {
				final AbstractButton button = buttonsOpt.nextElement();

				if (button.isSelected()) {
					option = button.getName();
					break;
				}
			}

			if (option != null) {
				final String time = getTimeSelected();

				runSelectedAction(option, time);
			}
		} else if (src == mainView.mainPanel.cancelButton) {
			powerManage.cancel();
		}
	}

	private void runSelectedAction(final String option, String time) {
		if (time.contentEquals("0")) {
			time = "10";
		}

		switch (option) {
		case "poweroff":
			powerManage.powerOff(time);
			break;
		case "restart":
			powerManage.restart(time);
			break;
		case "sleep":
			powerManage.sleep(time);
			break;
		case "lock":
			powerManage.lock(time);
			break;
		default:
			break;
		}
	}

	private String getTimeSelected() {
		final int hours = Integer.parseInt(mainView.mainPanel.getjSpinner_count_h()) * 3600;
		final int minutes = Integer.parseInt(mainView.mainPanel.getjSpinner_count_m()) * 60;
		final int seconds = Integer.parseInt(mainView.mainPanel.getjSpinner_count_s());

		return String.valueOf(hours + minutes + seconds);
	}

}
