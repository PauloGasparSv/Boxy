package com.paulovale.boxy.objects;

import com.badlogic.gdx.physics.box2d.World;
import com.paulovale.boxy.physics.BodyFactory;
import com.paulovale.boxy.physics.PhysicsObject;

public class Floor extends PhysicsObject {
    
    public Floor(World world, float x, float y, float width, float height){
        super(Type.Floor);
        
        setBody(BodyFactory
                .setStatic(world)
                .setBoxShape(width, height)
                .setMaterial(BodyFactory.Material.Stone)
                .transform(x, y)
                .create());
    }
}