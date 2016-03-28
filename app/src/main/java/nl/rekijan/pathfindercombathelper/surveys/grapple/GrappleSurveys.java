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

        if (context.getString(R.string.grapple_question_start).equals(surveyName)) {
            survey.setQuestion(surveyName);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.grapple_answer_start_self), "1 clicked");
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.grapple_answer_start_grappled), "2 clicked");
            AnswerModel answer3 = new AnswerModel(context.getString(R.string.grapple_answer_start_grappling), "3 clicked");

            survey.setAnswers(Arrays.asList(answer1, answer2, answer3));

            NoteModel note = new NoteModel(context.getString(R.string.grapple_note_start_action));
            NoteModel noteL = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition), "note clicked");

            survey.setNotes(Arrays.asList(note, noteL));
        }

        return survey;
    }
}