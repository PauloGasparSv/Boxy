package com.paulovale.boxy.actors.good;

import com.badlogic.gdx.physics.box2d.Body;
import com.paulovale.boxy.actors.Actor;
import com.paulovale.boxy.utils.In;

public class BallPlayer extends Actor{
    private Body body;

    public void setBody(Body body){
        this.body = body;
    }

    @Override
    public void update(float delta){
        if(In.right()){
            body.applyForceToCenter(10f, 0f, true);
        } else if(In.left()){
            body.applyForceToCenter(-10f, 0f, true);
        }
    }

}