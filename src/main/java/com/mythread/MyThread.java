package com.mythread;


import com.itheima.*;

import java.io.IOException;




public class MyThread implements Runnable {

    private final Integer num;
    private final String funcName;


    public MyThread(Integer num, String funcName) {
        this.num = num ;
        this.funcName = funcName;
    }


    // This method is called when the thread is started.
    @Override
    public void run() {

        // Do the work you want to do in the thread.
        try {

            switch (funcName){
                case "syn_yibai_prod_inf_country_grade":
                    DataMigrateProductToCk.DataMigratesInfCountryToCk(num);
                    break;
                case "syn_yibai_prod_forbidden_grade":
                    ThreadDataMigratesCk.DataMigrates(num);
                    break;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + funcName + " for num " + num + " is running");

    }





}
