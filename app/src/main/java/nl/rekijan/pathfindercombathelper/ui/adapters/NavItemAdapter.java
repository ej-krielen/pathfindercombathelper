package nl.rekijan.pathfindercombathelper.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.NavItemModel;

/**
 * Custom adapter for navigation items
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 2-4-2016
 */
public class NavItemAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private List<String> mListDataHeader;
    private List<String> mOriginalHeader;
    private HashMap<String, List<NavItemModel>> mListDataChild;
    private HashMap<String, List<NavItemModel>> mOriginalChild;
    private String selectedNavItem;

    public NavItemAdapter(Context context, List<String> listDataHeader,
                          HashMap<String, List<NavItemModel>> listDataChild) {
        mContext = context;
        mListDataHeader = listDataHeader;
        mOriginalHeader = new ArrayList<>(listDataHeader);
        mListDataChild = listDataChild;
        mOriginalChild = new HashMap<>(listDataChild);
    }

    public void setSelectedNavItem(String selectedNavItem) {
        this.selectedNavItem = selectedNavItem;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListDataChild.get(mListDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    private class ViewHolder {
        TextView textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View v, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final NavItemModel navItem = (NavItemModel) getChild(groupPosition, childPosition);

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
            //Set item as selected or not
            if (navItem.getTitle().equals(selectedNavItem)) {
                int index = ((ExpandableListView) parent).getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                ((ExpandableListView) parent).setItemChecked(index, true);
            } else {
                ((ExpandableListView) parent).setItemChecked(-1, true);
            }
        }
        return v;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListDataChild.get(mListDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.navigation_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * Rebuilds the list based on the query
     * @param query String entered in the SearchView of {@link nl.rekijan.pathfindercombathelper.ui.views.SearchableNavigationView SearchableNavigationView}
     */
    public void filterData(String query) {

        query = query.toLowerCase();
        mListDataHeader.clear();
        mListDataChild.clear();

        if (!TextUtils.isEmpty(query)) {
            for (String category : mOriginalHeader) {

                List<NavItemModel> navItems = mOriginalChild.get(category);
                List<NavItemModel> newList = new ArrayList<>();
                for (NavItemModel n : navItems) {
                    if (n.getTitle().toLowerCase().contains(query)) {
                        newList.add(n);
                    }
                }
                if (newList.size() > 0) {
                    mListDataHeader.add(category);
                    mListDataChild.put(category, newList);
                }
            }
        } else {
            mListDataHeader = new ArrayList<>(mOriginalHeader);
            for (String category : mOriginalHeader) {
                List<NavItemModel> navItems = mOriginalChild.get(category);
                mListDataChild.put(category, navItems);
            }
        }
        notifyDataSetChanged();
    }
}