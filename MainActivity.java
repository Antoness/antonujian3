package com.juara.ujiantigaandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.activeandroid.query.Select;
import com.juara.ujiantigaandroid.adapter.FilmAdapter;
import com.juara.ujiantigaandroid.model.InputFilm;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FilmAdapter fd;
    RecyclerView rc;
    List<InputFilm> lstFlm;
    ImageButton btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.listData);
        btnTambah = findViewById(R.id.btnTambah);

        loadData();
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputData.class);
                startActivityForResult(intent,100);
            }
        });


    }
//    public void tambah (View v){
//        Intent intent = new Intent(MainActivity.this, InputData.class);
//             startActivityForResult(intent,100);
//    }
    public void loadData(){
        lstFlm = new Select().from(InputFilm.class).execute();
        fd = new FilmAdapter(lstFlm);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc.setLayoutManager(mLayoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.setAdapter(fd);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }
}

