package com.savekirk.lecturenote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SaveNoteServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		out.println("Lecture Note");
		String aMessage = req.getParameter("message");
		if((aMessage == null ) || (aMessage.trim().length() == 0 )) {
			aMessage = "Testing the lecture note backend";
		}
		
		//create a note
		Note aNote = new Note(aMessage);
		//create instance of DAO
		NoteDAO dao = new NoteDAOJPA();
		try {
			//save the note
			dao.add(aNote);
			resp.setStatus(HttpServletResponse.SC_OK);
			out.println("Saved Note");
			out.println(aNote);
		} catch(Exception e) {
			resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
			out.println("Problem Saving Note");
		}
	}
	
	/**
     * Received a serialize <code>Note</code> from client and stored
     * it in the database as a <code>NoteJPA</code> which is identical
     * to a <code>Note</code> except it has a key field for JPA that
     * is auto generated by the DB. If an invalid <code>Note</code> is
     * received from client then set the response status to <code>
     * SC_BAD_REQUEST</code> otherwise set it to <code>SC_OK</code>
     * 
     * @param req HttpServletRequest from a java program that has a
     *        Note instance on its input stream. This method will read
     *        the Note and save it into a persistent data store
     * @param resp HttpServletResponse instance currently used to send
     *        back the object as proof that it was received. 
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Note receivedNote = NoteUtils.getNote(req.getInputStream());
        if (receivedNote == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            saveNote(receivedNote);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Saves a Note instance to a persistent datastore on Google App
     * Engine
     * 
     * @param aNote Note to save to a persistent datastore
     */
    private void saveNote(Note aNote) {
        NoteDAO dao = new NoteDAOJPA();
        dao.add(aNote);
        return;
    }

}


