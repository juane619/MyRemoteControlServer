/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author juane
 */
public class MainPanel extends javax.swing.JPanel {
	private static final Logger LOGGER = Logger.getLogger("MainPanel");
	/**
	 *
	 */
	private static final long serialVersionUID = -7409811611810730108L;

	JFrame topFrame = null;
	Point firstLocationMouse = null;

	/**
	 * Creates new form mainFrame
	 */
	public MainPanel() {
		setBorder(new LineBorder(Color.BLACK));

		initComponents();
		panel_center.setLayout(new BorderLayout(0, 0));

		panelServer = new JPanel();
		panelServer.setBackground(new Color(253, 245, 230));
		panelServer.setBorder(
				new TitledBorder(null, "Server settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServer.setPreferredSize(new Dimension(10, 90));
		panel_center.add(panelServer, BorderLayout.NORTH);
		panelServer.setLayout(null);

		final JLabel labelServerIP1 = new JLabel("Status");
		labelServerIP1.setBounds(20, 27, 85, 14);
		panelServer.add(labelServerIP1);

		final JLabel lblServerStatus = new JLabel("Server IP");
		lblServerStatus.setBounds(20, 52, 85, 14);
		panelServer.add(lblServerStatus);

		labelServerStatus = new JLabel("Disconnected");
		labelServerStatus.setForeground(Color.RED);
		labelServerStatus.setBounds(126, 27, 127, 14);
		panelServer.add(labelServerStatus);

		labelServerIP = new JLabel("");
		labelServerIP.setBounds(126, 52, 127, 14);
		panelServer.add(labelServerIP);

		rad_pow = new javax.swing.JRadioButton();
		rad_pow.setBackground(new Color(253, 245, 230));
		rad_rest = new javax.swing.JRadioButton();
		rad_rest.setBackground(new Color(253, 245, 230));
		rad_sleep = new javax.swing.JRadioButton();
		rad_sleep.setBackground(new Color(253, 245, 230));
		rad_lock = new javax.swing.JRadioButton();
		rad_lock.setBackground(new Color(253, 245, 230));

		buttonGroup_options.add(rad_pow);
		buttonGroup_options.add(rad_rest);
		buttonGroup_options.add(rad_sleep);
		buttonGroup_options.add(rad_lock);

		panelCenterPower = new JPanel();
		panelCenterPower.setBackground(new Color(37, 116, 169));
		panel_center.add(panelCenterPower, BorderLayout.CENTER);
		panelCenterPower.setLayout(new BorderLayout(0, 0));
		// jSpinner_count_h.setValue(1);
		panel_timer_countdown = new javax.swing.JPanel();
		panel_timer_countdown.setBackground(new Color(253, 245, 230));
		panelCenterPower.add(panel_timer_countdown, BorderLayout.CENTER);
		panel_timer_countdown.setPreferredSize(new Dimension(200, 10));
		jSpinner_count_h = new javax.swing.JSpinner();
		jSpinner_count_h.setModel(new SpinnerNumberModel(1, 0, 24, 1));
		jSpinner_count_m = new javax.swing.JSpinner();
		jSpinner_count_m.setModel(new SpinnerNumberModel(20, 0, 59, 1));
		jSpinner_count_s = new javax.swing.JSpinner();
		jSpinner_count_s.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();

		panel_timer_countdown.setBorder(javax.swing.BorderFactory.createTitledBorder("Countdown"));

		jLabel3.setText("HH");

		jLabel4.setText("SS");

		jLabel5.setText("MM");

		final javax.swing.GroupLayout panel_timer_countdownLayout = new javax.swing.GroupLayout(panel_timer_countdown);
		panel_timer_countdown.setLayout(panel_timer_countdownLayout);
		panel_timer_countdownLayout.setHorizontalGroup(
				panel_timer_countdownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panel_timer_countdownLayout.createSequentialGroup().addContainerGap()
								.addGroup(panel_timer_countdownLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jSpinner_count_h, javax.swing.GroupLayout.DEFAULT_SIZE, 41,
												Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panel_timer_countdownLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(panel_timer_countdownLayout.createSequentialGroup()
												.addComponent(jSpinner_count_m, javax.swing.GroupLayout.PREFERRED_SIZE,
														41, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jSpinner_count_s, javax.swing.GroupLayout.PREFERRED_SIZE,
														41, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(panel_timer_countdownLayout.createSequentialGroup()
												.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(jLabel4,
														javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(97, Short.MAX_VALUE)));
		panel_timer_countdownLayout.setVerticalGroup(panel_timer_countdownLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel_timer_countdownLayout.createSequentialGroup().addGap(4, 4, 4)
						.addGroup(panel_timer_countdownLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3)
								.addComponent(jLabel4).addComponent(jLabel5))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(panel_timer_countdownLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jSpinner_count_h, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jSpinner_count_m, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jSpinner_count_s, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(43, Short.MAX_VALUE)));
		panel_options = new javax.swing.JPanel();
		panel_options.setBackground(new Color(253, 245, 230));
		panel_options.setPreferredSize(new Dimension(125, 10));
		panelCenterPower.add(panel_options, BorderLayout.WEST);

		rad_lock.setText("Lock");

		panel_options.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

		rad_pow.setSelected(true);
		rad_pow.setText("Power OFf");
		rad_pow.setName("poweroff"); // NOI18N

		rad_rest.setText("Restart");
		rad_rest.setName("restart"); // NOI18N

		rad_sleep.setText("Sleep");
		rad_sleep.setName("sleep"); // NOI18N

		final javax.swing.GroupLayout panel_optionsLayout = new javax.swing.GroupLayout(panel_options);
		panel_options.setLayout(panel_optionsLayout);
		panel_optionsLayout.setHorizontalGroup(panel_optionsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel_optionsLayout.createSequentialGroup().addContainerGap()
						.addGroup(panel_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panel_optionsLayout.createSequentialGroup()
										.addGroup(panel_optionsLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(rad_pow, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(rad_rest, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(rad_sleep, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(rad_lock, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		panel_optionsLayout.setVerticalGroup(panel_optionsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel_optionsLayout.createSequentialGroup().addContainerGap().addComponent(rad_pow)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(rad_rest)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(rad_sleep)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(rad_lock)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		runButton = new javax.swing.JButton();
		panelButtonsControls = new JPanel();
		panelButtonsControls.setBackground(new Color(253, 245, 230));
		panelButtonsControls.add(runButton);

		runButton.setText("RUN");
		cancelButton = new javax.swing.JButton();
		panelButtonsControls.add(cancelButton);

		cancelButton.setText("CANCEL");

		panelButtonsControls.setPreferredSize(new Dimension(100, 40));
		panelCenterPower.add(panelButtonsControls, BorderLayout.SOUTH);

		final JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(253, 245, 230));
		panel_center.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new BorderLayout(0, 0));

		final JLabel lblByJuane = new JLabel("BY JuanE");
		lblByJuane.setBackground(new Color(253, 245, 230));
		lblByJuane.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblByJuane.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFooter.add(lblByJuane);

		panelClients = new JPanel();
		panelClients.setBackground(new Color(253, 245, 230));
		panelFooter.add(panelClients, BorderLayout.NORTH);
		panelClients.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clients connected",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelClients.setPreferredSize(new Dimension(10, 130));
		panelClients.setLayout(new BorderLayout(0, 0));

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		listClientsModel = new ClientsListModel();

		listClients = new JList<>(listClientsModel);
		listClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listClients.setBackground(Color.WHITE);

		listClients.setBounds(0, 0, 1, 1);

		scrollPane.setViewportView(listClients);

		panelClients.add(scrollPane);
	}

	public void updateGUIaddConnectedClient(final int index, final String client) {
		listClientsModel.add(index, client);
	}

	public void updateGUIremoveConnectedClient(final int index) {

		listClientsModel.remove(index);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		buttonGroup_options = new javax.swing.ButtonGroup();
		header_panel = new javax.swing.JPanel();
		header_panel.setBackground(new Color(1, 50, 67));
		header_panel.setPreferredSize(new Dimension(10, 45));
		panelMenu = new javax.swing.JPanel();
		panelMenu.setBackground(new Color(73, 128, 242));
		panel_center = new javax.swing.JPanel();
		panel_center.setBackground(new Color(73, 128, 242));
		final Date now = new Date();
		final SpinnerDateModel model = new SpinnerDateModel(now, null, null, Calendar.DAY_OF_MONTH);
		jSpinner_time = new javax.swing.JSpinner();

		setLayout(new java.awt.BorderLayout());
		header_panel.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(final MouseEvent e) {
				onTopMouseDragged(e);
			}
		});
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(final MouseEvent e) {
				onTopMouseClicked(e);
			}
		});
		panel.setBackground(new Color(1, 50, 67));
		panel.setPreferredSize(new Dimension(15, 10));
		header_panel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		lblMyRemoteControl = new javax.swing.JLabel();
		panel.add(lblMyRemoteControl);
		lblMyRemoteControl.setForeground(Color.WHITE);

		lblMyRemoteControl.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20)); // NOI18N
		lblMyRemoteControl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblMyRemoteControl.setText("My Remote Control");

		add(header_panel, java.awt.BorderLayout.NORTH);

		panelIconsWindow = new JPanel();
		panelIconsWindow.setBackground(new Color(5, 10, 46));
		panelIconsWindow.setPreferredSize(new Dimension(100, 50));
		header_panel.add(panelIconsWindow, BorderLayout.EAST);
		panelIconsWindow.setLayout(null);

		buttonClose = new JPanel();
		buttonClose.setBackground(new Color(5, 10, 46));
		buttonClose.setBounds(50, 0, 50, 45);
		panelIconsWindow.add(buttonClose);
		buttonClose.setLayout(new BorderLayout(0, 0));

		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent arg0) {
				changeColor(buttonClose, new Color(25, 29, 74));
			}

			@Override
			public void mouseExited(final MouseEvent e) {
				changeColor(buttonClose, new Color(5, 10, 46));
			}

			@Override
			public void mouseClicked(final MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(MainPanel.class.getResource("/icons/delete_32px.png")));
		buttonClose.add(lblNewLabel_1, BorderLayout.CENTER);

		buttonMaximize = new JPanel();
		buttonMaximize.setBackground(new Color(5, 10, 46));
		buttonMaximize.setBounds(0, 0, 50, 45);
		panelIconsWindow.add(buttonMaximize);
		buttonMaximize.setLayout(new BorderLayout(0, 0));

		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(final MouseEvent e) {
				changeColor(buttonMaximize, new Color(25, 29, 74));
			}

			@Override
			public void mouseExited(final MouseEvent e) {
				changeColor(buttonMaximize, new Color(5, 10, 46));
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(MainPanel.class.getResource("/icons/full_screen_32px.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		buttonMaximize.add(lblNewLabel_2, BorderLayout.CENTER);

		add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new BorderLayout(0, 0));
		panelLeftIcons.setPreferredSize(new Dimension(50, 200));
		panelLeftIcons.setBackground(new Color(1, 50, 67));
		panelMenu.add(panelLeftIcons);
		panelLeftIcons.setLayout(null);

		final JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(1, 50, 67));
		panel_1.setBounds(0, 5, 50, 50);
		panelLeftIcons.add(panel_1);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MainPanel.class.getResource("/icons/settings_32px.png")));
		panel_1.add(label);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(1, 50, 67));
		panel_1_1.setBounds(0, 55, 50, 50);
		panelLeftIcons.add(panel_1_1);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainPanel.class.getResource("/icons/info-2-32.png")));
		panel_1_1.add(label_1);

		final DateFormat df = new SimpleDateFormat("d/M/yyyy");

		final SpinnerDateModel model2 = new SpinnerDateModel(now, null, null, Calendar.DAY_OF_WEEK);

		add(panel_center, BorderLayout.CENTER);
	}// </editor-fold>//GEN-END:initComponents

	protected void onTopMouseClicked(final MouseEvent e) {
		firstLocationMouse = e.getLocationOnScreen();
	}

	private void onTopMouseDragged(final MouseEvent e) {
		if (topFrame != null) {
			final Point frameLocation = getLocationOnScreen();
			final Point mouseDifference = new Point(e.getLocationOnScreen().x - firstLocationMouse.x,
					e.getLocationOnScreen().y - firstLocationMouse.y);
			final Point finalFrameLocation = new Point(frameLocation.x + mouseDifference.x, frameLocation.y + mouseDifference.y);
			topFrame.setLocation(finalFrameLocation);
			firstLocationMouse = e.getLocationOnScreen();
		} else {
			topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		}
	}

	private void changeColor(final JPanel hover, final Color color) {
		hover.setBackground(color);
	}

	public javax.swing.ButtonGroup buttonGroup_options;
	public javax.swing.JButton cancelButton;
	public javax.swing.JButton runButton;
	private final javax.swing.JLabel jLabel3;
	private final javax.swing.JLabel jLabel4;
	private final javax.swing.JLabel jLabel5;
	private final javax.swing.JSpinner jSpinner_count_h;
	private final javax.swing.JSpinner jSpinner_count_m;
	private final javax.swing.JSpinner jSpinner_count_s;
	public javax.swing.JSpinner jSpinner_time;
	private javax.swing.JLabel lblMyRemoteControl;
	private javax.swing.JPanel panel_center;
	private javax.swing.JPanel panelMenu;
	private final javax.swing.JPanel panel_options;
	private final javax.swing.JPanel panel_timer_countdown;
	private javax.swing.JPanel header_panel;
	public javax.swing.JRadioButton rad_lock;
	public javax.swing.JRadioButton rad_pow;
	public javax.swing.JRadioButton rad_rest;
	public javax.swing.JRadioButton rad_sleep;
	private final JPanel panelLeftIcons = new JPanel();
	private JPanel panelIconsWindow;
	private JPanel buttonClose;
	private JPanel buttonMaximize;
	private JPanel panel;
	private final JPanel panelButtonsControls;
	private final JPanel panelServer;
	private JLabel label;
	private JLabel label_1;
	private final JPanel panelCenterPower;
	private final JPanel panelClients;

	public final JLabel labelServerStatus;
	public final JLabel labelServerIP;
	private final JList listClients;
	public final ClientsListModel listClientsModel;

	public String getjSpinner_count_h() {
		return jSpinner_count_h.getValue().toString();
	}

	public String getjSpinner_count_m() {
		return jSpinner_count_m.getValue().toString();
	}

	public String getjSpinner_count_s() {
		return jSpinner_count_s.getValue().toString();
	}

	private class ClientsListModel extends AbstractListModel<String> {

		/**
		 *
		 */
		private static final long serialVersionUID = 6606329930158084564L;
		Map<Integer, String> clientsMap = new HashMap<Integer, String>();

		@Override
		public String getElementAt(final int var1) {
			// TODO Auto-generated method stub
			return clientsMap.get(var1);
		}

		@Override
		public int getSize() {
			return clientsMap.size();
		}

		// if you need such updates:
		public void add(final int index, final String item) {
			LOGGER.info("Adding to list " + index);
			clientsMap.put(index, item);
			fireIntervalAdded(this, index, index);
		}

		public boolean remove(final Integer id) {
			final String removed = clientsMap.remove(id);
			if (removed == null) {
				return false;
			}
			LOGGER.info("Removing from list " + id);
			fireIntervalRemoved(this, id, id);
			return true;
		}

	}
}
