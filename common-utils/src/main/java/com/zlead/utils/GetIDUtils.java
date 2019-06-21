package com.zlead.utils;

public class GetIDUtils {
    public static Long id = 1L;
    public synchronized static String getId(String machineId) {
        /**
         * machineId:集群机器码    2位
         * 13位时间戳
         * 5位自增ID
         */
         //获得时间戳
        long time = System.currentTimeMillis();

        String orderId = machineId+time+String.format("%05d",id++);
        if(id>=99999){
            id=1L;
        }

        return orderId;
    }

}
