package com.max.GravityMaze.Loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Loader {

    public AssetManager assetManager;
    private LoadingPaths loadingPaths = new LoadingPaths();

    public Loader(AssetManager assetManager) {
        this.assetManager = assetManager;
        // only needed once
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    }





    public void loadMapPaths() {
        for (String mapPath : loadingPaths.getMapPaths()) {
            if (mapPath != null) {
                assetManager.load(mapPath, TiledMap.class);
            }
        }
    }

    public void loadSpritePaths(){
        for (String spritePath : loadingPaths.getSpritePaths()) {
            if (spritePath != null) {
                assetManager.load(spritePath, Texture.class);
            }
        }
    }

    public void loadFontPaths(){
        for(String fontPath : loadingPaths.getFontPaths()){
            if (fontPath != null) {
                assetManager.load(fontPath, BitmapFont.class);
            }
        }
    }



    public void updateLoader(){
        if (!assetManager.isFinished());
        assetManager.update();
    }
}
