package uy.com.demente.ideas.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author 1987diegog
 */
public class CacheHelper<K, V> {

	private Class<K> cacheKey;
	private Class<V> cacheValue;
	private CacheManager cacheManager;
	private Cache<K, V> dementeCache;
	private String nameCache;

	/**
	 */
	protected CacheHelper(Class<K> cacheKey, Class<V> cacheValue, String nameCache) {
		this.cacheKey = cacheKey;
		this.cacheValue = cacheValue;
		this.nameCache = nameCache;
	}

	protected void initCacheHelper(int heapSize) {

		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();

		dementeCache = cacheManager.createCache(nameCache, CacheConfigurationBuilder
				.newCacheConfigurationBuilder(cacheKey, cacheValue, ResourcePoolsBuilder.heap(heapSize)));

	}

	public Cache<K, V> getDementeCacheFromCacheManager() {
		return cacheManager.getCache(nameCache, cacheKey, cacheValue);
	}

	protected void add(K key, V value) {
		Cache<K, V> cache = getDementeCacheFromCacheManager();
		cache.put(key, value);
	}

	protected V get(K key) {
		Cache<K, V> cache = getDementeCacheFromCacheManager();
		return cache.get(key);
	}

	protected V replace(K key, V value) {
		Cache<K, V> cache = getDementeCacheFromCacheManager();
		return cache.replace(key, value);
	}

	protected void delete(K key) {
		Cache<K, V> cache = getDementeCacheFromCacheManager();
		cache.remove(key);
	}

	protected List<V> getAll() {

		List<Cache.Entry<K, V>> listEntry = StreamSupport.stream(getDementeCacheFromCacheManager().spliterator(), false)
				.collect(Collectors.toList());

		List<V> listObject = listEntry.stream().map(Cache.Entry::getValue).collect(Collectors.toList());

		return listObject;
	}

	protected Cache<K, V> getDementeCache() {
		return dementeCache;
	}

	protected void setDementeCache(Cache<K, V> dementeCache) {
		this.dementeCache = dementeCache;
	}
}
