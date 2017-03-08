package com.edu.tce.tecuthsav17.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.tce.tecuthsav17.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import static com.edu.tce.tecuthsav17.constants.constant.eCoordinator;
import static com.edu.tce.tecuthsav17.constants.constant.eDesc;
import static com.edu.tce.tecuthsav17.constants.constant.eName;
import static com.edu.tce.tecuthsav17.constants.constant.ePOST;
import static com.edu.tce.tecuthsav17.constants.constant.ePhn_no;
import static com.edu.tce.tecuthsav17.constants.constant.eRules;
import static com.edu.tce.tecuthsav17.constants.constant.eTeam;
import static com.edu.tce.tecuthsav17.constants.constant.eTime;
import static com.edu.tce.tecuthsav17.constants.constant.eVenue;

public class Event_Detail_Screen extends AppCompatActivity
{
    private ImageView eimg;
    private TextView ename;
    private TextView eteam;
    private TextView edesc;
    private TextView erules;
    private TextView ecoord;
    private TextView ephn_no;
    private TextView evenue;
    private TextView etime;
    private String Name;
    private FloatingActionButton fab;
    private CollapsingToolbarLayout collapsingtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__detail__screen);
        final Toolbar toolbar=(Toolbar)findViewById(R.id.expand_toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        initCollapsingToolbar();
        final LinearLayout layout = (LinearLayout) findViewById(R.id.pallete_image);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        eimg = (ImageView)findViewById(R.id.post);
        ename = (TextView)findViewById(R.id.name);
        etime = (TextView)findViewById(R.id.ex_time);
        evenue = (TextView)findViewById(R.id.ex_venue);
        edesc= (TextView)findViewById(R.id.setDescription);
        erules=(TextView)findViewById(R.id.setRules);
        String Post=getIntent().getStringExtra(ePOST);
        Name=getIntent().getStringExtra(eName);
        String Team=getIntent().getStringExtra(eTeam);
        String Description=getIntent().getStringExtra(eDesc);
        String event_Rules=getIntent().getStringExtra(eRules);
        final String Coordinator=getIntent().getStringExtra(eCoordinator);
        final String Pho_No=getIntent().getStringExtra(ePhn_no);
        String Venue=getIntent().getStringExtra(eVenue);
        String Time=getIntent().getStringExtra(eTime);
        Picasso.with(this)
                .load(Post)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch swatch = palette.getVibrantSwatch();
                                if(swatch != null)
                                {
                                    int pal = palette.getVibrantSwatch().getRgb();
                                    layout.setBackgroundColor(swatch.getRgb());
                                    collapsingtoolbar.setBackgroundColor(palette.getMutedColor(pal));
                                  //  collapsingtoolbar.setStatusBarScrimColor(palette.getDarkMutedColor(pal));
                                    collapsingtoolbar.setContentScrimColor(palette.getMutedColor(pal));
                                    ename.setTextColor(swatch.getTitleTextColor());
                                    etime.setTextColor(swatch.getBodyTextColor());
                                    evenue.setTextColor(swatch.getBodyTextColor());
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                    {
                                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        getWindow().setStatusBarColor(palette.getLightVibrantColor(pal)); }
                                }
                            }
                        });
                        eimg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        ename.setText(Name);
        etime.setText(Time);
        evenue.setText(Venue);
        edesc.setText(Description);
        erules.setText(event_Rules);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Event_Detail_Screen.this);
                View mView = getLayoutInflater().inflate(R.layout.coord_layout,null);
                final TextView coordName_Text = (TextView)mView.findViewById(R.id.coord_name);
                final TextView coordPhn_Text = (TextView)mView.findViewById(R.id.coord_phn);
                coordName_Text.setText(Coordinator);
                coordPhn_Text.setText(Pho_No);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
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
        switch (item.getItemId()) {
            case R.id.option1:
                Intent intent = new Intent(Event_Detail_Screen.this,Contact_Screen.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initCollapsingToolbar() {
        collapsingtoolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingtoolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingtoolbar.setTitle(Name);
                    isShow = true;
                } else if (isShow) {
                    collapsingtoolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
