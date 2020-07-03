/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.MainController;
import model.PowerManager;
import view.MainFrame;

/**
 *
 * @author juane
 */
public class MyRemoteControlApplication {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		PowerManager powerManage = new PowerManager();
		MainController mainController = new MainController(mainFrame, powerManage);
		
		mainController.start();
		
		mainFrame.setVisible(true);
	}
	
}
