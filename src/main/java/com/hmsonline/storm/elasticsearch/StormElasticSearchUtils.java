package com.hmsonline.storm.elasticsearch;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.FailedException;

//
// Copyright (c) 2013 Health Market Science, Inc.
//

public class StormElasticSearchUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StormElasticSearchUtils.class);

    public static void handleElasticSearchException(Class<?> clazz, ElasticsearchException e) {
        if (RestStatus.BAD_REQUEST.equals(e.status())) {
            LOGGER.error(clazz.getCanonicalName() + " - Bad request: " + e.getMessage() + ". DO NOT RETRY!");
        } else {
            throw new FailedException("Cannot create index via ES: " + e.getMessage());
        }
    }
}
