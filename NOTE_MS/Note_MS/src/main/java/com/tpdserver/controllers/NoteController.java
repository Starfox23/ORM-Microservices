package com.tpdserver.controllers;

import com.tpdserver.models.Note;
import com.tpdserver.services.NoteService;
import jakarta.ws.rs.*;
import java.util.ArrayList;

@Path("/note")
public class NoteController {
    private final NoteService noteService = new NoteService();

    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public ArrayList<Note> getNotesByUserId(@PathParam("userId") int userId) {
        return noteService.getNotesByUserId(userId);
    }

    @POST
    @Consumes("application/json")
    public void addNote(String response) {
        noteService.addNote(response);
    }
}
