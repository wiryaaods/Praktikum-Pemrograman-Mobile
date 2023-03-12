package com.example.tugas2praktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView jarijari, panjang, lebar, tinggi, hasil;
    EditText jarijari2, panjang2, lebar2, tinggi2;
    Button hitung;
    String [] bangunRuang = {"bola", "kerucut", "balok"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        jarijari = findViewById(R.id.jarijari);
        panjang = findViewById(R.id.panjang);
        lebar = findViewById(R.id.lebar);
        tinggi = findViewById(R.id.tinggi);
        jarijari2 = findViewById(R.id.jarijari2);
        panjang2 = findViewById(R.id.panjang2);
        lebar2 = findViewById(R.id.lebar2);
        tinggi2 = findViewById(R.id.tinggi2);
        hitung = findViewById(R.id.hitung);
        hasil = findViewById(R.id.hasil);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItem().toString().equals(bangunRuang[0])){
                    if (jarijari2.getText().toString().isEmpty()) {
                        jarijari2.setError("Field ini tidak boleh kosong");
                    }else {
                        Double jarijari = Double.valueOf(jarijari2.getText().toString());
                        Double volumeBola = (4.00/3.00) * Math.PI * Math.pow(jarijari, 3);
                        String duaFormat = String.format("%.2f", volumeBola);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(bangunRuang[1])) {
                    if (jarijari2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()){
                        jarijari2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (jarijari2.getText().toString().isEmpty()) {
                        jarijari2.setError("Field ini tidak boleh kosong");
                    } else if (tinggi2.getText().toString().isEmpty()) {
                        tinggi2.setError("Field ini tidak boleh kosong");
                    }else {
                        Double jarijari = Double.valueOf(jarijari2.getText().toString());
                        Double tinggi = Double.valueOf(tinggi2.getText().toString());
                        Double volumeKerucut = (Math.PI * Math.pow(jarijari, 2) * tinggi) / 3.00;
                        String duaFormat = String.format("%.2f", volumeKerucut);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(bangunRuang[2])) {
                    if (panjang2.getText().toString().isEmpty() && lebar2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                        lebar2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (panjang2.getText().toString().isEmpty() && lebar2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                        lebar2.setError("Field ini tidak boleh kosong");
                    } else if (panjang2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (lebar2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()) {
                        lebar2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (panjang2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                    } else if (lebar2.getText().toString().isEmpty()) {
                        lebar2.setError("Field ini tidak boleh kosong");
                    } else if (tinggi2.getText().toString().isEmpty()) {
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else {
                        Double panjang = Double.valueOf(panjang2.getText().toString());
                        Double lebar = Double.valueOf(lebar2.getText().toString());
                        Double tinggi = Double.valueOf(tinggi2.getText().toString());
                        Double volumeBalok = (panjang * lebar * tinggi);
                        String duaFormat = String.format("%.2f", volumeBalok);
                        hasil.setText(duaFormat);
                    }
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, bangunRuang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        jarijari.setVisibility(View.VISIBLE);
                        jarijari2.setVisibility(View.VISIBLE);
                        panjang.setVisibility(View.GONE);
                        panjang2.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        lebar2.setVisibility(View.GONE);
                        tinggi.setVisibility(View.GONE);
                        tinggi2.setVisibility(View.GONE);
                        break;
                    case 1:
                        jarijari.setVisibility(View.VISIBLE);
                        jarijari2.setVisibility(View.VISIBLE);
                        panjang.setVisibility(View.GONE);
                        panjang2.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        lebar2.setVisibility(View.GONE);
                        tinggi.setVisibility(View.VISIBLE);
                        tinggi2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        jarijari.setVisibility(View.GONE);
                        jarijari2.setVisibility(View.GONE);
                        panjang.setVisibility(View.VISIBLE);
                        panjang2.setVisibility(View.VISIBLE);
                        lebar.setVisibility(View.VISIBLE);
                        lebar2.setVisibility(View.VISIBLE);
                        tinggi.setVisibility(View.VISIBLE);
                        tinggi2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}