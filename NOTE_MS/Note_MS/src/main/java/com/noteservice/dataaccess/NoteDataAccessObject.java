package com.noteservice.dataaccess;

import com.example.DatabaseConnectionHelper;
import com.noteservice.models.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class NoteDataAccessObject {
    public ArrayList<Note> getNotesByUserId(int userId) {
        ArrayList<Note> result = new ArrayList<>();

        try (Connection conn = DatabaseConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"note\" WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new Note(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addNote(Note note) {
        if (note == null) {
            return;
        }

        try (Connection conn = DatabaseConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"note\"(title, content, user_id) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getContent());
            preparedStatement.setInt(3, note.getUserId());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
