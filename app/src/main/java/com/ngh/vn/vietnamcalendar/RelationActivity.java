package com.ngh.vn.vietnamcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lunar.calendar.Human;
import com.lunar.type.TypeDiaChi;
import com.lunar.type.TypeQuality;
import com.lunar.type.TypeSex;
import com.lunar.type.TypeStar;
import com.lunar.util.DateTime;

/**
 * Created by ngh on 5/20/2015.
 */
public class RelationActivity extends Activity implements View.OnClickListener {

    private DatePicker mDatePickerHuman1;
    private DatePicker mDatePickerHuman2;
    private Button mBtnOk;
    private TextView mTxtResult;
    private RadioButton mRaBtnMaleHuman1;
    private RadioButton mRaBtnMaleHuman2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
        mDatePickerHuman1 =(DatePicker)findViewById(R.id.datePicker_human_1);
        mDatePickerHuman2 =(DatePicker)findViewById(R.id.datePicker_human_2);
        mRaBtnMaleHuman1 =(RadioButton)findViewById(R.id.raBtn_male_human_1);
        mRaBtnMaleHuman2 =(RadioButton)findViewById(R.id.raBtn_male_human_2);
        mBtnOk=(Button)findViewById(R.id.btnOk);
        mBtnOk.setOnClickListener(this);
        mTxtResult=(TextView)findViewById(R.id.txt_result);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnOk:
                relation();
                break;
        }
    }

    private void relation(){
        String result="";
        int default_time_zone=7;

        TypeSex sex1,sex2;
        if(mRaBtnMaleHuman1.isChecked()){
            sex1=TypeSex.Male;
        }else{
            sex1=TypeSex.Female;
        }

        if(mRaBtnMaleHuman2.isChecked()){
            sex2=TypeSex.Male;
        }else{
            sex2=TypeSex.Female;
        }

        Human human1=new Human(new DateTime(mDatePickerHuman1.getYear(), mDatePickerHuman1.getMonth()+1, mDatePickerHuman1.getDayOfMonth()), sex1,default_time_zone);
        Human human2=new Human(new DateTime(mDatePickerHuman2.getYear(), mDatePickerHuman2.getMonth()+1, mDatePickerHuman2.getDayOfMonth()),sex2,default_time_zone);
        result+=getString(R.string.hop_khac_2_nguoi)+"\n";

        TypeQuality qualityThienCan= human1.checkThienCan(human2.getBirthYearCan());
        result+=getString(R.string.kiem_tra_thien_can)+qualityThienCan.getValue()+"\n";
        result+=getString(R.string.kiem_tra_dia_chi)+"\n";
        TypeDiaChi[] listDiaChi=human1.checkDiaChi(human2.getBirthYearChi());
        for(TypeDiaChi diachi:listDiaChi){
            result+= diachi.toString()+"\n";
        }


        TypeQuality qualityNguHanh= human1.checkMenhNguHanh(human2.getMenh().getNguHanh());
        result+=getString(R.string.kiem_tra_ngu_hanh)+" "+qualityNguHanh.getValue()+"\n";
        TypeStar qualityCung= human1.checkCung(human2.getCung());
        result+=getString(R.string.kiem_tra_cung)+" "+qualityCung+"\n";
        mTxtResult.setText(result);

    }
}
