package com.example.ex36_rawfiles;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private TextView tV;
    private EditText edText;
    private final String FILENAME = "rawtest.txt";
    private String fileName;
    private int resourceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edText = (EditText) findViewById(R.id.etTextInput);
        tV = (TextView) findViewById(R.id.tvOutput);
        fileName = FILENAME.substring(0, FILENAME.length() - 4);
        resourceId = getResources().getIdentifier(fileName, "raw", getPackageName());
    }

    public void onRawClick(View view) {
        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tV.setText(sB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTextClick(View view){
        tV.setText(edText.getText().toString());
    }

    /**
     * Creates the options menu for this activity.
     * Inflates the menu resource to display the menu items.
     *
     * @param menu the menu in which items should be added
     * @return true if the menu was successfully created, false otherwise
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creditsmenu, menu);
        return true;
    }
    /**
     * Handles item selection from the options menu.
     * If the "menuMain" item is selected, the activity finishes and returns to the main activity.
     *
     * @param item the menu item that was selected
     * @return true if the item selection was handled, false otherwise
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuMain) {
            finish();
        }
        return true;
    }
}