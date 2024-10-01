package com.example.myprojects;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class AddFragment extends Fragment {

    private EditText desc,p;
    private RadioGroup cate;
    private RadioButton rb;
    private Button add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add, container, false);

        desc=view.findViewById(R.id.description);
        p=view.findViewById(R.id.price);
        cate=view.findViewById(R.id.groupcate);
        add=view.findViewById(R.id.Add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = desc.getText().toString().trim();
                int price = Integer.parseInt(p.getText().toString().trim());

                // Get selected category
                int selectedCategoryId = cate.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = getView().findViewById(selectedCategoryId);
                String category = selectedRadioButton.getText().toString();

                Item i = new Item();
                i.setDescription(description);
                i.setPrice(price);
                i.setCategory(category);

                Dashboard.roomdatabase.itemDao().insert(i);
                Toast.makeText(getActivity(),"Data Successfully saved",Toast.LENGTH_SHORT).show();
                desc.setText("");
                p.setText("");

            }
        });
        return view;
    }
}


            // Create a new Product object




