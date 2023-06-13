package com.mega.tweaks.config;

import com.mega.tweaks.consts.NodeRoles;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;

@EnableAsync
@EnableScheduling
@Configuration
@Order(-2)
public class CommonConfig implements SchedulingConfigurer
{

    public static String ENV;
    private       String active;
    public static String NODE_ROLE = "master";
    private       String nodeRole;

    @Bean
    public ThreadPoolExecutor scheduledTaskExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors() * 8;
        return new ScheduledThreadPoolExecutor(nThreads);
    }

    @Bean
    public ThreadPoolExecutor taskExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors() * 2;
        return new ThreadPoolExecutor(nThreads, nThreads, 0, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<>(256), new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean
    public ThreadPoolExecutor nftMasterTaskExecutor() {
        int nThreads = Runtime.getRuntime().availableProcessors() * 8;
        return new ThreadPoolExecutor(nThreads, nThreads, 0, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<>(256), new ThreadPoolExecutor.AbortPolicy());
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduledTaskExecutor());
    }

    @Value("${spring.profiles.active}")
    public void setActiveEnv(String active) {
        ENV = active;
        this.active = active;
    }

    @Value("${metabus.node_role}")
    public void setNodeRole(String nodeRole) {
        NODE_ROLE = nodeRole;
        this.nodeRole = nodeRole;
    }


    public String getActive() {
        return active;
    }

    @Configuration
    static class RedisDatabaseConfig
    {

        @Primary
        @Bean("redisProperties0")
        @ConfigurationProperties(prefix = "spring.redis.db0")
        public RedisProperties redisProperties0() {
            return new RedisProperties();
        }

        @Primary
        @Bean("redisConnectionFactory0")
        LettuceConnectionFactory redisConnectionFactory0(
                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
                ClientResources clientResources,
                @Qualifier("redisProperties0") RedisProperties redisProperties) {
            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(builderCustomizers,
                                                                                    clientResources, redisProperties);
            return createLettuceConnectionFactory(clientConfig, redisProperties);
        }

        @Primary
        @Bean("stringRedisTemplate0")
        public StringRedisTemplate stringRedisTemplate0(
                @Qualifier("redisConnectionFactory0") RedisConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        @Bean("redisProperties3")
        @ConfigurationProperties(prefix = "spring.redis.db3")
        public RedisProperties redisProperties3() {
            return new RedisProperties();
        }

        @Bean("redisConnectionFactory3")
        LettuceConnectionFactory redisConnectionFactory3(
                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
                ClientResources clientResources,
                @Qualifier("redisProperties3") RedisProperties redisProperties) {
            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(builderCustomizers,
                                                                                    clientResources, redisProperties);
            return createLettuceConnectionFactory(clientConfig, redisProperties);
        }

        @Bean("stringRedisTemplate3")
        public StringRedisTemplate stringRedisTemplate3(
                @Qualifier("redisConnectionFactory3") RedisConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        @Bean("redisProperties8")
        @ConfigurationProperties(prefix = "spring.redis.db8")
        public RedisProperties redisProperties8() {
            return new RedisProperties();
        }

        @Bean("redisConnectionFactory8")
        LettuceConnectionFactory redisConnectionFactory8(
                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
                ClientResources clientResources,
                @Qualifier("redisProperties8") RedisProperties redisProperties) {
            LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(builderCustomizers,
                                                                                    clientResources, redisProperties);
            return createLettuceConnectionFactory(clientConfig, redisProperties);
        }

        @Bean("stringRedisTemplate8")
        public StringRedisTemplate stringRedisTemplate8(
                @Qualifier("redisConnectionFactory8") RedisConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        private LettuceConnectionFactory createLettuceConnectionFactory(LettuceClientConfiguration clientConfiguration,
                                                                        RedisProperties redisProperties) {
            RedisSentinelConfiguration sentinelConfig = getSentinelConfig(redisProperties);
            if (sentinelConfig != null) {
                return new LettuceConnectionFactory(sentinelConfig, clientConfiguration);
            }
            RedisClusterConfiguration clusterConfiguration = getClusterConfiguration(redisProperties);
            if (clusterConfiguration != null) {
                return new LettuceConnectionFactory(clusterConfiguration, clientConfiguration);
            }
            return new LettuceConnectionFactory(getStandaloneConfig(redisProperties), clientConfiguration);
        }

        private RedisSentinelConfiguration getSentinelConfig(RedisProperties redisProperties) {
            RedisProperties.Sentinel sentinelProperties = redisProperties.getSentinel();
            if (sentinelProperties != null) {
                RedisSentinelConfiguration config = new RedisSentinelConfiguration();
                config.master(sentinelProperties.getMaster());
                config.setSentinels(createSentinels(sentinelProperties));
                if (redisProperties.getPassword() != null) {
                    config.setPassword(RedisPassword.of(redisProperties.getPassword()));
                }
                if (sentinelProperties.getPassword() != null) {
                    config.setSentinelPassword(RedisPassword.of(sentinelProperties.getPassword()));
                }
                config.setDatabase(redisProperties.getDatabase());
                return config;
            }
            return null;
        }

        private RedisClusterConfiguration getClusterConfiguration(RedisProperties redisProperties) {
            if (redisProperties.getCluster() == null) {
                return null;
            }
            RedisProperties.Cluster   clusterProperties = redisProperties.getCluster();
            RedisClusterConfiguration config            = new RedisClusterConfiguration(clusterProperties.getNodes());
            if (clusterProperties.getMaxRedirects() != null) {
                config.setMaxRedirects(clusterProperties.getMaxRedirects());
            }
            if (redisProperties.getPassword() != null) {
                config.setPassword(RedisPassword.of(redisProperties.getPassword()));
            }
            return config;
        }

        private RedisStandaloneConfiguration getStandaloneConfig(RedisProperties redisProperties) {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(redisProperties.getHost());
            config.setPort(redisProperties.getPort());
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
            config.setDatabase(redisProperties.getDatabase());
            return config;
        }

        private List<RedisNode> createSentinels(RedisProperties.Sentinel sentinel) {
            List<RedisNode> nodes = new ArrayList<>();
            for (String node : sentinel.getNodes()) {
                try {
                    String[] parts = StringUtils.split(node, ":");
                    Assert.state(parts.length == 2, "Must be defined as 'host:port'");
                    nodes.add(new RedisNode(parts[0], Integer.parseInt(parts[1])));
                } catch (RuntimeException ex) {
                    throw new IllegalStateException("Invalid redis sentinel property '" + node + "'", ex);
                }
            }
            return nodes;
        }

        private LettuceClientConfiguration getLettuceClientConfiguration(
                ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers,
                ClientResources clientResources, RedisProperties redisProperties) {
            LettuceClientConfiguration.LettuceClientConfigurationBuilder builder =
                    createBuilder(redisProperties.getLettuce().getPool());
            applyProperties(builder, redisProperties);
            builder.clientOptions(initializeClientOptionsBuilder(redisProperties).timeoutOptions(TimeoutOptions.enabled()).build());
            builder.clientResources(clientResources);
            builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
            return builder.build();
        }

        private ClientOptions.Builder initializeClientOptionsBuilder(RedisProperties redisProperties) {
            if (redisProperties.getCluster() != null) {
                ClusterClientOptions.Builder builder = ClusterClientOptions.builder();
                RedisProperties.Lettuce.Cluster.Refresh refreshProperties =
                        redisProperties.getLettuce().getCluster().getRefresh();
                ClusterTopologyRefreshOptions.Builder refreshBuilder = ClusterTopologyRefreshOptions.builder();
                if (refreshProperties.getPeriod() != null) {
                    refreshBuilder.enablePeriodicRefresh(refreshProperties.getPeriod());
                }
                if (refreshProperties.isAdaptive()) {
                    refreshBuilder.enableAllAdaptiveRefreshTriggers();
                }
                return builder.topologyRefreshOptions(refreshBuilder.build());
            }
            return ClientOptions.builder();
        }

        private void applyProperties(LettuceClientConfiguration.LettuceClientConfigurationBuilder builder,
                                     RedisProperties redisProperties) {
            if (redisProperties.isSsl()) {
                builder.useSsl();
            }
            if (redisProperties.getTimeout() != null) {
                builder.commandTimeout(redisProperties.getTimeout());
            }
            if (redisProperties.getLettuce() != null) {
                RedisProperties.Lettuce lettuce = redisProperties.getLettuce();
                if (lettuce.getShutdownTimeout() != null && !lettuce.getShutdownTimeout().isZero()) {
                    builder.shutdownTimeout(redisProperties.getLettuce().getShutdownTimeout());
                }
            }
            if (StringUtils.hasText(redisProperties.getClientName())) {
                builder.clientName(redisProperties.getClientName());
            }
        }

        private LettuceClientConfiguration.LettuceClientConfigurationBuilder createBuilder(RedisProperties.Pool pool) {
            if (pool == null) {
                return LettuceClientConfiguration.builder();
            }
            return LettucePoolingClientConfiguration.builder().poolConfig(getPoolConfig(pool));
        }

        private GenericObjectPoolConfig<?> getPoolConfig(RedisProperties.Pool properties) {
            GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig<>();
            config.setMaxTotal(properties.getMaxActive());
            config.setMaxIdle(properties.getMaxIdle());
            config.setMinIdle(properties.getMinIdle());
            if (properties.getTimeBetweenEvictionRuns() != null) {
                config.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRuns().toMillis());
            }
            if (properties.getMaxWait() != null) {
                config.setMaxWaitMillis(properties.getMaxWait().toMillis());
            }
            return config;
        }

        /**
         * 是否master node
         *
         * @return
         */
        public static boolean isMaster() {
            if (NODE_ROLE.equals(NodeRoles.MASTER)) {
                return true;
            }
            return false;
        }
    }
}
