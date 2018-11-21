package com.paulovale.boxy.stages;

import com.paulovale.boxy.utils.FrameRate;
import com.paulovale.boxy.utils.In;

import java.util.LinkedList;

import com.badlogic.gdx.physics.box2d.Body;
import com.paulovale.boxy.actors.good.BallPlayer;
import com.paulovale.boxy.utils.BodyFactory;

public class TestStage extends PhysicsStage {
        
    private FrameRate frameRate;
    private LinkedList<Body> bodies;
    
    private BallPlayer player;

    public TestStage(){
        super();
        debugMode(true);
        
        frameRate = new FrameRate();

        player = new BallPlayer();
        
        bodies = new LinkedList<Body>();
        bodies.add(BodyFactory
                    .setStatic(world)
                    .setBoxShape(50f, 1f)
                    .setMaterial(BodyFactory.Material.Stone)
                    .transform(0f, -4f)
                    .create());

        player.setBody(BodyFactory
                    .setDynamic(world)
                    .setCircleShape(1.5f)
                    // .setFixedRotation(true)
                    .setMaterial(BodyFactory.Material.Rubber)
                    // .transform(0f, 5f)
                    .create());

        bodies.add(BodyFactory
                    .setDynamic(world)
                    .setCircleShape(1f)
                    // .setFixedRotation(true)
                    .setMaterial(BodyFactory.Material.Rubber)
                    .transform(1.2f, 10f)
                    .create());

    }

    @Override
    public void init(){
        super.init();
    }
    
    @Override
    public void update(float delta){
        super.update(delta);
        frameRate.update();

        if(In.cameraRight()){
            camera.position.x += delta * 50f;
        } else if(In.cameraLeft()){
            camera.position.x -= delta * 50f;
        }

        player.update(delta);
    }

    @Override
    public void draw(){
        super.draw();
        frameRate.render();
    }

    @Override
    public void dispose(){
        super.dispose();
        frameRate.dispose();
    }

}