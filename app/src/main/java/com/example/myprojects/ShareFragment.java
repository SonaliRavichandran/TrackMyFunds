package com.example.myprojects;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShareFragment extends Fragment {

    private Button shareButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        // Initialize the share button
        shareButton = view.findViewById(R.id.btnShareApp);

        // Set the click listener for the share button
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });

        return view;
    }

    private void shareApp() {
        // Define the app's URL or deep link
        String appUrl = "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName();

        // Create an intent to share the app
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app: " + appUrl);
        shareIntent.setType("text/plain");

        // Verify that there are apps that can handle this intent
        if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "Share app via"));
        } else {
            Toast.makeText(getActivity(), "No app available to share", Toast.LENGTH_SHORT).show();
        }
    }
}
