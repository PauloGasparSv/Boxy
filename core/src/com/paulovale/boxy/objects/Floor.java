package com.paulovale.boxy.objects;

import com.badlogic.gdx.physics.box2d.World;
import com.paulovale.boxy.utils.BodyFactory;
import com.paulovale.boxy.utils.PhysicsObject;

public class Floor extends PhysicsObject {
    
    public Floor(World world, float width, float height, float x, float y){
        super(Type.Floor);
        
        setBody(BodyFactory
                .setStatic(world)
                .setBoxShape(width / PXM, height / PXM)
                .setMaterial(BodyFactory.Material.Stone)
                .transform(x / PXM, y / PXM)
                .create());
    }
}