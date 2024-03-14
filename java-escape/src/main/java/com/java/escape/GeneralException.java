package com.java.escape;

import com.google.common.base.Enums;

import java.util.*;

/**
 * 编码中的常见的异常
 */
@SuppressWarnings("all")
public class GeneralException {

   private static class User{

       public User(){}
       private String name;

       public User(String name){
           this.name = name;
       }
       public String getName(){
           return name;
       }
   }

   public static class Manager extends User{}
    public static class Worker extends User{}

    private static final Map<String,StaffType> typeIndex = new HashMap<>(
            StaffType.values().length
    );

   static {
       for (StaffType staffType : StaffType.values()) {
           typeIndex.put(staffType.name(),staffType);
       }
   }

   private static void concurrentModificationException(ArrayList<User> users){
        // 直接使用 for 循环会出发并发修改异常
        //  for (User user : users) {
        //       if (user.getName().equals("Tom")){
        //         users.remove(user);
        //       }
        //  }

       // 使用迭代器则没有问题
       Iterator<User> iterator = users.iterator();
       while (iterator.hasNext()) {
           User user = iterator.next();
           if (user.getName().equals("Tom")) {
               iterator.remove();
           }
       }
   }

   private static StaffType enumFind(String type) {
       // return StaffType.valueOf(type);

       // 1. 最普通、最简单的实现
       // try {
       //     return StaffType.valueOf(type);
       // } catch (IllegalArgumentException e) {
       //     return null;
       // }

       // 2. 改进的实现
       // for(StaffType staffType : StaffType.values()){
       //     if (staffType.name().equals(type)){
       //         return staffType;
       //     }
       // }
       // return null;

       // 3. 静态 Map 实现，只有一次循环枚举的过程
       // return typeIndex.get(type);

       // 4. 使用 Google Guava Enums，需要相关的依赖
       return Enums.getIfPresent(StaffType.class,type).orNull();
   }

    public static void main(String[] args) {
       // 1. 并发修改异常
        // ArrayList<User> users = new ArrayList<>(Arrays.asList(new User("qinyi"),new User("Tom")));
        // concurrentModificationException(users);

        // 2. 类型转换异常
        // User user1 = new Manager();
        // User user2 = new Worker();

        // Manager m1 = (Manager) user1;
        // Manager m2 = (Manager) user2;

        // System.out.println(user2.getClass().getName());
        // System.out.println(user2 instanceof Manager);

        // 3. 枚举查找异常
        System.out.println(enumFind("RD"));
        System.out.println(enumFind("adc"));

    }
}
