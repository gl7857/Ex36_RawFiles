package com.example.ex36_rawfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Activity for displaying credits.
 * This activity shows the credits screen and allows the user to navigate back to the main activity.
 *
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       07/03/2025
 *
 * Short description:
 *        This activity enables edge-to-edge display and shows a credits screen.
 *        It provides a simple interface with a menu that allows the user to navigate back to
 *        the main screen.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * TextView to display the content of the raw file or user input.
     */
    private TextView textView;

    /**
     * EditText field for user input.
     */
    private EditText editText;

    /**
     * The name of the raw text file to be read.
     */
    private final String FILENAME = "rawtest.txt";

    /**
     * The formatted file name without the extension.
     */
    private String formattedFileName;

    /**
     * The resource ID of the raw file.
     */
    private int rawResourceId;

    /**
     * Initializes the activity, sets up UI components, and retrieves the resource ID for the raw file.
     *
     * @param savedInstanceState The saved instance state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etTextInput);
        textView = findViewById(R.id.tvOutput);
        formattedFileName = FILENAME.substring(0, FILENAME.length() - 4);
        rawResourceId = getResources().getIdentifier(formattedFileName, "raw", getPackageName());
    }

    /**
     * Reads a text file from the raw resources folder and displays its content in the TextView.
     *
     * @param view The button view that triggers this method.
     */
    public void onRawClick(View view) {
        try {
            InputStream iS = this.getResources().openRawResource(rawResourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line).append('\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            textView.setText(sB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the text entered by the user in the EditText field on the TextView.
     *
     * @param view The button view that triggers this method.
     */
    public void onTextClick(View view) {
        textView.setText(editText.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creditsmenu, menu);
        return true;
    }

    /**
     * Handles menu item selections, navigating to the credits screen if selected.
     *
     * @param item The selected menu item.
     * @return True if the selection was handled.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuCredits) {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}