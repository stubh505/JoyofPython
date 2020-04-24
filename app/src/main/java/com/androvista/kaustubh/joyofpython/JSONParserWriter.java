package com.androvista.kaustubh.joyofpython;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JSONParserWriter {

    private ArrayList<JSONObject> jsonObjects = new ArrayList<>();
    private File dir;
    void parseJSON() {
        int i;
        try {
            for (i = 0; i < LoadActivity.jsonArray.length(); i++) {
                jsonObjects.add(new JSONObject(LoadActivity.jsonArray.getString(i)));
            }
        } catch (Exception e) { e.printStackTrace(); }
        jsonWriter();
    }

    private void jsonWriter() {
        int i, n = 0;
        File f1, f2, f3;
        PrintWriter pw1, pw2, pw3=null;
        try {

            dir = new File(LoadActivity.dir+"/PDS");
            if (!dir.exists())
                dir.mkdirs();
            f1 = new File(dir, "AllVideos.dat");
            f2 = new File(dir, "AllVideoTitles.dat");
            pw1 = new PrintWriter(new BufferedWriter(new FileWriter(f1)));
            pw2 = new PrintWriter(new BufferedWriter(new FileWriter(f2)));
            for (i = 0; i < jsonObjects.size(); i++ ) {
                if (Integer.parseInt(jsonObjects.get(i).getString("week")) == 0) {
                    pw1.println(jsonObjects.get(i).getString("code"));
                    pw2.println(jsonObjects.get(i).getString("title"));
                } else {
                    n = i;
                    break;
                }
            }
            pw1.close();
            pw2.close();

            dir = new File(LoadActivity.dir+"/JOC");
            if (!dir.exists())
                dir.mkdirs();
            f1 = new File(dir, "AllVideos.dat");
            f2 = new File(dir, "AllVideoTitles.dat");
            pw1 = new PrintWriter(new BufferedWriter(new FileWriter(f1)));
            pw2 = new PrintWriter(new BufferedWriter(new FileWriter(f2)));
            for (i = n; i < jsonObjects.size(); i++ ) {
                if (Integer.parseInt(jsonObjects.get(i).getString("week")) != 0) {
                    pw1.println(jsonObjects.get(i).getString("code"));
                    pw2.println(jsonObjects.get(i).getString("title"));
                }
            }
            pw1.close();
            pw2.close();


            /**
             * Writing files for chapter wise data
             * @var dir is base directory
             * @var f1 is first index file
             * @var f2, f3 are inside indices
             */


            dir = new File(LoadActivity.dir + "/PDS/Chapter Wise");
            if(!dir.exists())
                dir.mkdirs();
            f1 = new File(dir, "AllChapters.dat");
            pw1 = new PrintWriter(new BufferedWriter(new FileWriter(f1)));
            dir = null;
            for (i = 0; i < n; i++ ) {
                if (Integer.parseInt(jsonObjects.get(i).getString("week")) == 0) {
                    dir = new File(LoadActivity.dir + "/PDS/Chapter Wise/" + jsonObjects.get(i).getString("chapter"));
                    if (!dir.exists()) {
                        dir.mkdirs();
                        System.out.println(jsonObjects.get(i).getString("chapter"));
                        pw1.println(jsonObjects.get(i).getString("chapter"));
                        pw2.close();
                        if (pw3 != null)
                            pw3.close();
                        f2 = new File(dir, "AllVideos.dat");
                        pw2 = new PrintWriter(new BufferedWriter(new FileWriter(f2, true)));
                        f3 = new File(dir, "AllVideoTitles.dat");
                        pw3 = new PrintWriter(new BufferedWriter(new FileWriter(f3, true)));

                    }

                    pw2.println(jsonObjects.get(i).getString("code"));
                    pw3.println(jsonObjects.get(i).getString("title"));
                } else break;
            }
            pw1.close();
            pw2.close();
            pw3.close();


            dir = new File(LoadActivity.dir + "/JOC/Chapter Wise");
            f1 = new File(dir, "AllChapters.dat");
            if(!dir.exists())
                dir.mkdirs();
            pw1 = new PrintWriter(new BufferedWriter(new FileWriter(f1)));
            dir = null;
            for (i = n; i < jsonObjects.size(); i++ ) {
                dir = new File(LoadActivity.dir + "/JOC/Chapter Wise/" + jsonObjects.get(i).getString("chapter"));
                if(!dir.exists()) {
                    dir.mkdirs();
                    pw1.println(jsonObjects.get(i).getString("chapter"));
                    pw2.close();
                    if(pw3 != null)
                        pw3.close();
                    f2 = new File(dir, "AllVideos.dat");
                    pw2 = new PrintWriter(new BufferedWriter(new FileWriter(f2, true)));
                    f3 = new File(dir, "AllVideoTitles.dat");
                    pw3 = new PrintWriter(new BufferedWriter(new FileWriter(f3, true)));

                }

                pw2.println(jsonObjects.get(i).getString("code"));
                pw3.println(jsonObjects.get(i).getString("title"));
            }
            pw1.close();
            pw2.close();
            pw3.close();


            /**
             * Writing files for week wise data
             * @var dir is base directory
             * @var f1 is first index file
             * @var f2, f3 are inside indices
             */



            dir = new File(LoadActivity.dir + "/JOC/Week Wise");
            f1 = new File(dir, "AllWeeks.dat");
            if(!dir.exists())
                dir.mkdirs();
            pw1 = new PrintWriter(new BufferedWriter(new FileWriter(f1)));
            dir = null;
            for (i = n; i < jsonObjects.size(); i++ ) {
                if (Integer.parseInt(jsonObjects.get(i).getString("week")) == 0)
                    continue;
                dir = new File(LoadActivity.dir + "/JOC/Week Wise/Week " + jsonObjects.get(i).getString("week"));
                if(!dir.exists()) {
                    dir.mkdirs();
                    pw1.println(jsonObjects.get(i).getString("week"));
                    pw2.close();
                    if(pw3 != null)
                        pw3.close();
                    f2 = new File(dir, "AllVideos.dat");
                    pw2 = new PrintWriter(new BufferedWriter(new FileWriter(f2, true)));
                    f3 = new File(dir, "AllVideoTitles.dat");
                    pw3 = new PrintWriter(new BufferedWriter(new FileWriter(f3, true)));

                }

                pw2.println(jsonObjects.get(i).getString("code"));
                pw3.println(jsonObjects.get(i).getString("title"));
            }
            pw1.close();
            pw2.close();
            pw3.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
