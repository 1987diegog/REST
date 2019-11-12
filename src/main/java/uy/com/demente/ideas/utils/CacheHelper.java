package uy.com.demente.ideas.utils;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * 
 * @author diego.gonzalezdurand
 *
 */
public class CacheHelper {

	private static CacheHelper cacheHelper;

	private CacheManager cacheManager;
	private Cache<Integer, String> dementeCache;

	private CacheHelper() {

		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();

		dementeCache = cacheManager.createCache("dementeCache", CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Integer.class, String.class, ResourcePoolsBuilder.heap(10)));
	}

	public static CacheHelper getSingletonCacheHelper() {

		if (cacheHelper == null) {
			cacheHelper = new CacheHelper();
		}

		return cacheHelper;
	}

	public Cache<Integer, String> getDementeCacheFromCacheManager() {
		return cacheManager.getCache("dementeCache", Integer.class, String.class);
	}

	public void add(Integer id, String data) {
		dementeCache.put(id, data);
	}

	public String get(Integer id) {
		return dementeCache.get(id);
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public Cache<Integer, String> getDementeCache() {
		return dementeCache;
	}

	public void setDementeCache(Cache<Integer, String> dementeCache) {
		this.dementeCache = dementeCache;
	}

}
