package de.higherorlower.bektroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
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

import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import android.view.View;
import android.graphics.Typeface;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {

    private Timer _timer = new Timer();

    private Toolbar _toolbar;
    private DrawerLayout _drawer;
    private double IntRandom = 0;
    private String StringSetOption = "";
    private double score = 0;
    private double tries = 0;
    private double scienceCount = 0;
    private double optionGenerator = 0;

    private LinearLayout lin_score;
    private LinearLayout lin_fullscreen;
    private LinearLayout lin_ad;
    private TextView tv_score;
    private TextView tv_tries;
    private LinearLayout lin_row0;
    private LinearLayout lin_row1;
    private WebView webview1;
    private TextView tv_result;
    private LinearLayout lin_lower;
    private Button btn_evaluate;
    private LinearLayout lin_bigger;
    private Button btn_lower;
    private Button btn_bigger;
    private AdView lin_ad_bottom;
    private LinearLayout _drawer_linear2;
    private LinearLayout _drawer_linear10;
    private LinearLayout _drawer_linear3;
    private LinearLayout _drawer_linear5;
    private LinearLayout _drawer_linear8;
    private LinearLayout _drawer_linear6;
    private LinearLayout _drawer_linear7;
    private LinearLayout _drawer_linear9;
    private LinearLayout _drawer_linear4;
    private TextView _drawer_infotext_01;
    private ImageView _drawer_imageview1;
    private TextView _drawer_infotext_03;
    private ImageView _drawer_imageview2;
    private TextView _drawer_infotext_04;
    private ImageView _drawer_imageview3;
    private TextView _drawer_infotext_05;
    private ImageView _drawer_imageview4;
    private TextView _drawer_infotext_02;
    private TextView _drawer_textview1;
    private ImageView _drawer_imageview5;

    private AlertDialog.Builder Dialog;
    private SharedPreferences savedScore;
    private Intent restartActivity = new Intent();
    private InterstitialAd homebanner;
    private AdListener _homebanner_ad_listener;
    private Calendar date = Calendar.getInstance();
    private AlertDialog.Builder reset_dialog;
    private AlertDialog.Builder highscore_dialog;
    private AlertDialog.Builder introduction;
    private AlertDialog.Builder info;
    private TimerTask animation;
    private TimerTask animation2;
    private Intent goWeb = new Intent();
    private TimerTask animation3;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
        initialize(_savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {

        _toolbar = (Toolbar) findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                onBackPressed();
            }
        });
        _drawer = (DrawerLayout) findViewById(R.id._drawer);
        ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
        _drawer.addDrawerListener(_toggle);
        _toggle.syncState();

        LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);

        lin_score = (LinearLayout) findViewById(R.id.lin_score);
        lin_fullscreen = (LinearLayout) findViewById(R.id.lin_fullscreen);
        lin_ad = (LinearLayout) findViewById(R.id.lin_ad);
        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_tries = (TextView) findViewById(R.id.tv_tries);
        lin_row0 = (LinearLayout) findViewById(R.id.lin_row0);
        lin_row1 = (LinearLayout) findViewById(R.id.lin_row1);
        webview1 = (WebView) findViewById(R.id.webview1);
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.getSettings().setSupportZoom(true);
        tv_result = (TextView) findViewById(R.id.tv_result);
        lin_lower = (LinearLayout) findViewById(R.id.lin_lower);
        btn_evaluate = (Button) findViewById(R.id.btn_evaluate);
        lin_bigger = (LinearLayout) findViewById(R.id.lin_bigger);
        btn_lower = (Button) findViewById(R.id.btn_lower);
        btn_bigger = (Button) findViewById(R.id.btn_bigger);
        lin_ad_bottom = (AdView) findViewById(R.id.lin_ad_bottom);
        _drawer_linear2 = (LinearLayout) _nav_view.findViewById(R.id.linear2);
        _drawer_linear10 = (LinearLayout) _nav_view.findViewById(R.id.linear10);
        _drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
        _drawer_linear5 = (LinearLayout) _nav_view.findViewById(R.id.linear5);
        _drawer_linear8 = (LinearLayout) _nav_view.findViewById(R.id.linear8);
        _drawer_linear6 = (LinearLayout) _nav_view.findViewById(R.id.linear6);
        _drawer_linear7 = (LinearLayout) _nav_view.findViewById(R.id.linear7);
        _drawer_linear9 = (LinearLayout) _nav_view.findViewById(R.id.linear9);
        _drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
        _drawer_infotext_01 = (TextView) _nav_view.findViewById(R.id.infotext_01);
        _drawer_imageview1 = (ImageView) _nav_view.findViewById(R.id.imageview1);
        _drawer_infotext_03 = (TextView) _nav_view.findViewById(R.id.infotext_03);
        _drawer_imageview2 = (ImageView) _nav_view.findViewById(R.id.imageview2);
        _drawer_infotext_04 = (TextView) _nav_view.findViewById(R.id.infotext_04);
        _drawer_imageview3 = (ImageView) _nav_view.findViewById(R.id.imageview3);
        _drawer_infotext_05 = (TextView) _nav_view.findViewById(R.id.infotext_05);
        _drawer_imageview4 = (ImageView) _nav_view.findViewById(R.id.imageview4);
        _drawer_infotext_02 = (TextView) _nav_view.findViewById(R.id.infotext_02);
        _drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
        _drawer_imageview5 = (ImageView) _nav_view.findViewById(R.id.imageview5);
        Dialog = new AlertDialog.Builder(this);
        savedScore = getSharedPreferences("savedScore", Activity.MODE_PRIVATE);
        reset_dialog = new AlertDialog.Builder(this);
        highscore_dialog = new AlertDialog.Builder(this);
        introduction = new AlertDialog.Builder(this);
        info = new AlertDialog.Builder(this);

        tv_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                reset_dialog.setTitle("Score zurücksetzen?");
                reset_dialog.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        savedScore.edit().putString("backup_score", String.valueOf((long) (score))).commit();
                        savedScore.edit().putString("backup_tries", String.valueOf((long) (tries))).commit();
                        _backup();
                        score = 0;
                        tries = 0;
                        tv_score.setText("Score: ".concat(String.valueOf((long) (score))));
                        tv_tries.setText("Score: ".concat(String.valueOf((long) (tries))));
                        try {


                            animation.cancel();
                            animation2.cancel();
                            tv_result.setTextColor(0xFF000000);
                        } catch (Exception _e) {


                            Log.e("try/catch error", _e.toString());
                        }
                        homebanner = new InterstitialAd(getApplicationContext());
                        homebanner.setAdListener(_homebanner_ad_listener);
                        homebanner.setAdUnitId("ca-app-pub-XXXXXXXXXXXXXXXXXXXXXXX");
                        homebanner.loadAd(new AdRequest.Builder().addTestDevice("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                                .build());
                    }
                });
                reset_dialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                reset_dialog.create().show();
            }
        });

        webview1.setWebViewClient(new WebViewClient() {
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

        btn_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (!StringSetOption.equals("")) {
                    IntRandom = Util.getRandom((int) (0), (int) (100));
                    tv_result.setText(String.valueOf((long) (IntRandom)));
                    if (IntRandom > 50) {
                        if (StringSetOption.equals("bigger")) {
                            score = score + 10;
                            _animate("win");
                        } else {
                            score = score - 10;
                            _animate("loss");
                        }
                    } else {
                        if (StringSetOption.equals("lower")) {
                            score = score + 10;
                            _animate("win");
                        } else {
                            score = score - 10;
                            _animate("loss");
                        }
                    }
                    StringSetOption = "";
                    IntRandom = 0;
                    tries++;
                    tv_score.setText("Score: ".concat(String.valueOf((long) (score))));
                    tv_tries.setText("Versuche: ".concat(String.valueOf((long) (tries))));
                    _reset();
                } else {
                    Util.showMessage(getApplicationContext(), "Option wählen!");
                }
            }
        });

        btn_lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                StringSetOption = "lower";
                lin_lower.setBackgroundColor(0xFFE8F5E9);
                lin_bigger.setBackgroundColor(Color.TRANSPARENT);
                try {


                    animation.cancel();
                    animation2.cancel();
                    tv_result.setTextColor(0xFF000000);
                } catch (Exception _e) {


                    Log.e("try/catch error", _e.toString());
                }
            }
        });

        btn_bigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                StringSetOption = "bigger";
                lin_bigger.setBackgroundColor(0xFFE8F5E9);
                lin_lower.setBackgroundColor(Color.TRANSPARENT);
                try {


                    animation.cancel();
                    animation2.cancel();
                    tv_result.setTextColor(0xFF000000);
                } catch (Exception _e) {


                    Log.e("try/catch error", _e.toString());
                }
            }
        });

        _drawer_infotext_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        _drawer_infotext_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                goWeb.setAction(Intent.ACTION_VIEW);
                goWeb.setData(Uri.parse("https://play.google.com/store/apps/dev?id=6792952036752848278"));
                startActivity(goWeb);
            }
        });

        _drawer_infotext_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                highscore_dialog.setTitle("Dein Highscore-Verlauf");
                highscore_dialog.setMessage(FileUtil.readFile("/storage/emulated/0/HigherOrLower/Backups/Backup.bektr"));
                highscore_dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                highscore_dialog.setNegativeButton("Verlauf löschen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        FileUtil.deleteFile("/storage/emulated/0/HigherOrLower/Backups/Backup.bektr");
                        Util.showMessage(getApplicationContext(), "Verlauf gelöscht!");
                    }
                });
                highscore_dialog.create().show();
            }
        });

        _drawer_infotext_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _restart();
                try {


                    score = Double.parseDouble(savedScore.getString("backup_score", ""));
                    tv_score.setText("Score: ".concat(String.valueOf((long) (score))));
                    tries = Double.parseDouble(savedScore.getString("backup_tries", ""));
                    tv_tries.setText("Versuche: ".concat(String.valueOf((long) (tries))));
                } catch (Exception _e) {


                    Log.e("try/catch error", _e.toString());
                }
                Util.showMessage(getApplicationContext(), "Score wiederhergestellt!");
            }
        });

        _drawer_infotext_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                restartActivity.setClass(getApplicationContext(), SubActivity.class);
                restartActivity.setAction(Intent.ACTION_VIEW);
                restartActivity.putExtra("startup", "dse");
                startActivity(restartActivity);
            }
        });

        _homebanner_ad_listener = new AdListener() {
            @Override
            public void onAdLoaded() {
                homebanner.show();
            }

            @Override
            public void onAdFailedToLoad(int _param1) {
                final int _errorCode = _param1;

            }

            @Override
            public void onAdOpened() {

            }

            @Override
            public void onAdClosed() {

            }
        };
    }

    private void initializeLogic() {
        try {


            if (getIntent().getStringExtra("conditions").equals("dec")) {
                savedScore.edit().putString("start", "").commit();
                finish();
            }
        } catch (Exception _e) {


            Log.e("try/catch error", _e.toString());
        }
        lin_ad_bottom.loadAd(new AdRequest.Builder().addTestDevice("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                .build());
        try {


            if (savedScore.getString("start", "").equals("")) {
                restartActivity.setClass(getApplicationContext(), SubActivity.class);
                restartActivity.setAction(Intent.ACTION_VIEW);
                restartActivity.putExtra("startup", "dse");
                startActivity(restartActivity);
            }
        } catch (Exception _e) {


            Log.e("try/catch error", _e.toString());
        }
        try {


            if (getIntent().getStringExtra("conditions").equals("acc")) {
                savedScore.edit().putString("start", "true").commit();
                _restart();
            }
        } catch (Exception _e) {


            Log.e("try/catch error", _e.toString());
        }
        _corners();
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
    public void onStop() {
        super.onStop();
        savedScore.edit().putString("score", String.valueOf((long) (score))).commit();
        savedScore.edit().putString("tries", String.valueOf((long) (tries))).commit();
        try {


            animation.cancel();
            animation2.cancel();
            animation3.cancel();
            tv_result.setTextColor(0xFF000000);
        } catch (Exception _e) {


            Log.e("try/catch error", _e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        savedScore.edit().putString("tries", String.valueOf((long) (tries))).commit();
        savedScore.edit().putString("score", String.valueOf((long) (score))).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        lin_ad_bottom.loadAd(new AdRequest.Builder().addTestDevice("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                .build());
    }

    @Override
    public void onBackPressed() {
        if (_drawer.isDrawerOpen(GravityCompat.START)) {
            _drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void _reset() {
        lin_lower.setBackgroundColor(Color.TRANSPARENT);
        lin_bigger.setBackgroundColor(Color.TRANSPARENT);
    }


    private void _dialog(final double _option) {
        //Maybe will be put in use later
        if (_option == 1) {
            Dialog.setTitle("Gewonnen!");
            Dialog.setMessage("Glückwunsch...");
        }
        if (_option == 2) {
            Dialog.setTitle("Verloren!");
            Dialog.setMessage("Pech gehabt!");
        }
        if (_option == 3) {
            Dialog.setTitle("Unentschieden");
            Dialog.setMessage("Wow wie wahrscheinlich ist das!");
        }
        Dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {

            }
        });
        Dialog.create().show();
    }


    private void _corners() {
        webview1.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
				/*
request.setDescription("Downloading file...");*/
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.allowScanningByMediaScanner();
                /*request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);*/
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));

                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
				/*
showMessage("Downloading File....");*/
                //Notif if success
                BroadcastReceiver onComplete = new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {
						/*
showMessage("Download Complete!");*/
                        unregisterReceiver(this);
                    }
                };
                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }
        });


        tv_result.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ft_stilltime.ttf"), 0);
        tv_score.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ft_stilltime.ttf"), 0);
        tv_tries.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ft_stilltime.ttf"), 0);
        try {


            score = Double.parseDouble(savedScore.getString("score", ""));
            tv_score.setText("Score: ".concat(String.valueOf((long) (score))));
            tries = Double.parseDouble(savedScore.getString("tries", ""));
            tv_tries.setText("Versuche:  ".concat(String.valueOf((long) (tries))));
        } catch (Exception _e) {


            Log.e("try/catch error", _e.toString());
        }
		/*
try {
    

if (savedScore.getString("design", "").equals("dark")) {
currentDesign = "dark";
lin_fullscreen.setBackgroundColor(0xFF424242);
tv_score.setTextColor(0xFFFAFAFA);
tv_result.setTextColor(0xFFFAFAFA);
lin_ad.setBackgroundColor(0xFF424242);
lin_score.setBackgroundColor(0xFF424242);
}
else {
currentDesign = "light";
lin_fullscreen.setBackgroundColor(Color.TRANSPARENT);
tv_score.setTextColor(0xFF000000);
tv_result.setTextColor(0xFF000000);
lin_ad.setBackgroundColor(Color.TRANSPARENT);
lin_score.setBackgroundColor(Color.TRANSPARENT);
}
} catch(Exception _e){
    

    Log.e("try/catch error", _e.toString());
}
*/
    }


    private void _restart() {
        restartActivity.setClass(getApplicationContext(), MainActivity.class);
        startActivity(restartActivity);
    }


    private void _backup() {
        date = Calendar.getInstance();
        FileUtil.writeFile("/storage/emulated/0/HigherOrLower/Backups/Backup.bektr", FileUtil.readFile("/storage/emulated/0/HigherOrLower/Backups/Backup.bektr").concat("\nHighscore am ".concat(new SimpleDateFormat("dd.MM.yyyy").format(date.getTime()).concat(" um ".concat(new SimpleDateFormat("HH:mm:ss").format(date.getTime()).concat(" Uhr.".concat("\nPunkte: ".concat(String.valueOf((long) (score)).concat(" in ".concat(String.valueOf((long) (tries)).concat(" Versuchen".concat(""))))))))))));
    }


    private void _animate(final String _mode) {
        if (_mode.equals("win")) {
            animation = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_result.setTextColor(0xFF4CAF50);
                            animation2 = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_result.setTextColor(0xFF000000);
                                        }
                                    });
                                }
                            };
                            _timer.schedule(animation2, (int) (250));
                        }
                    });
                }
            };
            _timer.scheduleAtFixedRate(animation, (int) (0), (int) (500));
        }
        if (_mode.equals("loss")) {
            animation = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_result.setTextColor(0xFFF44336);
                            animation2 = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_result.setTextColor(0xFF000000);
                                        }
                                    });
                                }
                            };
                            _timer.schedule(animation2, (int) (250));
                        }
                    });
                }
            };
            _timer.scheduleAtFixedRate(animation, (int) (0), (int) (500));
        }
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
                _result.add((double) _arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }

}
