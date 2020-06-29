package com.example.springbootcassandra.cache;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SessionCache  {

    @Getter
    @Autowired
    @Qualifier("Session")
    private Session session;

    private static final long CACHE_SIZE = 1000;

    @Getter
    private Cache<Integer, PreparedStatement> cache = Caffeine.newBuilder()
            .maximumSize(CACHE_SIZE)
            .build();

    public PreparedStatement getPreparedStatement(String cql) {
        int key = cql.hashCode();
        Cache<Integer, PreparedStatement> statementCache = getCache();
        PreparedStatement value = statementCache.getIfPresent(key);
        if (value == null) {
            value = getSession().prepare(cql);
            statementCache.put(key, value);
            log.info("add prepared statement : " + cql);
        }
        return value;
    }
}
