package org.rubrica;

import java.util.Vector;

public class ListaPersone {
    private Vector<Persona> listaPersone = new Vector<>();

    public Vector<Persona> getListaPersone() {
        return listaPersone;
    }

    public void aggiungiPersona(Persona persona) {
        listaPersone.add(persona);
    }

    public void rimuoviPersona(Persona persona) {
        listaPersone.remove(persona);
    }

    public void modificaPersona(Persona persona, String nome, String cognome, String indirizzo, String telefono, int eta) {
        persona.setNome(nome);
        persona.setCognome(cognome);
        persona.setIndirizzo(indirizzo);
        persona.setTelefono(telefono);
        persona.setEta(eta);
    }

}