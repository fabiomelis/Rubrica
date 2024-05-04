package org.rubrica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

public class Rubrica extends JFrame{


    private ListaPersone listaPersone;
    private Persona persona;

    private JTable tabella;

    public Rubrica(ListaPersone listaPersone){

        super("Rubrica Telefonica");
        this.listaPersone = listaPersone;
        this.persona = persona;

        setLayout(new BorderLayout());

        tabella = new JTable();
        aggiornaRubrica();

        JButton nuovoButton = new JButton("Nuovo");
        JButton modificaButton = new JButton("Modifica");
        JButton eliminaButton = new JButton("Elimina");


        nuovoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EditorPersona editor = new EditorPersona(listaPersone, persona);

                editor.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        aggiornaRubrica();
                        //FileInformazioni.salvaRubrica(listaPersone);
                    }
                });
                editor.setVisible(true);
            }
        });


        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rigaSelezionata = tabella.getSelectedRow();
                if (rigaSelezionata == -1) {

                    JOptionPane.showMessageDialog(Rubrica.this, "Per modificare è necessario prima selezionare una persona.", "Errore", JOptionPane.ERROR_MESSAGE);
                } else {

                    Persona personaSelezionata = listaPersone.getListaPersone().elementAt(rigaSelezionata);

                    EditorPersona editor = new EditorPersona(listaPersone, personaSelezionata);

                    editor.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            aggiornaRubrica();
                            //FileInformazioni.salvaRubrica(listaPersone);
                        }
                    });


                    editor.setVisible(true);
                }
            }
        });



        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rigaSelezionata = tabella.getSelectedRow();
                if (rigaSelezionata == -1) {
                    JOptionPane.showMessageDialog(Rubrica.this, "Per eliminare è necessario prima selezionare una persona.", "Errore", JOptionPane.ERROR_MESSAGE);
                } else {

                    Persona personaSelezionata = listaPersone.getListaPersone().elementAt(rigaSelezionata);
                    System.out.println(personaSelezionata);

                    int conferma = JOptionPane.showConfirmDialog(Rubrica.this, "Eliminare la persona " + personaSelezionata.getNome() + " " + personaSelezionata.getCognome() + "?", "Conferma", JOptionPane.YES_NO_OPTION);
                    if (conferma == JOptionPane.YES_OPTION) {
                        listaPersone.rimuoviPersona(personaSelezionata.getId());
                        System.out.println(personaSelezionata.getId());

                        aggiornaRubrica();
                        //FileInformazioni.salvaRubrica(listaPersone);
                    }
                }
            }
        });



        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(nuovoButton);
        buttonPanel.add(modificaButton);
        buttonPanel.add(eliminaButton);

        add(buttonPanel, BorderLayout.PAGE_END);

        add(new JScrollPane(tabella), BorderLayout.CENTER);

        setSize(400,250);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void aggiornaRubrica() {

        ListaPersone listaPersone = new ListaPersone();

        Vector<Persona> persone = listaPersone.getListaPersone();

        String[] colonne = {"Nome", "Cognome", "Telefono"};
        Object[][] dati = new Object[persone.size()][3];
        for (int i = 0; i < persone.size(); i++) {
            Persona persona = persone.get(i);
            dati[i][0] = persona.getNome();
            dati[i][1] = persona.getCognome();
            dati[i][2] = persona.getTelefono();
        }

        DefaultTableModel model = new DefaultTableModel(dati, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        tabella.setModel(model);
    }



}