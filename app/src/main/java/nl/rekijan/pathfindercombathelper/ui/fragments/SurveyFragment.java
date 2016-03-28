package nl.rekijan.pathfindercombathelper.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.ui.views.SurveyLinearLayout;


public class SurveyFragment extends Fragment {
    private static final String ARG_SURVEY_NAME = "ARG_SURVEY_NAME";
    private String surveyName;

    public SurveyFragment() {
        // Required empty public constructor
    }

    public static SurveyFragment newInstance(String surveyName) {
        SurveyFragment fragment = new SurveyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SURVEY_NAME, surveyName);
        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            surveyName = getArguments().getString(ARG_SURVEY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_survey, container, false);
        SurveyLinearLayout surveyLinearLayout = (SurveyLinearLayout) rootView.findViewById(R.id.survey_ll);
        surveyLinearLayout.addSurveyToLayout(surveyName);
        return rootView;
    }
}