package nl.rekijan.pathfindercombathelper.models;


import nl.rekijan.pathfindercombathelper.ui.dialogs.CustomDialogFragment;

/**
 * Custom class to set fields for Notes
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class NoteModel {
    private String text;
    private CustomDialogFragment dialogFragment;

    public NoteModel(String text) {
        this.text = text;
    }

    public NoteModel(String text, CustomDialogFragment dialogFragment) {
        this.text = text;
        this.dialogFragment = dialogFragment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CustomDialogFragment getDialogFragment() {
        return dialogFragment;
    }

    public void setDialogFragment(CustomDialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }
}