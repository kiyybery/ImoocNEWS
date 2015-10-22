package com.example.administrator.imoocnews;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lv_main);
        new NewsAsyncTask().execute(URL);
    }


    class NewsAsyncTask extends AsyncTask<String,Void,List<ItemBean>>{


        @Override
        protected List<ItemBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<ItemBean> list) {
            super.onPostExecute(list);
            NewsAdapter adapter = new NewsAdapter(MainActivity.this,list);
            mListView.setAdapter(adapter);
        }
    }

    private List<ItemBean> getJsonData(String url){

        List<ItemBean> itemBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            Log.i("xys",jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            ItemBean itemBean;
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                itemBean = new ItemBean();
                itemBean.newsIconUrl = jsonObject.getString("picSmall");
                itemBean.newstitle = jsonObject.getString("name");
                itemBean.newscontent = jsonObject.getString("description");
                itemBeanList.add(itemBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemBeanList;
    }

    private String readStream(InputStream is){
        InputStreamReader isr ;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line=br.readLine())!=null){
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
