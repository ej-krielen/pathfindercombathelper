package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class QuestionLinearLayout extends LinearLayout {

    public QuestionLinearLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.question_layout, this, true);
    }

    public void setText(String questionText) {
        TextView tv = (TextView) findViewById(R.id.question_textView);
        tv.setText(questionText);
    }
}