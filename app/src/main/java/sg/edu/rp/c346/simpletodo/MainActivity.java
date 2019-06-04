package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spn;
    EditText text;
    Button add;
    Button delete;
    Button clear;
    ListView list;
    ArrayAdapter<String> alltasks;

    ArrayList<String> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.editText);
        add=findViewById(R.id.buttonAdd);
        delete=findViewById(R.id.buttonDelete);
        clear=findViewById(R.id.buttonClear);
        list=findViewById(R.id.ListView);
        spn=findViewById(R.id.spinner);


        tasks=new ArrayList<String>();

        alltasks = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tasks);

        list.setAdapter(alltasks);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        delete.setEnabled(false);
                        add.setEnabled(true);
                        break;
                    case 1:
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result= text.getText().toString();
                tasks.add(result);
                text.setText("");
                alltasks.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tasks.size()==0){
                    Toast toast= Toast.makeText(MainActivity.this,"You dont have any tasks to remove",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    int delint = Integer.parseInt(text.getText().toString());
                    tasks.remove(delint);
                    alltasks.notifyDataSetChanged();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.clear();
                alltasks.notifyDataSetChanged();

            }
        });
    }
}
