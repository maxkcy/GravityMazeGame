package com.max.GravityMaze.Loading;


import com.max.GravityMaze.Loading.Paths.SpritePaths;
import com.max.GravityMaze.Loading.Paths.TiledMapPaths;

import java.util.ArrayList;
import java.util.List;

public class LoadingPaths {

    public LoadingPaths() {
    }

    public List<String> getMapPaths() {
        List<String> list = new ArrayList<>();
        list.add(TiledMapPaths.LVL1_PATH);
        return list;
    }

    public List<String> getSpritePaths() {
        List<String> list = new ArrayList<>();
        list.add(SpritePaths.END_PATH);
        list.add(SpritePaths.START_PATH);
        return list;
    }

    public List<String> getAnimationAtlasPaths() {
        List<String> list = new ArrayList<>();
        return list;
    }

    public List<String> getSkinPaths() {
        List<String> list = new ArrayList<>();
        return list;
    }

    public List<String> getAudioPaths() {
        List<String> list = new ArrayList<>();
        return list;
    }


}
