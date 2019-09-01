package com.yomi.sweat.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;
import com.yomi.sweat.R;
import com.yomi.sweat.model.Attribute;

import timber.log.Timber;

public class ProgramAttributeView extends ConstraintLayout {
    public ProgramAttributeView(Context context) {
        super(context);
        initializeViews(context);
    }

    public ProgramAttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public ProgramAttributeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.program_attribute, this);
    }

    public void setViews(Attribute programAttribute){
        TextView title = (TextView) this.findViewById(R.id.txt_attribute_title);
        RoundedHorizontalProgressBar bar = this.findViewById(R.id.progress_bar_attribute);

        title.setText(programAttribute.getName());
        //bar.setProgress(programAttribute.barValue());
        bar.animateProgress(1000, 0, programAttribute.barValue());
    }
}
