import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHelper {

    public String handleRequest(String method, String url, String xmlPayload,String readTimeout,String connectTimeout) throws IOException {
        HttpURLConnection connection = null;
        OutputStreamWriter wr = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL connectionUrl = new URL(url);
            connection = (HttpURLConnection) connectionUrl.openConnection();
            connection.setRequestMethod(method);
            if ( xmlPayload != null && ! xmlPayload.equals("")) {
                connection.setReadTimeout(Integer.parseInt(readTimeout));
                connection.setConnectTimeout(Integer.parseInt(connectTimeout));
                connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
                /*connection.setRequestProperty("SOAPAction", soapAction);*/
                connection.setDoOutput(true);
                wr = new OutputStreamWriter(connection.getOutputStream());
                wr.write(xmlPayload);
                wr.flush();
            }
            try {
                // getInputStream will throw exception when response code is
                // greater than 400, so we need to getErrorStream
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (Exception e) {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuilder bodyBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                bodyBuilder.append(inputLine);
            }
            in.close();
            result = String.valueOf( bodyBuilder.toString());
            System.out.println(result);

        } catch(Exception e){
        }finally {
            if (wr != null) {
                wr.close();
            }
            if (in != null) {
                in.close();
            }
            if ( connection != null ) {
                connection.disconnect();
            }

        }

        return result;
    }


}
