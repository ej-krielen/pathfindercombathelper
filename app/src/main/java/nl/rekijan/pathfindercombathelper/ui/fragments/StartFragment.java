package nl.rekijan.pathfindercombathelper.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;

public class StartFragment extends Fragment {
    public interface OnOpenDrawerClicked {
        void onOpenDrawerClicked();
    }

    private OnOpenDrawerClicked mListener;

    public StartFragment() {
        // Required empty public constructor
    }

    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof OnOpenDrawerClicked) {
            mListener = (OnOpenDrawerClicked) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement OnNavItemPressedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_start, container, false);
        TextView tv = (TextView) convertView.findViewById(R.id.start_fragment_textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onOpenDrawerClicked();
                }
            }
        });
        return convertView;
    }
}