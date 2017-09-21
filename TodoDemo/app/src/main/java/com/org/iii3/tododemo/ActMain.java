package com.org.iii3.tododemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ActMain extends AppCompatActivity {
    SharedPreferences table ;
    private View.OnClickListener btnnew_cilck=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            table = getSharedPreferences("tTest1",0);
            int count = table.getInt("COUNT",0);
            count++;
            SharedPreferences.Editor row = table.edit();
            row.putInt("COUNT",count).commit();
            String keyT="T"+String.valueOf(count);
            String keyD="D"+String.valueOf(count);
            row.putString(keyT,text1.getText().toString()).commit();
            row.putString(keyD,text2.getText().toString()).commit();
           // list.add(table.getString("kk","NoData")+"\r\n"+table.getString("dd","NoData"));
            //user=list.toArray(new String[list.size()]);
            text1.setText("");
            text2.setText("");

            Toast.makeText(ActMain.this, "寫入資料成功", Toast.LENGTH_SHORT).show();
        }
    };
    private DialogInterface.OnClickListener onbtn_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
           table =getSharedPreferences("tTest1", 0);
            String keyT="T"+String.valueOf(i+1);
            String keyD="D"+String.valueOf(i+1);
            String n1=table.getString(keyT,"");
            text1.setText(n1);
            text2.setText(table.getString(keyD,""));


        }
    };
    private View.OnClickListener btnsee_click =new View.OnClickListener() {
        @Override

        public void onClick(View view) {
            ArrayList<String>list=new ArrayList<String>();
           table =getSharedPreferences("tTest1", 0);
            int count=table.getInt("COUNT",0);
            if(count==0) {
                Toast.makeText(ActMain.this,"沒任何待辦工作",Toast.LENGTH_SHORT).show();
                return;
            }

            for(int i=0;i<=count;i++){
                String keyT="T"+String.valueOf(i);
                String keyD="D"+String.valueOf(i);
                if(table.contains(keyT)){
                    list.add(table.getString(keyT,"")+"\r\n"+
                            table.getString(keyD,""));
                }
            }
            if(list.size()==0) {
                Toast.makeText(ActMain.this,"沒任何待辦工作",Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog.Builder message = new AlertDialog.Builder(ActMain.this);
            message.setTitle("尚有"+String.valueOf(list.size())+"件工作未完成");
            message.setItems(list.toArray(new String[list.size()]),onbtn_click);
            Dialog dialog = message.create();
            dialog.show();


        }
    };
    DatePickerDialog.OnDateSetListener onDate_click=new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            text2.setText(String.valueOf(i)+"-"+
                    String.valueOf(i1+1)+"-"+
                    String.valueOf(i2));
        }
    };


    private View.OnClickListener text2_click =new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Calendar today = Calendar.getInstance();
            DatePickerDialog message=new DatePickerDialog(
                    ActMain.this,
                    onDate_click,
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DATE)
            );
            message.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actmain);
        Innisure();
    }

    private void Innisure() {
        text1=(EditText)findViewById(R.id.text1);
        text2=(EditText)findViewById(R.id.text2);
        text2.setOnClickListener(text2_click);
        btnnew=(Button)findViewById(R.id.btnnew);
        btnnew.setOnClickListener(btnnew_cilck);
        btnsee=(Button)findViewById(R.id.btnsee);
        btnsee.setOnClickListener(btnsee_click);


        lbltet = (TextView) findViewById(R.id.lbltest);
    }
    EditText text1;
    EditText text2;
    Button btnnew;
    Button btnsee;
    String[] user ;

    TextView lbltet;

}
