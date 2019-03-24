package com.example.dong.wd_liuyadong.bean;

import java.util.List;

public class LobbyInfo {
    public String message;
    public String status;
    public List<Result> result;
    public class Result{
        public String beginTime;
        public String duration;
        public String endTime;
        public double price;
        public String screeningHall;
        public int seatsTotal;
        public int seatsUseCount;
        public int status;
    }
}
