/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import model.PowerManage;
import view.MainFrame;

/**
 *
 * @author juane
 */
public class MainController implements ActionListener, ItemListener {

    MainFrame _mainView;
    PowerManage _powerManage;
    CardLayout card;

    public MainController(MainFrame main_frame, PowerManage power_manage) {
        _mainView = main_frame;
        _powerManage = power_manage;
        card = (CardLayout) _mainView.mainPanel1.panel_timer.getLayout();
    }

    public void start() {
        _mainView.setTitle("GUI Power Management");
        _mainView.setLocationRelativeTo(null);
        _mainView.mainPanel1.button_run.addActionListener(this);
        _mainView.mainPanel1.button_cancel.addActionListener(this);
        _mainView.mainPanel1.rad_count.addItemListener(this);
        _mainView.mainPanel1.rad_clock.addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        Enumeration<AbstractButton> buttons_opt = _mainView.mainPanel1.buttonGroup_options.getElements();

        if (src == _mainView.mainPanel1.button_run) {
            String option = "", time = "";

            for (; buttons_opt.hasMoreElements();) {
                AbstractButton button = buttons_opt.nextElement();

                if (button.isSelected()) {
                    option = button.getName();
                }
            }

            //time
            if (_mainView.mainPanel1.rad_count.isSelected()) {
                int hours = Integer.parseInt(_mainView.mainPanel1.getjSpinner_count_h().getValue().toString()) * 3600;
                int minutes = Integer.parseInt(_mainView.mainPanel1.getjSpinner_count_m().getValue().toString()) * 60;
                int seconds = Integer.parseInt(_mainView.mainPanel1.getjSpinner_count_s().getValue().toString());

                time += hours + minutes + seconds;

                if (null != option) {
                    switch (option) {
                        case "poweroff":
                            _powerManage.powerOff(time);
                            break;
                        case "restart":
                            _powerManage.restart(time);
                            break;
                        case "sleep":
                            _powerManage.sleep(time);
                            break;
                        case "lock":
                            _powerManage.lock(time);
                            break;
                        default:
                            break;
                    }
                }
            } else if (_mainView.mainPanel1.rad_clock.isSelected()) {
                Date timer = (Date) _mainView.mainPanel1.getjSpinner_time().getValue();
                Date date = (Date) _mainView.mainPanel1.getjSpinner_date().getValue();

                date.setHours(timer.getHours());
                date.setMinutes(timer.getMinutes());
                
                long time_aux = (date.getTime() - new Date().getTime())/1000;

                if (time_aux <= 0) {
                    time_aux = 30;
                    time += time_aux;
                }else
                    time += time_aux;

                System.out.println(new Date());
                System.out.println(date);
                System.out.println(time_aux);
                //int day
                if (null != option) {
                    switch (option) {
                        case "poweroff":
                            _powerManage.powerOff(time);
                            break;
                        case "restart":
                            _powerManage.restart(time);
                            break;
                        case "sleep":
                            _powerManage.sleep(time);
                            break;
                        case "lock":
                            _powerManage.lock(time);
                            break;
                        default:
                            break;
                    }
                }
            }

            //_mainView.showError(option);
        } else if (src == _mainView.mainPanel1.button_cancel) {
            //_mainView.showError("Cancelando..");
            _powerManage.cancel();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object src = e.getSource();

        if (src == _mainView.mainPanel1.rad_count) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                card.show(_mainView.mainPanel1.panel_timer, "card_count");
            }
        } else if (src == _mainView.mainPanel1.rad_clock) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                card.show(_mainView.mainPanel1.panel_timer, "card_clock");
            }
        }
    }

}
