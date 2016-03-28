package nl.rekijan.pathfindercombathelper.models;


import android.support.v7.app.AlertDialog;

/**
 * Custom class to set fields for Notes
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class NoteModel {
    private String text;
    private AlertDialog dialog;

    public NoteModel(String text, AlertDialog dialog) {
        this.text = text;
        this.dialog = dialog;
    }

    public NoteModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AlertDialog getDialog() {
        return dialog;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }
}