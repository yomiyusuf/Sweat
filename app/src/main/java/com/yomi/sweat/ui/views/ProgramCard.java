package com.yomi.sweat.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.github.tommykw.tagview.DataTransform;
import com.github.tommykw.tagview.TagView;
import com.squareup.picasso.Picasso;
import com.yomi.sweat.R;
import com.yomi.sweat.model.Attribute;
import com.yomi.sweat.model.Program;
import com.yomi.sweat.model.Tag;

public class ProgramCard extends CardView {
    private Context mContext;
    public ProgramCard(@NonNull Context context) {
        super(context);
        initializeViews(context);
    }

    public ProgramCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public ProgramCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.program_card, this);
        mContext = context;
    }

    public void setViews(Program program){
        TextView programTitle = findViewById(R.id.txt_program_title);
        TextView programTrainer = findViewById(R.id.txt_program_trainer_name);
        LinearLayout programAttributesContainer = findViewById(R.id.layout_program_attributes);
        TagView programTags= findViewById(R.id.tagview_program_tags);
        ImageView image = findViewById(R.id.image_program_trainer);

        programTitle.setText(program.getName());
        programTrainer.setText("with " + program.getTrainer().getName());
        Picasso.get()
                .load(program.getImage())
                .into(image);

        for(Attribute attribute: program.getAttributes()){
            ProgramAttributeView attr = new ProgramAttributeView(mContext);
            attr.setViews(attribute);
            programAttributesContainer.addView(attr);
        }

        if(program.getTags() != null){
            programTags.setTags(program.getTags(), (DataTransform<Tag>) t -> t.getName());
        }
    }
}
