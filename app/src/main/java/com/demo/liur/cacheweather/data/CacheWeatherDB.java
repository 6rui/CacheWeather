package com.demo.liur.cacheweather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.demo.liur.cacheweather.model.Area;
import com.demo.liur.cacheweather.model.City;
import com.demo.liur.cacheweather.model.County;
import com.demo.liur.cacheweather.model.Province;

import java.util.ArrayList;

/**
 * 数据库常用操作封装类
 * //TODO attention：可以优化实体类，让省市县的类继承一个父类，来避免重复的代码
 * Created by Liur on 2016/6/15.
 */
public class CacheWeatherDB {
    public static final String DB_NAME = "cache_weather";
    public static final int VERSION = 1;
    private static CacheWeatherDB mCacheWeatherDB;
    private SQLiteDatabase mDB;

    /**
     * 私有化构造方法，单列模式。
     */
    private CacheWeatherDB(Context context) {
        CacheOpenHelper dbHelper = new CacheOpenHelper(context,
                DB_NAME, null, VERSION);
        mDB = dbHelper.getWritableDatabase();
    }

    /**
     * 公布获取唯一实例的方法
     */
    public synchronized static CacheWeatherDB getInstance(Context context) {
        if (mCacheWeatherDB == null) {
            mCacheWeatherDB = new CacheWeatherDB(context);
        }
        return mCacheWeatherDB;
    }


    /**
     * 将实例对象存储在数据库中
     */
    public void savaProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            mDB.insert("Province", null, values);
        }
    }

    /**
     * 从数据库读取省级信息
     */
    public ArrayList<Province> loadProvinces() {
        ArrayList<Province> provinces = new ArrayList<>();
        Cursor cursor = mDB.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinces.add(province);
            } while (cursor.moveToNext());
        }
        return provinces;
    }

    /**
     * 将实例对象存储在数据库中
     */
    public void savaCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            mDB.insert("City", null, values);
        }
    }

    /**
     * 从数据库读取市级信息
     */
    public ArrayList<City> loadCities() {
        ArrayList<City> provinces = new ArrayList<>();
        Cursor cursor = mDB.query("City", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                provinces.add(city);
            } while (cursor.moveToNext());
        }
        return provinces;
    }

    /**
     * 将实例对象存储在数据库中
     */
    public void savaCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            mDB.insert("County", null, values);
        }
    }

    /**
     * 从数据库读取县级信息
     */
    public ArrayList<County> loadCounties() {
        ArrayList<County> counties = new ArrayList<>();
        Cursor cursor = mDB.query("County", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                counties.add(county);
            } while (cursor.moveToNext());
        }
        return counties;
    }

    /***********************************************************************************************
     * 优化后代码
     */
    public ArrayList<Area> loadArea(Area area) {
        ArrayList<Area> areas = new ArrayList<>();
        String tableName = getAreaClassString(area);
        Cursor cursor = mDB.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                area.setId(cursor.getInt(cursor.getColumnIndex("id")));
                area.setName(cursor.getString(cursor.getColumnIndex("name")));
                area.setCode(cursor.getString(cursor.getColumnIndex("code")));
                areas.add(area);
            } while (cursor.moveToNext());
        }
        return areas;
    }

    public void savaArea(Area area) {
        if (area != null) {
            ContentValues values = new ContentValues();
            String tableName = getAreaClassString(area);
            values.put("name", area.getName());
            values.put("code", area.getCode());
            mDB.insert(tableName, null, values);
        }
    }
    private String getAreaClassString(Area area) {
        String tableName;
        if (area instanceof Province) {
            tableName = "Province";
        } else if (area instanceof City) {
            tableName = "City";
        } else {
            tableName = "County";
        }
        return tableName;
    }

}
