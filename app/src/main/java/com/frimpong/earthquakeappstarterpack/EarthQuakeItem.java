package com.frimpong.earthquakeappstarterpack;

public class EarthQuakeItem {
    private String name;
    private String description;
    private String date;

    public EarthQuakeItem(String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public EarthQuakeItem(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EarthQuakeItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
