package com.project.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.db.User;

//import com.project.groupproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        final LoginActivity la = ((LoginActivity)getActivity());

        TextView tv = view.findViewById(R.id.linkLogin);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la.switchScreen();
            }
        });

        /**
         * register
         */
        Button btn = view.findViewById(R.id.btnRegister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User();
                newUser.name = ((EditText)view.findViewById(R.id.textName)).getText().toString();
                newUser.username = ((EditText)view.findViewById(R.id.textUsername)).getText().toString();
                newUser.password = ((EditText)view.findViewById(R.id.textPassword)).getText().toString();
                newUser.password_confirm = ((EditText)view.findViewById(R.id.textPasswordConfirm)).getText().toString();

                try{
                    newUser.register();
                } catch (Exception ex){
                    Toast.makeText(view.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
