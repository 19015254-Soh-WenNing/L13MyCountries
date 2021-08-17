package sg.edu.rp.c346.id19015254.mycountries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Country> countryList;
    //ArrayAdapter adapter;
    //String moduleCode;
    int requestCode = 9;
    Button btn5Stars;
    CustomAdapter caCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " +  getResources().getText(R.string.title_activity_second));

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        countryList = dbh.getAllCountries();
        dbh.close();

		/*adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
		lv.setAdapter(adapter);*/

        caCountry = new CustomAdapter(this, R.layout.row, countryList);
        lv.setAdapter(caCountry);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("country", countryList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                countryList.clear();
                countryList.addAll(dbh.getAllCountriesByStars(5));
                caCountry.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == this.requestCode && resultCode == RESULT_OK){
            DBHelper dbh = new DBHelper(this);
            countryList.clear();
            countryList.addAll(dbh.getAllCountries());
            dbh.close();
            caCountry.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        countryList.clear();
        countryList.addAll(dbh.getAllCountries());
        caCountry.notifyDataSetChanged();
    }
}