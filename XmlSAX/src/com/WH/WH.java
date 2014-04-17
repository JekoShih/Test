package com.WH;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

public class WH {
	public static int Width;
	public static int Height;

	// Width & Height
	public static void getDisplayMetrics(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		Width = dm.widthPixels;
		Height = dm.heightPixels;
		Log.e("CTC", Width + "" + Height);
	}

	public static int getWidth() {
		return Width;
	}
	
	public static int getHeight() {
		return Height;
	}
	
	public static int getWeightPercent(double Per) {
		return (int) ((Per > 100.0) ? Width : ((Width * Per) / 100));
	}

	public static int getHeightPercent(double Per) {
		return (int) ((Per > 100.0) ? Height : ((Height * Per) / 100));
	}
}