package com.juane.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import com.juane.view.MainFrame;

public class ServerThread extends Thread {
	private final Logger LOGGER = Logger.getLogger("Serverthread");

	final static int PORT = 8787;

	ServerSocket serverSocket = null;
	MainFrame mainFrame = null;
	public static int clientsConnected = 0;

	public ServerThread(final MainFrame mainPanel) {
		mainFrame = mainPanel;
	}

	@Override
	public void interrupt() {
		LOGGER.info("STOPPING SERVER " + this.toString());

		super.interrupt();
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(PORT);
			LOGGER.info("Server is listening on port " + PORT);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {

					String ipAddress = null;
					int portSetted = -1;
					try {
						ipAddress = InetAddress.getLocalHost().getHostAddress();
						portSetted = serverSocket.getLocalPort();
					} catch (final UnknownHostException e) {
						LOGGER.info("Error getting IP/port..");
						e.printStackTrace();
					}

					if (portSetted != -1) {
						mainFrame.updateGUIConnected(ipAddress, portSetted);
					} else {
						LOGGER.info("Error getting IP/port..");
					}
				}
			});

			while (serverSocket.isBound()) {
				final Socket socket = serverSocket.accept();

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						mainFrame.mainPanel.updateGUIaddConnectedClient(clientsConnected + 23, "");
					}
				});

				LOGGER.info("Creating client " + clientsConnected);
				new ClientManagerThread(socket, mainFrame, clientsConnected++).start();
			}
		} catch (final IOException e) {
			LOGGER.info("Server exception: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
