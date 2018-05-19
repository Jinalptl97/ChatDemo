package com.example.krunal.chatapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import static com.example.krunal.chatapp.DBHelper.context;

public class ViewProfileActiviry extends AppCompatActivity {

    public Toolbar toolbar;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public AppBarLayout appBarLayout;
    ImageView img_profile;
    Bitmap bitmap1;
    String img;
    public static String LIVE = "http://192.168.1.42/work/fancase";
    public static String IMAGE_SHOWCASE_DOWNLOAD_URL = LIVE + "/public/media/userdata/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_activiry);

        img_profile = (ImageView) findViewById(R.id.viewProfile);

        img = getIntent().getStringExtra("ChatBean");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(context).load(IMAGE_SHOWCASE_DOWNLOAD_URL + 79 + "/" + img).asBitmap().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).into(new BitmapImageViewTarget(img_profile) {
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                bitmap1 = resource;
                System.out.println("the bitmap is" + bitmap1);
            }
        });

        initCollapseToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        // Find the menuItem to add your SubMenu
        MenuItem myMenuItem = menu.findItem(R.id.empty);
        return super.onCreateOptionsMenu(menu);

    }

    private void initCollapseToolbar() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));

                    Palette.from(bitmap1).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
                            System.out.println("new bitmap" + bitmap1);
                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                            if (vibrantSwatch != null) {
                                System.out.println("inside  vibrantSwatch");
                                collapsingToolbarLayout.setContentScrimColor(vibrantSwatch.getRgb());
                            }
                        }
                    });

                    isShow = true;
                } else {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}


