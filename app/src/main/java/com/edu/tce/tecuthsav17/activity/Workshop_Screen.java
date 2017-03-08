package com.edu.tce.tecuthsav17.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.edu.tce.tecuthsav17.R;
import com.edu.tce.tecuthsav17.model.Workshop;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.edu.tce.tecuthsav17.constants.constant.LOADING;
import static com.edu.tce.tecuthsav17.constants.constant.WORKSHOP_CIVIL_REGISTRATION;
import static com.edu.tce.tecuthsav17.constants.constant.WORKSHOP_CSE_IT_MCA_REGISTRATION;
import static com.edu.tce.tecuthsav17.constants.constant.WORKSHOP_ECE_REGISTRATION;
import static com.edu.tce.tecuthsav17.constants.constant.WORKSHOP_EEE_REGISTRATION;
import static com.edu.tce.tecuthsav17.constants.constant.WORKSHOP_MECH_MECHATRONICS_REGISTRATION;
import static com.edu.tce.tecuthsav17.constants.constant.WS_CIVIL;
import static com.edu.tce.tecuthsav17.constants.constant.WS_CSE_IT_MCA;
import static com.edu.tce.tecuthsav17.constants.constant.WS_ECE;
import static com.edu.tce.tecuthsav17.constants.constant.WS_EEE;
import static com.edu.tce.tecuthsav17.constants.constant.WS_MECH_MECHAT;
import static com.edu.tce.tecuthsav17.constants.constant.eDEPARTMENT;
import static com.edu.tce.tecuthsav17.constants.constant.firebaseRoot;
import static com.edu.tce.tecuthsav17.constants.constant.firebaseWorkshopRoot;
import static com.edu.tce.tecuthsav17.constants.constant.passURL;

public class Workshop_Screen extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RecyclerView workshopList;
    private FloatingActionButton eventFab;
    private String REGISTRATION_URL;
    private ProgressDialog progressDialog;
    private String WORKSSHOP_REGISTRATION_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop__screen);
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
        workshopList = (RecyclerView)findViewById(R.id.ws_recycler);
        workshopList.setHasFixedSize(true);
        workshopList.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(LOADING);
        progressDialog.setCancelable(false);
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(firebaseRoot).child(firebaseWorkshopRoot);
       databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               FirebaseRecyclerAdapter<Workshop,WorkshopViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Workshop, WorkshopViewHolder>(Workshop.class,R.layout.workshop_item,WorkshopViewHolder.class,databaseReference) {
                   @Override
                   protected void populateViewHolder(WorkshopViewHolder viewHolder, final Workshop model, int position) {
                       viewHolder.setWSTitle(model.getWs_title());
                       viewHolder.setWSPerson(model.getWs_person());
                       viewHolder.setWSDept(model.getWs_dept());
                       viewHolder.setWSFees(model.getWs_fees());
                       viewHolder.setWSDesc(model.getWs_desc());
                       viewHolder.view.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               switch (model.getWs_dept())
                               {
                                   case WS_CIVIL:
                                       WORKSSHOP_REGISTRATION_URL = WORKSHOP_CIVIL_REGISTRATION;
                                       break;
                                   case WS_EEE:
                                       WORKSSHOP_REGISTRATION_URL = WORKSHOP_EEE_REGISTRATION;
                                       break;
                                   case WS_ECE:
                                       WORKSSHOP_REGISTRATION_URL = WORKSHOP_ECE_REGISTRATION;
                                       break;
                                   case WS_MECH_MECHAT:
                                       WORKSSHOP_REGISTRATION_URL = WORKSHOP_MECH_MECHATRONICS_REGISTRATION;
                                       break;
                                   case WS_CSE_IT_MCA:
                                       WORKSSHOP_REGISTRATION_URL = WORKSHOP_CSE_IT_MCA_REGISTRATION;
                                       break;
                               }
                               Intent intent = new Intent(Workshop_Screen.this,Register_Screen.class);
                               intent.putExtra(passURL,WORKSSHOP_REGISTRATION_URL);
                               startActivity(intent);
                           }
                       });
                   }
               };
               workshopList.setAdapter(firebaseRecyclerAdapter);
               progressDialog.dismiss();
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public static class WorkshopViewHolder extends RecyclerView.ViewHolder{
        View view;
        public WorkshopViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
        void setWSTitle(String WSTitle)
        {
            TextView wsTitle = (TextView)view.findViewById(R.id.ws_title);
            wsTitle.setText(WSTitle);
        }
        void setWSPerson(String WSPerson)
        {
            TextView wsPerson = (TextView)view.findViewById(R.id.ws_person);
            wsPerson.setText(WSPerson);
        }
        void setWSDept(String WSDept)
        {
            TextView wsDept = (TextView)view.findViewById(R.id.ws_dept);
            wsDept.setText(WSDept);
        }
        void setWSFees(String WSFees)
        {
            TextView wsFees =  (TextView)view.findViewById(R.id.ws_fees);
            wsFees.setText(WSFees);
        }
        void setWSDesc(String WSDesc)
        {
            TextView wsDesc = (TextView)view.findViewById(R.id.ws_desc);
            wsDesc.setText(WSDesc);
        }
    }
}
