package com.example.healthalarm.activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.healthalarm.R;
import com.example.healthalarm.adapters.PhotosListAdapter;
import com.example.healthalarm.dataSets.PhotoDataSet;
import com.example.healthalarm.databinding.ActivityMainBinding;
import com.example.healthalarm.models.ViewpagerModel;
import com.example.healthalarm.viewPagerFuncations.ViewpagerFuncation;
import com.example.healthalarm.workManager.Workmanager;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    ActivityMainBinding binding;

    Boolean isWorkingSharedpreference, isWorking;

    private List<ViewpagerModel> photoslist;
    int[] Photoscounts;

    PeriodicWorkRequest workMangerRequest;

    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        isWorkingSharedpreference = sharedpreferences.getBoolean("key", true);
        isWorking = sharedpreferences.getBoolean("isworking", false);
        checkstatus(isWorkingSharedpreference);

        editor = sharedpreferences.edit();

        LoadPhotos();

        PhotosListAdapter photosListAdapter = new PhotosListAdapter(photoslist, getApplicationContext());
        binding.viewpager.setAdapter(photosListAdapter);
        ViewpagerFuncation viewpagerFuncation = new ViewpagerFuncation();
        viewpagerFuncation.setviewpager(binding.viewpager);

        workMangerRequest = new PeriodicWorkRequest.Builder(Workmanager.class, 3, TimeUnit.SECONDS)
                .setInitialDelay(Duration.ofSeconds(2))
                .setConstraints(new Constraints.Builder().setRequiresDeviceIdle(false).build())
                .addTag("work")
                .build();
    }

    private void LoadPhotos() {
        photoslist = PhotoDataSet.getPhotos();
        Photoscounts = new int[photoslist.size()];
    }

    public void startbtn(View view) {
        if (isWorking) {
            startMode();
            setWorkerOn();
            isWorking = false;

        } else {
            stopMode();
            setWorkerOff();
            isWorking = true;
        }
        editor.putBoolean("key", isWorking);
        editor.putBoolean("isworking", isWorking);
        editor.apply();
    }

    @SuppressLint("SetTextI18n")
    private void startMode() {
        binding.startBtn.setText(R.string.stop);
        binding.startBtn.setBackgroundResource(R.drawable.button_shape_red);
        binding.remainingTimeTextview.setVisibility(View.VISIBLE);
        binding.remainingTimeTextview.setText(R.string.Working);
    }

    private void setWorkerOn(){
        WorkManager.getInstance(getApplicationContext()).
                enqueueUniquePeriodicWork("workk", ExistingPeriodicWorkPolicy.REPLACE, workMangerRequest);
    }

    private void stopMode() {
        binding.startBtn.setText(R.string.start);
        binding.startBtn.setBackgroundResource(R.drawable.button_shape_green);
        binding.remainingTimeTextview.setVisibility(View.INVISIBLE);
    }

    private void setWorkerOff(){
        WorkManager.getInstance(getApplicationContext()).cancelUniqueWork("workk");
    }

    private void checkstatus(Boolean status){
        if (status){
            stopMode();
        }else{
            startMode();
        }
    }
 }