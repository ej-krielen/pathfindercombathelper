package nl.rekijan.pathfindercombathelper.surveys.combatmaneuvers;

import android.content.Context;

import java.util.Arrays;
import java.util.Collections;

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

    public SurveyModel createGrappleSurvey(Context context, QuestionModel questionModel) {
        SurveyModel survey = new SurveyModel();
        if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_start))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.grapple_answer_start_self), new QuestionModel(context.getString(R.string.grapple_question_avoid_aoo), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.grapple_answer_start_grappled), new QuestionModel(context.getString(R.string.grapple_question_defend_start), CATEGORY_GRAPPLE));
            AnswerModel answer3 = new AnswerModel(context.getString(R.string.grapple_answer_start_grappling), new QuestionModel("3 clicked", CATEGORY_GRAPPLE));

            survey.setAnswers(Arrays.asList(answer1, answer2, answer3));

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_start_action));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.condition_grappled_title), context.getString(R.string.condition_grappled_message)));

            survey.setNotes(Arrays.asList(note1, note2));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_avoid_aoo))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel(context.getString(R.string.grapple_question_cmb_roll), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_no), new QuestionModel(context.getString(R.string.grapple_question_provoked), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_improved_grapple));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_grab),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.grapple_dialog_grab_title), context.getString(R.string.grapple_dialog_grab_message)));
            survey.setNotes(Arrays.asList(note1, note2));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_provoked))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel(context.getString(R.string.grapple_question_provoked_hit), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_no), new QuestionModel(context.getString(R.string.grapple_question_cmb_roll), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_provoked_hit))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_continue), new QuestionModel(context.getString(R.string.grapple_question_cmb_roll), CATEGORY_GRAPPLE));
            survey.setAnswers(Collections.singletonList(answer1));

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_cmb_roll));
            survey.setNotes(Collections.singletonList(note1));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_cmb_roll))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_succes), new QuestionModel(context.getString(R.string.grapple_question_adjacent), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_fail), new QuestionModel(context.getString(R.string.grapple_question_grapple_fails), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_grapple_fails))) {
            survey.setQuestionModel(questionModel);
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_adjacent))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel(context.getString(R.string.grapple_question_attacker_grappled), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_no), new QuestionModel(context.getString(R.string.grapple_question_adjacent_available), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_adjacent_available))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel(context.getString(R.string.grapple_question_move_adjacent), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_no), new QuestionModel(context.getString(R.string.grapple_question_grapple_fails), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_move_adjacent))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_continue), new QuestionModel(context.getString(R.string.grapple_question_attacker_grappled), CATEGORY_GRAPPLE));
            survey.setAnswers(Collections.singletonList(answer1));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_attacker_grappled))) {
            survey.setQuestionModel(questionModel);

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_success_note));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_success_note_bonus));
            NoteModel note3 = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.condition_grappled_title), context.getString(R.string.condition_grappled_message)));
            survey.setNotes(Arrays.asList(note1, note2, note3));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_defend_start))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel(context.getString(R.string.grapple_question_pinned), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_no), new QuestionModel(context.getString(R.string.grapple_question_defend_start_choose), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));

        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_pinned))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_yes), new QuestionModel(context.getString(R.string.grapple_question_free_yourself), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.grapple_answer_pinned_cast), new QuestionModel(context.getString(R.string.grapple_question_pinned_cast), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_pinned_restricted));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_pinned_actions));
            NoteModel note3 = new NoteModel(context.getString(R.string.grapple_note_pinned_casting));
            NoteModel note4 = new NoteModel(context.getString(R.string.grapple_note_pinned_condition));
            survey.setNotes(Arrays.asList(note1, note2, note3, note4));

        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_pinned_cast))) {
            survey.setQuestionModel(questionModel);

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_pinned_actions));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_pinned_casting));
            NoteModel note3 = new NoteModel(context.getString(R.string.grapple_note_pinned_restricted));
            NoteModel note4 = new NoteModel(context.getString(R.string.grapple_note_pinned_condition));
            survey.setNotes(Arrays.asList(note1, note2, note3, note4));

        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_defend_start_choose))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.grapple_answer_control), new QuestionModel(context.getString(R.string.grapple_question_control), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.grapple_answer_free_myself), new QuestionModel(context.getString(R.string.grapple_question_free_yourself), CATEGORY_GRAPPLE));
            AnswerModel answer3 = new AnswerModel(context.getString(R.string.grapple_answer_attack), new QuestionModel(context.getString(R.string.grapple_question_attack), CATEGORY_GRAPPLE));
            AnswerModel answer4 = new AnswerModel(context.getString(R.string.grapple_answer_cast), new QuestionModel(context.getString(R.string.grapple_question_cast), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2, answer3, answer4));

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.condition_grappled_title), context.getString(R.string.condition_grappled_message)));
            survey.setNotes(Collections.singletonList(note1));

        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_control))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_succes), new QuestionModel(context.getString(R.string.grapple_question_defender_takes_control), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_fail), new QuestionModel(context.getString(R.string.grapple_question_failed_cmd), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));

            NoteModel note1 = new NoteModel(context.getString(R.string.note_standard_action));
            survey.setNotes(Collections.singletonList(note1));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_failed_cmd))) {
            survey.setQuestionModel(questionModel);

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.condition_grappled_title), context.getString(R.string.condition_grappled_message)));
            survey.setNotes(Collections.singletonList(note1));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_defender_takes_control))) {
            survey.setQuestionModel(questionModel);

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_start_grapple_condition),
                    NavigationHandler.getInstance(context).createDialogFragment(context.getString(R.string.condition_grappled_title), context.getString(R.string.condition_grappled_message)));
            survey.setNotes(Collections.singletonList(note1));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_free_yourself))) {
            survey.setQuestionModel(questionModel);

            AnswerModel answer1 = new AnswerModel(context.getString(R.string.answer_succes), new QuestionModel(context.getString(R.string.grapple_question_defender_escapes), CATEGORY_GRAPPLE));
            AnswerModel answer2 = new AnswerModel(context.getString(R.string.answer_fail), new QuestionModel(context.getString(R.string.grapple_question_failed_cmd), CATEGORY_GRAPPLE));
            survey.setAnswers(Arrays.asList(answer1, answer2));

            NoteModel note1 = new NoteModel(context.getString(R.string.note_standard_action));
            survey.setNotes(Collections.singletonList(note1));
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_defender_escapes))) {
            survey.setQuestionModel(questionModel);
        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_attack))) {
            survey.setQuestionModel(questionModel);

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_attack));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_attack_range));
            survey.setNotes(Arrays.asList(note1, note2));

        } else if (questionModel.getQuestion().equals(context.getString(R.string.grapple_question_cast))) {
            survey.setQuestionModel(questionModel);

            NoteModel note1 = new NoteModel(context.getString(R.string.grapple_note_cast_restriction));
            NoteModel note2 = new NoteModel(context.getString(R.string.grapple_note_cast_check));
            survey.setNotes(Arrays.asList(note1, note2));

        } else {
            survey.setErrorMessage(context.getString(R.string.error_surveyName));
        }
        return survey;
    }
}