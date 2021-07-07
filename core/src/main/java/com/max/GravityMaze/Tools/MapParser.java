package com.max.GravityMaze.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;
import com.max.GravityMaze.GameStuff.EndpointEntity;
import com.max.GravityMaze.GameStuff.WallEntity;

public class MapParser {

TiledMap map;
World world;
Array<Item> wallArray;
Array<Item> endpointArray;

    public MapParser(TiledMap map, World world, Array<Item> wallArray, Array<Item> endpointArray) {
        this.map = map;
        this.world = world;
        this.wallArray = wallArray;
        this.endpointArray = endpointArray;
    }

    public void parse(){

        for (MapObject mapObject : map.getLayers().get("wallLayer").getObjects()){
            if(mapObject instanceof RectangleMapObject){
                Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
                Item<Entity> wallItem = new Item<>(new WallEntity(rect.x, rect.y, rect.width, rect.height));
                world.add(wallItem, wallItem.userData.position.x, wallItem.userData.position.y, wallItem.userData.width, wallItem.userData.height);
                wallArray.add(wallItem);
                Gdx.app.log(this.toString(), "wallItem " +  " added to wallArray");
            }
        }

        for (MapObject mapObject : map.getLayers().get("endpointLayer").getObjects()){
            if(mapObject instanceof RectangleMapObject){
                Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
                Item<Entity> item = new Item<>(new EndpointEntity(rect.x, rect.y, rect.width, rect.height));
                ((EndpointEntity)item.userData).setEndpoint(mapObject.getProperties().get("ENDPOINT", null, String.class));
                if(((EndpointEntity)item.userData).getEndpoint().equals("end")){
                world.add(item, item.userData.position.x, item.userData.position.y,
                        item.userData.width + 8, item.userData.height + 5);
                }else {world.add(item, item.userData.position.x, item.userData.position.y,
                        item.userData.width + 1, item.userData.height + 1);}
                endpointArray.add(item);
                Gdx.app.log(this.toString(), "endItem added to endpointArray");
            }
        }

    }
}
