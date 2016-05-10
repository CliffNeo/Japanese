package com.example.japanese;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cliff on 2016/5/4.
 */
public class TestActivityUtil {

    private static LetManage lm = new ping().getPings();
    private static SQLiteDatabase db;
    private static Let let;
    protected static int wrco = 0;


    public static int iniIO(TextView Letter, Button button1,Button button2,Button button3,Button button4
                            ,Let let){
        Letter.setText(let.getSpe());
        List<Button> list = new ArrayList<Button>();
        list.add(button1);
        list.add(button2);
        list.add(button3);
        list.add(button4);
        Random r = new Random();
        int ra = r.nextInt(4)+1;
        switch(ra){
            case 1:
                list.remove(0);
                choiceButton(button1, list,lm,let);
                break;
            case 2:
                list.remove(1);
                choiceButton(button2, list,lm,let);
                break;
            case 3:
                list.remove(2);
                choiceButton(button3, list,lm,let);
                break;
            case 4:
                list.remove(3);
                choiceButton(button4, list,lm,let);
                break;
        }
        return ra;
    }
    private static void choiceButton(Button bu,List<Button> list,LetManage lm,Let right){
        String rp = right.getPro();
        bu.setText(rp);
        for(Button a:list){
            Let ran = lm.random();
            while(rp.contains(ran.getPro())){
                ran = lm.random();
            }
            a.setText(ran.getPro());
            rp += ran.getPro();
        }
    }

    public static int click(boolean rw, Activity con, SQLiteDatabase db,Let let){
        TestActivityUtil.db = db;
        TestActivityUtil.let = let;
        if(rw){
            return TestActivityUtil.wrco;
        }else {
            Toast.makeText(con, "wrong", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    synchronized ("") {
                        ContentValues value = new ContentValues();
                        Cursor result = TestActivityUtil.db.query("ping", new String[]{"wrco"}, "pro=?",
                                new String[]{TestActivityUtil.let.getSpe()}, null, null, null);
                        int count = 0;
                        if (result.moveToFirst()) {
                            do {
                                count = result.getInt(result.getColumnIndex("wrco"));
                            } while (result.moveToNext());
                        }
                        value.put("wrco", ++count);
                        TestActivityUtil.db.update("ping", value, "pro=?", new String[]{TestActivityUtil.let.getSpe()});
                        result.close();
                        TestActivityUtil.wrco++;
                    }
                }
            }).start();
        }
        return -1;
    }
}
