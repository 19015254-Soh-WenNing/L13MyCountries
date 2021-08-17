package sg.edu.rp.c346.id19015254.mycountries;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etCountry, etDesc, etArea, etYear, etCity;
    //RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        /*rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);*/

        rb = (RatingBar) findViewById(R.id.ratingBar2);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etDesc = (EditText) findViewById(R.id.etDesc);
        etArea = (EditText) findViewById(R.id.etArea);
        etYear = (EditText) findViewById(R.id.etYear);
        etCity = (EditText) findViewById(R.id.etCity);

        Intent i = getIntent();
        final Country currentCountry = (Country) i.getSerializableExtra("country");

        etID.setText(currentCountry.getId()+"");
        etCountry.setText(currentCountry.getCountry());
        etDesc.setText(currentCountry.getDesc());
        etArea.setText(currentCountry.getArea()+"");
        etYear.setText(currentCountry.getYear()+"");
        etCity.setText(currentCountry.getCity());
        rb.setRating(currentCountry.getStars());

        /*switch (currentSong.getStars()){
            case 5: rb5.setChecked(true);
                    break;
            case 4: rb4.setChecked(true);
                    break;
            case 3: rb3.setChecked(true);
                    break;
            case 2: rb2.setChecked(true);
                    break;
            case 1: rb1.setChecked(true);
        }*/

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentCountry.setCountry(etCountry.getText().toString().trim());
                currentCountry.setDesc(etDesc.getText().toString().trim());

                int area = 0;
                try {
                    area = Integer.valueOf(etArea.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid area", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentCountry.setArea(area);

                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentCountry.setYear(year);
                currentCountry.setCity(etCity.getText().toString().trim());

                /*int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);*/

                currentCountry.setStars(rb.getRating());
                int result = dbh.updateCountry(currentCountry);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Country updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the country\n" + currentCountry.getCountry());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteCountry(currentCountry.getId());
                        if (result>0){
                            Toast.makeText(ThirdActivity.this, "Country deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setNegativeButton("Do not discard", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}