package com.example.myprojects;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class UpdateFragment extends Fragment {

    private EditText descupd,pricup;
    private RadioGroup radioGroup;
    private Button btnupdate;
    private RadioButton radiobutt;
    public UpdateFragment(){


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);
        descupd=view.findViewById(R.id.descriptionupdate);
        pricup=view.findViewById(R.id.priceupdate);
        radioGroup=view.findViewById(R.id.groupcateupdate);
        btnupdate=view.findViewById(R.id.btnupdate);



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price=Integer.parseInt(pricup.getText().toString());
                String desc=descupd.getText().toString();

                int selectedCategoryId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = getView().findViewById(selectedCategoryId);
                String category = selectedRadioButton.getText().toString();

                Item item=new Item();
                item.setPrice(price);
                item.setDescription(desc);
                item.setCategory(category);

                Dashboard.roomdatabase.itemDao().update(item);
                Dashboard.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ViewFragment(),null).commit();

                Toast.makeText(getActivity(),"Item Update successfully",Toast.LENGTH_SHORT).show();


            }
        });


        return view;
    }
}