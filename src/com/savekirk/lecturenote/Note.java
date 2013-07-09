package com.savekirk.lecturenote;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable {

    private Date date;
    private String message;

   
    public Note() {
        this("");
    }

    /**
     * Initializes Note with supplied date and
     * message.
     * 
     * @param date a date representing the date and time of message
     * @param message String representing the text message received.
     */
    public Note(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    /**
     * Copy constructor to make a duplicate of <code>note</code>
     * 
     * @param note a note to make a full copy of 
     *        date and message.
     */
    public Note(Note note) {
        this.date = (Date) note.date.clone();
        this.message = new String(note.message);
    }

    /**
     * Initializes <code>Note</code> with supplied
     * <code>message</code> for location at latitude 0 and longitude 0
     * for the current date and time.
     * 
     * @param message String representing the text message received.
     */
    public Note(String message) {
        this.message = message;
        date = new Date();
    }

    /**
     * Objects are considered equal if they have the same 
     * time of creation and message.
     * 
     * @param obj a note to test against this
     * @return true if same date and message, otherwise
     *         false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Note other = (Note) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
    
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        return true;
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
