package com.libraries;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class BusPassWalletDaoGenerator {

    private static final String MODEL_PACKAGE_NAME = "com.microsoft.hack.buspasswallet.database";
    private static final int DB_VERSION = 1;

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(DB_VERSION, MODEL_PACKAGE_NAME);

        Entity user = schema.addEntity("User");
        user.addIdProperty();
        user.addStringProperty("name");
        user.addIntProperty("age");
        user.addStringProperty("photoUri");
        user.addStringProperty("phone");
        user.addStringProperty("password");

        Entity pass = schema.addEntity("Pass");
        pass.addIdProperty();
        pass.addDateProperty("validFrom");
        pass.addDateProperty("validTo");
        pass.addIntProperty("type");

        Property userId = pass.addLongProperty("userId").getProperty();
        pass.addToOne(user, userId);

        //new DaoGenerator().generateAll(schema, "../../../../../../app/src/main/java/com/microsoft/hack/buspasswallet/models");
        //TODO - files generated in D. check if they can be changed
        new DaoGenerator().generateAll(schema, "D:\\");
    }
}
