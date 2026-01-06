package utils;

import com.google.gson.Gson;

import datamodels.sales.PreSalesData;
import datamodels.users.UserEMPRoles;

import java.io.InputStreamReader;

public class JsonDataReader {

    private static final Gson gson = new Gson();

    public static UserEMPRoles readUsers() {
        return gson.fromJson(
                new InputStreamReader(
                        JsonDataReader.class
                                .getClassLoader()
                                .getResourceAsStream("testdata/users.json")
                ),
                UserEMPRoles.class
        );
    }

    public static PreSalesData readSalesData() {
        return gson.fromJson(
                new InputStreamReader(
                        JsonDataReader.class
                                .getClassLoader()
                                .getResourceAsStream("testdata/sales/lead-flow.json")
                ),
                PreSalesData.class
        );
    }
}
