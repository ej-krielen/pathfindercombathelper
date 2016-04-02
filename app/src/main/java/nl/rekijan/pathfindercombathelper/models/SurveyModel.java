package nl.rekijan.pathfindercombathelper.models;

import java.util.List;

/**
 * Custom class to set fields for Survey
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public class SurveyModel {
    private QuestionModel question;
    private List<AnswerModel> answers;
    private List<NoteModel> notes;
    private String errorMessage;

    public QuestionModel getQuestionModel() {
        return question;
    }

    public void setQuestionModel(QuestionModel question) {
        this.question = question;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}