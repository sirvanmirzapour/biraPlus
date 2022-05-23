package com.sirvan.ltenfinal.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sirvan.ltenfinal.App.MyAlertDialog;
import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.Model.DeviceListModel;
import com.sirvan.ltenfinal.R;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private LinearLayout llMainMenu;
    private RelativeLayout rlMainTop, rlMainBottom;
    private LinearLayout llUnActiveFunctionOneDOne, llActiveFunctionOneDOne, llUnActiveFunctionOneDTwo, llActiveFunctionOneDTwo;
    private LinearLayout llCall, llSituation, llHistory;

    private RelativeLayout rlFunctionTree;
    private LinearLayout llActiveFunctionOneDTree, llUnActiveFunctionOneDTree;

    private RelativeLayout rlDemo;

    private TextView txtFunctionOneDOne, txtFunctionOneDTwo, txtFunctionOneDTree, txtButtonOnDOne, txtButtonOffDOne, txtButtonOnDTwo, txtButtonOffDTwo, txtButtonOnDTree, txtButtonOffDTree;
    private TextView txtSituation, txtHistory, txtCall;

    private MaterialSpinner spinner;
    private static String Situation = "*10#";
    private static String ActiveFunctionOneDOne = "*11*1#";
    private static String UnActiveFunctionOneDOne = "*11*2#";
    private static String ActiveFunctionOneDTwo = "*12*1#";
    private static String UnActiveFunctionOneDTwo = "*12*2#";
    private static String ActiveFunctionOneDTree = "*23*1#";
    private static String UnActiveFunctionOneDTree = "*23*2#";

    private static String strSituation = "آیا میخواهید وضعیت دستگاه را دریافت کنید؟";
    private static String strActiveFunctionOneDOne = "آیا میخواهید دستگاه یک را روشن کنید؟";
    private static String strUnActiveFunctionOneDOne = "آیا میخواهید دستگاه یک را خاموش کنید؟";
    private static String strActiveFunctionOneDTwo = "آیا میخواهید دستگاه دو را روشن کنید؟";
    private static String strUnActiveFunctionOneDTwo = "آیا میخواهید دستگاه دو را خاموش کنید؟";
    private static String strActiveFunctionOneDTree = "آیا میخواهید دستگاه سه را روشن کنید؟";
    private static String strUnActiveFunctionOneDTree = "آیا میخواهید دستگاه سه را خاموش کنید؟";

    private static String strNumber = null;
    private static String strName = null;
    private static String strPassword = null;

    private LinearLayout llInsertNumber,llNoNumberCancel;
    private TextView txtNoNumberFound,txtSaveInGetNumber,txtNoNumberCancel;

    private  MyAlertDialog myAlertDialog;

    private LinearLayout llFunction;
    private TextView txtFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ini();
        ClickFunctionOne();
        ClickOnMenu();
        ClickFunctionMain();
        setTypeFace();
        ClickNoNumber();

         myAlertDialog = new MyAlertDialog(MainActivity.this,getApplicationContext());


        int i = getWindowSize() / 14;
        rlMainTop.getLayoutParams().height = (getWindowSize() / 2) - i;
        rlMainBottom.getLayoutParams().height = (getWindowSize() / 2) + i;

    }

    private void Ini() {
        rlMainTop = findViewById(R.id.rlMainTop);
        rlMainBottom = findViewById(R.id.rlMainBottom);

        llMainMenu = findViewById(R.id.llMainMenu);

        //Function One
        llUnActiveFunctionOneDOne = findViewById(R.id.llUnActiveFunctionOneDOne);
        llActiveFunctionOneDOne = findViewById(R.id.llActiveFunctionOneDOne);
        llUnActiveFunctionOneDTwo = findViewById(R.id.llUnActiveFunctionOneDTwo);
        llActiveFunctionOneDTwo = findViewById(R.id.llActiveFunctionOneDTwo);

        //Function Main
        llCall = findViewById(R.id.llCall);
        llSituation = findViewById(R.id.llSituation);
        llHistory = findViewById(R.id.llHistory);

        rlFunctionTree = findViewById(R.id.rlFunctionTree);
        llActiveFunctionOneDTree = findViewById(R.id.llActiveFunctionOneDTree);
        llUnActiveFunctionOneDTree = findViewById(R.id.llUnActiveFunctionOneDTree);


        //TextView

        txtFunctionOneDOne = findViewById(R.id.txtFunctionOneDOne);
        txtFunctionOneDTwo = findViewById(R.id.txtFunctionOneDTwo);
        txtFunctionOneDTree = findViewById(R.id.txtFunctionOneDTree);
        txtButtonOnDOne = findViewById(R.id.txtButtonOnDOne);
        txtButtonOffDOne = findViewById(R.id.txtButtonOffDOne);
        txtButtonOnDTwo = findViewById(R.id.txtButtonOnDTwo);
        txtButtonOffDTwo = findViewById(R.id.txtButtonOffDTwo);
        txtButtonOnDTree = findViewById(R.id.txtButtonOnDTree);
        txtButtonOffDTree = findViewById(R.id.txtButtonOffDTree);

        txtSituation = findViewById(R.id.txtSituation);
        txtHistory = findViewById(R.id.txtHistory);
        txtCall = findViewById(R.id.txtCall);

        spinner = findViewById(R.id.spinner);

        rlDemo = findViewById(R.id.rlDemo);
        llInsertNumber = findViewById(R.id.llInsertNumber);
        llNoNumberCancel = findViewById(R.id.llNoNumberCancel);
        txtNoNumberFound = findViewById(R.id.txtNoNumberFound);
        txtSaveInGetNumber = findViewById(R.id.txtSaveInGetNumber);
        txtNoNumberCancel = findViewById(R.id.txtNoNumberCancel);

        llFunction = findViewById(R.id.llFunction);
        txtFunction = findViewById(R.id.txtFunction);

    }

    private void ClickFunctionOne() {
        llActiveFunctionOneDOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert(strActiveFunctionOneDOne, ActiveFunctionOneDOne);
                myAlertDialog.Show(strActiveFunctionOneDOne,ActiveFunctionOneDOne,strNumber,strPassword);

            }
        });
        llUnActiveFunctionOneDOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert(strUnActiveFunctionOneDOne, UnActiveFunctionOneDOne);
                myAlertDialog.Show(strUnActiveFunctionOneDOne,UnActiveFunctionOneDOne,strNumber,strPassword);
            }
        });
        llActiveFunctionOneDTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert(strActiveFunctionOneDTwo, ActiveFunctionOneDTwo);
                myAlertDialog.Show(strActiveFunctionOneDTwo,ActiveFunctionOneDTwo,strNumber,strPassword);
            }
        });
        llUnActiveFunctionOneDTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert(strUnActiveFunctionOneDTwo, UnActiveFunctionOneDTwo);
                myAlertDialog.Show(strUnActiveFunctionOneDTwo,UnActiveFunctionOneDTwo,strNumber,strPassword);
            }
        });

        llActiveFunctionOneDTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Alert(strActiveFunctionOneDTree, ActiveFunctionOneDTree);
                myAlertDialog.Show(strActiveFunctionOneDTree,ActiveFunctionOneDTree,strNumber,strPassword);
            }
        });
        llUnActiveFunctionOneDTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Alert(strUnActiveFunctionOneDTree, UnActiveFunctionOneDTree);
                myAlertDialog.Show(strUnActiveFunctionOneDTree,UnActiveFunctionOneDTree,strNumber,strPassword);
            }
        });
    }

    private void ClickOnMenu() {
        llMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.addNumber) {
                            Intent i = new Intent(getApplicationContext(), ActivityGetNumber.class);
                            startActivity(i);
                            return true;
                        } else if (id == R.id.addContacts) {
                            Intent i = new Intent(getApplicationContext(), ActivityContacts.class);
                            i.putExtra("number",strNumber);
                            i.putExtra("password",strPassword);
                            i.putExtra("name",strName);
                            startActivity(i);
                            return true;
                        } else if (id == R.id.setting) {
                            Intent i = new Intent(getApplicationContext(), ActivitySetting.class);
                            i.putExtra("number",strNumber);
                            i.putExtra("password",strPassword);
                            i.putExtra("name",strName);
                            startActivity(i);
                            return true;
                        }else if (id == R.id.USSD) {
                            Intent i = new Intent(getApplicationContext(), ActivityUSSD.class);
                            i.putExtra("number",strNumber);
                            i.putExtra("password",strPassword);
                            i.putExtra("name",strName);
                            startActivity(i);
                            return true;
                        } else if (id == R.id.about) {
                            return true;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    private void ClickFunctionMain() {
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneCallIntent = new Intent(Intent.ACTION_DIAL);
                phoneCallIntent.setData(Uri.parse("tel:" + strNumber));
                startActivity(phoneCallIntent);
            }
        });
        llSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Alert(strSituation, Situation);
                myAlertDialog.Show(strSituation,Situation,strNumber,strPassword);
            }
        });
        llHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityHistory.class);
                i.putExtra("number",strNumber);
                i.putExtra("password",strPassword);
                i.putExtra("name",strName);
                startActivity(i);

            }
        });
    }

    private  ArrayList<DeviceListModel> model =new ArrayList<>() ;
    private List<String> listNumber =  new ArrayList<>() ;
    private List<Integer> listFunction =  new ArrayList<>() ;
    private List<String> listPassword =  new ArrayList<>() ;
    private List<String> listName =  new ArrayList<>() ;

    private void buildData(){

        listFunction.clear();
        listName.clear();
        listPassword.clear();
        listNumber.clear();

        if ( model == null ){
            listNumber.add("");
        }else{
            for (int i=0;i<model.size();i++){
                listNumber.add(model.get(i).getNumber());
                listName.add(model.get(i).getName());
                listFunction.add(model.get(i).getFunction());
                listPassword.add(model.get(i).getPass());
            }
        }
        strNumber = listNumber.get(0);
        strPassword = listPassword.get(0);
        strName = listName.get(0);
        llMainMenu.setBackgroundColor(Color.parseColor("#1e8a09"));
        llFunction.setBackgroundColor(Color.parseColor("#1e8a09"));
        rlMainTop.setBackgroundColor(Color.parseColor("#1e8a09"));
        txtFunction.setText("کاربرد یک");
    }

    private void Spinner() {

        spinner.setItems(listName);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
             if(listFunction.get(position).equals(1)){
                    strNumber = listNumber.get(position);
                    strPassword = listPassword.get(position);
                    strName = listName.get(position);
                    FunctionOne();
                }
                if(listFunction.get(position).equals(2)){
                    strNumber = listNumber.get(position);
                    strPassword = listPassword.get(position);
                    strName = listName.get(position);
                    FunctionTwo();
                }
                if(listFunction.get(position).equals(3)){
                    strNumber = listNumber.get(position);
                    strPassword = listPassword.get(position);
                    strName = listName.get(position);
                    FunctionThree();
                }
                if(listFunction.get(position).equals(4)){
                    strNumber = listNumber.get(position);
                    strPassword = listPassword.get(position);
                    strName = listName.get(position);
                    FunctionFour();
                }
            }
        });
    }

    private int getWindowSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return height;
    }

    private void FunctionOne() {
        rlFunctionTree.setVisibility(View.GONE);
        llMainMenu.setBackgroundColor(Color.parseColor("#1e8a09"));
        llFunction.setBackgroundColor(Color.parseColor("#1e8a09"));
        rlMainTop.setBackgroundColor(Color.parseColor("#1e8a09"));
        txtFunction.setText("کاربرد یک");

        Situation = "*10#";
        ActiveFunctionOneDOne = "*11*1#";
        UnActiveFunctionOneDOne = "*11*2#";
        ActiveFunctionOneDTwo = "*12*1#";
        UnActiveFunctionOneDTwo = "*12*2#";

        strSituation = "آیا میخواهید وضعیت دستگاه را دریافت کنید؟";
        strActiveFunctionOneDOne = "آیا میخواهید دستگاه یک را روشن کنید؟";
        strUnActiveFunctionOneDOne = "آیا میخواهید دستگاه یک را خاموش کنید؟";
        strActiveFunctionOneDTwo = "آیا میخواهید دستگاه دو را روشن کنید؟";
        strUnActiveFunctionOneDTwo = "آیا میخواهید دستگاه دو را خاموش کنید؟";

        txtFunctionOneDOne.setText("دستگاه یک");
        txtFunctionOneDTwo.setText("دستگاه دو");
        txtFunctionOneDTree.setText("دستگاه سه");
        txtButtonOnDOne.setText("روشن");
        txtButtonOffDOne.setText("خاموش");
        txtButtonOnDTwo.setText("روشن");
        txtButtonOffDTwo.setText("خاموش");

    }

    private void FunctionTwo() {
        rlFunctionTree.setVisibility(View.VISIBLE);
        llMainMenu.setBackgroundColor(Color.parseColor("#607D8B"));
        llFunction.setBackgroundColor(Color.parseColor("#607D8B"));
        rlMainTop.setBackgroundColor(Color.parseColor("#607D8B"));
        txtFunction.setText("کاربرد دو");

        Situation = "*20#";
        ActiveFunctionOneDOne = "*21*1#";
        UnActiveFunctionOneDOne = "*21*2#";
        ActiveFunctionOneDTwo = "*22*1#";
        UnActiveFunctionOneDTwo = "*22*2#";
        ActiveFunctionOneDTree = "*23*1#";
        UnActiveFunctionOneDTree = "*23*2#";

        strSituation = "آیا میخواهید وضعیت دستگاه را دریافت کنید؟";
        strActiveFunctionOneDOne = "آیا میخواهید دستگاه یک را روشن کنید؟";
        strUnActiveFunctionOneDOne = "آیا میخواهید دستگاه یک را خاموش کنید؟";
        strActiveFunctionOneDTwo = "آیا میخواهید دستگاه دو را روشن کنید؟";
        strUnActiveFunctionOneDTwo = "آیا میخواهید دستگاه دو را خاموش کنید؟";
        strActiveFunctionOneDTree = "آیا میخواهید دستگاه سه را روشن کنید؟";
        strUnActiveFunctionOneDTree = "آیا میخواهید دستگاه سه را خاموش کنید؟";

        txtFunctionOneDOne.setText("دستگاه یک");
        txtFunctionOneDTwo.setText("کلید اول");
        txtFunctionOneDTree.setText("کلید دوم");
        txtButtonOnDOne.setText("روشن");
        txtButtonOffDOne.setText("خاموش");
        txtButtonOnDTwo.setText("روشن");
        txtButtonOffDTwo.setText("خاموش");
        txtButtonOnDTree.setText("روشن");
        txtButtonOffDTree.setText("خاموش");

    }

    private void FunctionThree() {
        rlFunctionTree.setVisibility(View.VISIBLE);
        llMainMenu.setBackgroundColor(Color.parseColor("#3F51B5"));
        llFunction.setBackgroundColor(Color.parseColor("#3F51B5"));
        rlMainTop.setBackgroundColor(Color.parseColor("#3F51B5"));
        txtFunction.setText("کاربرد سوم");

        Situation = "*30#";
        ActiveFunctionOneDOne = "*31*1#";
        UnActiveFunctionOneDOne = "*31*2#";
        ActiveFunctionOneDTwo = "*32*1#";
        UnActiveFunctionOneDTwo = "*32*2#";
        ActiveFunctionOneDTree = "*33*1#";
        UnActiveFunctionOneDTree = "*33*2#";

        strSituation = "آیا میخواهید وضعیت دستگاه را دریافت کنید؟";
        strActiveFunctionOneDOne = "آیا میخواهید کرکره بالا برود؟";
        strUnActiveFunctionOneDOne = "آیا میخواهید کرکره پایین بیاید؟";
        strActiveFunctionOneDTwo = "آیا میخواهید دستگاه یک را روشن کنید؟";
        strUnActiveFunctionOneDTwo = "آیا میخواهید دستگاه یک را خاموش کنید؟";
        strActiveFunctionOneDTree = "آیا میخواهید دستگاه دو را روشن کنید؟";
        strUnActiveFunctionOneDTree = "آیا میخواهید دستگاه دو را خاموش کنید؟";

        txtFunctionOneDOne.setText("کرکره برقی");
        txtFunctionOneDTwo.setText("کلید اول");
        txtFunctionOneDTree.setText("کلید دوم");
        txtButtonOnDOne.setText("  بالا  ");
        txtButtonOffDOne.setText("  پایین ");
        txtButtonOnDTwo.setText("روشن");
        txtButtonOffDTwo.setText("خاموش");
        txtButtonOnDTree.setText("روشن");
        txtButtonOffDTree.setText("خاموش");
    }

    private void FunctionFour() {
        rlFunctionTree.setVisibility(View.GONE);
        llMainMenu.setBackgroundColor(Color.parseColor("#4A148C"));
        llFunction.setBackgroundColor(Color.parseColor("#4A148C"));
        rlMainTop.setBackgroundColor(Color.parseColor("#4A148C"));
        txtFunction.setText("کاربرد چهارم");
        Situation = "*40#";
        ActiveFunctionOneDOne = "*41*1#";
        UnActiveFunctionOneDOne = "*41*2#";
        ActiveFunctionOneDTwo = "*42*1#";
        UnActiveFunctionOneDTwo = "*42*2#";

        strSituation = "آیا میخواهید وضعیت دستگاه را دریافت کنید؟";
        strActiveFunctionOneDOne = "آیا میخواهید کرکره بالا برود؟";
        strUnActiveFunctionOneDOne = "آیا میخواهید کرکره پایین بیاید؟";
        strActiveFunctionOneDTwo = "آیا میخواهید دزدگیر فعال شود؟";
        strUnActiveFunctionOneDTwo = "آیا میخواهید دزدگیر غیر فعال شود؟";

        txtFunctionOneDOne.setText("کرکره برقی");
        txtFunctionOneDTwo.setText("دزدگیر");
        txtFunctionOneDTree.setText("کلید دوم");
        txtButtonOnDOne.setText("  بالا  ");
        txtButtonOffDOne.setText(" پایین  ");
        txtButtonOnDTwo.setText(" فعال ");
        txtButtonOffDTwo.setText("غیرفعال");
        txtButtonOnDTree.setText("روشن");
        txtButtonOffDTree.setText("خاموش");
    }

    private void setTypeFace() {
        txtFunctionOneDOne.setTypeface(G.faceBold);
        txtFunctionOneDTwo.setTypeface(G.faceBold);
        txtFunctionOneDTree.setTypeface(G.faceBold);
        txtButtonOnDOne.setTypeface(G.face);
        txtButtonOffDOne.setTypeface(G.face);
        txtButtonOnDTwo.setTypeface(G.face);
        txtButtonOffDTwo.setTypeface(G.face);
        txtButtonOnDTree.setTypeface(G.face);
        txtButtonOffDTree.setTypeface(G.face);

        txtSituation.setTypeface(G.face);
        txtHistory.setTypeface(G.face);
        txtCall.setTypeface(G.face);


        txtNoNumberFound.setTypeface(G.face);
        txtSaveInGetNumber.setTypeface(G.face);
        txtNoNumberCancel.setTypeface(G.face);
        txtFunction.setTypeface(G.faceBold);

    }

    private ArrayList<DeviceListModel> loadData(){
        ArrayList<DeviceListModel> model  = new ArrayList<>();
        model.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("listDevice", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<DeviceListModel>>() {
        }.getType();
        model = gson.fromJson(json, type);
        return model;
    }

    private void  ClickNoNumber(){
        llInsertNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ActivityGetNumber.class);
                startActivity(i);
            }
        });

        llNoNumberCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        model.clear();
        model = loadData();
        if(model.size() >0){
            buildData();
            Spinner();
            rlDemo.setVisibility(View.GONE);
            rlMainTop.setVisibility(View.VISIBLE);
            rlMainBottom.setVisibility(View.VISIBLE);
        }else
        {
            rlDemo.setVisibility(View.VISIBLE);
            rlMainTop.setVisibility(View.GONE);
            rlMainBottom.setVisibility(View.GONE);
        }


    }
}
