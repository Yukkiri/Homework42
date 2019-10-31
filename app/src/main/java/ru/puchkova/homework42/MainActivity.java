package ru.puchkova.homework42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner country, city, house;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        country = (Spinner) findViewById(R.id.country);
        city = (Spinner) findViewById(R.id.city);
        house = (Spinner) findViewById(R.id.house);
        ok = (Button) findViewById(R.id.ok);

        initSpinnerCountries();
        initSpinnerHouses();

        ok.setOnClickListener(oclOk);

    }

    private void initSpinnerHouses(){
        Integer[] housesNumbers = new Integer[50];

        for(int i = 1; i < housesNumbers.length; i++){
            housesNumbers[i-1] = i;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, housesNumbers);
        house.setAdapter(adapter);
    }

    private void initSpinnerCountries(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities, android.R.layout.simple_spinner_item);
                break;
            case "Беларусь":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city.setAdapter(adapter);
        }
    }

    View.OnClickListener oclOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,
                    country.getSelectedItem().toString() + " " + city.getSelectedItem().toString() + " " + house.getSelectedItem().toString(),
                    Toast.LENGTH_LONG).show();
        }
    };
}
