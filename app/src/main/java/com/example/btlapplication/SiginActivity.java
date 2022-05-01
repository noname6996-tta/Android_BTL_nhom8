package com.example.btlapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SiginActivity extends AppCompatActivity {
    //email:theanh682001@gmail.com
    // pass 123456
    EditText sign_Email,sign_Password;
    Button btnSignin;
    private LinearLayout layout_singUp,layout_ForgotPassword;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        anhxa();
        initLister();
    }

    private void initLister() {
        layout_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SiginActivity.this, com.example.btlapplication.SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }
        });
        layout_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }
    private void setProcessDialog(){
        progressDialog = new ProgressDialog(this,ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setTitle("Hệ thống đang tải dữ liệu");
        progressDialog.setMessage("Vui lòng chờ");
    }
    private void onClickSignIn() {
        String email = sign_Email.getText().toString().trim();
        String password = sign_Password.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        setProcessDialog();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           Intent intent = new Intent(SiginActivity.this,MainActivity.class);
                           startActivity(intent);
                           finishAffinity();


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SiginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void anhxa(){
        progressDialog = new ProgressDialog(this);
        layout_singUp = findViewById(R.id.layout_singUp);
        layout_ForgotPassword = findViewById(R.id.layout_ForgotPassword);
        sign_Email = findViewById(R.id.sign_Email);
        sign_Password = findViewById(R.id.sign_Password);
        btnSignin = findViewById(R.id.btnSignin);
    }
    private void onClickForgotPass(String email){
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(SiginActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Reset");
        alertDialog.setMessage("Enter your Email");

        final EditText email = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        email.setLayoutParams(lp);
        alertDialog.setView(email);
        alertDialog.setIcon(R.drawable.ic_baseline_account_circle_24);

        alertDialog.setPositiveButton("Send",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String email1 = email.getText().toString();
                        onClickForgotPass(email1);
                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
}