package com.refresh.pos.apppro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("Test Subtitle");
        toolbar.inflateMenu(R.layout.menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.item1)
                {
                    // do something
                    Toast toast = Toast.makeText(getApplicationContext(), "item1", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(item.getItemId()== R.id.item2)
                {
                    // do something
                    Toast toast = Toast.makeText(getApplicationContext(), "item2", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(item.getItemId()== R.id.item3)
                {
                    // do something
                    Toast toast = Toast.makeText(getApplicationContext(), "item3", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(item.getItemId()== R.id.item4)
                {
                    // do something
                    Toast toast = Toast.makeText(getApplicationContext(), "item4", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                    startActivity(intent);
                }
                else{
                    // do something
                }

                return false;
            }
        });
    }
}
