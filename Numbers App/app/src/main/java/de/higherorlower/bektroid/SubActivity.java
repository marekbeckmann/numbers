package de.higherorlower.bektroid;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;

public class SubActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private FloatingActionButton _fab;
	private String conn = "";
	
	private LinearLayout lin_datenschutz;
	private LinearLayout lin_buttons;
	private WebView privacyview;
	private LinearLayout lin_img_01;
	private LinearLayout lin_txterror;
	private LinearLayout lin_btn;
	private ImageView img_noconn;
	private TextView tv_connerror;
	private Button btn_goweb;
	private Button btn_accept;
	private Button btn_reject;
	
	private Intent GoBack = new Intent();
	private SharedPreferences data;
	private AlertDialog.Builder exit;
	private RequestNetwork testConnection;
	private RequestNetwork.RequestListener _testConnection_request_listener;
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.sub);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		lin_datenschutz = (LinearLayout) findViewById(R.id.lin_datenschutz);
		lin_buttons = (LinearLayout) findViewById(R.id.lin_buttons);
		privacyview = (WebView) findViewById(R.id.privacyview);
		privacyview.getSettings().setJavaScriptEnabled(true);
		privacyview.getSettings().setSupportZoom(true);
		lin_img_01 = (LinearLayout) findViewById(R.id.lin_img_01);
		lin_txterror = (LinearLayout) findViewById(R.id.lin_txterror);
		lin_btn = (LinearLayout) findViewById(R.id.lin_btn);
		img_noconn = (ImageView) findViewById(R.id.img_noconn);
		tv_connerror = (TextView) findViewById(R.id.tv_connerror);
		btn_goweb = (Button) findViewById(R.id.btn_goweb);
		btn_accept = (Button) findViewById(R.id.btn_accept);
		btn_reject = (Button) findViewById(R.id.btn_reject);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		exit = new AlertDialog.Builder(this);
		testConnection = new RequestNetwork(this);
		
		privacyview.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		btn_goweb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				GoBack.setAction(Intent.ACTION_VIEW);
				GoBack.setData(Uri.parse("https://app.privacy.marekbeckmann.de"));
				startActivity(GoBack);
			}
		});
		
		btn_accept.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				data.edit().putString("conditions", "acc").commit();
				GoBack.setClass(getApplicationContext(), MainActivity.class);
				GoBack.setAction(Intent.ACTION_VIEW);
				GoBack.putExtra("conditions", "acc");
				startActivity(GoBack);
			}
		});
		
		btn_reject.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				data.edit().putString("conditions", "dec").commit();
				GoBack.setClass(getApplicationContext(), MainActivity.class);
				GoBack.setAction(Intent.ACTION_VIEW);
				GoBack.putExtra("conditions", "dec");
				startActivity(GoBack);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (data.getString("conditions", "").equals("")) {
					exit.setTitle("Zurück zur App");
					exit.setMessage("Wichtiger Hinweis: Du musst die Datenschutzerklärung akzeptieren um die App zu benutzen! \n");
					exit.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					exit.create().show();
				}
				else {
					GoBack.setClass(getApplicationContext(), MainActivity.class);
					startActivity(GoBack);
				}
			}
		});
		
		_testConnection_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				privacyview.loadUrl("https://privacy.bektroid-studios.de");
				privacyview.setVisibility(View.VISIBLE);
				lin_btn.setVisibility(View.GONE);
				lin_txterror.setVisibility(View.GONE);
				lin_img_01.setVisibility(View.GONE);
				try {
					    
					
					timer.cancel();
				} catch(Exception _e){
					    
					
					    Log.e("try/catch error", _e.toString());
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				privacyview.setVisibility(View.GONE);
				lin_btn.setVisibility(View.VISIBLE);
				lin_txterror.setVisibility(View.VISIBLE);
				lin_img_01.setVisibility(View.VISIBLE);
			}
		};
	}
	private void initializeLogic() {
		_startup();
		_checkConnection();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (privacyview.canGoBack()) {
			privacyview.goBack();
		}
		else {
			if (data.getString("conditions", "").equals("acc")) {
				GoBack.setClass(getApplicationContext(), MainActivity.class);
				startActivity(GoBack);
			}
			else {
				Util.showMessage(getApplicationContext(), "Datenschutzerklärung muss dafür akzeptiert werden!");
			}
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		try {
			    
			
			timer.cancel();
		} catch(Exception _e){
			    
			
			    Log.e("try/catch error", _e.toString());
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		android.graphics.drawable.GradientDrawable corner = new android.graphics.drawable.GradientDrawable();
		corner.setColor(Color.parseColor("#555555"));
		corner.setCornerRadius(50);
		
		btn_accept.setBackground(corner);
		btn_reject.setBackground(corner);
		btn_goweb.setBackground(corner);
	}
	private void _startup () {
		
		privacyview.getSettings().setLoadWithOverviewMode(true); privacyview.getSettings().setUseWideViewPort(true); final WebSettings webSettings = privacyview.getSettings(); final String newUserAgent; newUserAgent = "Mozilla/5.0 (Android) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"; webSettings.setUserAgentString(newUserAgent);
		try {
			    
			
			if (getIntent().getStringExtra("startup").equals("dse")) {
				lin_datenschutz.setVisibility(View.VISIBLE);
				if (data.getString("conditions", "").equals("acc")) {
					btn_accept.setVisibility(View.GONE);
				}
			}
			if (data.getString("conditions", "").equals("acc")) {
				_fab.setVisibility(View.VISIBLE);
			}
			else {
				_fab.setVisibility(View.INVISIBLE);
			}
		} catch(Exception _e){
			    
			
			    Log.e("try/catch error", _e.toString());
		}
		android.graphics.drawable.GradientDrawable corner = new android.graphics.drawable.GradientDrawable();
		corner.setColor(Color.parseColor("#555555"));
		corner.setCornerRadius(50);
		
		btn_accept.setBackground(corner);
		btn_reject.setBackground(corner);
		btn_goweb.setBackground(corner);
		tv_connerror.setText("Es konnte keine Internetverbindung hergestellt werden! Bitte besuchen Sie die folgende Website: \nhttps://app.privacy.marekbeckmann.de\nWenn Sie die Bedingungen akzeptieren, können Sie auf \"Akzeptieren\" klicken, andernfalls können Sie die App nicht verwenden.");
	}
	
	
	private void _restart () {
		GoBack.setClass(getApplicationContext(), SubActivity.class);
		startActivity(GoBack);
	}
	
	
	private void _checkConnection () {
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						testConnection.startRequestNetwork(RequestNetworkController.GET, "https://www.google.de/", "ping", _testConnection_request_listener);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(timer, (int)(0), (int)(512));
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
