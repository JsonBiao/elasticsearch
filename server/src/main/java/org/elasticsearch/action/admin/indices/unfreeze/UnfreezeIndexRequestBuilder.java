/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */

package org.elasticsearch.action.admin.indices.unfreeze;

import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedRequestBuilder;
import org.elasticsearch.client.ElasticsearchClient;

/**
 * Builder for unfreeze index request
 */
public class UnfreezeIndexRequestBuilder extends
    AcknowledgedRequestBuilder<UnfreezeIndexRequest, UnfreezeIndexResponse, UnfreezeIndexRequestBuilder> {

    public UnfreezeIndexRequestBuilder(ElasticsearchClient client, UnfreezeIndexAction action) {
        super(client, action, new UnfreezeIndexRequest());
    }

    public UnfreezeIndexRequestBuilder(ElasticsearchClient client, UnfreezeIndexAction action, String... indices) {
        super(client, action, new UnfreezeIndexRequest(indices));
    }

    /**
     * Sets the indices to be unfrozen
     *
     * @param indices the indices to be unfrozen
     * @return the request itself
     */
    public UnfreezeIndexRequestBuilder setIndices(String... indices) {
        request.indices(indices);
        return this;
    }

    /**
     * Specifies what type of requested indices to ignore and wildcard indices expressions
     * For example indices that don't exist.
     *
     * @param indicesOptions the desired behaviour regarding indices to ignore and indices wildcard expressions
     * @return the request itself
     */
    public UnfreezeIndexRequestBuilder setIndicesOptions(IndicesOptions indicesOptions) {
        request.indicesOptions(indicesOptions);
        return this;
    }

    /**
     * Sets the number of shard copies that should be active for indices unfreezing to return.
     * Defaults to {@link ActiveShardCount#DEFAULT}, which will wait for one shard copy
     * (the primary) to become active. Set this value to {@link ActiveShardCount#ALL} to
     * wait for all shards (primary and all replicas) to be active before returning.
     * Otherwise, use {@link ActiveShardCount#from(int)} to set this value to any
     * non-negative integer, up to the number of copies per shard (number of replicas + 1),
     * to wait for the desired amount of shard copies to become active before returning.
     * Indices unfreezing will only wait up until the timeout value for the number of shard copies
     * to be active before returning.  Check {@link UnfreezeIndexResponse#isShardsAcknowledged()} to
     * determine if the requisite shard copies were all started before returning or timing out.
     *
     * @param waitForActiveShards number of active shard copies to wait on
     */
    public UnfreezeIndexRequestBuilder setWaitForActiveShards(ActiveShardCount waitForActiveShards) {
        request.waitForActiveShards(waitForActiveShards);
        return this;
    }

    /**
     * A shortcut for {@link #setWaitForActiveShards(ActiveShardCount)} where the numerical
     * shard count is passed in, instead of having to first call {@link ActiveShardCount#from(int)}
     * to get the ActiveShardCount.
     */
    public UnfreezeIndexRequestBuilder setWaitForActiveShards(final int waitForActiveShards) {
        return setWaitForActiveShards(ActiveShardCount.from(waitForActiveShards));
    }
}
