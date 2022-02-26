package com.nicolas.bahamut.organizze.helper;

import java.text.SimpleDateFormat;

public class DateUtil {
    public static String dataAtual() {
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static String mesAnoDataEscolhida (String data) {
        String retornoData [] = data.split("/");
        return retornoData[1] + retornoData[2];
    }
}
