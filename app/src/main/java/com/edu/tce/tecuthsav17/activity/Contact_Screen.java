package com.edu.tce.tecuthsav17.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.edu.tce.tecuthsav17.R;
import com.edu.tce.tecuthsav17.constants.constant;
import com.edu.tce.tecuthsav17.model.Contact;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contact_Screen extends AppCompatActivity implements constant {

    private DatabaseReference databaseReference;
    private RecyclerView contacttList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__screen);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(CONTACT_US);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        contacttList = (RecyclerView)findViewById(R.id.contact_recycler_id);
        contacttList.setHasFixedSize(true);
        contacttList.setLayoutManager(new LinearLayoutManager(this));
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(LOADING);
        progressDialog.setCancelable(false);
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(firebaseRoot).child(firebaseContactRoot);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseRecyclerAdapter<Contact,ContactViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Contact,ContactViewHolder>(Contact.class,R.layout.contact_item,ContactViewHolder.class,databaseReference)
                {
                    @Override
                    protected void populateViewHolder(ContactViewHolder viewHolder, Contact model, int position) {
                        viewHolder.setContactDEPT(model.getMember_dept());
                        viewHolder.setContactNAME(model.getMember_name());
                        viewHolder.setContactPHN(model.getMember_phn());
                        viewHolder.setContactEMAIL(model.getMember_email());
                    }
                };
                contacttList.setAdapter(firebaseRecyclerAdapter);
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

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        View view;
        public ContactViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
        void setContactDEPT(String contactDEPT)
        {
            TextView contactDept = (TextView)view.findViewById(R.id.contact_dept);
            contactDept.setText(contactDEPT);
        }
        void setContactNAME(String contactNAME)
        {
            TextView contactName = (TextView)view.findViewById(R.id.contact_name);
            contactName.setText(contactNAME);
        }
        void setContactPHN(String contactPHN)
        {
            TextView contactPhn = (TextView)view.findViewById(R.id.contact_phn);
            contactPhn.setText(contactPHN);
        }
        void setContactEMAIL(String contactEMAIL)
        {
            TextView contactEmail = (TextView)view.findViewById(R.id.contact_email);
            contactEmail.setText(contactEMAIL);
        }
    }
}
