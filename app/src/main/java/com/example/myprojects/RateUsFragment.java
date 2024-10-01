package com.example.myprojects;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RateUsFragment extends Fragment {

    private RatingBar ratingBar;
    private Button submitButton;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rate_us, container, false);

        // Initialize views
        ratingBar = view.findViewById(R.id.ratingBar);
        submitButton = view.findViewById(R.id.btnSubmitRating);

        // Initialize SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Load saved rating if exists
        float savedRating = sharedPreferences.getFloat("rating", 0);
        if (savedRating != 0) {
            ratingBar.setRating(savedRating);
        }

        // Set submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                saveRating(rating);
            }
        });

        return view;
    }

    private void saveRating(float rating) {
        // Save the rating in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating", rating);
        editor.apply();

        // Show a confirmation message
        Toast.makeText(getActivity(), "Rating submitted: " + rating, Toast.LENGTH_SHORT).show();
    }
}
