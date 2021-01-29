package org.koala.task.dispatch.executor.slave;

import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.koala.task.dispatch.common.enums.ZnodeTypeEnum;
import org.koala.task.dispatch.executor.Server;
import org.koala.task.dispatch.zookeeper.utils.ZnodeUtil;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class DefaultSlaveInitializeProcess extends SlaveInitializeProcess {

    @Override
    void registerToSlave(Server server) {
        ZnodeUtil.createEphemeralNode(server.getClient(), ZnodeTypeEnum.SLAVES.getPath() + "/" + server.getHost());
    }

    @Override
    void watchToAssign(Server server) {

        CuratorCache cache = CuratorCache.build(server.getClient(), ZnodeTypeEnum.ASSIGN.getPath());

        CuratorCacheListener listener = CuratorCacheListener.builder().forPathChildrenCache(ZnodeTypeEnum.ASSIGN.getPath(), server.getClient(), (c, e) -> {
            System.out.println("--------------------watchToAssign:" + e);
        }).build();

        cache.listenable().addListener(listener);
        cache.start();

        server.getCaches().add(cache);
    }

    @Override
    void unWatch(Server server) {
        Set<CuratorCache> caches = server.getCaches();
        if(caches.size() > 0) {
            for(CuratorCache cache : caches) {
                cache.close();
            }
        }
        caches.clear();
    }
}
