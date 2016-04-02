package nl.rekijan.pathfindercombathelper.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Arrays;

import nl.rekijan.pathfindercombathelper.R;

/**
 * Custom adapter for navigation items
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 2-4-2016
 */
public class NavItemAdapter extends ArrayAdapter implements Filterable {
    private final Context context;
    public ArrayList<String> navItemsArrayList;
    public ArrayList<String> preSearchArrayList;
    private String selectedNavItem;
    private ListView mListView;

    public NavItemAdapter(Context context, int resourceId, String[] navItems, ListView listView) {
        super(context, resourceId);
        this.context = context;
        mListView = listView;
        for (String navItem : navItems) {
            add(navItem);
        }
        navItemsArrayList = new ArrayList<>(navItems.length);
        navItemsArrayList.addAll(Arrays.asList(navItems));
    }

    public void setSelectedNavItem(String selectedNavItem) {
        this.selectedNavItem = selectedNavItem;
    }

    private class ViewHolder {
        TextView textView;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence query) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<String> results = new ArrayList<>();
                if (preSearchArrayList == null)
                    preSearchArrayList = navItemsArrayList;
                if (query != null) {
                    if (preSearchArrayList != null && preSearchArrayList.size() > 0) {
                        for (final String navItem : preSearchArrayList) {
                            if (navItem.toLowerCase().contains(query.toString()))
                                results.add(navItem);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                navItemsArrayList = (ArrayList<String>) results.values;
                clear();
                for (String navItem : navItemsArrayList) {
                    add(navItem);
                }
                notifyDataSetChanged();
                if (mListView != null)
                    mListView.setAdapter(mListView.getAdapter()); //Needed to clear the recycler
            }
        };
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        String navItem = getItem(position).toString();

        if (v == null) {
            v = inflater.inflate(R.layout.navitem_list_item, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) v.findViewById(R.id.navitem_list_item_textview);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (navItem != null) {
            holder.textView.setText(navItem);
            if (navItem.equals(selectedNavItem))
                ((ListView) parent).setItemChecked(position, true);
        }
        return v;
    }
}