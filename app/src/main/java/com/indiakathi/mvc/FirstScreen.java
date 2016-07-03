package com.indiakathi.mvc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstScreen extends Activity {


    Controller aController = null ;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        mListView = (ListView)findViewById(R.id.productListView);

        aController = (Controller) getApplicationContext();

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
        final Button secondBtn = (Button) findViewById(R.id.second);


        String strUrl = "https://www.indiakathi.com/wc-api/v3/products?filter[categories]=chinese-starters&consumer_key=ck_28f853daf1e9200af47d5ce08f60a77df567b1a3&consumer_secret=cs_b9c783eb7f2f3462c63754e1b574906291f062e0";


        //String bata = "{\"products\":[{\"title\":\"sky lantern\",\"id\":58,\"created_at\":\"2016-05-22T12:27:48Z\",\"updated_at\":\"2016-05-22T12:27:48Z\",\"type\":\"simple\",\"status\":\"publish\",\"downloadable\":false,\"virtual\":false,\"permalink\":\"https:\\/\\/www.indiakathi.com\\/product\\/sky-lantern\\/\",\"sku\":\"\",\"price\":\"300\",\"regular_price\":\"500\",\"sale_price\":\"300\",\"price_html\":\"<del><span class=\\\"amount\\\">&#8377;&nbsp;500.00<\\/span><\\/del> <ins><span class=\\\"amount\\\">&#8377;&nbsp;300.00<\\/span><\\/ins>\",\"taxable\":false,\"tax_status\":\"taxable\",\"tax_class\":\"\",\"managing_stock\":false,\"stock_quantity\":null,\"in_stock\":true,\"backorders_allowed\":false,\"backordered\":false,\"sold_individually\":false,\"purchaseable\":true,\"featured\":false,\"visible\":true,\"catalog_visibility\":\"visible\",\"on_sale\":true,\"product_url\":\"\",\"button_text\":\"\",\"weight\":null,\"dimensions\":{\"length\":\"\",\"width\":\"\",\"height\":\"\",\"unit\":\"in\"},\"shipping_required\":true,\"shipping_taxable\":true,\"shipping_class\":\"\",\"shipping_class_id\":null,\"description\":\"<p>very nice product<\\/p>\\n\",\"short_description\":\"\",\"reviews_allowed\":true,\"average_rating\":\"0.00\",\"rating_count\":0,\"related_ids\":[],\"upsell_ids\":[],\"cross_sell_ids\":[],\"parent_id\":0,\"categories\":[\"sky lantern\"],\"tags\":[],\"images\":[{\"id\":31,\"created_at\":\"2016-05-01T10:16:10Z\",\"updated_at\":\"2016-05-01T10:16:10Z\",\"src\":\"https:\\/\\/www.indiakathi.com\\/wp-content\\/uploads\\/2012\\/10\\/16.jpg\",\"title\":\"16\",\"alt\":\"\",\"position\":0}],\"featured_src\":\"https:\\/\\/www.indiakathi.com\\/wp-content\\/uploads\\/2012\\/10\\/16.jpg\",\"attributes\":[],\"downloads\":[],\"download_limit\":0,\"download_expiry\":0,\"download_type\":\"\",\"purchase_note\":\"\",\"total_sales\":0,\"variations\":[],\"parent\":[],\"grouped_products\":[],\"menu_order\":0}]}";
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(strUrl);

        //Get Global Controller Class object (see application tag in AndroidManifest.xml)


        /******************  Create Dummy Products Data

         ModelProducts productObject = null;
         for(int i=1;i<=4;i++) {
         int price = 10 + i;
         // Create product model class object
         productObject = new ModelProducts("Product " + i, "Description " + i, price);

         //store product object to arraylist in controller
         aController.setProducts(productObject);

         }
         ***********/

        /******************  Products Data Creation End   ***********/


        /******* Create view elements dynamically and show on activity ******/

        //Product arraylist size
        int ProductsSize = aController.getProductsArraylistSize();

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        /******** Dynamically create view elements - Start

         for(int j=0;j< ProductsSize;j++)
         {
         // Get probuct data from product data arraylist
         String pName = aController.getProducts(j).getProductName();
         int pPrice   = aController.getProducts(j).getProductPrice();

         // Create LinearLayout to view elemnts+
         LinearLayout ll = new LinearLayout(this);
         ll.setOrientation(LinearLayout.HORIZONTAL);

         TextView product = new TextView(this);
         product.setText(" "+pName+"    ");

         //Add textView to LinearLayout
         ll.addView(product);

         TextView price = new TextView(this);
         price.setText("  $"+pPrice+"     ");

         //Add textView to LinearLayout
         ll.addView(price);

         final Button btn = new Button(this);
         btn.setId(j+1);
         btn.setText("Add To Cart");

         // set the layoutParams on the button
         btn.setLayoutParams(params);

         final int index = j;

         //Create click listener for dynamically created button
         btn.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {

         //Clicked button index
         Log.i("TAG", "index :" + index);

         // Get product instance for index
         ModelProducts tempProductObject = aController.getProducts(index);

         //Check Product already exist in Cart or Not
         if(!aController.getCart().checkProductInCart(tempProductObject))
         {
         btn.setText("Added");

         // Product not Exist in cart so add product to
         // Cart product arraylist
         aController.getCart().setProducts(tempProductObject);

         Toast.makeText(getApplicationContext(), "Now Cart size: "+aController.getCart().getCartSize(),
         Toast.LENGTH_LONG).show();
         }
         else
         {
         // Cart product arraylist contains Product
         Toast.makeText(getApplicationContext(), "Product "+(index+1)+" already added in cart.",
         Toast.LENGTH_LONG).show();
         }
         }
         });

         //Add button to LinearLayout
         ll.addView(btn);

         //Add LinearLayout to XML layout
         lm.addView(ll);
         }  **********/

        /******** Dynamically create view elements - End **********/

        secondBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), SecondScreen.class);
                startActivity(i);
            }
        });
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

    private class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter> {

        JSONObject jObject;

        @Override
        protected SimpleAdapter doInBackground(String... strJson) {
            try {
                jObject = new JSONObject(strJson[0]);
                JsonParserTry jsonParserTry = new JsonParserTry();
                jsonParserTry.parse(jObject);
            } catch (Exception e) {
                Log.d("JSON Exception1", e.toString());
            }

            JsonParserTry jsonParserTry = new JsonParserTry();

            List<ModelProducts> countries ;

            countries = jsonParserTry.parse(jObject);


            aController.addProductList(countries);


            ArrayList<HashMap<String,Object>> productHMList = new ArrayList<>();

            for (int i = 0;i< countries.size(); i++){
                ModelProducts producti = countries.get(i);
                HashMap<String,Object> productHm = convertProductToHM(producti);
                productHMList.add(productHm);
            }

            String[] from = {"title", "flag","price"};
            int[] to = {R.id.item_title, R.id.item_image,R.id.item_price1};

            return new SimpleAdapter(getBaseContext(),productHMList, R.layout.item_list, from, to);
        }

        private HashMap<String,Object> convertProductToHM(ModelProducts producti) {
            String title = producti.getProductName();
            int price  = producti.getProductPrice();
            String desc = producti.getProductDesc();
            String imgLink = producti.getImageLink();

            HashMap<String,Object> productHm = new HashMap<>();
            productHm.put("title",title);
            productHm.put("price",price);
            productHm.put("flag_path",imgLink);
            productHm.put("flag", R.mipmap.ic_launcher);

            return productHm;


        }


        @Override
        protected void onPostExecute(SimpleAdapter adapter) {

            mListView.setAdapter(adapter);
            letsTryClickItem(adapter);


            for (int i = 0; i < adapter.getCount(); i++) {
                HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(i);
                String imgUrl = (String) hm.get("flag_path");
                ImageLoaderTask imageLoaderTask = new ImageLoaderTask();

                // HashMap<String, Object> hmDownload = new HashMap<String, Object>();
                hm.put("flag_path", imgUrl);
                hm.put("position", i);

                imageLoaderTask.execute(hm);

            }
        }
    }

    private class ImageLoaderTask extends AsyncTask<HashMap<String, Object>, Void, HashMap<String, Object>> {

        @Override
        protected HashMap<String, Object> doInBackground(HashMap<String, Object>... hm) {

            InputStream iStream = null;
            String imgUrl = (String) hm[0].get("flag_path");
            int position = (Integer) hm[0].get("position");

            URL url;
            try {
                url = new URL(imgUrl);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                iStream = urlConnection.getInputStream();
                File cacheDirectory = getBaseContext().getCacheDir();
                File tmpFile = new File(cacheDirectory.getPath() + "/wpta_" + position + ".jpg");
                FileOutputStream fOutStream = new FileOutputStream(tmpFile);
                Bitmap b = BitmapFactory.decodeStream(iStream);
                b.compress(Bitmap.CompressFormat.PNG, 100, fOutStream);
                fOutStream.flush();
                fOutStream.close();
                HashMap<String, Object> hmBitmap = new HashMap<String, Object>();
                hmBitmap.put("flag", tmpFile.getPath());
                hmBitmap.put("position", position);
                return hmBitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(HashMap<String, Object> result) {
            String path = (String) result.get("flag");
            int position = (Integer) result.get("position");
            SimpleAdapter adapter = (SimpleAdapter) mListView.getAdapter();
            HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(position);
            hm.put("flag", path);
            adapter.notifyDataSetChanged();
        }
    }

    private void letsTryClickItem(final SimpleAdapter adapter) {
        mListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        HashMap<String,Object> selectedProduct =(HashMap<String, Object>) adapter.getItem(position);

                        aController.getCart().setProducts(selectedProduct);

                        String toastText = "item has been added to cart"+position;
                        Toast.makeText(getBaseContext(), toastText, Toast.LENGTH_SHORT).show();

                    }
                });



        for (int i = 0; i < adapter.getCount(); i++) {
            HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem(i);
            String imgUrl = (String) hm.get("flag_path");
            hm.put("flag_path", imgUrl);
            hm.put("position", i);
        }
    }



}




