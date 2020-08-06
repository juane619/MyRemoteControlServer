/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juane.view;

import java.awt.Color;
import java.awt.Dimension;

import com.juane.application.AppProperties;

/**
 *
 * @author juane
 */
public class MainFrame extends javax.swing.JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 8155163288829103052L;

	/**
	 * Creates new form MainFrame
	 */
	public MainFrame() {
		setBackground(Color.MAGENTA);
		getContentPane().setPreferredSize(new Dimension(400, 450));
		setLocationByPlatform(true);
		setUndecorated(true);
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		mainPanel = new MainPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
						450, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 33, Short.MAX_VALUE)));

		pack();

		setVersionOnGUI();
	}// </editor-fold>//GEN-END:initComponents

	public void updateGUIConnected(final String ipAddress, final int port) {
		mainPanel.labelServerStatus.setText("Connected");
		mainPanel.labelServerStatus.setForeground(new Color(63, 191, 63));

		mainPanel.labelServerIP.setText(ipAddress + ":" + port);
	}

	private void setVersionOnGUI() {
		mainPanel.lblAutorVersion.setText("By juanE " + AppProperties.getProperty(AppProperties.VERSION_PROPERTY));
	}

	public MainPanel mainPanel;
}
