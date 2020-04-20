package com.company.service;

import com.company.utils.CSVUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditService {
    private List<String> audit;
    private static AuditService INSTANCE;
    final static String filePath = System.getProperty("user.dir") + "/data/audit.csv";

    public AuditService() {
        audit = new ArrayList<>();
    }

    public static AuditService getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new AuditService();
        return INSTANCE;
    }

    public void addToAudit(String actiune){
        FileWriter fileWriter;
        boolean appendDataAtTheEnd = true;
        try {
            fileWriter = new FileWriter(filePath, appendDataAtTheEnd);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Date date = ts;
            String d = date.toString();
            List<String> string = new ArrayList<>();
            string.add(actiune);
            string.add(d);

            CSVUtils.writeLine(fileWriter,string);

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
