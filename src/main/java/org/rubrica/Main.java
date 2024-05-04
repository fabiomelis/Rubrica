package org.rubrica;


import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        ListaPersone listaPersone = FileInformazioni.caricaRubrica();

        new Rubrica(listaPersone);


    }
}
