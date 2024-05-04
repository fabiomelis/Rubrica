/*
package org.rubrica;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class FileInformazioni {
    private static final String FILE_PATH = "informazioni.txt";

    public static ListaPersone caricaRubrica() {

        ListaPersone rubrica = new ListaPersone();
        File file = new File(FILE_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                String nome = parts[0];
                String cognome = parts[1];
                String indirizzo = parts[2];
                String telefono = parts[3];
                int eta = Integer.parseInt(parts[4]);

                rubrica.aggiungiPersona(new Persona( nome, cognome, indirizzo, telefono, eta));
            }
        } catch (FileNotFoundException e) {

        }

        return rubrica;
    }

    public static void salvaRubrica(ListaPersone rubrica) {
        try (PrintStream writer = new PrintStream(new FileOutputStream(FILE_PATH))) {
            Vector<Persona> listaPersone = rubrica.getListaPersone();
            for (int i = 0; i < listaPersone.size(); i++) {
                Persona persona = listaPersone.get(i);
                writer.println(persona.getNome() + ";" + persona.getCognome() + ";" +
                        persona.getIndirizzo() + ";" + persona.getTelefono() + ";" +
                        persona.getEta());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
*/

