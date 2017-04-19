package com.arges.sepan.argPolygonViewSample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arges.sepan.argPolygonView.ArgPolygonView;

public class MainActivity extends AppCompatActivity {
    int rotate, childHeight, childWidth;
    boolean useCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArgPolygonView polygonView = (ArgPolygonView)findViewById(R.id.myView);

        childHeight = polygonView.getChildHeight();
        childWidth = polygonView.getChildWidth();
        rotate = polygonView.getRotate();
        useCenter = polygonView.getUseCenter();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                polygonView.setChildHeight((int)(childHeight *1.2));
                polygonView.setChildWidth((int)(childWidth *1.5));
                polygonView.setRotate(rotate += 15);
                polygonView.setUseCenter(useCenter = !useCenter);
                polygonView.applyChanges();
            }
        });
    }
}
