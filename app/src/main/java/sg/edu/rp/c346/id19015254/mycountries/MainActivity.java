package sg.edu.rp.c346.id19015254.mycountries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etCountry, etDesc, etArea, etYear, etCity;
    Button btnInsert, btnShowList;
    RatingBar rbStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main));

        etCountry = findViewById(R.id.etCountry);
        etDesc = findViewById(R.id.etDesc);
        etArea = findViewById(R.id.etArea);
        etYear = findViewById(R.id.etYear);
        etCity = findViewById(R.id.etCity);
        btnInsert = findViewById(R.id.btnInsertIsland);
        btnShowList = findViewById(R.id.btnShowList);
        rbStars = findViewById(R.id.ratingBar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String country = etCountry.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                if (country.length() == 0 || desc.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String area_str = etArea.getText().toString().trim();
                int area = Integer.valueOf(area_str);
                String year_str = etYear.getText().toString().trim();
                int year = Integer.valueOf(year_str);
                String city = etCity.getText().toString().trim();
                float stars = rbStars.getRating();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertCountry(country, desc, area, year, city, stars);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Country inserted", Toast.LENGTH_LONG).show();
                    etCountry.setText("");
                    etDesc.setText("");
                    etArea.setText("");
                    etYear.setText("");
                    etCity.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    /*private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }*/
}