package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Pupuk extends AppCompatActivity {

    CardView cardView,cardView2,cardView3,cardView4,cardView5;
    ImageButton showButton, showButton2, showButton3, showButton4,showButton5;
    LinearLayout hiddenLayout, hiddenLayout2, hiddenLayout3, hiddenLayout4,hiddenLayout5;
    private GridLayout gridLayout;
    private Button button1, button2, button3;
    private ImageView imageView, imageView1, imageView2, imageView3;
    ImageButton btbackpupuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupuk);

        cardView = findViewById(R.id.material_card_view);
        showButton = findViewById(R.id.image_button);
        hiddenLayout = findViewById(R.id.layout_expand);

        showButton.setOnClickListener(view -> {
            if (hiddenLayout.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenLayout.setVisibility(View.GONE);
                showButton.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenLayout.setVisibility(View.VISIBLE);
                showButton.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            }
        });

        cardView2 = findViewById(R.id.second_card_view);
        showButton2 = findViewById(R.id.new_image_button);
        hiddenLayout2 = findViewById(R.id.new_layout_expand);

        showButton2.setOnClickListener(view -> {
            if (hiddenLayout2.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenLayout2.setVisibility(View.GONE);
                showButton2.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenLayout2.setVisibility(View.VISIBLE);
                showButton2.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            }
        });

        cardView3 = findViewById(R.id.third_card_view);
        showButton3 = findViewById(R.id.third_image_button);
        hiddenLayout3 = findViewById(R.id.third_layout_expand);

        showButton3.setOnClickListener(view -> {
            if (hiddenLayout3.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                hiddenLayout3.setVisibility(View.GONE);
                showButton3.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                hiddenLayout3.setVisibility(View.VISIBLE);
                showButton3.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            }
        });

        cardView4 = findViewById(R.id.fourth_card_view);
        showButton4 = findViewById(R.id.fourth_image_button);
        hiddenLayout4 = findViewById(R.id.fourth_layout_expand);

        showButton4.setOnClickListener(view -> {
            if (hiddenLayout4.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                hiddenLayout4.setVisibility(View.GONE);
                showButton4.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                hiddenLayout4.setVisibility(View.VISIBLE);
                showButton4.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            }
        });

        cardView5 = findViewById(R.id.fifth_card_view);
        showButton5 = findViewById(R.id.fifth_image_button);
        hiddenLayout5 = findViewById(R.id.fifth_layout_expand);

        showButton5.setOnClickListener(view -> {
            if (hiddenLayout5.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                hiddenLayout5.setVisibility(View.GONE);
                showButton5.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                hiddenLayout5.setVisibility(View.VISIBLE);
                showButton5.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            }
        });

        btbackpupuk = findViewById(R.id.backpupuk);
        btbackpupuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pupuk.this, Homepage.class);
                startActivity(intent);
            }
        });

        gridLayout = findViewById(R.id.gridLayout);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        gridLayout.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleGridLayoutVisibility();
                toggleImageViewVisibility(imageView);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleGridLayoutVisibility();
                toggleImageViewVisibility(imageView);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleGridLayoutVisibility();
                toggleImageViewVisibility(imageView);
            }
        });
    }

    private void toggleGridLayoutVisibility() {
        if (gridLayout.getVisibility() == View.VISIBLE) {
            gridLayout.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            imageView1.setVisibility(View.GONE);
            imageView2.setVisibility(View.GONE);
            imageView3.setVisibility(View.GONE);
        } else {
            gridLayout.setVisibility(View.VISIBLE);
        }
    }

    private void toggleImageViewVisibility(ImageView imageView) {
        if (imageView.getVisibility() == View.VISIBLE) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
        }
    }
}
