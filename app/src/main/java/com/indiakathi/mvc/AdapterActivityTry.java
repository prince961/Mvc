package com.indiakathi.mvc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AdapterActivityTry extends AppCompatActivity {

    Controller aController = null;
    ListView mListView;
    String bata = "{\"products\":[{\"title\":\"sky lantern\",\"id\":58,\"created_at\":\"2016-05-22T12:27:48Z\",\"updated_at\":\"2016-05-22T12:27:48Z\",\"type\":\"simple\",\"status\":\"publish\",\"downloadable\":false,\"virtual\":false,\"permalink\":\"https:\\/\\/www.indiakathi.com\\/product\\/sky-lantern\\/\",\"sku\":\"\",\"price\":\"300\",\"regular_price\":\"500\",\"sale_price\":\"300\",\"price_html\":\"<del><span class=\\\"amount\\\">&#8377;&nbsp;500.00<\\/span><\\/del> <ins><span class=\\\"amount\\\">&#8377;&nbsp;300.00<\\/span><\\/ins>\",\"taxable\":false,\"tax_status\":\"taxable\",\"tax_class\":\"\",\"managing_stock\":false,\"stock_quantity\":null,\"in_stock\":true,\"backorders_allowed\":false,\"backordered\":false,\"sold_individually\":false,\"purchaseable\":true,\"featured\":false,\"visible\":true,\"catalog_visibility\":\"visible\",\"on_sale\":true,\"product_url\":\"\",\"button_text\":\"\",\"weight\":null,\"dimensions\":{\"length\":\"\",\"width\":\"\",\"height\":\"\",\"unit\":\"in\"},\"shipping_required\":true,\"shipping_taxable\":true,\"shipping_class\":\"\",\"shipping_class_id\":null,\"description\":\"<p>very nice product<\\/p>\\n\",\"short_description\":\"\",\"reviews_allowed\":true,\"average_rating\":\"0.00\",\"rating_count\":0,\"related_ids\":[],\"upsell_ids\":[],\"cross_sell_ids\":[],\"parent_id\":0,\"categories\":[\"sky lantern\"],\"tags\":[],\"images\":[{\"id\":31,\"created_at\":\"2016-05-01T10:16:10Z\",\"updated_at\":\"2016-05-01T10:16:10Z\",\"src\":\"https:\\/\\/www.indiakathi.com\\/wp-content\\/uploads\\/2012\\/10\\/16.jpg\",\"title\":\"16\",\"alt\":\"\",\"position\":0}],\"featured_src\":\"https:\\/\\/www.indiakathi.com\\/wp-content\\/uploads\\/2012\\/10\\/16.jpg\",\"attributes\":[],\"downloads\":[],\"download_limit\":0,\"download_expiry\":0,\"download_type\":\"\",\"purchase_note\":\"\",\"total_sales\":0,\"variations\":[],\"parent\":[],\"grouped_products\":[],\"menu_order\":0}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_activity_try);
        mListView = (ListView) findViewById(R.id.productListViewA);

        aController = (Controller) getApplicationContext();

        String strUrl = "https://www.indiakathi.com/wc-api/v3/products?filter[categories]=chinese-starters&consumer_key=ck_28f853daf1e9200af47d5ce08f60a77df567b1a3&consumer_secret=cs_b9c783eb7f2f3462c63754e1b574906291f062e0";
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(strUrl);

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {
        String data = null;

        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {

            ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();
            listViewLoaderTask.execute(result);

        }
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            iStream.close();
        }

        return data;
    }

    private class ListViewLoaderTask extends AsyncTask<String, Void, AdapterTry> {

        JSONObject jObject;

        @Override
        protected AdapterTry doInBackground(String... strJson) {
            try {
                jObject = new JSONObject(strJson[0]);
                JsonParserTry jsonParserTry = new JsonParserTry();
                jsonParserTry.parse(jObject);
            } catch (Exception e) {
                Log.d("JSON Exception1", e.toString());
            }

            JsonParserTry jsonParserTry = new JsonParserTry();

            List<ModelProducts> countries;

            countries = jsonParserTry.parse(jObject);

            aController.addProductList(countries);

            return new AdapterTry(getBaseContext(), R.layout.item_list, countries);
        }

        @Override
        protected void onPostExecute(AdapterTry adapter) {

            mListView.setAdapter(adapter);

        }
    }
}





