package com.example.jedi.survey;

import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Settings extends AppCompatActivity {
    Button EXPORT , DELETE;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        handler = new DatabaseHandler(this);
        EXPORT = (Button) findViewById(R.id.export);
        DELETE = (Button) findViewById(R.id.delete);
        EXPORT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor REMINDER_CURSOR = handler.GET_ALL_DATA();
                startManagingCursor(REMINDER_CURSOR);
                exportToExcel(REMINDER_CURSOR);
            }
        });
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.DELETE_ALL();
                Toast.makeText(getApplicationContext(),"Database Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exportToExcel(Cursor cursor) {
        final String fileName = "SurveyList.xls";
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/Survey");
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("MySurveyList", 0);

            try {
                sheet.addCell(new Label(0, 0, "ID")); // column and row
                sheet.addCell(new Label(1, 0, "NAME"));
                sheet.addCell(new Label(2, 0, "ORGANIZATION_NAME"));
                sheet.addCell(new Label(3, 0, "EMAIL_ID"));
                sheet.addCell(new Label(4, 0, "CONTACT_NUM"));
                sheet.addCell(new Label(5, 0, "DESIGNATION"));
                sheet.addCell(new Label(6, 0, "BUSINESS_TYPE"));
                sheet.addCell(new Label(7, 0, "STREET_ADD"));
                sheet.addCell(new Label(8, 0, "ADD_LINE2"));
                sheet.addCell(new Label(9, 0, "CITY"));
                sheet.addCell(new Label(10, 0, "STATE"));
                sheet.addCell(new Label(11, 0, "ZIP"));
                sheet.addCell(new Label(12, 0, "COUNTRY"));
                sheet.addCell(new Label(13, 0, "WORKING_HRS"));
                sheet.addCell(new Label(14, 0, "WEBSITE"));
                sheet.addCell(new Label(15, 0, "PART_TIME_AVAIL"));
                sheet.addCell(new Label(16, 0, "FREQ_PART"));
                sheet.addCell(new Label(17, 0, "OPINION_PART"));
                sheet.addCell(new Label(18, 0, "PART_TIMING"));
                sheet.addCell(new Label(19, 0, "SALARY_TYPE"));
                sheet.addCell(new Label(20, 0, "MONTHLY_SALARY"));
                sheet.addCell(new Label(21, 0, "AGENCIES"));
                sheet.addCell(new Label(22, 0, "MONEY_SPENT"));

                if (cursor.moveToFirst()) {
                    do {
                        String id = cursor.getString(cursor.getColumnIndex(handler.ID));
                        String name = cursor.getString(cursor.getColumnIndex(handler.NAME));
                        String orgname = cursor.getString(cursor.getColumnIndex(handler.ORGANIZATION_NAME));
                        String email = cursor.getString(cursor.getColumnIndex(handler.EMAIL_ID));
                        String cno = cursor.getString(cursor.getColumnIndex(handler.CONTACT_NUM));
                        String designation = cursor.getString(cursor.getColumnIndex(handler.DESIGNATION));
                        String btype = cursor.getString(cursor.getColumnIndex(handler.BUSINESS_TYPE));
                        String sadd = cursor.getString(cursor.getColumnIndex(handler.STREET_ADD));
                        String addl2 = cursor.getString(cursor.getColumnIndex(handler.ADD_LINE2));
                        String city = cursor.getString(cursor.getColumnIndex(handler.CITY));
                        String state = cursor.getString(cursor.getColumnIndex(handler.STATE));
                        String zip = cursor.getString(cursor.getColumnIndex(handler.ZIP));
                        String country = cursor.getString(cursor.getColumnIndex(handler.COUNTRY));
                        String wrkhrs = cursor.getString(cursor.getColumnIndex(handler.WORKING_HRS));
                        String website = cursor.getString(cursor.getColumnIndex(handler.WEBSITE));
                        String pavail = cursor.getString(cursor.getColumnIndex(handler.PART_TIME_AVAIL));
                        String fpart = cursor.getString(cursor.getColumnIndex(handler.FREQ_PART));
                        String opart = cursor.getString(cursor.getColumnIndex(handler.OPINION_PART));
                        String ptiming= cursor.getString(cursor.getColumnIndex(handler.PART_TIMING));
                        String saltyp = cursor.getString(cursor.getColumnIndex(handler.SALARY_TYPE));
                        String monsal = cursor.getString(cursor.getColumnIndex(handler.MONTHLY_SALARY));
                        String agen = cursor.getString(cursor.getColumnIndex(handler.AGENCIES));
                        String msp = cursor.getString(cursor.getColumnIndex(handler.MONEY_SPENT));

                        int i = cursor.getPosition() + 1;
                        sheet.addCell(new Label(0, i, id));
                        sheet.addCell(new Label(1, i, name));
                        sheet.addCell(new Label(2, i, orgname));
                        sheet.addCell(new Label(3, i, email));
                        sheet.addCell(new Label(4, i, cno));
                        sheet.addCell(new Label(5, i, designation));
                        sheet.addCell(new Label(6, i, btype));
                        sheet.addCell(new Label(7, i, sadd));
                        sheet.addCell(new Label(8, i, addl2));
                        sheet.addCell(new Label(9, i, city));
                        sheet.addCell(new Label(10, i, state));
                        sheet.addCell(new Label(11, i, zip));
                        sheet.addCell(new Label(12, i, country));
                        sheet.addCell(new Label(13, i, wrkhrs));
                        sheet.addCell(new Label(14, i, website));
                        sheet.addCell(new Label(15, i, pavail));
                        sheet.addCell(new Label(16, i, fpart));
                        sheet.addCell(new Label(17, i, opart));
                        sheet.addCell(new Label(18, i, ptiming));
                        sheet.addCell(new Label(19, i, saltyp));
                        sheet.addCell(new Label(20, i, monsal));
                        sheet.addCell(new Label(21, i, agen));
                        sheet.addCell(new Label(22, i, msp));
                        Toast.makeText(this,sdCard.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
                //closing cursor
                cursor.close();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
