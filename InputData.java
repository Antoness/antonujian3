package com.juara.ujiantigaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.juara.ujiantigaandroid.model.InputFilm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class InputData extends AppCompatActivity {

    EditText txtNama, txtDirect, txtWrite, txtStudio;
    TextView txtTanggal;
    Spinner spnRating, spnGenre;
    Button btnSend, btnDate;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        txtNama = findViewById(R.id.txtNama);
        txtDirect = findViewById(R.id.txtDirectby);
        txtWrite = findViewById(R.id.txtWrite);
        txtStudio = findViewById(R.id.txtStudio);
        btnSend = findViewById(R.id.btnSend);
        txtTanggal = findViewById(R.id.txtTanggal);
        btnDate = findViewById(R.id.btnDate);


        spnRating =findViewById(R.id.spnRating);
        List<String> list = new ArrayList<String>();
        list.add("G");
        list.add("PG");
        list.add("PG-13");
        list.add("R");
        list.add("NC-17");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRating.setAdapter(dataAdapter);

        spnGenre = findViewById(R.id.spnGenre);
        List<String> list1 = new ArrayList<String>();
        list1.add("ACTION");
        list1.add("COMEDY");
        list1.add("DRAMA");
        list1.add("HISTORYCAL");
        list1.add("HORROR");
        list1.add("MUSICAL");
        list1.add("ROMANTIC");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenre.setAdapter(dataAdapter1);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        txtTanggal = findViewById(R.id.txtTanggal);
        btnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDateDialog();
                }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputFilm inf = new InputFilm();
                inf.setJudulfilm(txtNama.getText().toString());
                inf.setRating(spnRating.getSelectedItem().toString());
                inf.setGenre(spnGenre.getSelectedItem().toString());
                inf.setDirect(txtDirect.getText().toString());
                inf.setWrite(txtWrite.getText().toString());
                inf.setStudio(txtStudio.getText().toString());
                inf.save();
                finish();
            }
        });
    }
    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                txtTanggal.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
