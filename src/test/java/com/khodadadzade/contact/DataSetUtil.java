package com.khodadadzade.contact;

import java.io.*;

public class DataSetUtil {

    public String getJsonSample(String fileName){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            File file = new File(this.getClass().getClassLoader().getResource("dataset/"+fileName).getPath());

            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            StringBuilder stringBuilder = new StringBuilder();
            while ((st = br.readLine()) != null)
                stringBuilder.append(st);
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
