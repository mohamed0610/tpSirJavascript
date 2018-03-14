package fr.TpMorphia.TpMorphia;

import redis.clients.jedis.Jedis;

public class app2 {

	public static void main(String[] args) {
		String cacheKey = "cachekey";
        Jedis jedis = new Jedis("localhost");
        // adding a new key
        jedis.set(cacheKey, "cached value");
        // setting the TTL in seconds
        jedis.expire(cacheKey, 15);
        // Getting the remaining ttl
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        // Getting the cache value
        System.out.println("Cached Value:" + jedis.get(cacheKey));

        // Wait for the TTL finishs
        try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // trying to get the expired key
        System.out.println("Expired Key:" + jedis.get(cacheKey));
	}

}
