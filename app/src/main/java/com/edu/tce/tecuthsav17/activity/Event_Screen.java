package com.edu.tce.tecuthsav17.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.tce.tecuthsav17.R;
import com.edu.tce.tecuthsav17.model.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static com.edu.tce.tecuthsav17.constants.constant.LOADING;
import static com.edu.tce.tecuthsav17.constants.constant.eCoordinator;
import static com.edu.tce.tecuthsav17.constants.constant.eDEPARTMENT;
import static com.edu.tce.tecuthsav17.constants.constant.eDesc;
import static com.edu.tce.tecuthsav17.constants.constant.eName;
import static com.edu.tce.tecuthsav17.constants.constant.ePOST;
import static com.edu.tce.tecuthsav17.constants.constant.ePhn_no;
import static com.edu.tce.tecuthsav17.constants.constant.eRules;
import static com.edu.tce.tecuthsav17.constants.constant.eTeam;
import static com.edu.tce.tecuthsav17.constants.constant.eTime;
import static com.edu.tce.tecuthsav17.constants.constant.eVenue;
import static com.edu.tce.tecuthsav17.constants.constant.firebaseEventRoot;
import static com.edu.tce.tecuthsav17.constants.constant.firebaseRoot;
import static com.edu.tce.tecuthsav17.constants.constant.passURL;

public class Event_Screen extends AppCompatActivity
{
    public DatabaseReference databaseReference;
    private RecyclerView eventList;
    private FloatingActionButton eventFab;
    private String REGISTRATION_URL;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__screen);
        String DEPT_NAME=getIntent().getStringExtra(eDEPARTMENT);
        REGISTRATION_URL = getIntent().getStringExtra(passURL);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(DEPT_NAME);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        databaseReference= FirebaseDatabase.getInstance().getReference().child(firebaseRoot).child(firebaseEventRoot).child(DEPT_NAME);
        eventList = (RecyclerView)findViewById(R.id.recycler_id);
        eventList.setHasFixedSize(true);
        eventList.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(Event_Screen.this);
        progressDialog.setMessage(LOADING);
        progressDialog.show();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseRecyclerAdapter<Event, EventViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>(
                        Event.class, R.layout.event_item, EventViewHolder.class, databaseReference ) {
                    @Override
                    protected void populateViewHolder(EventViewHolder viewHolder, final Event model, int position) {
                        viewHolder.setEventName(model.getEvent_name());
                        viewHolder.setEventPost(getApplicationContext(),model.getEvent_post());
                        viewHolder.setEventTeamSize(model.getEvent_teamsize());
                        viewHolder.view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Event_Screen.this,Event_Detail_Screen.class);
                                intent.putExtra(ePOST,model.getEvent_post());
                                intent.putExtra(eName,model.getEvent_name());
                                intent.putExtra(eTeam,model.getEvent_teamsize());
                                intent.putExtra(eDesc,model.getEvent_desc());
                                intent.putExtra(eRules,model.getEvent_rules());
                                intent.putExtra(eCoordinator,model.getEvent_coord());
                                intent.putExtra(ePhn_no,model.getEvent_phno());
                                intent.putExtra(eVenue,model.getEvent_venue());
                                intent.putExtra(eTime,model.getEvent_time());
                                startActivity(intent);
                            }
                        });
                    }
                };
                eventList.setAdapter(firebaseRecyclerAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        eventFab = (FloatingActionButton)findViewById(R.id.event_fab);
        eventFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Event_Screen.this,Register_Screen.class);
                intent.putExtra(passURL,REGISTRATION_URL);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                Intent intent = new Intent(Event_Screen.this,Contact_Screen.class);
                startActivity(intent);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public EventViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }
        void setEventName(String name)
        {
            TextView eNamw=(TextView)view.findViewById(R.id.event_name_id);
            eNamw.setText(name);
        }
        void setEventPost(Context context, String imageurl)
        {
            ImageView ePost=(ImageView)view.findViewById(R.id.event_img_id);
            Picasso.with(context).load(imageurl).into(ePost);
        }
        void setEventTeamSize(String teamSize)
        {
            TextView eTeamSize=(TextView)view.findViewById(R.id.setteam_size);
            eTeamSize.setText(teamSize);
        }
    }

}
