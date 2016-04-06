package nl.rekijan.pathfindercombathelper.models;

/**
 * Custom class to set fields for NavItems
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 2-4-2016
 */
public class NavItemModel {

    private String title;
    private QuestionModel question;

    public NavItemModel(String title, QuestionModel question) {
        this.title = title;
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionModel getQuestionModel() {
        return question;
    }

    public void setQuestionModel(QuestionModel question) {
        this.question = question;
    }
}