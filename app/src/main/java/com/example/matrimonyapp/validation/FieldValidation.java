package com.example.matrimonyapp.validation;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matrimonyapp.modal.MultipleHierarchyModel;

import java.util.ArrayList;

public class FieldValidation {

    static Context context;
    private static String radioButton_value;

    public FieldValidation() {
    }

    public FieldValidation(Context context) {
        this.context = context;
    }

    public static void validateRadioGroup(RadioGroup radioGroup)
    {

        for(int i=0; i<radioGroup.getChildCount(); i++)
        {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
/*
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((RadioButton)v).isChecked())
                    {
                        se
                    }
                }
            });*/
            if(rb.isChecked())
            {
                rb.setChecked(false);
            }
            else
            {
                rb.setChecked(true);
                radioButton_value = rb.getText().toString();
            }
        }
    }


    // returns value of checked button
    public static String radioGroupValidation(RadioGroup radioGroup)
    {
        //RadioButton rb1 = (RadioButton)((Activity)context).findViewById(radioGroup.getCheckedRadioButtonId());

        //radioButton_value = rb1.getText().toString();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {

                for(int i=0; i<radioGroup.getChildCount(); i++)
                {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if(rb.getId()!=id)
                    {
                        rb.setChecked(false);
                    }
                    else
                    {
                        rb.setChecked(true);
                        radioButton_value = rb.getText().toString();
                    }
                }



            }
        });
        return radioButton_value;
    }


    public static void setRadioButtonAccToValue(RadioGroup radioGroup, String value)
    {
        if(value!=null)
        {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton rb = (RadioButton) radioGroup.getChildAt(i);

                if (value.equals(rb.getText().toString())) {
                    rb.setChecked(true);
                } else {
                    rb.setChecked(false);
                }

            }
        }

    }


    public  static String onClickListenerForSDT( final String urlFor, final TextView textView_id, final Context ctx)
    {
        String id = "0";

        if (textView_id != null) {
            id = textView_id.getText().toString();

            if (id.equals("0")) {
                if (urlFor.contains("State")) {
                    Toast.makeText(ctx, "Select Country first", Toast.LENGTH_SHORT).show();
                } else if (urlFor.contains("City")) {
                    Toast.makeText(ctx, "Select State first", Toast.LENGTH_SHORT).show();
                }

            }

        }
        return id;
    }

    public static void textChangedListenerForSDT(final EditText editText_country, final EditText editText_state,
                                                 final EditText editText_city, TextView textView_countryId,
                                                 final TextView textView_stateId, final TextView textView_cityId)
    {


        editText_state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editText_state.hasFocus()) {
                    editText_state.setText("");
                    textView_stateId.setText("0");
                    editText_city.setText("");
                    textView_cityId.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText_state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editText_state.hasFocus()) {
                    editText_city.setText("");
                    textView_cityId.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    /*public static void multipleHierarchyTextOnChangeListener(final ArrayList<MultipleHierarchyModel> multipleHierarchyModelArrayList, final boolean change)
    {

        for(int i=0; i<multipleHierarchyModelArrayList.size(); i++)
        {
            multipleHierarchyModelArrayList.get(i).getView().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if(multipleHierarchyModelArrayList.get(i).getView().hasFocus())// &&
                            {
*//*
                        for (int j = i + 1; j < multipleHierarchyModelArrayList.size(); j++) {
                            multipleHierarchyModelArrayList.get(j).getView().requestFocus();
                            multipleHierarchyModelArrayList.get(j).getView().setText("");


                            multipleHierarchyModelArrayList.get(j).getArrayList().clear();
                        }*//*
                        int j=i+1;
                        if(j < multipleHierarchyModelArrayList.size())
                        {
                            multipleHierarchyModelArrayList.get(i).getView().clearFocus();
                            multipleHierarchyModelArrayList.get(j).getView().requestFocus();
                            multipleHierarchyModelArrayList.get(j).getView().setCursorVisible(true);
                            multipleHierarchyModelArrayList.get(j).getView().setText("");


                            multipleHierarchyModelArrayList.get(j).getArrayList().clear();
                        }

                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }


    }
*/

/*
    public static void multipeHierarchyOnClickListener(final Context ctx, final View parentView, final ArrayList parentList, final View childView, final String message)
    {
*/
/*
        childView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {*//*

                if(parentList.size()==0 && ((EditText)parentView).getText().toString().equals(""))
                {
                    //((EditText)childView).setText();
                    Toast.makeText(ctx, "Please select "+message+" first", Toast.LENGTH_SHORT).show();
                    childView.setClickable(true);
                }

          */
/*  }
        });
*//*




    }

*/




}
