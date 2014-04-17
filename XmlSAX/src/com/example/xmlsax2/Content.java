package com.example.xmlsax2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import com.WH.WH;
import com.example.xmlsax2.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Content extends Activity {
	private TextView name, opentime, add, website, remarks, tel, toldescribe;
	private ImageButton call, gomaps, gochrome, goweather;
	private ImageView[] p = new ImageView[3];
	private TextView[] pd = new TextView[3];
	private LinearLayout ll;
	private int w, h;
	private WH wh;
	private ImageView imageview1;
	private Gallery gallery;
	private TextView textview1;
	private Drawable draw[];
	ArrayList<String> arr, arr2;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		h = dm.heightPixels;// 手機解析度 高
		w = dm.widthPixels;
		wh.getDisplayMetrics(this);
		final Bundle bundle = Content.this.getIntent().getExtras();
		name = (TextView) findViewById(R.id.name);
		opentime = (TextView) findViewById(R.id.opentime);
		add = (TextView) findViewById(R.id.add);
		website = (TextView) findViewById(R.id.website);
		remarks = (TextView) findViewById(R.id.remarks);
		tel = (TextView) findViewById(R.id.tel);
		toldescribe = (TextView) findViewById(R.id.toldescribe);
		ll = (LinearLayout) findViewById(R.id.ll);
		gallery = (Gallery) findViewById(R.id.gallery);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		arr = new ArrayList<String>();
		arr2 = new ArrayList<String>();
		for (int j = 0; j < 6; j++) {
			if (!bundle.getString("Picture" + (j + 1)).equals("")) {
				arr.add(bundle.getString("Picture" + (j + 1)));
				arr2.add(bundle.getString("Picdescribe" + (j + 1)));
			}
		}
		if (arr.size() > 0)
			gallery.setAdapter(new GalleryAdapter(this, arr));

		gomaps = (ImageButton) findViewById(R.id.gomaps);
		gomaps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!bundle.getString("Px").equals("")) {
					Bundle bundle1 = new Bundle();
					bundle1.putString("Px", bundle.getString("Px"));
					bundle1.putString("Py", bundle.getString("Py"));
					bundle1.putString("Name", bundle.getString("Name"));
					bundle1.putString("Add", bundle.getString("Add"));
					Intent intent = new Intent();
					intent.setClass(Content.this, Maps.class);
					intent.putExtras(bundle1);
					startActivity(intent);
				} else
					Toast.makeText(Content.this, "沒有地圖導覽!", Toast.LENGTH_SHORT)
							.show();
			}
		});

		/*
		 * wv=(WebView)findViewById(R.id.wv);
		 * wv.getSettings().setJavaScriptEnabled(true);
		 * wv.getSettings().setSupportZoom(true);
		 * wv.getSettings().setBuiltInZoomControls(true);
		 * wv.setInitialScale(getScale());
		 */

		/*
		 * for (int i = 0; i < 3; i++) { if (!bundle.getString("Picture" + (i +
		 * 1)).equals("")) { p[i] = new ImageButton(this); LayoutParams lp = new
		 * LayoutParams(wh.getWeightPercent(40), wh.getWeightPercent(30));
		 * p[i].setLayoutParams(lp); new getImage().execute(new String[] {
		 * bundle.getString("Picture" + (i + 1)), i + "" }); pd[i] = new
		 * TextView(this); lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT);
		 * lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		 * pd[i].setLayoutParams(lp);
		 * pd[i].setText(bundle.getString("Picdescribe" + (i + 1)));
		 * ll.addView(pd[i]); ll.addView(p[i]); /*p[i].setOnClickListener(new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View view) {
		 * zoomImageFromThumb(thumb1View, R.drawable.image1); } }); // Retrieve
		 * and cache the system's default "short" animation time.
		 * mShortAnimationDuration = getResources().getInteger(
		 * android.R.integer.config_shortAnimTime);
		 */

		// } else
		// break;

		// }
		name.setText(ToDBC(bundle.getString("Name")));
		opentime.setText(ToDBC("營業時間：" + bundle.getString("Opentime")));
		add.setText(ToDBC("地址：" + bundle.getString("Add")));
		website.setText(ToDBC("網站首頁：" + bundle.getString("Website")));
		remarks.setText(ToDBC("備註：" + bundle.getString("Remarks")));
		tel.setText(ToDBC("電話：" + bundle.getString("Tel")));
		toldescribe.setText(ToDBC(bundle.getString("Toldescribe")));

		call = (ImageButton) findViewById(R.id.call);
		call.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("tel:" + bundle.getString("Tel"));
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);

			}
		});

		gochrome = (ImageButton) findViewById(R.id.gochrome);
		gochrome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle1= new Bundle();
	            bundle1.putString("Website",bundle.getString("Website"));
	            Log.e("xxx",bundle.getString("Website"));
	            //Uri uri = Uri.parse(bundle.getString("Website"));
				//Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				
	            intent.setClass(Content.this, MyWebView.class);
	            intent.putExtras(bundle1);
	            startActivity(intent);
			}
		});

		goweather = (ImageButton) findViewById(R.id.goweather);
		goweather.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
	            intent.setClass(Content.this, MyWebView.class);
				Bundle bundle = new Bundle();
	            bundle.putString("Website","http://www.cwb.gov.tw/V7/forecast/taiwan/Taichung_City.htm");
	            intent.putExtras(bundle);
	            //Uri uri = Uri.parse(bundle.getString("Website"));
				//Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				/*Intent ie = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://www.cwb.gov.tw/V7/forecast/taiwan/Taichung_City.htm"));
				startActivity(ie);*/
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				textview1.setText(arr2.get(arg2));
				imageview1.setImageDrawable(draw[arg2]);
				imageview1.setLayoutParams(new LinearLayout.LayoutParams(wh
						.getWeightPercent(100), wh.getWeightPercent(80)));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private class getImage extends AsyncTask<String, Integer, Bitmap> {
		int index;

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			index = Integer.parseInt(params[1]);
			return loadBitmap(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			p[index].setImageBitmap(result);
			super.onPostExecute(result);
		}
	}

	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public Bitmap loadBitmap(String url) {
		URL newurl;
		Bitmap bitmap = null;
		try {
			newurl = new URL(url);
			URLConnection conn = newurl.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
			// image.setImageBitmap(bitmap);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("e", e.toString());
			Log.e("url", url);
			e.printStackTrace();
		}
		return bitmap;
	}

	private int getScale() {
		int width = w;
		Double val = new Double(width) / new Double(320);
		val = val * 100d;
		return val.intValue();
	}

	public class GalleryAdapter extends BaseAdapter {

		int galleryItem, count;
		holder h;
		private Context context;
		private GalleryConfig galleryConfig;

		public GalleryAdapter(Context context, ArrayList<String> url) {
			super();
			this.context = context;
			galleryConfig = new GalleryConfig(url);
			draw = new Drawable[galleryConfig.getGallery_list().size()];
			count = 0;
			new getImage().execute();

		}

		@Override
		public int getCount() {
			return galleryConfig.getGallery_list().size();
		}

		@Override
		public Object getItem(int position) {
			return galleryConfig.getGallery_list().get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		int i;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				h = new holder();
				h.holder_im = new ImageView(context);
			} else {
				h = (holder) convertView.getTag();
			}
			i = position;
			h.holder_im.setImageDrawable(draw[position]);
			h.holder_im.setLayoutParams(new Gallery.LayoutParams(200, 120));
			h.holder_im.setScaleType(ImageView.ScaleType.FIT_XY);
			// Log.e("get", "get");
			return h.holder_im;
		}

		private class getImage extends AsyncTask<Integer, Integer, Bitmap> {
			int index;
			Drawable d;

			@Override
			protected Bitmap doInBackground(Integer... params) {
				// TODO Auto-generated method stub
				draw[count] = loadImageFromURL(galleryConfig.getGallery_list()
						.get(count));
				Log.e("get", galleryConfig.getGallery_list().get(count));
				return null;
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				// TODO Auto-generated method stub
				count++;
				if (count < galleryConfig.getGallery_list().size())
					new getImage().execute();
				GalleryAdapter.this.notifyDataSetChanged();
				super.onPostExecute(result);
			}

		}

		private Drawable loadImageFromURL(String url) {
			try {
				InputStream is = (InputStream) new URL(url).getContent();
				Drawable d = Drawable.createFromStream(is, "src");
				return d;
			} catch (Exception e) {
				Log.e("err", e.toString());
				System.out.println(e);
				return null;
			}
		}

	}

	static class holder {
		ImageView holder_im;
	}
}
