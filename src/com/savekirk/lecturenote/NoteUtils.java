package com.savekirk.lecturenote;

import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Methods shared by servlets.
 * 
 */
public class NoteUtils {

    /**
     * Default constructor for best practices.
     */
    public NoteUtils() {
	// intentionally left blank
    }

    /**
     * Reads a serialized Note instance from the input stream and
     * returns the Note instance
     * 
     * @param ais Input stream holding a serialized <code>Note</code>
     *        instance
     * @return copy of <code>Note</code> instance read from input
     *         stream or null if any problem with reading a valid note
     */
    public static Note getNote(InputStream ais) {
	Note result = null;
	ObjectInputStream ois = null;
	try {
	    ois = new ObjectInputStream(ais);
	    Object aObject = ois.readObject();
	    result = (Note) aObject;
	} catch (ClassNotFoundException e) {
	    result = null;
	} catch (Exception e) {
	    result = null;
	}
	return result;
    }

}
