package com.savekirk.lecturenote;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Currently a Note can be saved to a persistent data store and all of
 * the Notes can be retrieved as an ArrayList
 * 
 */
public interface NoteDAO extends Serializable {

    /**
     * Persists Note information to a persistent data store
     * 
     * @param note <code>Note</code> holding time and message
     *        to store
     */
    public void add(Note note);

    /**
     * Gets all of the <code>Note</code> instances in the data store
     * 
     * @return <code>ArrayList&lt;Note&gt;</code> of every
     *         <code>Note</code> instance stored in the data store
     */
    public ArrayList<Note> getAll();

    /**
     * Removed a <code>Note</code> instance in data store that
     * matches <code>note</code>
     * 
     * @param note
     *        <code>Note</code> instance containing date and
     * message to remove from the data store
     */
    public void remove(Note note);
}
