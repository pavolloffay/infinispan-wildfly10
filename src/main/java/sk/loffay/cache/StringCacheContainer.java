package sk.loffay.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.infinispan.query.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

/**
 * @author Pavol Loffay
 *

<cache-container name="apm" default-cache="local-query" jndi-name="infinispan/APM" module="org.hibernate.infinispan">
    <local-cache name="stringsContainer">
    </local-cache>
</cache-container>

 */
public class StringCacheContainer {
    private static final String CACHE_NAME = "stringsContainer";

    @Resource(lookup = "java:jboss/infinispan/APM")
    private CacheContainer cacheContainer;

    private Cache<String, StringWrapper> cache;

    @PostConstruct
    public void init() {
        cache = cacheContainer.getCache(CACHE_NAME);
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
