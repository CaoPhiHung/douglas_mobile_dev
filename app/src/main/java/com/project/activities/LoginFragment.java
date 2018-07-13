package com.project.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
public class LoginFragment extends Fragment {

    View view;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final Context context = view.getContext();

        final LoginActivity loginActivity = ((LoginActivity)getActivity());
        final EditText textUsername = (EditText)view.findViewById(R.id.txtUserName);
        final EditText textPassword = (EditText)view.findViewById(R.id.txtPassword);

        Button btn = view.findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    User.login(textUsername.getText().toString(), textPassword.getText().toString());

                    Intent i = new Intent(context, HomeActivity.class);
                    startActivity(i);
                } catch (Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        TextView btnRegister = view.findViewById(R.id.linkChangeToRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity.switchScreen();
            }
        });

        return view;
    }

}
