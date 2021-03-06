package com.example.matrimonyapp.customViews;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.activity.LoginActivity;
import com.example.matrimonyapp.adapter.AddPersonAdapter;
import com.example.matrimonyapp.adapter.DataFetcher;
import com.example.matrimonyapp.adapter.PopupFetcher;
import com.example.matrimonyapp.modal.AddPersonModel;
import com.example.matrimonyapp.modal.UserModel;
import com.example.matrimonyapp.sqlite.SQLiteFarmDetails;
import com.example.matrimonyapp.sqlite.SQLiteMamaDetails;
import com.example.matrimonyapp.sqlite.SQLitePropertyDetails;
import com.example.matrimonyapp.sqlite.SQLiteSiblingDetails;
import com.example.matrimonyapp.validation.FieldValidation;
import com.example.matrimonyapp.volley.CustomSharedPreference;
import com.example.matrimonyapp.volley.URLs;

import java.util.ArrayList;
import java.util.Map;

import jp.wasabeef.blurry.internal.Blur;
import jp.wasabeef.blurry.internal.BlurFactor;
import me.abhinay.input.CurrencyEditText;
import me.abhinay.input.CurrencySymbols;

public class CustomDialogAddFarm extends Dialog {



    public Context context;

    private EditText editText_crops;
    private TextView textView_title, textView_addFarm;

    private CurrencyEditText editText_farmArea;

    private ImageView imageView_back;

    private RadioGroup radioGroup_type, radioGroup_irrigationType;

    private Map<String, Integer> list;

    private SQLiteFarmDetails sqLiteFarmDetails;
    private DataFetcher dataFetcher;

    private String id, farm_details_id;

    private CustomDialogLoadingProgressBar customDialogLoadingProgressBar;


    private UserModel userModel;

    private AddPersonAdapter addPersonAdapter;
    private ArrayList<AddPersonModel> addPersonModelArrayList;
    private int position;

    public CustomDialogAddFarm(Context context, String id, String farm_details_id,  AddPersonAdapter addPersonAdapter,
                                   ArrayList<AddPersonModel> addPersonModelArrayList, int position)
    {
        super(context);
        this.context = context;
        this.id = id;
        this.farm_details_id = farm_details_id;
        this.addPersonAdapter = addPersonAdapter;
        this.addPersonModelArrayList = addPersonModelArrayList;
        this.position = position;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);




        setContentView(R.layout.custom_dialog_add_farm);

        if (!CustomSharedPreference.getInstance(context).isLoggedIn()) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }

        userModel = CustomSharedPreference.getInstance(getContext()).getUser();

        sqLiteFarmDetails = new SQLiteFarmDetails(context);


        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        imageView_back = findViewById(R.id.imageView_back);
        textView_title = findViewById(R.id.textView_title);
        editText_farmArea = findViewById(R.id.editText_farmArea);
        radioGroup_type = findViewById(R.id.radioGroup_type);
        radioGroup_irrigationType = findViewById(R.id.radioGroup_irrigationType);
        editText_crops = findViewById(R.id.editText_crops);
        textView_addFarm = findViewById(R.id.textView_addFarm);

        dataFetcher = new DataFetcher("Address",context);


        textView_title.setText("Farm Details");
        imageView_back.setVisibility(View.GONE);

        editText_farmArea.setCurrency(CurrencySymbols.NONE);
        editText_farmArea.setDecimals(false);

        if(!id.equals("0"))
        {

            Cursor cursor = sqLiteFarmDetails.getDataById(Integer.parseInt(id));

            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext())
            {

                editText_farmArea.setText(cursor.getString(cursor.getColumnIndex(SQLiteFarmDetails.AREA)));
                editText_crops.setText(cursor.getString(cursor.getColumnIndex(SQLiteFarmDetails.CROPS)));
                FieldValidation.setRadioButtonAccToValue(radioGroup_type,
                        cursor.getString(cursor.getColumnIndex(SQLiteFarmDetails.TYPE)));
                FieldValidation.setRadioButtonAccToValue(radioGroup_irrigationType,
                        cursor.getString(cursor.getColumnIndex(SQLiteFarmDetails.IRRIGATION_TYPE)));
                //Toast.makeText(context, cursor.getString(cursor.getColumnIndex(SQLiteFarmDetails.TYPE))+"---",Toast.LENGTH_SHORT).show();


            }


            cursor.close();
        }

        textView_addFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String area = editText_farmArea.getText().toString().trim();
                String crops = editText_crops.getText().toString().trim();
                String type = ((RadioButton)findViewById(radioGroup_type.getCheckedRadioButtonId())).getText().toString();
                String irrigationType = ((RadioButton)findViewById(radioGroup_irrigationType.getCheckedRadioButtonId())).getText().toString();


                if(id.equals("0")) {
                    long res = sqLiteFarmDetails.insertFarmDetails("0", area, type, crops, irrigationType);

                    if (res != -1) {
                        Toast.makeText(context, "Value added & id is " + res, Toast.LENGTH_SHORT).show();
                        addPersonModelArrayList.add(new AddPersonModel(String.valueOf(res),"0", area+" sq. ft.", crops));
                        addPersonAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Error in sqlite insertion", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    int res = sqLiteFarmDetails.updateFarmDetails(id, farm_details_id, area, type, crops, irrigationType);
                    if (res != -1) {
                        Toast.makeText(context, "Value Updated & id is " + res, Toast.LENGTH_SHORT).show();
                        addPersonModelArrayList.set(position, new AddPersonModel(String.valueOf(id), farm_details_id, area+" sq. ft.", crops));
                        addPersonAdapter.notifyDataSetChanged();


                    } else {
                        Toast.makeText(context, "Error in sqlite updation", Toast.LENGTH_SHORT).show();

                    }


                }
                dismiss();

            }
        });


        onClickListener();


    }

    private void blurBackground()
    {
/*
        //take Screenshot
        Bitmap bitmap = Screenshot.getInstance().takeScreenshotForScreen((Activity)context);

        //set blurring factor and heighth width of screenshot
        BlurFactor blurFactor = new BlurFactor();
        blurFactor.height = bitmap.getHeight();
        blurFactor.width = bitmap.getWidth();
        blurFactor.color = context.getResources().getColor(R.color.transparent_bg);

        //blurred image
        Bitmap blurBitmap = Blur.of(context, bitmap, blurFactor);
        //convert blurred image into drawable
        Drawable drawable = new BitmapDrawable(context.getResources(), blurBitmap);

        //set blurred screenshot to background
        getWindow().setBackgroundDrawable(drawable);*/

    }


    private void onClickListener()
    {




    }




    private class AsyncTaskLoad extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()

            try {





            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {

        }


        @Override
        protected void onPreExecute() {

            customDialogLoadingProgressBar = new CustomDialogLoadingProgressBar(context);
            customDialogLoadingProgressBar.show();

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }

    }


}
