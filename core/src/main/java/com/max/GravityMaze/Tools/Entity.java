package com.max.GravityMaze.Tools;

import com.badlogic.gdx.math.Vector2;

public class Entity {
    public Vector2 position = new Vector2();
    public float width;
    public float height;

    public Entity(float x, float y, float width, float height) {
        this.position.x = x;
        this.position.y = y;
        this.width = width;
        this.height = height;
    }

}
