package sg.edu.rp.c346.id19015254.mycountries;

import java.io.Serializable;

public class Country implements Serializable {

    private int id;
    private String country;
    private String desc;
    private int area;
    private int year;
    private String city;
    private float stars;

    public Country(String country, String desc, int area, int year, String city, float stars) {
        this.country = country;
        this.desc = desc;
        this.area = area;
        this.stars = stars;
        this.year = year;
        this.city = city;
    }

    public Country(int id, String country, String desc, int area, int year, String city, float stars) {
        this.id = id;
        this.country = country;
        this.desc = desc;
        this.area = area;
        this.stars = stars;
        this.year = year;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public Country setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Country setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Country setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public int getArea() {
        return area;
    }

    public Country setArea(int area) {
        this.area = area;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Country setYear(int year) {
        this.year = year;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Country setCity(String city) {
        this.city = city;
        return this;
    }

    public float getStars() {
        return stars;
    }

    public Country setStars(float stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public String toString() {
        String starsString = "";
      /*  if (stars == 5){
            starsString = "*****";
        } else if (stars == 4){
            starsString = "****";
        }*/

        //or
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return starsString;

    }
}
