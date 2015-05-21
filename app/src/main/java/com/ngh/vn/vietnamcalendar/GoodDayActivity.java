package com.ngh.vn.vietnamcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.lunar.calendar.DayLunar;
import com.lunar.calendar.VietCalendar;
import com.lunar.type.TypeChi;
import com.lunar.type.TypeNhiThapBatTu;
import com.lunar.type.TypeTruc;

/**
 * Created by ngh on 5/20/2015.
 */
public class GoodDayActivity extends Activity implements View.OnClickListener {

    private DatePicker mDatePicker;
    private TextView mTxtResult;
    private Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_day);
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
                detailGoodDay();
                break;
        }
    }

    private void detailGoodDay(){
        String result="";
        int dd=mDatePicker.getDayOfMonth();
        int mm=mDatePicker.getMonth()+1;
        int yy=mDatePicker.getYear();

        int jd= VietCalendar.jdFromDate(dd, mm, yy);
        int[] lunar=VietCalendar.convertSolarDate2LunarDate(dd, mm, yy, 7.0);
        TypeChi chiOfDay=VietCalendar.get_Chi_Day(jd);
        DayLunar typeDay=VietCalendar.getDay_HoangDao(lunar[1], chiOfDay);
        result+=getString(R.string.gio_hoang_dao)+VietCalendar.getGioHoangDao(jd)+"\n";

        String goodBad="";
        switch (typeDay.typeDay){
            case GoodDay:
                goodBad=getString(R.string.good);
                break;
            case BadDay:
                goodBad=getString(R.string.bad);
                break;
            case NormalDay:
                goodBad=getString(R.string.normal);
                break;
        }

        result+=getString(R.string.date_lunar)+lunar[0]+"/"+lunar[1]+"/"+lunar[2]+(lunar[3]==1?getString(R.string.leap_year):"")+"\n";
        result+=getString(R.string.date_good_bad)+goodBad+"\n";

        TypeTruc typeTrucOfDay=VietCalendar.get_Truc(lunar[1], chiOfDay);
        result+=getString(R.string.date_good_bad)+getString(R.string.theo_truc)+typeTrucOfDay+"\n";
        TypeNhiThapBatTu nhiThapBatTu=VietCalendar.get_NhiThapBatTu(jd);
        result+=getString(R.string.date_good_bad)+getString(R.string.theo_nhi_thap_bat_tu)+nhiThapBatTu+"\n";
        mTxtResult.setText(result);
    }
}
