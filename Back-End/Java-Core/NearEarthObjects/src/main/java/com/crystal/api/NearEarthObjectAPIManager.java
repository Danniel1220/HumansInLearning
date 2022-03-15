package com.crystal.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NearEarthObjectAPIManager {
    private final String NEAR_EARTH_OBJECT_DEMO_KEY = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY";
    private final int CONNECT_TIMEOUT = 5000;
    private final int READ_TIMEOUT = 5000;
    HttpURLConnection httpConnection;

    public NearEarthObjectAPIManager() {

    }

    public String getDemoKeyAPIResponse() {
        StringBuffer responseContent = new StringBuffer();
        int status;

        try {
            URL url = new URL(NEAR_EARTH_OBJECT_DEMO_KEY);
            httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setConnectTimeout(CONNECT_TIMEOUT);
            httpConnection.setReadTimeout(READ_TIMEOUT);
            status = httpConnection.getResponseCode();
            System.out.println("Connection status: " + status);
            String line;
            BufferedReader reader;

            if (status == 200) {
                reader = new BufferedReader(new InputStreamReader(this.httpConnection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(this.httpConnection.getErrorStream()));
            }

            while((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            reader.close();

            return responseContent.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
