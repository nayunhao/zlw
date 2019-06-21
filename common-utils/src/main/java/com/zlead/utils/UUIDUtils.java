package com.zlead.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class UUIDUtils {
    public static String getOrderIdByUUId(String machineId) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            //有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0 , 13 代表长度为13 , d 代表参数为正数型
        String orderId=machineId + String.format("%013d", hashCodeV);
        System.out.println(orderId);
        return orderId;
    }


    public static void main(String[] args)throws Exception {
        testUUID();
    }

    public static void testUUID() throws Exception {

        int a = "c84ba708-26c8-44c6-beb2-22e95d354b28".hashCode();
        int b = "e1e0da2c-adc3-4b0f-933d-271f5e364739".hashCode();

        System.out.println(a+"========"+b);

        System.out.println(MD5Utils.MD5Encode("c84ba708-26c8-44c6-beb2-22e95d354b28")
                +"========"+MD5Utils.MD5Encode("e1e0da2c-adc3-4b0f-933d-271f5e364739"));


//        HashSet<String> k = new HashSet<String>();
//        HashMap<String, String> kv = new HashMap<String, String>();
//        for (int i = 0; i < 10000000; i++) {
//            String uuid = UUID.randomUUID().toString();
////			int hashCodeV = uuid.hashCode();
////			if (hashCodeV < 0){ //有可能是负数
////				hashCodeV = -hashCodeV;
////			 }
//            // 0 代表前面补充0 , 13 代表长度为13 , d 代表参数为正数型
////			String orderId= String.format("%013d", hashCodeV);
//
//            String orderId = MD5Utils.MD5Encode(uuid);
//            if (k.contains(orderId)) {
//                System.out.println(uuid + "============" + orderId);
//                System.out.println(kv.get(orderId) + "============" + orderId);
//                return;
//            } else {
//                k.add(orderId);
//                kv.put(orderId, uuid);
//                System.out.println("============" + i + "============");
//            }
//        }
    }

}
