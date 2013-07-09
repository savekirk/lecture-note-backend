package com.savekirk.lecturenote;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet designed to be connected to directly from a java
 * application such as a desktop application or Android. The servlet
 * writes an serialized ArrayList of Note as a single object to the
 * output stream
 * 
 */
@SuppressWarnings("serial")
public class GetNotesServlet extends HttpServlet {

    /**
     * doGet is the same as doPost. 
     * 
     * @see #doPost
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	doPost(req, resp);
    }

    /**
     * Gets all of the messages in the data store and returns them as
     * an <code>ArrayList&lt;Note&gt;</code>
     * 
     * @param req HttpServletRequest from a java program. Not
     *        currently used by this servlet.
     * @param resp HttpServletResponse used to send ArrayList of Note
     *        back to calling Java program as a serialized object
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	// resp.setContentType("application/octet-stream");
	resp.setContentType("application/x-java-serialized-object");
	ArrayList<Note> dbNotes = getAllNotes();
	sendNotes(dbNotes, resp);
    }

    /**
     * Get all of the Note instances stored in the persistent data
     * store and return them in an <code>ArrayList</code>. Currently,
     * no filtering is done
     * 
     * @return ArrayList of Note for all Note in DB.
     */
    private ArrayList<Note> getAllNotes() {
	NoteDAO dao = new NoteDAOJPA();
	ArrayList<Note> result = dao.getAll();
	return result;
    }

    /**
     * Helper method for debugging purposes to send an ArrayList of
     * Note for all Note stored in DB over the outputstream to a java
     * program
     * 
     * @param notes ArrayList of Note to be sent
     * @param aresp HttpServletResponse to set number of bytes being
     *        returned to client and to get access to the
     *        <code>OutputStream</code> to write the ArrayList to
     */
    private void sendNotes(ArrayList<Note> notes, HttpServletResponse aresp) {
	ObjectOutputStream oos = null;
	ByteArrayOutputStream bos = null;
	try {
	    bos = new ByteArrayOutputStream();
	    oos = new ObjectOutputStream(bos);
	    oos.writeObject(notes);
	    oos.flush();
	    int bufferLength = bos.size();
	    aresp.setContentLength(bufferLength);
	    bos.writeTo(aresp.getOutputStream());
	    bos.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
