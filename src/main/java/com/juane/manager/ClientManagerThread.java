package com.juane.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import com.juane.model.RemoteConstants;
import com.juane.view.MainFrame;

public class ClientManagerThread extends Thread {
	private static final Logger LOGGER = Logger.getLogger("ClientManagerThread");

	private final Socket socket;
	private boolean connected = true;
	MainFrame mainFrame = null;
	int idClient;

	public ClientManagerThread(final Socket socket, final MainFrame mainFrame, final int id) {
		this.socket = socket;
		this.mainFrame = mainFrame;
		idClient = id;
	}

	@Override
	public void interrupt() {
		LOGGER.info("DISCONNECTED CLIENT!\n");
		super.interrupt();
	}

	@Override
	public void run() {
		try {
			LOGGER.info("New client " + Thread.currentThread().getName() + " connected");

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mainFrame.mainPanel.updateGUIaddConnectedClient(idClient,
							socket.getRemoteSocketAddress().toString());
				}
			});

			final InputStream input = socket.getInputStream();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			final OutputStream output = socket.getOutputStream();
			final PrintWriter writer = new PrintWriter(output, true);

			String dataReceived = "";

			writer.println(RemoteConstants.WELCOME_CLIENT_MESSAGE);

			do {
				if (socket.isConnected()) {
					final int messageType = reader.read();
					dataReceived = reader.readLine();

					RemoteCommandProcessManager.processCommand(messageType, dataReceived);

					writer.println("Message of type " + messageType + " received.");
					if (dataReceived == null) {
						dataReceived = "";
						connected = false;
						close();
					}
				} else {
					close();
				}
			} while (!dataReceived.equals(RemoteConstants.BYE_CLIENT_MESSAGE) && connected);

			socket.close();

			this.interrupt();
		} catch (final IOException ex) {
			LOGGER.info("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void close() {
		LOGGER.info("Client " + Thread.currentThread().getId() + " disconnected.");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.mainPanel.updateGUIremoveConnectedClient(idClient);
			}
		});
	}
}
