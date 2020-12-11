/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juane.application;

import com.juane.controller.MainController;
import com.juane.network.ServerThread;
import com.juane.view.MainFrame;

/**
 *
 * @author juane
 */
public class MyRemoteControlApplication {

	/**
	 * @param args the command line arguments
	 */
	public static void main(final String[] args) {
		final MainFrame mainFrame = new MainFrame();
		ServerThread serverThread = new ServerThread(mainFrame);

		final MainController mainController = new MainController(mainFrame, serverThread);

		mainController.start();
		mainFrame.setVisible(true);
	}

}
