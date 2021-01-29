package org.koala.task.dispatch.zookeeper.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

public class ZnodeUtil {

    public static void createPersistentNode(CuratorFramework client, String path) {
        try {
            client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createEphemeralNode(CuratorFramework client, String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPersistentSeqNode(CuratorFramework client, String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPersistentSeqNode(CuratorFramework client, String path, String data) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createEphemeralSqlNode(CuratorFramework client, String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteNode(CuratorFramework client, String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkExistNode(CuratorFramework client, String path) {
        try {
            return client.checkExists().forPath(path) != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
