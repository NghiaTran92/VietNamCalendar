package com.ngh.vn.vietnamcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lunar.calendar.Human;
import com.lunar.type.TypeSex;
import com.lunar.util.DateTime;

import java.text.SimpleDateFormat;

/**
 * Created by ngh on 5/20/2015.
 */
public class DetailHumanActivity extends Activity implements View.OnClickListener{
    private DatePicker mDatePicker;
    private TextView mTxtResult;
    private RadioButton mRaBtnMale;
    private RadioButton mRaBtnFemale;
    private Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_human);
        mDatePicker=(DatePicker)findViewById(R.id.datePicker);
        mTxtResult=(TextView)findViewById(R.id.txt_result);
        mBtnOk=(Button)findViewById(R.id.btnOk);
        mBtnOk.setOnClickListener(this);
        mRaBtnMale=(RadioButton)findViewById(R.id.raBtn_male);
        mRaBtnFemale=(RadioButton)findViewById(R.id.raBtn_female);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnOk:
                detailHuman();
                break;
        }
    }

    private void detailHuman(){
        String result=getString(R.string.detail_human)+"\n";
        TypeSex sex;
        if(mRaBtnMale.isChecked()){
            sex=TypeSex.Male;
        }else{
            sex=TypeSex.Female;
        }
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Human human=new Human(new DateTime(mDatePicker.getYear(),mDatePicker.getMonth()+1,mDatePicker.getDayOfMonth()),sex);
        result+=getString(R.string.sex)+human.getSex()+"\n";
        result+=getString(R.string.year_solar)+dateFormat.format(human.getBirthDay())+"\n";
        result+=getString(R.string.year_lunar)+dateFormat.format(human.getBirthDayLunar())+"\n";
        result+=getString(R.string.can)+human.getBirthYearCan()+"  "+getString(R.string.chi)+" "+human.getBirthYearChi()+"\n";
        result+=getString(R.string.menh)+human.getMenh()+"\n"+getString(R.string.ngu_hanh)+human.getMenh().getNguHanh()+"\n"+getString(R.string.Cung)+" "+human.getCung()+"\n";
        result+=getString(R.string.can_luong)+": "+human.checkCanLuong();
        mTxtResult.setText(result);
    }
}
