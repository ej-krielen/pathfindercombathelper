package nl.rekijan.pathfindercombathelper.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.NavItemModel;

/**
 * Custom adapter for navigation items
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 2-4-2016
 */
public class NavItemAdapter extends ArrayAdapter<NavItemModel> implements Filterable {
    private final Context mContext;
    public ArrayList<NavItemModel> navItemsArrayList;
    public ArrayList<NavItemModel> preSearchArrayList;
    private String selectedNavItem;
    private ListView mListView;

    public NavItemAdapter(Context context, int resourceId, List<NavItemModel> navItems, ListView listView) {
        super(context, resourceId);
        mContext = context;
        mListView = listView;
        addAll(navItems);
        navItemsArrayList = new ArrayList<>(navItems);
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
                final ArrayList<NavItemModel> results = new ArrayList<>();
                if (preSearchArrayList == null)
                    preSearchArrayList = navItemsArrayList;
                if (query != null) {
                    if (preSearchArrayList != null && preSearchArrayList.size() > 0) {
                        for (final NavItemModel navItem : preSearchArrayList) {
                            if (navItem.getTitle().toLowerCase().contains(query.toString()))
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
                navItemsArrayList = (ArrayList<NavItemModel>) results.values;
                clear();
                for (NavItemModel navItem : navItemsArrayList) {
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        NavItemModel navItem = getItem(position);

        if (v == null) {
            v = inflater.inflate(R.layout.navitem_list_item, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) v.findViewById(R.id.navitem_list_item_textView);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (navItem != null) {
            holder.textView.setText(navItem.getTitle());
            if (navItem.getTitle().equals(selectedNavItem))
                ((ListView) parent).setItemChecked(position, true);
        }
        return v;
    }
}