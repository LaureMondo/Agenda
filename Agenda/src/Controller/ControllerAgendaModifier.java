package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Model.ModelAgenda;

public class ControllerAgendaModifier implements ActionListener, CaretListener {
	
	private JButton validerButton;
	private JTextField contactInfoChamp;
	private ModelAgenda model;
	private JList list;

	public ControllerAgendaModifier(JButton validerButton, JTextField contactInfoChamp, JList list, ModelAgenda model) {
		this.validerButton = validerButton;
		this.contactInfoChamp = contactInfoChamp;
		this.model = model;
		this.list = list;
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String modificationContact = contactInfoChamp.getText();
		model.changeProperty(list.getSelectedValue().toString(), modificationContact);
	}

	

}
