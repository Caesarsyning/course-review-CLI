package Data_Layer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/*
This file is to download uva courses, and now they are all preloaded.
So this file is useless now. keep it for future use
 */
public class loadData {
//    public static List<Course> getUVAdata() throws IOException {
//        URL url = new URL("https://api.devhub.virginia.edu/v1/courses");
//        URLConnection con = url.openConnection();
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        con.getInputStream()));
//
//        StringBuffer sb = new StringBuffer();
//        String inputLine;
//        while ((inputLine = in.readLine()) != null)
//            sb.append(inputLine);
//        in.close();
//
//        JSONObject schedule = new JSONObject(sb.toString());
//        //Starting at the root, get the quiz map
//        JSONObject result = (JSONObject) schedule.get("class_schedules");
//        List<JSONArray> options = new ArrayList<>();
//        List<Course> aCourse = new ArrayList<>();
//        JSONArray choiceArray = result.getJSONArray("records");
//        for (int i = 0; i < choiceArray.length(); i++) {
//            options.add( choiceArray.getJSONArray(i));
//        }
//        for (JSONArray sr : options) {
//            try {
//                String num = sr.getString(1).trim();
//                int number = Integer.parseInt(num);
//                String sub =sr.getString(0).trim();
//                String  title =sr.getString(4).trim();
//                aCourse.add(new Course(sub,number,title));
//            }catch (NumberFormatException e){
//            }
//        }
//        return aCourse;
//    }


//    public static void main(String[] args) throws IOException {
//    List<Course> aa = getUVAdata();
//
//    for (Course c: aa){
//        System.out.println(c.toString());
//    }
//
//    }
}