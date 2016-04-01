package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.AnswerModel;
import nl.rekijan.pathfindercombathelper.models.NoteModel;
import nl.rekijan.pathfindercombathelper.models.SurveyModel;
import nl.rekijan.pathfindercombathelper.surveys.grapple.GrappleSurveys;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class SurveyLinearLayout extends LinearLayout {
    private Context mContext;

    public SurveyLinearLayout(Context context) {
        super(context);
    }

    public SurveyLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
    }

    public void addSurveyToLayout(String surveyName) {
        SurveyModel survey = GrappleSurveys.getInstance(mContext).createSurvey(mContext, surveyName);
        if (!TextUtils.isEmpty(survey.getErrorMessage())) {
            displayError(survey.getErrorMessage());
            return;
        }

        if (!TextUtils.isEmpty(survey.getQuestion()))
            addQuestion(survey.getQuestion());
        if (survey.getAnswers() != null && survey.getAnswers().size() > 0)
            addAnswers(survey.getAnswers());
        if (survey.getNotes() != null && survey.getNotes().size() > 0)
            addNotes(survey.getNotes());
    }

    private void displayError(String errorMessage) {
        HeaderLinearLayout header = new HeaderLinearLayout(mContext);
        header.setText(mContext.getString(R.string.error_title));
        this.addView(header);
        ErrorLinearLayout errorLayout = new ErrorLinearLayout(mContext);
        errorLayout.setText(errorMessage);
        this.addView(errorLayout);
    }

    private void addQuestion(String question) {
        QuestionLinearLayout questionLayout = new QuestionLinearLayout(mContext);
        questionLayout.setText(question);
        this.addView(questionLayout);
    }

    private void addAnswers(List<AnswerModel> answers) {
        for (AnswerModel answer : answers) {
            AnswerLinearLayout answerLayout = new AnswerLinearLayout(mContext);
            answerLayout.setText(answer.getText());
            if (!TextUtils.isEmpty(answer.getNavigation()))
                answerLayout.setNavigationClickListener(answer.getNavigation());
            this.addView(answerLayout);
        }
    }

    private void addNotes(List<NoteModel> notes) {
        HeaderLinearLayout header = new HeaderLinearLayout(mContext);
        header.setText(mContext.getString(R.string.notes_title));
        this.addView(header);
        for (NoteModel note : notes) {
            NoteLinearLayout noteLayout = new NoteLinearLayout(mContext);
            noteLayout.setText(note.getText());
            if (note.getDialogFragment() != null)
                noteLayout.setNavigationClickListener(note.getDialogFragment());
            this.addView(noteLayout);
        }
    }
}