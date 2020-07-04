package com.hoinguyen.uitcoffeeapp.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.hoinguyen.uitcoffeeapp.R;

import java.lang.reflect.Field;
import java.util.Calendar;

public class DatePickerFragmentStartDay extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int iYear    = calendar.get(Calendar.YEAR);
        int iMonth   = calendar.get(Calendar.MONTH);
        int iDay     = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, iYear, iMonth, iDay);
        Field mDatePickerField;
        try {
            mDatePickerField = dialog.getClass().getDeclaredField("mDatePicker");
            mDatePickerField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dialog.getDatePicker().setMinDate(2020);
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText edBirth = getActivity().findViewById(R.id.edStartDay);
        String sDate_birth = dayOfMonth + "/" + (month + 1) + "/" + year;
        edBirth.setText(sDate_birth);
    }
}
