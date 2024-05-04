package org.rubrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PersonaDAO {

    public void inserisciPersona(Persona persona) throws SQLException {
        // Ottieni una connessione al database
        Connection connection = DatabaseConnection.getConnection();

        String sql = "INSERT INTO persona (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Imposta i valori dei parametri della query
            statement.setString(1, persona.getNome());
            statement.setString(2, persona.getCognome());
            statement.setString(3, persona.getIndirizzo());
            statement.setString(4, persona.getTelefono());
            statement.setInt(5, persona.getEta());

            // Esegui l'inserimento
            statement.executeUpdate();
        }
    }


    // Metodo per leggere tutte le persone
    public static Vector<Persona> leggiPersone() throws SQLException {
        Vector<Persona> persone = new Vector<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = DatabaseConnection.getConnection(); // Metodo per ottenere la connessione al database

            String query = "SELECT * FROM persona";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                int eta = rs.getInt("eta");

                Persona persona = new Persona(id, nome, cognome, indirizzo, telefono, eta);
                persone.add(persona);
            }
        } finally {
            // Chiudi le risorse
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return persone;
    }

    // Metodo per aggiornare i dettagli di una persona esistente
    public void modificaPersona(Long id, String nome, String cognome, String indirizzo, String telefono, int eta) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE persona SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setString(3, indirizzo);
            statement.setString(4, telefono);
            statement.setInt(5, eta);
            statement.setLong(6, id);
            statement.executeUpdate();
        } finally {
            // Chiudi le risorse
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    // Metodo per eliminare una persona in base all'ID
    public void eliminaPersona(long id) throws SQLException {
        String sql = "DELETE FROM persona WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}