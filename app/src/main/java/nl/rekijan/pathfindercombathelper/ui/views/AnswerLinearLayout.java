package nl.rekijan.pathfindercombathelper.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.rekijan.pathfindercombathelper.R;
import nl.rekijan.pathfindercombathelper.models.QuestionModel;

/**
 * Custom class to create custom layout
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 27-3-2016
 */
public class AnswerLinearLayout extends LinearLayout {

    public interface OnAnswerPressedListener {
        void onAnswerPressed(QuestionModel questionModel);
    }

    private OnAnswerPressedListener mListener;

    public AnswerLinearLayout(Context context) {
        super(context);
        if (context instanceof OnAnswerPressedListener) {
            mListener = (OnAnswerPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAnswerPressedListener");
        }
        LayoutInflater.from(context).inflate(R.layout.answer_layout, this, true);
    }

    public void setOnAnswerClickListener(final QuestionModel questionModel) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAnswerPressed(questionModel);
                }
            }
        });
    }

    public void setText(String answerText) {
        TextView tv = (TextView) findViewById(R.id.answer_textView);
        tv.setText(answerText);
    }
}