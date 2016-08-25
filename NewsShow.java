package cn.edu.nsu;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Teacher on 2016/8/25.
*�ο�Դ����
 */
public class NewsShow {
    public static  void main(String args[]){
        String httpUrl = "http://apis.baidu.com/txapi/social/social";
        String httpArg = "num=10&page=1";
        String jsonResult = request(httpUrl, httpArg);
        System.out.println(jsonResult);
        JSONObject jsonObject=JSONObject.fromObject(jsonResult);
        int code=jsonObject.getInt("code");
        if(code==200){
            JSONArray jsonArray=jsonObject.getJSONArray("newslist");
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String newsTime=jsonObject1.getString("ctime");
                String newsTitle=jsonObject1.getString("title");
                System.out.println(newsTitle + "," + newsTime);
            }
        }else{
            System.out.println("�����ݷ���");
        }


    }


    /**
     * @param urlAll
     *            :����ӿ�
     * @param httpArg
     *            :����
     * @return ���ؽ��
     */
    public static String request(String httpUrl, String httpArg) {

        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // ����apikey��HTTP header
            connection.setRequestProperty("apikey",  "ac546028096ecc2508300657d1f794d8");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
