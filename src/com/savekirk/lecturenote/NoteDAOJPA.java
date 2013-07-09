package com.savekirk.lecturenote;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Object responsible for providing functionality to store to Google
 * JPA object storage
 * 
 */
public class NoteDAOJPA implements NoteDAO {

    /**
     * save a <code>Node</code> into the Google App Engine BigTable.
     * Use the JPA API.
     * 
     * @param aNote Note to store into the database
     */
    @Override
    public void add(Note aNote) {
	EntityManager em = null;
	try {
	    em = EMF.get().createEntityManager();
	    NoteJPA aNoteJPA = new NoteJPA(aNote);
	    em.persist(aNoteJPA);
	} finally {
	    em.close();
	}

    }

    /**
     * Gets all of the Note instances in the database. There is no
     * filtering.
     * 
     * @return ArrayList of Note instances in the database. No
     *         ordering is enforced.
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Note> getAll() {
	EntityManager em = null;
	List<NoteJPA> dbNotes = null;
	ArrayList<Note> notes = new ArrayList<Note>();
	String aQuery = "SELECT n FROM NoteJPA n";
	try {
	    em = EMF.get().createEntityManager();
	    Query query = em.createQuery(aQuery);
	    dbNotes = query.getResultList();
	    if ((dbNotes != null) && (dbNotes.size() > 0)) {
		for (NoteJPA aNoteJPA : dbNotes) {
		    notes.add(aNoteJPA.getNote());
		}
	    }
	} finally {
	    em.close();
	}
	return notes;
    }

    /**
     * The assumption is that each <code>Note</code> gets created at a
     * unique date/time. Currently this is the only field searched for
     * to identify the DB objects to delete. If desired then a more
     * complicated query can be written to match on perhaps date and
     * message.
     * 
     * @param note <code>Note</code> instance with date / time stamp
     *        to find and delete if exists in the DB
     */
    @Override
    public void remove(Note note) {
	EntityManager em = null;
	String aQuery = "DELETE FROM NoteJPA n WHERE n.message = :message";
	try {
	    em = EMF.get().createEntityManager();
	    Query query = em.createQuery(aQuery);
	    query.setParameter("message", note.getMessage());
	    int x = query.executeUpdate();
	} finally {
	    em.close();
	}
    }

}
