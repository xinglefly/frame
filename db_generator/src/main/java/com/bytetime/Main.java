package com.bytetime;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    private static final String DB_PACKAGE = "com.bytetime.jrim.data.database";
    private static final String CODE_PATH = "library/src/main/java";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, DB_PACKAGE);
        addUserEntity(schema);
        addMessageEntity(schema);

        //Empty generate code folder to clear old entities
        clearOldEntities();

        new DaoGenerator().generateAll(schema, CODE_PATH);
    }

    private static void addUserEntity(Schema schema) {
        Entity user = schema.addEntity("UserInfo");
        user.addIdProperty();
        user.addStringProperty("userId").notNull();
        user.addStringProperty("easemobId").notNull();
        user.addStringProperty("avatar");
        user.addStringProperty("nickName").notNull();
    }

    private static void addMessageEntity(Schema schema) {
        Entity message = schema.addEntity("Message");
        message.addIdProperty();
        message.addStringProperty("msgId").notNull();
        message.addStringProperty("from").notNull();
        message.addStringProperty("content").notNull();
        message.addDateProperty("receiveTime").notNull();
    }

    private static void clearOldEntities() {
        File packagePath = new File(CODE_PATH, DB_PACKAGE.replace('.', '/'));
        if(!packagePath.exists())
        {
            System.out.println("Package path not exist, Path: " + packagePath.toString());
            return;
        }
        deleteFolder(packagePath);
        System.out.println("Old entities deleted.");
    }

    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files != null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

}
