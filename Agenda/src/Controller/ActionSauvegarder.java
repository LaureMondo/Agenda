package Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import Model.ModelAgenda;

public class ActionSauvegarder extends AbstractAction{
	
	private ModelAgenda model;

	public ActionSauvegarder(String text, Icon icon, String desc, ModelAgenda model) {
		super(text, icon);
		putValue(SHORT_DESCRIPTION, desc);
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.saveProperties();
	}

}
