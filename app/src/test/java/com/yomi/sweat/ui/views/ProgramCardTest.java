package com.yomi.sweat.ui.views;

import android.os.Build;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yomi.sweat.R;
import com.yomi.sweat.model.Program;
import com.yomi.sweat.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.techery.properratingbar.ProperRatingBar;

import static org.junit.Assert.*;

@Config(sdk = Build.VERSION_CODES.O_MR1)
@RunWith(RobolectricTestRunner.class)
public class ProgramCardTest {
    private MainActivity activity;
    private TextView cardTitle;
    private TextView cardTrainerName;
    private ProgramCard programCard;
    private Program program;
    private String prgramString = "{\n" +
            "\"id\": 1,\n" +
            "\"acronym\": \"BBG\",\n" +
            "\"code_name\": \"kayla\",\n" +
            "\"name\": \"BBG\",\n" +
            "\"image\": \"https://assets.sweat.com/workout_groups/images/000/000/001/original/bbg-1242x2208593b84bcce1c6835be6cf215d7a01832.png?1542659076\",\n" +
            "\"attributes\": [\n" +
            "{\n" +
            "\"id\": 1,\n" +
            "\"code_name\": \"intensity\",\n" +
            "\"name\": \"Intensity\",\n" +
            "\"value\": \"2.0\",\n" +
            "\"total\": \"3.0\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 2,\n" +
            "\"code_name\": \"weight loss\",\n" +
            "\"name\": \"Weight Loss\",\n" +
            "\"value\": \"5.0\",\n" +
            "\"total\": \"5.0\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 3,\n" +
            "\"code_name\": \"muscle\",\n" +
            "\"name\": \"Muscle\",\n" +
            "\"value\": \"3.0\",\n" +
            "\"total\": \"5.0\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 4,\n" +
            "\"code_name\": \"strength\",\n" +
            "\"name\": \"Strength\",\n" +
            "\"value\": \"3.0\",\n" +
            "\"total\": \"5.0\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 6,\n" +
            "\"code_name\": \"fitness\",\n" +
            "\"name\": \"Fitness\",\n" +
            "\"value\": \"5.0\",\n" +
            "\"total\": \"5.0\"\n" +
            "}\n" +
            "],\n" +
            "\"trainer\": {\n" +
            "\"id\": 2,\n" +
            "\"name\": \"Kayla Itsines\",\n" +
            "\"code_name\": \"kayla\",\n" +
            "\"image_url\": \"https://assets.sweat.com/trainers/images/000/000/002/original/BBG_Stronger_3x.png?1521524134\"\n" +
            "},\n" +
            "\"tags\": [\n" +
            "{\n" +
            "\"id\": 129,\n" +
            "\"name\": \"Equipment Needed\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 128,\n" +
            "\"name\": \"Outdoors\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 126,\n" +
            "\"name\": \"Home\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 127,\n" +
            "\"name\": \"Gym\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 130,\n" +
            "\"name\": \"28-40 mins\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 140,\n" +
            "\"name\": \"Yoga mat\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 145,\n" +
            "\"name\": \"Medicine ball\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 150,\n" +
            "\"name\": \"Sweat towel\"\n" +
            "}\n" +
            "]\n" +
            "}".trim();

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        programCard = new ProgramCard(activity);
        program = new Gson().fromJson(prgramString, Program.class);
        programCard.setViews(program);

        cardTitle = programCard.findViewById(R.id.txt_program_title);
        cardTrainerName = programCard.findViewById(R.id.txt_program_trainer_name);
    }

    @Test
    public void verify_correct_details_in_product_card(){
        assertEquals(program.getName(), cardTitle.getText().toString());
        assertEquals("with " + program.getTrainer().getName(), cardTrainerName.getText().toString());
    }

    @Test
    public void verify_number_of_attributes_displayed(){
        LinearLayout ll = programCard.findViewById(R.id.layout_program_attributes);
        assertEquals(ll.getChildCount(), program.getAttributes().size());
    }

    @Test
    public void verify_correct_intensity_rating(){
        ProperRatingBar rating = programCard.findViewById(R.id.rating_program_intensity);
        assertEquals(rating.getRating(), program.getIntensityRating());
    }
}