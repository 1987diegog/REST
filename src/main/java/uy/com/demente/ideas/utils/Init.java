package uy.com.demente.ideas.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Init {

	public Init() {
	}

	@PostConstruct
	public void loadDataApp() {

		CacheHelper cache = CacheHelper.getSingletonCacheHelper();
		cache.loadData();
	}
}
