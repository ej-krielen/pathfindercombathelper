package nl.rekijan.pathfindercombathelper.surveys.grapple;

import android.content.Context;

import java.util.Arrays;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.AnswerModel;
import nl.rekijan.pathfindercombathelper.models.NoteModel;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;
import nl.rekijan.pathfindercombathelper.models.SurveyModel;
import nl.rekijan.pathfindercombathelper.utilities.NavigationHandler;
import static nl.rekijan.pathfindercombathelper.AppConstants.CATEGORY_GRAPPLE;
/**
 * Surveys are built here based on the question String parameter
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public final class GrappleSurvey {
    private static GrappleSurvey sInstance = null;

    public static synchronized GrappleSurvey getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GrappleSurvey();
        }
        return sInstance;
    }

    public SurveyModel createGrappleSurvey(Context context, String surveyName) {
        SurveyModel survey = new SurveyModel();
        if (surveyName.equals(context.getString(R.string.grapple_question_start))) {
            QuestionModel question = new QuestionModel(surveyName, CATEGORY_GRAPPLE);
            survey.setQuestionModel(question);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.grapple_answer_start_self), new QuestionModel(context.getString(R.string.grapple_question_avoid_aoo), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.grapple_answer_start_grappled), new QuestionModel("2 clicked", CATEGORY_GRAPPLE));
            AnswerModel answer3 = new AnswerModel(context.getString(R.string.grapple_answer_start_grappling), new QuestionModel("3 clicked", CATEGORY_GRAPPLE));

            survey.setAnswers(Arrays.asList(answer1, answer2, answer3));

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_start_action));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.condition_grappled_title), context.getString(R.string.condition_grappled_message)));

            survey.setNotes(Arrays.asList(note1, note2));
        } else if (surveyName.equals(context.getString(R.string.grapple_question_avoid_aoo))) {
            QuestionModel question = new QuestionModel(surveyName, CATEGORY_GRAPPLE);
            survey.setQuestionModel(question);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel("1 clicked", CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_no), new QuestionModel("2 clicked", CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));
        } else {
            survey.setErrorMessage(context.getString(R.string.error_surveyName));
        }
        return survey;
    }
}