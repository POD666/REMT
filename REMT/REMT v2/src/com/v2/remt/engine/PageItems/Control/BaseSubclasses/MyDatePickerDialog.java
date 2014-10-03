package com.v2.remt.engine.PageItems.Control.BaseSubclasses;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDatePickerDialog extends DatePickerDialog{
    private int minYear;
    private int minMonth;
    private int minDay;

    private int maxYear;
    private int maxMonth;
    private int maxDay;

    private final Calendar mCalendar;
    private final SimpleDateFormat formatter;

    public void SetMinDate(int year,int monthOfYear,int dayOfMonth)
    {
        minYear = year;
        minMonth = monthOfYear;
        minDay = dayOfMonth;
    }
    public void SetMaxDate(int year,int monthOfYear,int dayOfMonth)
    {
        maxYear=year;
        maxMonth=monthOfYear;
        maxDay=dayOfMonth;
    }



    public MyDatePickerDialog(Context context,OnDateSetListener callBack,int year,int monthOfYear,int dayOfMonth){
        super(context,callBack,year,monthOfYear,dayOfMonth);

        mCalendar = Calendar.getInstance();

        mCalendar.setTime(new Date());

        maxYear=mCalendar.get(Calendar.YEAR);
        maxMonth=mCalendar.get(Calendar.MONTH);
        maxDay=mCalendar.get(Calendar.DATE);

        //set up init date
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //set up date display format
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        setTitle(formatter.format(mCalendar.getTime()));
    }

    @Override
    public void onDateChanged(DatePicker view, int year,int month, int day){
        boolean beforeMinDate=false;
        boolean afterMaxDate=false;

        if(year<minYear){
            beforeMinDate=true;
        }
        else if(year==minYear){
            if(month<minMonth){
                beforeMinDate=true;
            }
            else if(month==minMonth){
                if(day<minDay){
                    beforeMinDate=true;
                }
            }
        }

        if(!beforeMinDate){
            if(year>maxYear){
                afterMaxDate=true;
            }
            else if(year==maxYear){
                if(month>maxMonth){
                    afterMaxDate=true;
                }
                else if(month==maxMonth){
                    if(day>maxDay){
                        afterMaxDate=true;
                    }
                }
            }
        }

        //need set invalid date to mindate or maxdate
        if(beforeMinDate || afterMaxDate){
            if(beforeMinDate){
                year=minYear;
                month=minMonth;
                day=minDay;
            }
            else{
                year=maxYear;
                month=maxMonth;
                day=maxDay;
            }
            view.updateDate(year,  month,  day);
        }

        //display in view title
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
        setTitle(formatter.format(mCalendar.getTime()));
    }
}