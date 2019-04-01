package com.bw.movie.bean;

import java.util.List;

public class MovieBean {
    public String message;
    public String status;
    public List<Result> result;
    public class Result{
        public String address;
        public String logo;
        public String name;
        public  int id;
        public String followCinema;
        public String commentTotal;
        public String distance;
    }
}
