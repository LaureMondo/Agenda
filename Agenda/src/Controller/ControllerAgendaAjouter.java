package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;

import Model.ModelAgenda;

public class ControllerAgendaAjouter implements ActionListener, CaretListener {
	
	private JButton ajouterButton;
	private JTextField textField;
	private ModelAgenda model;
	private JList list;
	
	public ControllerAgendaAjouter(JButton ajouterButton, JTextField textField, JList list, ModelAgenda model) {
		this.ajouterButton = ajouterButton;
		this.textField = textField;
		this.list = list;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nouveauContact = textField.getText();
		model.addProperty(nouveauContact);
		model.addElement(nouveauContact);	
		textField.setText("");
		list.setSelectedValue(nouveauContact, true);
	}
	
	private boolean enableButton(CaretEvent e) {
		if(((JTextComponent) e.getSource()).getDocument().getLength() > 0) {
			if (!ajouterButton.isEnabled()) {
				ajouterButton.setEnabled(true);
				return true;
			}		
		} else {			
			ajouterButton.setEnabled(false);
		}
    	return false;
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		enableButton(e);
	}

}
