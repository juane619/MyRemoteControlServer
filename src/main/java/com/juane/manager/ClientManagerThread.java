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

import com.juane.model.MessagesType;
import com.juane.view.MainFrame;

public class ClientManagerThread extends Thread {
	private static final Logger LOGGER = Logger.getLogger("ClientManagerThread");

	private final Socket socket;
	private final boolean connected = true;
	MainFrame mainFrame = null;
	int clientId;

	public ClientManagerThread(final Socket socket, final MainFrame mainFrame, final int id) {
		this.socket = socket;
		this.mainFrame = mainFrame;
		clientId = id;
	}

	@Override
	public void run() {
		try {
			LOGGER.info("New client " + Thread.currentThread().getName() + " connected");

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mainFrame.mainPanel.updateGUIaddConnectedClient(clientId,
							socket.getRemoteSocketAddress().toString());
				}
			});

			final InputStream input = socket.getInputStream();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			final OutputStream output = socket.getOutputStream();
			final PrintWriter writer = new PrintWriter(output, true);

			String dataReceived = "";

			// first connection with this client
			if (writer != null && !writer.checkError()) {
				writer.write(MessagesType.CONNECT_REQUEST_MESSAGE);
				writer.println(clientId);

				writer.flush();
			}

			// Wait messages from client
			do {
				if (socket.isConnected()) {
					final int messageType = reader.read();

					// Android client disconnected not gracefully
					if (messageType == -1) {
						break;
					}

					dataReceived = reader.readLine();

					// Android client disconnected gracefully
					if (messageType == MessagesType.DISCONNECT_REQUEST_MESSAGE) {
						break;
					}

					RemoteCommandProcessManager.processCommand(messageType, dataReceived);
					// writer.println("Message of type " + messageType + " received.");
				}
			} while (connected);

			close();
		} catch (final IOException ex) {
			LOGGER.info("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			LOGGER.info("In finally");
		}
	}

	private void close() {
		LOGGER.info("Client " + clientId + " disconnected.");

		try {
			socket.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.mainPanel.updateGUIremoveConnectedClient(clientId);
			}
		});
	}
}
