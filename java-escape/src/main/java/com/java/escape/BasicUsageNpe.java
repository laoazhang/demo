package com.java.escape;


import java.util.ArrayList;
import java.util.List;

/**
 * 字符串、数组、集合在使用时出现空指针
 */
@SuppressWarnings("all")
public class BasicUsageNpe {

    private static boolean stringEquals(String x, String y) {
        return x.equals(y);
    }


    public static class User {
        public String name;
    }
    public static void main(String[] args) {

        // 1.字符串equals 可能会报错空指针
        // System.out.println(stringEquals("xyz",null));
        // System.out.println(stringEquals(null,"xyz"));

        // 2. 对象数组 new 出来，但是元素没有初始化
       // User[] users = new User[10];
       //  for (int i = 0; i !=10; ++i) {
       //      users[i] = new User();
       //      users[i].name = "java-" + i;
       //  }

        // 3. List 对象 addAll 传递 null 会抛出空指针
        List<User> users = new ArrayList<User>();
        User user = null;
        List<User> users_ = null;

        users.add(user);
        users.addAll(users_);
    }
}
