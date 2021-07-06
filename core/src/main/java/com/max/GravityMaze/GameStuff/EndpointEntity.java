package com.max.GravityMaze.GameStuff;

import com.max.GravityMaze.Tools.Entity;

public class EndpointEntity extends Entity {
    public EndpointEntity(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
