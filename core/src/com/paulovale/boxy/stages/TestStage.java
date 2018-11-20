package com.paulovale.boxy.stages;

import com.paulovale.boxy.utils.FrameRate;

import java.util.LinkedList;

import com.badlogic.gdx.physics.box2d.Body;
import com.paulovale.boxy.utils.BodyFactory;

public class TestStage extends PhysicsStage {
        
    private FrameRate frameRate;
    private LinkedList<Body> bodies;
    
    public TestStage(){
        super();
        debugMode(true);
        
        frameRate = new FrameRate();

        bodies = new LinkedList<Body>();
        bodies.add(BodyFactory
                    .setStatic(world)
                    .setBoxShape(50f, 1f)
                    .setMaterial(BodyFactory.Material.Stone)
                    .transform(0f, -4f)
                    .create());

        bodies.add(BodyFactory
                    .setDynamic(world)
                    .setBoxShape(1f, 1f)
                    .setMaterial(BodyFactory.Material.Rubber)
                    .transform(0f, 5f)
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