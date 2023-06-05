package sg.edu.rp.c346.id22022416.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

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

    Spinner spnAction;
    EditText etAddDel;
    Button btnAdd, btnDel, btnClr;
    ListView lvTask;
    ArrayList<String> alTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAction = findViewById(R.id.spinner);
        etAddDel = findViewById(R.id.editTextNewDel);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDel);
        btnClr = findViewById(R.id.buttonClr);
        lvTask = findViewById(R.id.listViewTask);

        alTasks = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(adapter);

        spnAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etAddDel.setHint("Type in a new task here");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etAddDel.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etAddDel.getText().toString();
                alTasks.add(newTask);
                adapter.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etAddDel.getText().toString();
                if(newTask.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
                else {
                    int pos = Integer.parseInt(etAddDel.getText().toString());
                    int max = alTasks.size() - 1;
                    if(pos < 0 || pos > max) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        alTasks.remove(pos);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}