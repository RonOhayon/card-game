package com.example.hw1_ron;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment_List extends Fragment {
    public static final String ARG_KEY_SCORE = "ARG_KEY_SCORE";
    public static final String ARG_KEY_NAME = "ARG_KEY_NAME";
    public static final String ARG_KEY_LAT = "ARG_KEY_LAT";
    public static final String ARG_KEY_LONG = "ARG_KEY_LONG";

    private ListView list_LV;
    protected View view;
    private ArrayList<Winner> winners;
    ArrayAdapter<Winner> aryAdapt;
    private double latitude;
    private double longitude;
    int score;
    String name;
    private CallBack_list callBack_list;

    public void setCallBack_list( CallBack_list callBack_list_){
        this.callBack_list = callBack_list_;
    }

    public  static  Fragment_List newInstance(String name,int score ,double latitude, double longitude){
        Fragment_List fragment = new Fragment_List();
        Bundle args =new Bundle();
        args.putString("ARG_KEY_NAME",name);
        args.putInt("ARG_KEY_SCORE",score);
        args.putDouble("ARG_KEY_LAT",latitude);
        args.putDouble("ARG_KEY_LONG",longitude);
        fragment.setArguments(args);
       return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list,container,false);
        if(winners == null) {
            winners = new ArrayList<Winner>();
        }
        initList(view);
        addToList();
        return view;
    }

    private void initList(View view){
        list_LV = view.findViewById(R.id.list_LV);
        aryAdapt = new ArrayAdapter<Winner>(getActivity(),R.layout.simple_list_item,android.R.id.text1,winners);
        list_LV.setAdapter(aryAdapt);
        list_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callBack_list.setLocation(winners.get(position).getLatitude(),winners.get(position).getLongitude());
            }
        });

    }

    private void getArgs(){
     if(getArguments()!= null){
         name=getArguments().getString("ARG_KEY_NAME");
         score=getArguments().getInt("ARG_KEY_SCORE");
         latitude=getArguments().getDouble("ARG_KEY_LAT");
         longitude=getArguments().getDouble("ARG_KEY_LONG");
     }
    }

    public void addToList(){
        if(getArguments() != null) {
           getArgs();
           winners.add(new Winner(name, score, latitude, longitude));
           aryAdapt.notifyDataSetChanged();
       }
    }

    public ArrayList<Winner> getList(){
        return winners;
    }
    public void setList(ArrayList<Winner> winList){
        if(winners== null){
            winners =  new ArrayList<Winner>();
        }
        winners = winList;
    }
    public void checkSize(){
        for (int i = 0; i < winners.size() ; i++) {
            if(winners.get(i).getScore()==0)winners.remove(i);
        }
    }
}
