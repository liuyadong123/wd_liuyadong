package com.bw.movie.bean;

import java.util.List;

public class WeiLoginInfo {
    public String message;
    public String status;
    public Result result;
    public class Result{
        public String sessionId;
        public String userId;
        public UserInfo userInfo;
        public class UserInfo{
            public String birthday;
            public String id;
            public long lastLoginTime;
            public String nickName;
            public String phone;
            public int sex;
            public String headPic;
        }
    }
}
