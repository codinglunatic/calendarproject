package com.example.calendarproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {
  // variabe declaration
    Button submitbtn,mailbtn;
    TextView textbox,pval,remaninder;
    SeekBar seekval;
    CalendarView cal;
    String fulldate;
    ScrollView s;
    LinearLayout l;
    int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitbtn=findViewById(R.id.addbtn);
        textbox = findViewById(R.id.textView);
        seekval= findViewById(R.id.seekBar);
        cal = findViewById(R.id.calendarView);
        pval = findViewById(R.id.textView2);
        l=findViewById(R.id.linearview);
        s =findViewById(R.id.scrollView2);
        remaninder= findViewById(R.id.remainders);

        mailbtn=findViewById(R.id.mailbtn);


        seekval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                pval.setText(String.valueOf(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });// seekval ending

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                fulldate=String.valueOf(i2)+"/"+String.valueOf(i1)+"/"+String.valueOf(i);

                textbox.setText(fulldate);
            }
        });


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView t = new TextView(getApplicationContext());
                i++;
              //  t.setText(String.valueOf(i));
                t.setId(i);
                t.setLayoutParams(new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.FILL_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT));
                String text;
                text= fulldate+"  --- "+ pval.getText() +"   :"+remaninder.getText();
                t.setText(text);
                t.setTextSize(20);
                l.addView(t);
                s.removeAllViews();
                s.addView(l);

                pval.setText("0");
                remaninder.setText("");
                textbox.setText("");
                seekval.setProgress(0);

            }




        });

        mailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // String recipientList = email.getText().toString();
                String txt = remaninder.getText().toString();
                // String[] recipients = recipientList.split(",");
                String subject = "Reminder";
                String msg = "you have a remainder on "+ fulldate + " for "+txt;
                Intent intent1 = new Intent(Intent.ACTION_SENDTO);
                intent1.setData(Uri.parse("mailto:"));
                //intent1.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent1.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent1.putExtra(Intent.EXTRA_TEXT, msg);
//        intent1.setType("message/rfc822");
                startActivity(Intent.createChooser(intent1, "Choose an email client"));

            }
        });








    }
}