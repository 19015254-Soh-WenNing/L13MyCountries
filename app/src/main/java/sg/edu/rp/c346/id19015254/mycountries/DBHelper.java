package sg.edu.rp.c346.id19015254.mycountries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycountries.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_COUNTRY = "Country";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_DESC = "desc";
    private static final String COLUMN_AREA = "area";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Island
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // islands TEXT, stars INTEGER, year INTEGER );
        String createCountryTableSql = "CREATE TABLE " + TABLE_COUNTRY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_COUNTRY + " TEXT, "
                + COLUMN_DESC + " TEXT, "
                + COLUMN_AREA + " INTEGER, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_CITY + " TEXT, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createCountryTableSql);
        Log.i("info", createCountryTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);
        onCreate(db);
    }

    public long insertCountry(String country, String desc, int area, int year, String city, float stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COUNTRY, country);
        values.put(COLUMN_DESC, desc);
        values.put(COLUMN_AREA, area);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_STARS, stars);

        long result = db.insert(TABLE_COUNTRY, null, values);

        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> countrieslist = new ArrayList<Country>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_COUNTRY + "," + COLUMN_DESC + ","
                + COLUMN_AREA + "," + COLUMN_YEAR + ","
                + COLUMN_CITY + "," + COLUMN_STARS
                + " FROM " + TABLE_COUNTRY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String country = cursor.getString(1);
                String desc = cursor.getString(2);
                int area = cursor.getInt(3);
                int year = cursor.getInt(4);
                String city = cursor.getString(5);
                int stars = cursor.getInt(6);

                Country newCountry = new Country(id, country, desc, area, year, city, stars);
                countrieslist.add(newCountry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return countrieslist;
    }

    public ArrayList<Country> getAllCountriesByStars(int starsFilter) {
        ArrayList<Country> countrieslist = new ArrayList<Country>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_COUNTRY, COLUMN_DESC, COLUMN_AREA, COLUMN_YEAR, COLUMN_CITY, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        //String selectQuery = "SELECT " + COLUMN_ID + ","
        //            + COLUMN_TITLE + ","
        //            + COLUMN_SINGERS + ","
        //            + COLUMN_YEAR + ","
        //            + COLUMN_STARS
        //            + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_COUNTRY, columns, condition, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String country = cursor.getString(1);
                String desc = cursor.getString(2);
                int area = cursor.getInt(3);
                int year = cursor.getInt(4);
                String city = cursor.getString(5);
                int stars = cursor.getInt(6);

                Country newCountry = new Country(id, country, desc, area, year, city, stars);
                countrieslist.add(newCountry);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return countrieslist;
    }

    public int updateCountry(Country data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COUNTRY, data.getCountry());
        values.put(COLUMN_DESC, data.getDesc());
        values.put(COLUMN_AREA, data.getArea());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_CITY, data.getCity());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_COUNTRY, values, condition, args);
        db.close();
        return result;
    }

    public int deleteCountry(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_COUNTRY, condition, args);
        db.close();
        return result;
    }

}
