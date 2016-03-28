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
    public SurveyLinearLayout(Context context) {
        super(context);
    }

    public SurveyLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addSurveyToLayout(String surveyName) {
        SurveyModel survey = GrappleSurveys.getInstance(getContext()).createSurvey(getContext(), surveyName);
        if (!TextUtils.isEmpty(survey.getQuestion()))
            addQuestion(survey.getQuestion());
        if (survey.getAnswers() != null && survey.getAnswers().size() > 0)
            addAnswers(survey.getAnswers());
        if (survey.getNotes() != null && survey.getNotes().size() > 0)
            addNotes(survey.getNotes());
    }

    private void addQuestion(String question) {
        QuestionTextView questionTextView = new QuestionTextView(getContext());
        questionTextView.setText(question);
        this.addView(questionTextView);
    }

    private void addAnswers(List<AnswerModel> answers) {
        for (AnswerModel answer : answers) {
            AnswerTextView answerTextView = new AnswerTextView(getContext());
            answerTextView.setText(answer.getText());
            if (!TextUtils.isEmpty(answer.getNavigation()))
                answerTextView.setNavigationClickListener(answer.getNavigation());
            this.addView(answerTextView);
        }
    }

    private void addNotes(List<NoteModel> notes) {
        HeaderTextView header = new HeaderTextView(getContext());
        header.setText(getContext().getString(R.string.notes_title));
        this.addView(header);
        for (NoteModel note : notes) {
            NoteTextView noteTextView = new NoteTextView(getContext());
            noteTextView.setText(note.getText());
            if (note.getDialog() != null)
                noteTextView.setNavigationClickListener(note.getDialog());
            this.addView(noteTextView);
        }
    }
}