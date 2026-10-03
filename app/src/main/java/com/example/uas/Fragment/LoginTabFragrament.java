package com.example.uas.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.uas.Activity.DashboardActivity;
import com.example.uas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragrament extends Fragment {

    private EditText edtEmail, edtPassword;
    private TextView lupaPass;
    private Button login;
    private float v = 0;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        edtEmail = root.findViewById(R.id.edtemail);
        edtPassword = root.findViewById(R.id.edtpassword);
        lupaPass = root.findViewById(R.id.lupaPw);
        login = root.findViewById(R.id.btnLog);

        edtEmail.setTranslationX(800);
        edtPassword.setTranslationX(800);
        lupaPass.setTranslationX(800);
        login.setTranslationX(800);

        mAuth = FirebaseAuth.getInstance();

        edtEmail.setAlpha(v);
        edtPassword.setAlpha(v);
        lupaPass.setAlpha(v);
        login.setAlpha(v);

        edtEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edtPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        lupaPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTxt = edtEmail.getText().toString();
                String passwordTxt = edtPassword.getText().toString();

                if (emailTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(getContext().getApplicationContext(), "Email dan Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                   logUser(emailTxt, passwordTxt);
                }
            }
        });

        return root;
    }

    private void logUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task -> {
            if (task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null){
                    String userId = user.getUid();
                    Toast.makeText(getContext().getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                    Intent dashboard = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(dashboard);
                    getActivity().finish();
                }
            } else {
                Toast.makeText(getContext().getApplicationContext(), "Login Gagal!, Periksa Kembali Email dan Password Anda!", Toast.LENGTH_SHORT).show();
                edtEmail.setText("");
                edtPassword.setText("");
            }
        });
    }

}
