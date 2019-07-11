package com.github.chenhao96.utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ReadJsonFileUtil {

    private JsonNode node;

    private long lastUpdateTime = -1;

    private String fileName;

    private boolean refreshed;

    private static final Map<String, ReadJsonFileUtil> cacheMap = new HashMap<>();

    private ReadJsonFileUtil(String fileName) {
        this.fileName = fileName;
        this.refreshed = false;
        this.refreshed = refreshJsonFile();
    }

    public static synchronized ReadJsonFileUtil newInstance(String name) {
        ReadJsonFileUtil util = cacheMap.get(name);
        if (util == null) {
            util = new ReadJsonFileUtil(name);
            cacheMap.put(name, util);
        }
        return util;
    }

    public static synchronized void removeInstance(String name) {
        cacheMap.remove(name);
    }

    public boolean refreshJsonFile() {
        String parent = ReadJsonFileUtil.class.getResource("/").getPath();
        File merchandise = new File(parent, this.fileName + ".json");
        long modified = merchandise.lastModified();
        if (this.lastUpdateTime != modified) {
            this.lastUpdateTime = modified;
            return readJsonFile(merchandise);
        }
        return false;
    }

    private boolean readJsonFile(File file) {
        try {
            String json = Utils.file2String(file);
            this.node = JsonUtils.jsonStr2JsonNode(json);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean isRefreshed() {
        return refreshed;
    }

    public JsonNode getNode() {
        return getNode(false);
    }

    public JsonNode getNode(boolean refresh) {
        this.refreshed = false;
        if (refresh) {
            this.refreshed = refreshJsonFile();
        }
        return node;
    }
}
