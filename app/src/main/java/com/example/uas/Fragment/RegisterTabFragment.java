package com.example.uas.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.uas.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterTabFragment extends Fragment {

    private EditText edtEmail, edtNama, edtNohp, edtPassword;
    private Button btnReg;
    private DatabaseReference databaseReference;
    private ViewPager viewPager;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);

        edtEmail = root.findViewById(R.id.reg_email);
        edtNama = root.findViewById(R.id.reg_nama);
        edtNohp = root.findViewById(R.id.reg_noHp);
        edtPassword = root.findViewById(R.id.reg_password);
        btnReg = root.findViewById(R.id.btn_reg);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://uas-orderfood-default-rtdb.firebaseio.com/");

        viewPager = getActivity().findViewById(R.id.view_pager);
        mAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString().trim();
                String nama = edtNama.getText().toString().trim();
                String noHp = edtNohp.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty() || nama.isEmpty() || noHp.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext().getApplicationContext(), "Ada Data Yang Masih Kosong!", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getContext().getApplicationContext(), "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                } else {
                    regUser(email, password, nama, noHp);
                }
            }
        });


        return root;
    }

    private void regUser(String email, String password, String nama, String noHp) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    String userId = firebaseUser.getUid();
                    User user = new User(email, nama, noHp, password);
                    databaseReference.child("users").child(userId).setValue(user).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(getContext().getApplicationContext(), "Register Berhasil", Toast.LENGTH_LONG).show();
                            edtEmail.setText("");
                            edtNama.setText("");
                            edtNohp.setText("");
                            edtPassword.setText("");

                            if (viewPager != null) {
                                viewPager.setCurrentItem(0);

                            } else {
                                Toast.makeText(getContext().getApplicationContext(), "Register Gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            } else {
                String pesanError = task.getException().getMessage();
                if (pesanError.contains("The given password is invalid")){
                    Toast.makeText(getContext().getApplicationContext(), "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "Register Gagal!" + pesanError, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static class User {
        public String email;
        public String nama;
        public String noHp;
        public String password;

        public User(){

        }

        public User(String email, String nama, String noHp, String password){
            this.email = email;
            this.nama = nama;
            this.noHp = noHp;
            this.password = password;
        }
    }
}