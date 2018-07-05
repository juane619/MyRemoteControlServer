/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guipowermanagement;

import controller.MainController;
import model.PowerManage;
import view.MainFrame;

/**
 *
 * @author juane
 */
public class GUIPowerManagement {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		MainFrame main_frame = new MainFrame();
		PowerManage powerManage = new PowerManage();
		MainController mainController = new MainController(main_frame, powerManage);
		
		mainController.start();
		
		main_frame.setVisible(true);
	}
	
}
