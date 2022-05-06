package com.example.btlapplication.Fragment;

import static com.example.btlapplication.View.MainActivity.MY_REQUESR_CODE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.btlapplication.View.MainActivity;
import com.example.btlapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MyProfigFragment extends Fragment {
    private View view;
    private ImageView img_profig;
    private EditText ed_fullname,ed_email;
    private Button btn_update_profig,btn_update_email;
    private Uri mUri;
    private MainActivity mainActivity;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myprofig,container,false);
        initUi();
        mainActivity = (MainActivity) getActivity();
        setUserInfo();
        initLister();
        return view;
    }

    private void initLister() {
        img_profig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicRequestPermisson();
            }
        });
    }
    private void setProcessDialog(){
        progressDialog = new ProgressDialog(getActivity(),ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setTitle("Hệ thống đang tải dữ liệu");
        progressDialog.setMessage("Vui lòng chờ");
    }

    private void onClicRequestPermisson() {
        mainActivity = (MainActivity) getActivity();
        if (mainActivity == null){
            return;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }

        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        }
        else {
            String[] persmisson = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(persmisson,MY_REQUESR_CODE);
        }
    }

    private void setUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }
        else {
            ed_fullname.setText(user.getDisplayName());
            ed_email.setText(user.getEmail());
            Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.img).into(img_profig);
        }
    }

    private void initUi(){
        img_profig = view.findViewById(R.id.img_profig);
        ed_fullname = view.findViewById(R.id.ed_fullname);
        ed_email = view.findViewById(R.id.ed_email);
        btn_update_profig = view.findViewById(R.id.btn_update_name);
        btn_update_email = view.findViewById(R.id.btn_update_email);
        progressDialog = new ProgressDialog(getActivity());
        setProcessDialog();
        btn_update_profig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfig();
            }
        });
        btn_update_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateEmail();
            }
        });
    }



    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    private void onClickUpdateProfig() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user ==null){
            return;
        }
        progressDialog.show();
        String strFullname = ed_fullname.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullname)
                .setPhotoUri(mUri)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Update Profig Success", Toast.LENGTH_SHORT).show();
                            mainActivity.showInformationInFireBase();
                        }
                    }
                });
    }
    private void onClickUpdateEmail() {
        String email = ed_email.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user ==null){
            return;
        }
        progressDialog.show();
        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User email address updated", Toast.LENGTH_SHORT).show();
                            mainActivity.showInformationInFireBase();
                        }
                    }
                });

    }

    public void setBitMapImageView(Bitmap  bitMapImageView){
        img_profig.setImageBitmap(bitMapImageView);
        Log.e("AA",bitMapImageView.toString());
    }
}
