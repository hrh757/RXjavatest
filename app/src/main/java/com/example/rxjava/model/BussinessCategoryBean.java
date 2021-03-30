package com.example.rxjava.model;

public class BussinessCategoryBean {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


//    @SerializedName(value = "id",alternate = {"id","type_id"})
        private int id;
//    @SerializedName(value = "name", alternate = {"name","type_name"})

        private String name;

        private int type_id;

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        private String type_name;
         private  int num;




}
