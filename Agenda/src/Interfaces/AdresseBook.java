package Interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.ActionSauvegarder;
import Controller.ControllerAgendaAjouter;
import Controller.ControllerAgendaModifier;
import Model.ModelAgenda;


public class AdresseBook extends JFrame implements ListSelectionListener{
	
	private JList listContact;
	private JButton supprimerBtn;
	private JButton ajoutButton;
    private JTextField nouveauContact;
    private JTextField contactInfoChamp;
    private JButton validerButton;
    
    private ModelAgenda listContactModel;
    
    private JMenuBar menuBar;
	private JMenu menuFirst;
	
	private JMenuItem fileSav;
	private ActionSauvegarder actionSauvegarder;
	private JToolBar toolBar;
	private JButton buttonSav;
	
	public AdresseBook() {
		listContactModel = new ModelAgenda();
	}
	
	
	// ---------------------------------------------------------
	
	
	// Fenetre : view
	public void init() {
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Agenda");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildMenu();
		buildPane();
	}
	
	private void buildPane() {
		//construction de la jlist qui contient les contacts
		createListContact();
		
		JScrollPane listScrollPane = new JScrollPane(listContact);
		
		nouveauContact = new JTextField(10);
		ajoutButton = new JButton("Ajouter");
		contactInfoChamp = new JTextField(30);
		validerButton = new JButton("Valider");
		
        ControllerAgendaAjouter ajoutListener = new ControllerAgendaAjouter(ajoutButton, nouveauContact, listContact, listContactModel);
        
        ControllerAgendaModifier modificationListener = new ControllerAgendaModifier(validerButton, contactInfoChamp, listContact, listContactModel);
        
        // ajouter un nouveau contact
        ajoutButton.addActionListener(ajoutListener);
        ajoutButton.setEnabled(false);
       
        nouveauContact.addCaretListener(ajoutListener);
        
        // Afficher et modifier la valeur du numero d'un contact existant
        contactInfoChamp.setText(listContactModel.getValue(listContact.getSelectedValue().toString()));
        contactInfoChamp.addActionListener(modificationListener);
        
        validerButton.addActionListener(modificationListener);
        
        JPanel contactBox = new JPanel();
        contactBox.add(contactInfoChamp);
        contactBox.add(validerButton);
        
        JPanel buttonBox = new JPanel();
        buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.LINE_AXIS));
        buttonBox.add(ajoutButton);
        buttonBox.add(nouveauContact);
        
        JPanel centerBox = new JPanel(); 
        centerBox.setLayout(new BorderLayout());
        centerBox.add(listScrollPane, BorderLayout.PAGE_START);
        centerBox.add(contactBox, BorderLayout.CENTER);

        add(centerBox, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.PAGE_END);
		
	}


	private void createListContact() {
		listContact = new JList(listContactModel);
		listContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listContact.setSelectedIndex(0);
		listContact.addListSelectionListener(this);
	}
	
	private void buildMenu() {
		menuBar = new JMenuBar();
		
		menuFirst = new JMenu("Fichier");
		menuFirst.setMnemonic(KeyEvent.VK_F);

		actionSauvegarder = new ActionSauvegarder("Sauvegarder", null, "Sauvegarde vos modifications", listContactModel);
		
		fileSav = new JMenuItem(actionSauvegarder);
		fileSav.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		// penser faire annuler = retourne par default et boite dialogue et toolbar
		
		menuBar.add(menuFirst);
		menuFirst.add(fileSav);
		
		setJMenuBar(menuBar);
		
		buildToolBar();
	}


	private void buildToolBar() {
		toolBar = new JToolBar();
		buttonSav = new JButton(actionSauvegarder);
		toolBar.add(buttonSav);
		add(toolBar, BorderLayout.PAGE_START);
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		contactInfoChamp.setText(listContactModel.getValue(listContact.getSelectedValue().toString()));
	}

	// Main
	public static void main(String[] args) {
		AdresseBook agenda = new AdresseBook();
		agenda.init();
		agenda.setVisible(true);
	}
}
