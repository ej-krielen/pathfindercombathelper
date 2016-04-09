package nl.rekijan.pathfindercombathelper.surveys;

import android.content.Context;
import android.text.TextUtils;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;
import nl.rekijan.pathfindercombathelper.models.SurveyModel;
import nl.rekijan.pathfindercombathelper.surveys.grapple.GrappleSurvey;

import static nl.rekijan.pathfindercombathelper.AppConstants.*;

/**
 * Surveys are built here based on the question String parameter
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 28-3-2016
 */
public final class Surveys {
    public interface OnSurveyCreatedListener {
        void onSurveyCreated(String navItem);
    }

    private OnSurveyCreatedListener mListener;
    private static Surveys sInstance = null;

    public static synchronized Surveys getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Surveys();
        }
        return sInstance;
    }

    public SurveyModel createSurvey(Context context, QuestionModel questionModel) {
        if (context instanceof OnSurveyCreatedListener) {
            mListener = (OnSurveyCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSurveyCreatedListener");
        }
        SurveyModel survey;
        if (questionModel != null && !TextUtils.isEmpty(questionModel.getCategory())) {
            switch (questionModel.getCategory()) {
                case CATEGORY_GRAPPLE:
                    if (mListener != null)
                        mListener.onSurveyCreated(context.getString(R.string.cmb_grapple));
                    survey = GrappleSurvey.getInstance(context).createGrappleSurvey(context, questionModel.getQuestion());
                    break;
                default:
                    if (mListener != null)
                        mListener.onSurveyCreated("");
                    survey = new SurveyModel();
                    survey.setErrorMessage(context.getString(R.string.error_surveyName));
                    break;
            }
        } else {
            if (mListener != null)
                mListener.onSurveyCreated("");
            survey = new SurveyModel();
            survey.setErrorMessage(context.getString(R.string.error_surveyName));
        }
        return survey;
    }
}