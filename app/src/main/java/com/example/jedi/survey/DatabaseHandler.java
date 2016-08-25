package com.example.jedi.survey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Jedi on 8/23/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Survey";
    public static final String TABLE_NAME = "Survey_Data";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String ORGANIZATION_NAME = "ORGANIZATION_NAME";
    public static final String EMAIL_ID = "EMAIL_ID";
    public static final String CONTACT_NUM = "CONTACT_NUM";
    public static final String DESIGNATION = "DESIGNATION";
    public static final String BUSINESS_TYPE = "BUSINESS_TYPE";
    public static final String STREET_ADD = "STREET_ADD";
    public static final String ADD_LINE2 = "ADD_LINE2";
    public static final String CITY = "CITY";
    public static final String STATE = "STATE";
    public static final String ZIP ="ZIP";
    public static final String COUNTRY="COUNTRY";
    public static final String WORKING_HRS="WORKING_HRS";
    public static final String WORKING_HRS_TO="WORKING_HRS_TO";
    public static final String WEBSITE = "WEBSITE";
    public static final String PART_TIME_AVAIL = "PART_TIME_AVAIL";
    public static final String FREQ_PART = "FREQ_PART";
    public static final String OPINION_PART="OPINION_PART";
    public static final String PART_TIMING="PART_TIMING";
    public static final String PART_TIMING_TO="PART_TIMING_TO";
    public static final String SALARY_TYPE="SALARY_TYPE";
    public static final String MONTHLY_SALARY="MONTHLY_SALARY";
    public static final String AGENCIES="AGENCIES";
    public static final String MONEY_SPENT="MONEY_SPENT";


    Context mcontext;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mcontext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME
                +"(" + ID + " integer primary key autoincrement, " + NAME  + " text, " + ORGANIZATION_NAME + " text, "
                + EMAIL_ID  + " text, " + CONTACT_NUM + " text, " + DESIGNATION + " text, " + BUSINESS_TYPE + " text, "
                + STREET_ADD + " text, " + ADD_LINE2 + " text, " + CITY + " text, " + STATE + " text, " + ZIP + " text, "
                + COUNTRY + " text, " +WORKING_HRS + " text, "+ WORKING_HRS_TO + " text, "+ WEBSITE + " text, " + PART_TIME_AVAIL + " text, "
                + FREQ_PART + " text, " + OPINION_PART + " text, " + PART_TIMING + " text, "+PART_TIMING_TO+ " text, "  + SALARY_TYPE + " text, "
                + MONTHLY_SALARY + " text, " + AGENCIES + " text, " + MONEY_SPENT + " text); "  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);
    }

    public long INSERT_DATA(String name, String orgname, String email, String num, String desg, String btype, String sadd
                                , String addl2, String city, String state, String zip, String country, String workhrs, String workhrs2
                                , String website, String partav, String fprt, String oppart, String parttim,String parttim2, String saltyp
                                , String sal, String agen, String msp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(ORGANIZATION_NAME, orgname);
        contentValues.put(EMAIL_ID,email);
        contentValues.put(CONTACT_NUM, num);
        contentValues.put(DESIGNATION, desg);
        contentValues.put(BUSINESS_TYPE,btype);
        contentValues.put(STREET_ADD,sadd);
        contentValues.put(ADD_LINE2, addl2);
        contentValues.put(CITY,city);
        contentValues.put(STATE, state);
        contentValues.put(ZIP,zip);
        contentValues.put(COUNTRY,country);
        contentValues.put(WORKING_HRS,workhrs);
        contentValues.put(WORKING_HRS_TO,workhrs2);
        contentValues.put(WEBSITE,website);
        contentValues.put(PART_TIME_AVAIL,partav);
        contentValues.put(FREQ_PART,fprt);
        contentValues.put(OPINION_PART,oppart);
        contentValues.put(PART_TIMING,parttim);
        contentValues.put(PART_TIMING_TO,parttim2);
        contentValues.put(SALARY_TYPE,saltyp);
        contentValues.put(MONTHLY_SALARY,sal);
        contentValues.put(AGENCIES,agen);
        contentValues.put(MONEY_SPENT,msp);
        return  db.insert(TABLE_NAME,null,contentValues);
    }

//    public Boolean UPDATE_DATA(long row_id,String name, String orgname, String email, String num, String desg, String btype, String sadd
//            , String addl2, String city, String state, String zip, String country, String workhrs
//            , String website, String partav, String fprt, String oppart, String parttim, String saltyp
//            , String sal, String agen, String msp)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NAME,name);
//        contentValues.put(ORGANIZATION_NAME, orgname);
//        contentValues.put(EMAIL_ID,email);
//        contentValues.put(CONTACT_NUM, num);
//        contentValues.put(DESIGNATION, desg);
//        contentValues.put(BUSINESS_TYPE,btype);
//        contentValues.put(STREET_ADD,sadd);
//        contentValues.put(ADD_LINE2, addl2);
//        contentValues.put(CITY,city);
//        contentValues.put(STATE, state);
//        contentValues.put(ZIP,zip);
//        contentValues.put(COUNTRY,country);
//        contentValues.put(WORKING_HRS,workhrs);
//        contentValues.put(WEBSITE,website);
//        contentValues.put(PART_TIME_AVAIL,partav);
//        contentValues.put(FREQ_PART,fprt);
//        contentValues.put(OPINION_PART,oppart);
//        contentValues.put(PART_TIMING,parttim);
//        contentValues.put(SALARY_TYPE,saltyp);
//        contentValues.put(MONTHLY_SALARY,sal);
//        contentValues.put(AGENCIES,agen);
//        contentValues.put(MONEY_SPENT,msp);
//        return db.update(TABLE_NAME,contentValues,ID+"="+row_id,null) > 0;
//    }

//    public boolean DELETE_DATA(long row_id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, ID + "=" + row_id, null)>0;
//    }

    public void DELETE_ALL(){
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("TRUNCATE table " + TABLE_NAME+";");
            db.delete(TABLE_NAME,null,null);
    }
    public Cursor GET_ALL_DATA()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select ID As _id ,* from "+TABLE_NAME,null);
    }
//    public Cursor GET(long row_id)throws SQLException
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(true, TABLE_NAME, new String[]{ID, NAME, ORGANIZATION_NAME, EMAIL_ID,
//         CONTACT_NUM, DESIGNATION, BUSINESS_TYPE, STREET_ADD, ADD_LINE2, CITY, STATE, ZIP, COUNTRY,
//         WORKING_HRS, WEBSITE , PART_TIME_AVAIL , FREQ_PART , OPINION_PART, PART_TIMING, SALARY_TYPE,
//                MONTHLY_SALARY,AGENCIES,MONEY_SPENT}, row_id + "=" + ID, null, null, null, null, null);
//        if(cursor!=null)
//        {
//            cursor.moveToNext();
//        }
//        return cursor;
//    }
}
