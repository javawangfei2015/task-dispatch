package org.koala.task.dispatch.executor.leader;

import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.koala.task.dispatch.common.enums.ZnodeTypeEnum;
import org.koala.task.dispatch.executor.Server;
import org.koala.task.dispatch.zookeeper.utils.ZnodeUtil;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DefaultLeaderInitializeProcess extends LeaderInitializeProcess {

    @Override
    void unRegisterToSlave(Server server) {
        if(ZnodeUtil.checkExistNode(server.getClient(), ZnodeTypeEnum.SLAVES.getPath() + "/" + server.getHost())) {
            ZnodeUtil.deleteNode(server.getClient(), ZnodeTypeEnum.SLAVES.getPath() + "/" + server.getHost());
        }
    }

    @Override
    void watchTasksNode(Server server) {

        CuratorCache cache = CuratorCache.build(server.getClient(), ZnodeTypeEnum.TASKS.getPath());

        CuratorCacheListener listener = CuratorCacheListener.builder().forPathChildrenCache(ZnodeTypeEnum.TASKS.getPath(), server.getClient(), (c, e) -> {
            if(e.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
                // TODO show task
            }
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
