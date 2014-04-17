package com.example.xmlsax2;



import com.google.android.gms.internal.e;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
public class Maps extends FragmentActivity {
	GoogleMap map;
	//LatLng geopointsA=new LatLng(24.31216,(120.54984);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		Bundle bundle = Maps.this.getIntent().getExtras();
		LatLng point=new LatLng(Double.parseDouble(bundle.getString("Py")),Double.parseDouble(bundle.getString("Px")));
		
		com.google.android.gms.maps.GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 14));
        map.addMarker(new MarkerOptions().position(point).title(bundle.getString("Name")).snippet(bundle.getString("Add")));
	}
}