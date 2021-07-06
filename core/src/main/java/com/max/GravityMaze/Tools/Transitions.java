package com.max.GravityMaze.Tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

public interface Transitions {
    public void TransitionMethod(float delta, OrthographicCamera cam, Viewport viewport, Sprite endpoint, float angle);
}
