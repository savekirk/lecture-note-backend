package com.savekirk.lecturenote;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NoteJPA implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    private Date date;
    private String message;

   
    public NoteJPA() {
        id = null;
        date = new Date();
        message = "";
    }

    /**
     * Initializes Note with supplied date and
     * message.
     * 
     * @param date a date representing the date and time of message
     * @param message String representing the text message received.
     */
    public NoteJPA(Date date, String message) {
        this.date = date;
        this.message = message;
    }
    
    /**
     * Initializes instance given a Note instance. This is a helper
     * constructor that makes it easy to take a Note and store it with 
     * a key in the format required by JPA
     * 
     * @param aNote note instance to fill all fields but the id
     */
    public NoteJPA(Note aNote) {
    	id = null;
    	setDate(aNote.getDate());
    	setMessage(aNote.getMessage());
    }

    /**
     * Copy constructor to make a duplicate of <code>note</code>
     * 
     * @param note a note to make a full copy of 
     *        date and message.
     */
    public NoteJPA(NoteJPA note) {
        this.date = (Date) note.date.clone();
        this.message = new String(note.message);
    }



    /**
     * Gets the date that the <code>Note</code> was created.
     * 
     * @return the date and time when the <code>Note</code> was
     *         created.
     */
    public Date getDate() {
        return date;
    }

    
    /**
     * Gets the text message on a <code>Note</code>
     * 
     * @return string representing the text message.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Takes all of the data of current instance except id(the key)
     * needed by a Note, creates a note and returns it.
     * 
     * @return Note instance with all of current data except id
     */
    public Note getNote() {
    	Note result = new Note(message);
    	result.setDate(date);
    	return result;
    }

    /**
     * Sets the date and time for the cretion of the <code>Note</code>
     * 
     * @param date date and time of <code>Note</code> creation
     */
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * Sets the current message to <code>message</code>.
     * 
     * @param message string of the message to store in the
     *        <code>Note</code>
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets a string representation of the Note represented as name
     * values on separate lines with message and
     * date
     * 
     * @return string name value representation of fields making up a
     *         Note
     */
    @Override
    public String toString() {
        String newLine = "\n";
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss z");
        String s = "Date: " + dateFormat.format(this.getDate()) + newLine;
        s += "Message: " + getMessage();
        return s;
    }
}