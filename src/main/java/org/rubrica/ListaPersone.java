package org.rubrica;

import java.sql.SQLException;
import java.util.Vector;

public class ListaPersone {
    private Vector<Persona> listaPersone = new Vector<>();

    public ListaPersone() {
        caricaPersoneDalDatabase();
    }

    private void caricaPersoneDalDatabase() {
        try {
            PersonaDAO personaDAO = new PersonaDAO();
            listaPersone = personaDAO.leggiPersone();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Vector<Persona> getListaPersone() {

        caricaPersoneDalDatabase();

        return listaPersone;
    }

    public void aggiungiPersona(Persona persona) {
        try {
            PersonaDAO personaDAO = new PersonaDAO();
            personaDAO.inserisciPersona(persona);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rimuoviPersona(Long id) {
        try {
            PersonaDAO personaDAO = new PersonaDAO();
            personaDAO.eliminaPersona(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modificaPersona(Long id, String nome, String cognome, String indirizzo, String telefono, int eta) {
        try {
            PersonaDAO personaDAO = new PersonaDAO();
            personaDAO.modificaPersona(id, nome, cognome, indirizzo, telefono, eta);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}