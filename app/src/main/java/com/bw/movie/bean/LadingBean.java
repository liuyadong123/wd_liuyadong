package com.bw.movie.bean;

public class LadingBean {



    public ResultBean result;
    public String message;
    public String status;



    public static class ResultBean {

        public String sessionId;
        public int userId;
        public UserInfoBean userInfo;


        public static class UserInfoBean {


            public long birthday;
            public String headPic;
            public int id;
            public long lastLoginTime;
            public String nickName;
            public String phone;
            public int sex;

        }
    }
}
