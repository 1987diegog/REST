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
public class CacheHelper<T> {

	private Class<T> cacheType;
	private CacheManager cacheManager;
	private Cache<Long, T> dementeCache;
	private String nameCache;

	/**
	 */
	protected CacheHelper(Class<T> cacheType, String nameCache) {
		this.cacheType = cacheType;
		this.nameCache = nameCache;
	}

	protected void initCacheHelper(int heapSize) {

		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();

		dementeCache = cacheManager.createCache(nameCache, CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Long.class, cacheType, ResourcePoolsBuilder.heap(heapSize)));

	}

	public Cache<Long, T> getDementeCacheFromCacheManager() {
		return cacheManager.getCache(nameCache, Long.class, cacheType);
	}

	protected void add(T data, Long index) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		cache.put(index, data);
	}

	protected T get(Long id) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		return cache.get(id);
	}

	protected T replace(Long id, T data) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		return cache.replace(id, data);
	}

	protected void delete(Long id) {
		Cache<Long, T> cache = getDementeCacheFromCacheManager();
		cache.remove(id);
	}

	protected List<T> getAll() {

		List<Cache.Entry<Long, T>> listEntry = StreamSupport
				.stream(getDementeCacheFromCacheManager().spliterator(), false).collect(Collectors.toList());
		List<T> listObject = listEntry.stream().map(Cache.Entry::getValue).collect(Collectors.toList());

		return listObject;

	}

	protected Cache<Long, T> getDementeCache() {
		return dementeCache;
	}

	protected void setDementeCache(Cache<Long, T> dementeCache) {
		this.dementeCache = dementeCache;
	}
}
