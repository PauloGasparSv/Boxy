package com.paulovale.boxy.utils;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.paulovale.boxy.actors.friendly.Player;
import com.paulovale.boxy.utils.PhysicsObject.Type;

public class SimpleContactListener implements ContactListener {
    @Override
    public void endContact(Contact contact) {
        PhysicsObject bodyA = (PhysicsObject) contact.getFixtureA().getBody().getUserData();
        PhysicsObject bodyB = (PhysicsObject) contact.getFixtureB().getBody().getUserData();

        System.out.println("1: " + bodyA.type);
        System.out.println("2: " + bodyB.type);
        
        if(bodyA.type == Type.Player){
            Player player = (Player) bodyA;
            Vector2 playerVelocity = player.body.getLinearVelocity();

            if(!player.isGrounded() && playerVelocity.y > 0f &&
                bodyB.type == Type.Floor){
                player.groundMe();
            }
        }
    }
    
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}
};
