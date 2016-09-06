package sk.loffay.cache;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.query.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

/**
 * @author Pavol Loffay
 */
@Singleton
public class StringCache {

    private static final String CACHE_NAME = "strings";

//    @Resource(lookup = "java:jboss/infinispan/APM")
//    private CacheContainer cacheContainer;

    private Cache<String, StringWrapper> cache;

    @PostConstruct
    public void init() {
        DefaultCacheManager defaultCacheManager = new DefaultCacheManager();
        cache = defaultCacheManager.getCache(CACHE_NAME);


//        cache = cacheContainer.getCache(CACHE_NAME);
    }

    public void store(StringWrapper stringWrapper) {
        cache.put(stringWrapper.getId(), stringWrapper);
    }

    public StringWrapper get(String id) {
        return cache.get(id);
    }

    public List<StringWrapper> query(String str) {

        QueryFactory<?> queryFactory = Search.getQueryFactory(cache);
        Query query = queryFactory.from(StringWrapper.class)
                .having("str")
                .eq(str)
                .toBuilder().build();

        return query.list();
    }
}
