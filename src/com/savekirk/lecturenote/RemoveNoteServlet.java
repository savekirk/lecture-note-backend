package com.savekirk.lecturenote;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet designed to be connected to directly from a java
 * application such as a desktop application or Android. The servlet
 * receives a <code>Note</code> instance as a java
 * serialized object from the input stream and removes it from the
 * database
 * 
 * @author dpowell2
 */
@SuppressWarnings("serial")
public class RemoveNoteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // not supported to do anything
    }

    /**
     * Received a <code>Note</code> instance from client and deletes
     * it from the data store
     * 
     * @param req HttpServletRequest from a java program that has a
     *        Note instance on its input stream. This method will read
     *        the Note and remove it into a persistent data store
     * @param resp HttpServletResponse instance currently not used.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        NoteUtils noteUtilities = new NoteUtils();
        Note receivedNote = noteUtilities.getNote(req.getInputStream());
        if (receivedNote == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            removeNote(receivedNote);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Remove a Note instance from the persistent datastore on Google
     * App Engine
     * 
     * @param aNote Note to remove from persistent datastore
     */
    private void removeNote(Note note) {
        NoteDAO dao = new NoteDAOJPA();
        dao.remove(note);
        return;
    }

}
