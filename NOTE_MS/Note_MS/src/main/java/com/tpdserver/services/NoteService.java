package com.tpdserver.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpdserver.dataaccess.NoteDataAccessObject;
import com.tpdserver.models.Note;

import java.util.ArrayList;

public class NoteService {
    private NoteDataAccessObject noteDataAccessObject;

    public NoteService() {
        noteDataAccessObject = new NoteDataAccessObject();
    }

    public ArrayList<Note> getNotesByUserId(int userId) {
        return noteDataAccessObject.getNotesByUserId(userId);
    }

    public void addNote(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Note note = mapper.readValue(response, new TypeReference<Note>() { });
            noteDataAccessObject.addNote(note);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
