package com.edu.tce.tecuthsav17.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.edu.tce.tecuthsav17.R;
import com.edu.tce.tecuthsav17.adapter.Grid_adapter;
import com.edu.tce.tecuthsav17.model.Grid;

import java.util.ArrayList;

import static com.edu.tce.tecuthsav17.constants.constant.ARCH_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.CIVIL_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.CSE_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.ECE_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.EEE_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.IT_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.MECHATRONICS_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.MECH_REGISTRATION_URL;
import static com.edu.tce.tecuthsav17.constants.constant.eDEPARTMENT;
import static com.edu.tce.tecuthsav17.constants.constant.passURL;

public class Main_Screen extends AppCompatActivity {
    private String URL;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__screen);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GridView gridView = (GridView) findViewById(R.id.grid_id);
        ArrayList<Grid> mDepartment = new ArrayList<>();
        mDepartment.add(new Grid("ARCH"));
        mDepartment.add(new Grid("CIVIL"));
        mDepartment.add(new Grid("CSE"));
        mDepartment.add(new Grid("ECE"));
        mDepartment.add(new Grid("EEE"));
        mDepartment.add(new Grid("IT"));
        mDepartment.add(new Grid("MECH"));
        mDepartment.add(new Grid("MECHATRONICS"));
        mDepartment.add(new Grid("WORKSHOP"));
        Grid_adapter adapter = new Grid_adapter(this,R.layout.grid_item,mDepartment);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grid grid = (Grid)parent.getItemAtPosition(position);
                switch (grid.getmDepartment()) {
                    case "ARCH":
                        URL = ARCH_REGISTRATION_URL;
                        break;
                    case "CIVIL":
                        URL = CIVIL_REGISTRATION_URL;
                        break;
                    case "CSE":
                        URL = CSE_REGISTRATION_URL;
                        break;
                    case "ECE":
                        URL = ECE_REGISTRATION_URL;
                        break;
                    case "EEE":
                        URL = EEE_REGISTRATION_URL;
                        break;
                    case "IT":
                        URL = IT_REGISTRATION_URL;
                        break;
                    case "MECH":
                        URL = MECH_REGISTRATION_URL;
                        break;
                    case "MECHATRONICS":
                        URL = MECHATRONICS_REGISTRATION_URL;
                        break;
                }
                if(grid.getmDepartment().equals("WORKSHOP"))
                {
                    intent = new Intent(Main_Screen.this,Workshop_Screen.class);
                }else
                {
                    intent = new Intent(Main_Screen.this, Event_Screen.class);
                }
                intent.putExtra(eDEPARTMENT,grid.getmDepartment());
                intent.putExtra(passURL,URL);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.option1:
                Intent intent = new Intent(Main_Screen.this,Contact_Screen.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
