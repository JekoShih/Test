package com.example.xmlsax2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes.Name;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.WH.WH;
import com.example.xmlsax2.R;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	static String list_country[] = { "台中市" };
	static String list_type[] = { "請選擇區域", "中區", "東區", "南區", "西區", "北區",
			"北屯區", "南屯區", "西屯區", "太平區", "大里區", "霧峰區", "烏日區", "豐原區", "后里區",
			"潭子區", "大雅區", "神岡區", "石岡區", "東勢區","新社區", "和平區", "大肚區", "沙鹿區", "龍井區",
			"梧棲區", "清水區", "大甲區", "外埔區", "大安區", };

	static String type_country[] = {};
	List<Data> all_data;
	ListView lv;
	AnimationSet set;
	Spinner country, type;
	EditText search;
	ImageView imageviewlogo;
	TextView textviewname, textviewadd;
	private int w, h;
	private WH wh;
	String[] presidents;
	public ProgressDialog myDialog = null;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wh.getDisplayMetrics(this);
		search = (EditText) findViewById(R.id.search);
		lv = (ListView) findViewById(R.id.lv);
		country = (Spinner) findViewById(R.id.country);
		handler=new Handler();
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.myspinner, list_country);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		country.setAdapter(adapter1);
		set = new AnimationSet(false);
		Animation animation = new AlphaAnimation(0, 1);
		animation.setDuration(150);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 1);
		lv.setLayoutAnimation(controller);
		type = (Spinner) findViewById(R.id.type);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				R.layout.myspinner, list_type);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type.setAdapter(adapter2);

		handler.postDelayed(r, 1000);
		search.setOnKeyListener(new EditText.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub

				return false;
			}
		});
		/*
		 QuickActionView qa = QuickActionView.Builder( v );	
					BaseAdapter ba=new CustomAdapter(context);
					qa.setAdapter(ba);
					// set the number of columns ( setting -1 for auto )
					qa.setNumColumns(5); 
					qa.show();	
		 */
		type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				new getData().execute();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("Town", all_data.get(arg2).Town);
				bundle.putString("Name", all_data.get(arg2).Name);
				bundle.putString("Description", all_data.get(arg2).Description);
				bundle.putString("Opentime", all_data.get(arg2).Opentime);
				bundle.putString("Toldescribe", all_data.get(arg2).Toldescribe);
				bundle.putString("Tel", all_data.get(arg2).Tel);
				bundle.putString("Add", all_data.get(arg2).Add);
				bundle.putString("Website", all_data.get(arg2).Website);
				bundle.putString("Picture1", all_data.get(arg2).Picture1);
				bundle.putString("Picture2", all_data.get(arg2).Picture2);
				bundle.putString("Picture3", all_data.get(arg2).Picture3);
				bundle.putString("Picture4", all_data.get(arg2).Picture4);
				bundle.putString("Picture5", all_data.get(arg2).Picture5);
				bundle.putString("Picture6", all_data.get(arg2).Picture6);
				bundle.putString("Picdescribe1",
						all_data.get(arg2).Picdescribe1);
				bundle.putString("Picdescribe2",
						all_data.get(arg2).Picdescribe2);
				bundle.putString("Picdescribe3",
						all_data.get(arg2).Picdescribe3);
				bundle.putString("Picdescribe4",
						all_data.get(arg2).Picdescribe4);
				bundle.putString("Picdescribe5",
						all_data.get(arg2).Picdescribe5);
				bundle.putString("Picdescribe6",
						all_data.get(arg2).Picdescribe6);
				bundle.putString("Remarks", all_data.get(arg2).Remarks);
				bundle.putString("Px", all_data.get(arg2).Px);
				bundle.putString("Py", all_data.get(arg2).Py);
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Content.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private class getData extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			if(!isSearch)
			{
				myDialog = ProgressDialog.show(MainActivity.this,
						getString(R.string.app_about),
						getString(R.string.str_loading));//讀取中的進度條
			}
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			all_data = _parseXml();
			presidents = new String[all_data.size()];
			for (int i = 0; i < presidents.length; i++) {
				presidents[i] = all_data.get(i).Name;
				Log.e("presidents", presidents[i]);
				URL url;
				try {//抓listview圖片，搜尋時先下載到sd卡在讀取
					url = new URL(all_data.get(i).Picture1);
					URLConnection connection = url.openConnection();
					connection.connect();
					// this will be useful so that you can show a typical 0-100%
					// progress bar
					int fileLength = connection.getContentLength();

					// download the file
					InputStream input = new BufferedInputStream(
							url.openStream());
					OutputStream output = new FileOutputStream("/sdcard/temp"
							+ i);

					byte data[] = new byte[1024];
					long total = 0;
					int count;
					while ((count = input.read(data)) != -1) {
						total += count;
						// publishing the progress....
						publishProgress((int) (total * 100 / fileLength));
						output.write(data, 0, count);
					}

					output.flush();
					output.close();
					input.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("e", e.toString());
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// Log.e("3",all_data.get(2).Name);
			// newFragment.setAdapter(all_data);
			isSearch=false;
			if (presidents.length == 0);
				/*Toast.makeText(MainActivity.this, "暫無相關分類資訊‧",
						Toast.LENGTH_LONG).show();*/
			else
				lv.setAdapter(new ListViewAdapter(MainActivity.this));
			if(!isSearch)
			{
				if (myDialog != null) {
					myDialog.dismiss();
					myDialog = null;
				}
			}
			handler.postDelayed(r,500);
		}

	}

	/*
	 * Fragment1 newFragment ; FragmentTransaction ft;
	 * 
	 * @SuppressLint("NewApi")
	 * 
	 * @TargetApi(Build.VERSION_CODES.HONEYCOMB) void addFragment() {
	 * 
	 * // Instantiate a new fragment. newFragment= new Fragment1();
	 * 
	 * ft = getFragmentManager().beginTransaction(); ft.add(R.id.MainActivityUI,
	 * newFragment, "Fragment1");
	 * ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); ft.commit();
	 * }
	 */

	private List<Data> _parseXml() {
		List<Data> data = null;
		String country = this.country.getSelectedItem().toString();
		String type = this.type.getSelectedItem().toString();
		// sax stuff
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			XMLReader xr = sp.getXMLReader();

			XmlHandler dataHandler = new XmlHandler(isSearch);
			// dataHandler.setCondition(country,list_type[type.getSelectedItemPosition()]);
			dataHandler.setCondition(country, type, search.getText().toString());
			xr.setContentHandler(dataHandler);
			InputStream is = getAssets().open("scenic_spot_W.xml");
			/*
			 * URL url = new URL("file:///android_asset/scenic_spot_C.xml");
			 * InputStream is=url.openStream();
			 */
			xr.parse(new InputSource(is));
			data = dataHandler.gatAllData();
			is.close();
		} catch (ParserConfigurationException pce) {
			Log.e("SAX XML", "sax parse error", pce);
		} catch (SAXException se) {
			Log.e("SAX XML", "sax error", se);
		} catch (IOException ioe) {
			Log.e("SAX XML", "sax parse io error", ioe);
		}
		return data;
	}

	public class ListViewAdapter extends BaseAdapter {
		private LayoutInflater myInflater;

		public ListViewAdapter(Context c) {
			myInflater = LayoutInflater.from(c);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return all_data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return all_data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			view = myInflater.inflate(R.layout.mylistview, null);
			imageviewlogo = (ImageView) view.findViewById(R.id.imageviewlogo);
			textviewname = (TextView) view.findViewById(R.id.textviewname);
			textviewadd = (TextView) view.findViewById(R.id.textviewadd);

			// imageviewlogo.setImageResource(all_data.get(position).Pitcher1);
			textviewname.setText(all_data.get(position).Name);
			textviewadd.setText(all_data.get(position).Add);
			imageviewlogo.setImageDrawable(Drawable
					.createFromPath("/mnt/sdcard/temp" + position));
			imageviewlogo.setLayoutParams(new LinearLayout.LayoutParams(wh
					.getWeightPercent(30), wh.getWeightPercent(30)));
			return view;
		}
	}
	
	private int temp=0;
	private boolean isSearch=false;
	Runnable r =new Runnable()
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(search.getText().toString().length()!=temp)
			{
				isSearch=true;
				temp=search.getText().toString().length();
				new getData().execute();
			}else
			{
				handler.postDelayed(r,500);
			}
		}	
	};
}
