package nl.rekijan.pathfindercombathelper.models;

/**
 * Custom class to set fields for Answers
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class AnswerModel {
    private String text;
    private QuestionModel questionModel;

    public AnswerModel(String text, QuestionModel questionModel) {
        this.text = text;
        this.questionModel = questionModel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public void setNQuestionModel(QuestionModel navigation) {
        this.questionModel = navigation;
    }
}