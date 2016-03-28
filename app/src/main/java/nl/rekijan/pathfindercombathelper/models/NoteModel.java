package nl.rekijan.pathfindercombathelper.models;

/**
 * Custom class to set fields for Notes
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class NoteModel {
    private String text;
    private String navigation;

    public NoteModel(String text, String navigation) {
        this.text = text;
        this.navigation = navigation;
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

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }
}