package com.ngh.vn.vietnamcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.lunar.calendar.VietCalendar;


/**
 * Created by ngh on 5/20/2015.
 */
public class ConvertSolar2LunarActivity extends Activity implements View.OnClickListener {

    private DatePicker mDatePicker;
    private Button mBtnOk;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_solar_to_lunar);
        mDatePicker=(DatePicker)findViewById(R.id.datePicker);
        mBtnOk=(Button)findViewById(R.id.btnOk);
        mBtnOk.setOnClickListener(this);
        mTxtResult=(TextView)findViewById(R.id.txt_result);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnOk:
                String result=getString(R.string.date_lunar)+"\n";
                int[] lunar= VietCalendar.convertSolarDate2LunarDate(mDatePicker.getDayOfMonth(), mDatePicker.getMonth() + 1, mDatePicker.getYear(), 7.0);
                result+=lunar[0]+"/"+lunar[1]+"/"+lunar[2]+(lunar[3]==1?getString(R.string.leap_year):"");
                mTxtResult.setText(result);
                break;
        }
    }
}
