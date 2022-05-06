package com.example.btlapplication.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btlapplication.Fragment.ChangeFragment;
import com.example.btlapplication.Fragment.FravoriteFragment;
import com.example.btlapplication.Fragment.HIstoryFragment;
import com.example.btlapplication.Fragment.HomeFragment;
import com.example.btlapplication.Fragment.MyProfigFragment;
import com.example.btlapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVORITE = 1;
    private static final int FRAGMENT_HISTORY = 2;
    private static final int FRAGMENT_ACCOUNT = 3;
    private static final int FRAGMENT_CHANGEPASSWORD = 4;
    public static final int MY_REQUESR_CODE = 10;
    private int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private TextView tvName_header,tvEmail_header;
    final private MyProfigFragment mMyProfigFragment = new MyProfigFragment();
    final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()== RESULT_OK){
                Intent intent = result.getData();
                if (intent == null){
                    return;
                }
                Uri uri = intent.getData();
                mMyProfigFragment.setmUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    mMyProfigFragment.setBitMapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        drawerLayout =findViewById(R.id.drawerLayout);
        // add toorbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        // chon item hom
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        showInformationInFireBase();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            if (mCurrentFragment != FRAGMENT_HOME){
                // Main
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        } else if (id == R.id.nav_favorite){
            if (mCurrentFragment != FRAGMENT_FAVORITE){
                //Cart
                replaceFragment(new FravoriteFragment());
                mCurrentFragment = FRAGMENT_FAVORITE;
            }
        } else if (id == R.id.nav_history){
            if (mCurrentFragment != FRAGMENT_HISTORY){
                replaceFragment(new HIstoryFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        else if (id == R.id.nav_proflie){
            if (mCurrentFragment != FRAGMENT_ACCOUNT){
                replaceFragment(mMyProfigFragment);
                mCurrentFragment = FRAGMENT_ACCOUNT;
            }
        }
        else if (id == R.id.nav_ChangePassword){
            if (mCurrentFragment != FRAGMENT_CHANGEPASSWORD){
                replaceFragment(new ChangeFragment());
                mCurrentFragment = FRAGMENT_CHANGEPASSWORD;
            }
        }
        else if (id == R.id.nav_SignOut){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this,SiginActivity.class);
            startActivity(intent);
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
    private void initUI(){
        navigationView = findViewById(R.id.navgationView);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvEmail_header =navigationView.getHeaderView(0).findViewById(R.id.tvEmail_header);
        tvName_header = navigationView.getHeaderView(0).findViewById(R.id.tvName_header);
    }

    public void showInformationInFireBase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        Uri image = user.getPhotoUrl();
        String name = user.getDisplayName();
        String email = user.getEmail();
        if (name == null){
            tvName_header.setVisibility(View.GONE);
        }
        else {
            tvName_header.setVisibility(View.VISIBLE);
        }
        tvName_header.setText(name);
        tvEmail_header.setText(email);
        Glide.with(this).load(image).error(R.drawable.img).into(imageView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUESR_CODE){
            if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
            else {
                Toast.makeText(this, "Vui lòng cho phép mở thư viện ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"select Picture"));
    }
}