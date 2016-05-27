/*
 * Copyright (c) 2016 deltaDNA Ltd. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deltadna.android.sdk;

import android.support.annotation.Nullable;

import com.deltadna.android.sdk.helpers.Objects;
import com.deltadna.android.sdk.net.Response;

import org.json.JSONObject;

/**
 * Constructs an engagement {@link Event}.
 */
public class Engagement<T extends Engagement<T>> extends Event<T> {
    
    @Nullable
    final String flavour;
    
    private Response<JSONObject> response;
    private int statusCode;
    @Nullable
    private JSONObject json;
    @Nullable
    private String error;
    
    /**
     * Creates a new instance.
     *
     * @param decisionPoint the decision point
     */
    public Engagement(String decisionPoint) {
        this(decisionPoint, null);
    }
    
    /**
     * Creates a new instance, with a flavour.
     *
     * @param decisionPoint the decision point
     * @param flavour       the flavour, may be {@code null}
     */
    public Engagement(String decisionPoint, @Nullable String flavour) {
        this(decisionPoint, flavour, new Params());
    }
    
    public Engagement(
            String decisionPoint,
            @Nullable String flavour,
            Params params) {
        
        super(decisionPoint, params);
        
        this.flavour = flavour;
    }
    
    @Override
    public T putParam(String key, JsonParams value) {
        return super.putParam(key, value);
    }
    
    @Override
    public T putParam(String key, Object value) {
        return super.putParam(key, value);
    }
    
    @Override
    public String toString() {
        return new Objects.ToStringHelper(this)
                .add("decisionPoint", name)
                .add("flavour", flavour)
                .add("params", params)
                .add("response", response)
                .toString();
    }
    
    /**
     * Gets the status code of the response after the Engage request has
     * completed.
     *
     * @return  the status code of the response, or {@code 0} if the request
     *          hasn't completed yet
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    /**
     * Gets whether the response was successful after the Engage request has
     * completed.
     *
     * @return {@code true} if the response was a success, else {@code false}
     */
    public boolean isSuccessful() {
        return (statusCode >= 200 && statusCode < 300);
    }
    
    /**
     * Gets the JSON body of the response after the Engage request has
     * completed with a success.
     *
     * @return  the JSON body of the response, or {@code null} if the request
     *          failed
     */
    @Nullable
    public JSONObject getJson() {
        return json;
    }
    
    /**
     * Gets the error message of the response after the Engage request has
     * completed with a failure.
     *
     * @return  the error message of the response, or {@code null} if the
     *          request succeeded
     */
    @Nullable
    public String getError() {
        return error;
    }
    
    public String getDecisionPoint() {
        return name;
    }
    
    void setResponse(Response<JSONObject> response) {
        this.response = response;
        // unpack response for easy access
        this.statusCode = response.code;
        this.json = response.body;
        this.error = response.error;
    }
}
