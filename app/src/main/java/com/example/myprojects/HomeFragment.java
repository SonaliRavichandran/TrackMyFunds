package com.example.myprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnAdd,btnUpdate,btnView;


    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        btnAdd=view.findViewById(R.id.Add);
        btnUpdate=view.findViewById(R.id.Update);
        btnView=view.findViewById(R.id.View);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        return view;
    }


    @Override

    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.Add:
                Dashboard.fragmentManager.beginTransaction().replace(R.id.fragment_container,new AddFragment(),null).addToBackStack(null).commit();
                break;

            case R.id.View:
                Dashboard.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ViewFragment(),null).addToBackStack(null).commit();
                break;

            case R.id.Update:
                Dashboard.fragmentManager.beginTransaction().replace(R.id.fragment_container,new UpdateFragment(),null).addToBackStack(null).commit();
                break;
        }
    }
}
