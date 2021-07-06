package com.max.GravityMaze.Tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OpeningTransition implements Transitions{
    float time = 0;
    public boolean cameraTransitioned = false;

    @Override
    public void TransitionMethod(float delta, OrthographicCamera cam, Viewport viewport, Sprite start, float angle){
        time += delta;
        if ( time > 1.69f) {
            if (viewport.getWorldWidth() > 200) {
                viewport.setWorldWidth(viewport.getWorldWidth() - 5);
            }
            if (viewport.getWorldHeight() > 200) {
                viewport.setWorldHeight(viewport.getWorldHeight() - 5);
            }
        if(!cameraTransitioned) {
            if ((start.getX() + start.getWidth() / 2) - cam.position.x > 6 || start.getX() - cam.position.x < -6
                    && start.getY() - cam.position.y > 6 || start.getY() - cam.position.y < -6) {
                cam.translate(MathUtils.cosDeg(angle) * 5, MathUtils.sinDeg(angle) * 5);
            } else {
                cam.position.set(start.getX() + start.getWidth() / 2, start.getY() + start.getHeight() / 2, 0);
                cameraTransitioned = true;
                }
            }
        }

    }


}
