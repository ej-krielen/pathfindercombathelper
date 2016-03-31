package nl.rekijan.pathfindercombathelper.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.utilities.CommonUtil;

public class StartFragment extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.navigation_listView);
        String[] navItems = new String[]{getString(R.string.cmb_bull_rush), getString(R.string.cmb_dirty_trick), getString(R.string.cmb_disarm), getString(R.string.cmb_drag), getString(R.string.cmb_grapple)};
        listView.setAdapter(new StableArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, Arrays.asList(navItems)));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }
        });

        EditText editText = (EditText) rootView.findViewById(R.id.navigation_editText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.getId() == R.id.navigation_editText && !hasFocus) {
                    CommonUtil.getInstance(getContext()).hideSoftKeyboard(getActivity(), v);
                }
            }
        });

        return rootView;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}