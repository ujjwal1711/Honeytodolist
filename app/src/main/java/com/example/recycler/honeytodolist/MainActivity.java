package com.example.recycler.honeytodolist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
     private EditText entermessage;
    private Button savebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entermessage = (EditText) findViewById(R.id.editText);
        savebutton = (Button) findViewById(R.id.button);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(!entermessage.getText().toString().equals(""))
             {
                 writetofile(entermessage.getText().toString());
             }
             else
             {

             }
            }
        });
        try {
            if(returnfromfile()!=null)
            {
                entermessage.setText(returnfromfile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private void writetofile(String message)
    {
        try {
            OutputStreamWriter outputstream = new OutputStreamWriter(openFileOutput("todolist.txt", Context.MODE_PRIVATE));
            outputstream.write(message);
            outputstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String returnfromfile() throws IOException { String ad="";
        InputStream inputStream=openFileInput("todolist.txt");
        if(inputStream!=null)
        {
            InputStreamReader inputStreamreader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamreader);
            String tempstring ="";
            StringBuilder stringBuilder=new StringBuilder();
            while((tempstring=bufferedReader.readLine())!=null)
            {
             stringBuilder.append(tempstring);

            }
            inputStream.close();
            ad=stringBuilder.toString();
        }
        return  ad;
    }
}
