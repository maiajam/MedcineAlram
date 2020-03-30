package com.example.maiajam.medcinealram;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TimePicker;

/**
 * Created by maiAjam on 9/10/2017.
 */

public class TimeDoseDialge extends DialogFragment  {



    public interface AlarmDose_value
    {
        //if a = 1 --- am if a = 2 ---- pm
        public void AlarmSet(int hour,int min,int a,int Notime);
        public void DoseSet(int dose,int noTime);
    }

    AlarmDose_value value;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timepicker,container);

        TimePicker timePicker = (TimePicker) view.findViewById(R.id.alarmPicker);
        NumberPicker DosePicker = (NumberPicker) view.findViewById(R.id.Dose);

        String alarmNo = getArguments().getString("AlarmNo");
        int noTime = getArguments().getInt("noTime",1);
       int Hour = timePicker.getHour();
        int min = timePicker.getMinute();

        int a ;
        if(Hour > 12)
        {
            a = 2 ;
        }else
        {
            a =1 ;
        }



        DosePicker.setMinValue(1);
        DosePicker.setMaxValue(10);
        DosePicker.setValue(1);

        int dose = DosePicker.getValue();


        value.AlarmSet(Hour,min,a,noTime);
        value.DoseSet(dose,noTime);

        return view ;
    }








    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        try {

            value = (AlarmDose_value)context;

        }catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }


    }
}
