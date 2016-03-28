package nl.rekijan.pathfindercombathelper.surveys.grapple;

import android.content.Context;

import java.util.Arrays;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.AnswerModel;
import nl.rekijan.pathfindercombathelper.models.NoteModel;
import nl.rekijan.pathfindercombathelper.models.SurveyModel;

/**
 * Surveys are built here based on the question String parameter
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public final class GrappleSurveys {

    private static GrappleSurveys sInstance = null;

    public static synchronized GrappleSurveys getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GrappleSurveys();
        }
        return sInstance;
    }

    public SurveyModel createSurvey(Context context, String surveyName) {
        SurveyModel survey = new SurveyModel();

        if (context.getString(R.string.grapple_start_question).equals(surveyName)) {
            survey.setQuestion(surveyName);

            AnswerModel answer1 = new AnswerModel("1", "1 clicked");
            AnswerModel answer2 = new AnswerModel("2", "2 clicked");

            survey.setAnswers(Arrays.asList(answer1, answer2));

            NoteModel note = new NoteModel("Note no link");
            NoteModel noteL = new NoteModel("Note with link", "note clicked");

            survey.setNotes(Arrays.asList(note, noteL));
        }

        return survey;
    }
}