package com.example.crim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ImageSlider imgslider = findViewById(R.id.imgslider);
        GridLayout gridLayout = findViewById(R.id.home_grid);

        //Image auto sliderview

        List<SlideModel> slidemodels = new ArrayList<>();
        slidemodels.add(new SlideModel(R.drawable.iron, "Image 1"));
        slidemodels.add(new SlideModel(R.drawable.vendetta, "Image 2"));
        slidemodels.add(new SlideModel(R.drawable.vendetta2, "Image 3"));
        slidemodels.add(new SlideModel(R.drawable.shield, "Image 4"));
        slidemodels.add(new SlideModel(R.drawable.hammer, "Image 5"));
        slidemodels.add(new SlideModel(R.drawable.potter, "Image 6"));
        imgslider.setImageList(slidemodels, true);

        setClickEvent(gridLayout);


    }

    private void setClickEvent(final GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finali = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finali == 0) {
                        startActivity(new Intent(getApplicationContext(), cardavtivity0.class));
                    } else if (finali == 1) {
                        startActivity(new Intent(getApplicationContext(), cardactivity1.class));
                    } else {
                        Toast.makeText(homepage.this, "Activity Coming Soon :)", Toast.LENGTH_SHORT).show();
                    }
                    //Continue Else if for remaining cardView Activities


                }
            });
        }
    }
}
