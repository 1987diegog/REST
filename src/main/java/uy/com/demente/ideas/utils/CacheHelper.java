package uy.com.demente.ideas.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
public class CacheHelper<T> {

	private Class<T> cacheType;
	private CacheManager cacheManager;
	private Cache<Long, T> dementeCache;
	private String nameCache;
	private static Long sequence;

	/**
	 */
	public CacheHelper(Class<T> cacheType, String nameCache) {
		this.cacheType = cacheType;
		this.nameCache = nameCache;
	}

	public void initCacheHelper(int heapSize) {

		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();

		dementeCache = cacheManager.createCache(nameCache, CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Long.class, cacheType, ResourcePoolsBuilder.heap(heapSize)));

		sequence = 0L;
	}

	public static Long getSequence() {
		return sequence++;

	}

	public Cache<Long, T> getDementeCacheFromCacheManager() {
		return cacheManager.getCache(nameCache, Long.class, cacheType);
	}

	public void add(T data) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		cache.put(sequence, data);
	}

	public T get(Long id) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		return cache.get(id);
	}

	public T replace(Long id, T data) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		return cache.replace(id, data);
	}

	public void delete(Long id) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		cache.remove(id);
	}

	public List<T> getAll() {

		List<Cache.Entry<Long, T>> listEntry = StreamSupport
				.stream(getDementeCacheFromCacheManager().spliterator(), false).collect(Collectors.toList());
		List<T> listObject = listEntry.stream().map(Cache.Entry::getValue).collect(Collectors.toList());

		return listObject;

	}

	public Cache<Long, T> getDementeCache() {
		return dementeCache;
	}

	public void setDementeCache(Cache<Long, T> dementeCache) {
		this.dementeCache = dementeCache;
	}
}
