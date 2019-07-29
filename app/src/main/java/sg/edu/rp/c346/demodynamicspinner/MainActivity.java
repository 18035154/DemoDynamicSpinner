package sg.edu.rp.c346.demodynamicspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    Spinner spn1, spn2;
    Button btnUpdate;
    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnUpdate = findViewById(R.id.buttonUpdate);

        // Initialise the ArrayList
        alNumbers = new ArrayList<>();
        //alNumbers.add("2");
        //alNumbers.add("4");
        //alNumbers.add("6");

        // Get the string-array and store as an Array
        String[] strNumbers = getResources().getStringArray(R.array.even_numbers);

        // Convert Array to List and add to ArrayList
        alNumbers.addAll(Arrays.asList(strNumbers));

        // Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaNumbers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alNumbers);

        // Bind the ArrayAdapter to the Spinner
        spn2.setAdapter(aaNumbers);


        // Implement Update Button onClick() to load correct number list when clicked
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int pos = spn1.getSelectedItemPosition();
                alNumbers.clear();
                String[] strNumbers = null;

                if(pos == 0)
                    strNumbers = getResources().getStringArray(R.array.even_numbers);
                else if(pos == 1)
                    strNumbers = getResources().getStringArray(R.array.odd_numbers);

                alNumbers.addAll(Arrays.asList(strNumbers));
                aaNumbers.notifyDataSetChanged();

            }
        });


        // Automatic Update of Spinner

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                alNumbers.clear();
                String[] strNumbers = null;
                switch(position)
                {
                    case 0:
                        // item 1 selected
                        strNumbers = getResources().getStringArray(R.array.even_numbers);
                        break;
                    case 1:
                        // item 2 selected
                        strNumbers = getResources().getStringArray(R.array.odd_numbers);
                        spn2.setSelection(1);
                        break;


                }
                alNumbers.addAll(Arrays.asList(strNumbers));
                aaNumbers.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set default spinner first item
        if(spn1.getSelectedItemPosition() == 0)
            spn2.setSelection(2);
        else if(spn1.getSelectedItemPosition() == 1)
            spn2.setSelection(1);

    }
}
