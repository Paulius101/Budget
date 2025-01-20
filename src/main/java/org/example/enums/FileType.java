package org.example.enums;

public enum FileType {
    CSV(0, "CSV"),
    JSON(1, "JSON");

    private int id;
    private String value;

    FileType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
