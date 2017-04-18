package com.thelittleworld.entity;

public class Item {

    public Integer id;

    public String name;

    public String description;

    public String type;

    public Double weight;

    public static final String TABLE_NAME = "items";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_WEIGHT = "weight";

    public static final String SQL_CREATE =
            "CREATE TABLE IF NOT EXISTS " + Item.TABLE_NAME + " (" +
                    Item.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    Item.COLUMN_NAME + " TEXT," +
                    Item.COLUMN_DESCRIPTION + " TEXT," +
                    Item.COLUMN_TYPE + " TEXT," +
                    Item.COLUMN_WEIGHT + " REAL" +
                    ")";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + Item.TABLE_NAME;
}