package com.example.springbootcassandra.config;

import com.datastax.driver.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
        basePackages = "com.example.springbootcassandra.repository",
        cassandraTemplateRef = "cassandraTemplate")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Override
    @Primary
    @Bean(name = "cassandraTemplate")
    public CassandraAdminTemplate cassandraTemplate() {
        Session session = session().getObject();
        return new CassandraAdminTemplate(session, cassandraConverter());
    }

    @Override
    @Bean(name = "Port")
    public int getPort() {
        return port;
    }

    @Override
    @Bean(name = "KeySpace")
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Bean(name = "cluster")
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        PlainTextAuthProvider plainTextAuthProvider = new PlainTextAuthProvider(username,password);

        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setAuthProvider(plainTextAuthProvider);
        cluster.setQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.LOCAL_ONE));
        cluster.setSocketOptions(new SocketOptions().setReadTimeoutMillis(5000));
        cluster.setRetryPolicy(getRetryPolicy());
        cluster.setPoolingOptions(new PoolingOptions().setMaxRequestsPerConnection(HostDistance.LOCAL, 10));
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }

    @Override
    @Bean(name = "Session")
    public CassandraSessionFactoryBean session() {

        CassandraSessionFactoryBean session = super.session();
        session.setKeyspaceName(this.getKeyspaceName());

        return session;
    }
}
