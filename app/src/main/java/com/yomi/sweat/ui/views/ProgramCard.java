package com.yomi.sweat.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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

import io.techery.properratingbar.ProperRatingBar;

public class ProgramCard extends CardView {
    private Context mContext;
    LinearLayout programAttributesContainer;
    ImageView image;
    TagView programTags;
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
        ProperRatingBar intensityRating = findViewById(R.id.rating_program);
        programAttributesContainer = findViewById(R.id.layout_program_attributes);
        programTags = findViewById(R.id.tagview_program_tags);
        image = findViewById(R.id.image_program_trainer);


        resetViews();

        programTitle.setText(program.getName());
        programTrainer.setText("with " + program.getTrainer().getName());

        int intensity = program.getIntensityRating();
        if (intensity == -1){
            intensityRating.setVisibility(View.GONE);
        } else {
            intensityRating.setRating(intensity);
        }
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

    //Method to clear data in case the view is recycled in a recyclerView
    private void resetViews(){
        programAttributesContainer.removeAllViews();
        image.setImageResource(0);
        programTags.clear();
    }
}
