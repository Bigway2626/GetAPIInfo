package com.apitest.apitest.entity;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Talk {
    public static String sentIDAndGetJson(String requestUrl) {
        //介面地址
        //params用於儲存要請求的引數
        Map params = new HashMap();
        //showapi_appid的值,把###替換成你的appid
        params.put("updated", "Dec 27, 2022 01:28:00 UTC");
        //Map轉json字串
        JSONObject jsonMap = JSONObject.fromObject(params);
        System.out.println(jsonMap);
        //我們請求的字串
        //params.put("info", info);
        //數字簽名,###填你的數字簽名,可以在你的個人中心看到
        //params.put("showapi_sign", "###");
        //呼叫httpRequest方法,這個方法主要用於請求地址,並加上請求引數
        String Content = httpRequest(requestUrl, params);
        //System.out.println("COntent:" + COntent);輸出全部內容
        return Content;
    }

    private static String httpRequest(String requestUrl, Map params) {
        //buffer用於接受返回的字元
        StringBuffer buffer = new StringBuffer();
        try {
            //建立URL,把請求地址給補全,其中urlencode()方法用於把params裡的引數給取出來
            URL url = new URL(requestUrl + "?" + urlencode(params));
            //開啟http連線
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            //獲得輸入
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //將bufferReader的值給放到buffer裡
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            //關閉bufferReader和輸入流
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            //斷開連線
            httpUrlConn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回字串
        return buffer.toString();
    }

    public static String urlencode(Map<String, Object> data) {
        //將map裡的引數變成像 showapi_appid=###&showapi_sign=###&的樣子
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getFirstLayerContent(String resultURL, String ContentKey, String GetContent) {
        Map params = new HashMap();
        String Content = httpRequest(resultURL, params);
        //處理返回的JSON資料並返回
        JSONObject pageBean = JSONObject.fromObject(Content).getJSONObject(ContentKey);
        return pageBean.getString(GetContent);
    }

    public static String[] getCoinMessage(String cion) {
        String Time = getFirstLayerContent("https://api.coindesk.com/v1/bpi/currentprice.json", "time", "updated");
        String bpi = getFirstLayerContent("https://api.coindesk.com/v1/bpi/currentprice.json", "bpi", cion);
        //將Test字串轉為JSON然後取值
        String code = JSONObject.fromObject(bpi).getString("code");
        String symbol = JSONObject.fromObject(bpi).getString("symbol");
        String rate = JSONObject.fromObject(bpi).getString("rate");
        String description = JSONObject.fromObject(bpi).getString("description");
        String rate_float = JSONObject.fromObject(bpi).getString("rate_float");
        System.out.println("時間:" + Time + "幣種:" + code + "單位:" + symbol + "匯率:" + rate + "詳述:" + description + "利率浮動:" + rate_float);
        String[] ConinData = new String[6];
        ConinData[0] = code;
        ConinData[1] = symbol;
        ConinData[2] = rate;
        ConinData[3] = description;
        ConinData[4] = rate_float;
        ConinData[5] = Time;
        return ConinData;
    }

    //測試是否有效
    public static void main(String[] args) {
        //靜態不用實體即可使用
        getCoinMessage("USD");
        getCoinMessage("GBP");
        getCoinMessage("EUR");
    }
}
