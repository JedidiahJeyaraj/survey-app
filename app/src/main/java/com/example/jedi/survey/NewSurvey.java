package com.example.jedi.survey;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.WorkbookSettings;

public class NewSurvey extends AppCompatActivity {

    EditText NAME, ORGNAME, EMAIL, PHONE, DES, BUSTYPE, STREETADD, LINE2ADD, CITYADD, STATEADD, ZIPADD, WEBSITE, FREQUENCY, OPINIONPART, MONTHSAL,AGEN,MONEYSPENT;
    Button SUBMIT, PARTAVAIL, WORKHR;
    RadioButton RADIOBUTTON, RADIOBUTTON1, SALMON, SALWEEK, SALDAY;
    Spinner COUNTRY;
    DatabaseHandler handler;
    Calendar calendar = Calendar.getInstance();
    Calendar calendar2= Calendar.getInstance();;
    RadioGroup PARTTIMERAD , SALTYPE;

    private long ROW_ID;
    final String cont[] = {"India","Pakistan","Bangladesh","Nepal","China","America"};
    int contr;
    private static final int WORK_HOUR = 0;
    private static final int AVAILABLE_HOUR = 1;
    public static final String TIME_FORMAT = "kk:mm";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);
        NAME = (EditText)findViewById(R.id.name);
        ORGNAME = (EditText)findViewById(R.id.orgname);
        EMAIL= (EditText)findViewById(R.id.email);
        PHONE = (EditText)findViewById(R.id.phone);
        DES = (EditText)findViewById(R.id.des);
        BUSTYPE = (EditText)findViewById(R.id.bustypes);
        STREETADD = (EditText)findViewById(R.id.streetAdd);
        LINE2ADD = (EditText)findViewById(R.id.line2Add);
        CITYADD = (EditText)findViewById(R.id.cityAdd);
        STATEADD = (EditText)findViewById(R.id.stateAdd);
        ZIPADD = (EditText)findViewById(R.id.zipAdd);
        WEBSITE = (EditText)findViewById(R.id.website);
        FREQUENCY = (EditText)findViewById(R.id.frequency);
        OPINIONPART = (EditText)findViewById(R.id.opinionpart);
        MONTHSAL = (EditText)findViewById(R.id.monthsal);
        AGEN = (EditText)findViewById(R.id.agen);
        MONEYSPENT = (EditText)findViewById(R.id.moneyspent);
        SUBMIT = (Button)findViewById(R.id.submit);
        PARTAVAIL = (Button)findViewById(R.id.partavail);
        WORKHR = (Button)findViewById(R.id.workhr);
//        RADIOBUTTON = (RadioButton)findViewById(R.id.radioButton);
//        RADIOBUTTON1 = (RadioButton)findViewById(R.id.radioButton2);
        SALMON = (RadioButton)findViewById(R.id.salmon);
        SALWEEK = (RadioButton)findViewById(R.id.salweek);
        SALDAY = (RadioButton)findViewById(R.id.salday);

        PARTTIMERAD = (RadioGroup)findViewById(R.id.parttimeavail);
        SALTYPE = (RadioGroup)findViewById(R.id.saltyp);

        COUNTRY =(Spinner)findViewById(R.id.country);
        handler = new DatabaseHandler(this);
        if (savedInstanceState != null) ROW_ID = savedInstanceState.getLong(DatabaseHandler.ID);
        else ROW_ID = 0;
        SET_SPINNER();

        REGISTER_BUTTON_LISTENER();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SET_ROW_ID_FROM_INTENT();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id)
        {
            case WORK_HOUR :
                return SHOW_WORK_HR_TIME_PICKER();
            case AVAILABLE_HOUR:
                return SHOW_PART_AVAIL_TIME_PICKER();
        }
        return super.onCreateDialog(id);
    }

    private void SET_ROW_ID_FROM_INTENT()
    {
        if(ROW_ID == 0)
        {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) ROW_ID = bundle.getLong(handler.ID);
            else ROW_ID = 0;
        }
    }

    private void SET_SPINNER()
    {
        ArrayAdapter<String> arrayAdapter = (new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerview,cont){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
        });
        COUNTRY.setAdapter(arrayAdapter);
        COUNTRY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contr = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private TimePickerDialog SHOW_PART_AVAIL_TIME_PICKER()
    {
        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {	calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                UPDATE_PART_AVAIL_TIME_BUTTON_TEXT();
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        return timePicker;
    }

    private void UPDATE_PART_AVAIL_TIME_BUTTON_TEXT()
    {
        // Set the time button text based upon the value from the database
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        String timeForButton = timeFormat.format(calendar.getTime());
        PARTAVAIL.setText(timeForButton);
    }

    private TimePickerDialog SHOW_WORK_HR_TIME_PICKER()
    {
        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {	calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar2.set(Calendar.MINUTE, minute);
                UPDATE_WORK_HR_TIME_BUTTON_TEXT();
            }
        }, calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE), true);
        return timePicker;
    }

    private void UPDATE_WORK_HR_TIME_BUTTON_TEXT()
    {
        // Set the time button text based upon the value from the database
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        String timeForButton = timeFormat.format(calendar2.getTime());
        WORKHR.setText(timeForButton);
    }

    private void REGISTER_BUTTON_LISTENER()
    {
        PARTAVAIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(AVAILABLE_HOUR);
            }
        });

        WORKHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(WORK_HOUR);
            }
        });

        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SAVE_STATE();
            }
        });
        UPDATE_WORK_HR_TIME_BUTTON_TEXT();
        UPDATE_PART_AVAIL_TIME_BUTTON_TEXT();
    }

    private void SAVE_STATE()
    {
        int flag = 1;
        String name = NAME.getText().toString();
        if(name.length()==0)
        {
            NAME.setError("Required!!!");
            Toast.makeText(getApplicationContext(),"Name Required",Toast.LENGTH_SHORT).show();
            flag=0;
        }

        String orgname= ORGNAME.getText().toString();
        if (orgname.length()==0)
        {
            ORGNAME.setError("Required!!!");
            Toast.makeText(getApplicationContext(),"Organization Name Required",Toast.LENGTH_SHORT).show();
            flag=0;
        }

        String email = EMAIL.getText().toString();
        if(email.length()==0)
        {
            EMAIL.setError("Required!!!");
            Toast.makeText(getApplicationContext(),"Email Required",Toast.LENGTH_SHORT).show();
            flag=0;
        }

        String phone = PHONE.getText().toString();
        if(phone.length()==0)
        {
            PHONE.setError("Required!!!");
            Toast.makeText(getApplicationContext(),"Phone number Required",Toast.LENGTH_SHORT).show();
            flag=0;
        }

        String des = DES.getText().toString();
        String bustype = BUSTYPE.getText().toString();
        String streetadd = STREETADD.getText().toString();
        String line2add = LINE2ADD.getText().toString();
        String cityadd = CITYADD.getText().toString();
        String stateadd = STATEADD.getText().toString();
        String zipadd = ZIPADD.getText().toString();
        String contry = cont[contr];
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        String workinghr = format.format(calendar2.getTime());
        String website = WEBSITE.getText().toString();
        int pt = PARTTIMERAD.getCheckedRadioButtonId();
        RADIOBUTTON = (RadioButton)findViewById(pt);
        String partjob = RADIOBUTTON.getText().toString();
        String frequency = FREQUENCY.getText().toString();
        String opnprt = OPINIONPART.getText().toString();
        String availtime = format.format(calendar.getTime());
        //salary type
        int dt = SALTYPE.getCheckedRadioButtonId();
        RADIOBUTTON1 = (RadioButton)findViewById(dt);
        String saltype=RADIOBUTTON1.getText().toString();
        String monsal = MONTHSAL.getText().toString();
        String agen = AGEN.getText().toString();
        String moneyspent = MONEYSPENT.getText().toString();
        if(flag!=0)
        {
            if (ROW_ID == 0) {
                long id = handler.INSERT_DATA(name, orgname, email, phone
                        , des, bustype, streetadd, line2add, cityadd, stateadd
                        , zipadd, contry, workinghr, website, partjob, frequency
                        , opnprt, availtime, saltype, monsal, agen, moneyspent);
                if (id > 0) {
                    ROW_ID = id;
                }
            }
            finish();
            Toast.makeText(getApplicationContext(), "Reminder Added: ", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStop() {
        // if(reminderManager!=null)
        //   reminderManager.doUnbindService();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(DatabaseHandler.ID,ROW_ID);
    }

}
