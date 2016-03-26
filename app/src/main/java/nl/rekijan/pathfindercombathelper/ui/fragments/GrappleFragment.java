package nl.rekijan.pathfindercombathelper.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.rekijan.pathfindercombathelper.R;


public class GrappleFragment extends Fragment {

    public GrappleFragment() {
        // Required empty public constructor
    }

    public static GrappleFragment newInstance() {
        GrappleFragment fragment = new GrappleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grapple, container, false);
    }
}
