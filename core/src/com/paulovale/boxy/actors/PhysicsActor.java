package com.paulovale.boxy.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.paulovale.boxy.utils.PhysicsObject;

public class PhysicsActor extends PhysicsObject{
    private Vector2 position;
    
    public PhysicsActor(Type type){
        super(type);
    }

    public void update(float delta){}
    public void draw(SpriteBatch batch){}

    public void setPosition(Vector2 position){
        this.position = position;
    } 
    public void setX(float x){
        position.x = x;
    }
    public void setY(float y){
        position.y = y;
    }
    public float getX(){
        return position.x;
    }
    public float getY(){
        return position.y;
    }
    public Vector2 getPosition(){
        return position;
    }
}