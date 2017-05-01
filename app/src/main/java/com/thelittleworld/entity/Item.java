package com.thelittleworld.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(
        nameInDb = "items"
)
public class Item {

    @Id
    @Property(nameInDb = "id")
    public Long id;

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

    @Generated(hash = 1201866111)
    public Item(Long id, String name, String description, String type,
                Double weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.weight = weight;
    }

    @Generated(hash = 1470900980)
    public Item() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}