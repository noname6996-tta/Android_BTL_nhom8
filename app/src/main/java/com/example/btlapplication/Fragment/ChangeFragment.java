package com.example.btlapplication.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeFragment extends Fragment {
    private View view;
    private EditText edt_old_password,edt_new_password,edt_confirm_password;
    private Button btnChagePassword;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_changepassword,container,false);
        initUi();
        setProcessDialog();
        btnChagePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInputPass();
            }
        });
        return view;
    }
    private void initUi(){
        progressDialog = new ProgressDialog(getActivity());
        edt_old_password = view.findViewById(R.id.edt_old_password);
        edt_new_password = view.findViewById(R.id.edt_new_password);
        edt_confirm_password = view.findViewById(R.id.edt_confirm_password);
        btnChagePassword = view.findViewById(R.id.btnChagePassword);
    }
    private void checkInputPass(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }
        if (edt_confirm_password.getText().toString().trim()==edt_new_password.getText().toString().trim()){
            onCliclChangePassword();
        }
        else {
            Toast.makeText(getActivity(), "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
        }
    }
    private void onCliclChangePassword() {
        String strnewPassword = edt_new_password.getText().toString();
        progressDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(strnewPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Update password Success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            showAlertDialog();
                        }
                    }
                });
    }
    private void setProcessDialog(){
        progressDialog = new ProgressDialog(getActivity(),ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setTitle("Hệ thống đang tải dữ liệu");
        progressDialog.setMessage("Vui lòng chờ");
    }
    private void reauthenticate(String email,String pass){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, pass);
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                onCliclChangePassword();
                            }
                            else {
                                Toast.makeText(getActivity(), "Vui lòng nhập đúng thông tin đăng nhập cũ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    private void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Login");
        alertDialog.setMessage("Enter Password and Email");

        final EditText email = new EditText(getActivity());
        email.setHint("Email");
        final EditText password = new EditText(getActivity());
        password.setHint("Password");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        email.setLayoutParams(lp);
        alertDialog.setView(email);
        alertDialog.setIcon(R.drawable.ic_baseline_account_circle_24);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String email1 = email.getText().toString();
                        String password1 = password.getText().toString();
                        reauthenticate(email1,password1);
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

