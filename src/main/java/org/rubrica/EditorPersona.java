package org.rubrica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditorPersona extends JFrame {
    private JTextField nomeField, cognomeField, indirizzoField, telefonoField, etaField;
    private ListaPersone listaPersone;
    private Rubrica rubrica;
    private Persona persona;

    public EditorPersona(ListaPersone listaPersone, Persona persona) {
        super("Editor Persona");
        this.listaPersone = listaPersone;
        this.rubrica = rubrica;

        setLayout(new GridLayout(6, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel cognomeLabel = new JLabel("Cognome:");
        cognomeField = new JTextField();
        JLabel indirizzoLabel = new JLabel("Indirizzo:");
        indirizzoField = new JTextField();
        JLabel telefonoLabel = new JLabel("Telefono:");
        telefonoField = new JTextField();
        JLabel etaLabel = new JLabel("Età:");
        etaField = new JTextField();

        if (persona != null) {
            nomeField.setText(persona.getNome());
            cognomeField.setText(persona.getCognome());
            indirizzoField.setText(persona.getIndirizzo());
            telefonoField.setText(persona.getTelefono());
            etaField.setText(String.valueOf(persona.getEta()));
        }

        JButton salvaButton = new JButton("Salva");
        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = nomeField.getText();
                String cognome = cognomeField.getText();
                String indirizzo = indirizzoField.getText();
                String telefono = telefonoField.getText();
                int eta = Integer.parseInt(etaField.getText());


                if(persona != null){
                    Long id = persona.getId();
                    listaPersone.modificaPersona(id, nome, cognome, indirizzo, telefono, eta);
                } else {
                    Long id = (long) (listaPersone.getListaPersone().size() + 1);
                    listaPersone.aggiungiPersona(new Persona(id, nome, cognome, indirizzo, telefono, eta));
                }
                dispose();
            }
        });

        JButton annullaButton = new JButton("Annulla");
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        // Aggiungi componenti alla finestra
        add(nomeLabel);
        add(nomeField);
        add(cognomeLabel);
        add(cognomeField);
        add(indirizzoLabel);
        add(indirizzoField);
        add(telefonoLabel);
        add(telefonoField);
        add(etaLabel);
        add(etaField);
        add(salvaButton);
        add(annullaButton);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

