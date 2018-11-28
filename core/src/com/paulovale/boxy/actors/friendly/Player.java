package com.paulovale.boxy.actors.friendly;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.paulovale.boxy.actors.PhysicsActor;
import com.paulovale.boxy.physics.BodyFactory;
import com.paulovale.boxy.utils.In;

public class Player extends PhysicsActor{

    private boolean grounded;

    private float speed;
    private float turnSpeed;
    private float jumpSpeed;

    public Player(World world){
        super(Type.Player);
        
        speed = 410f;
        turnSpeed = 520f;
        jumpSpeed = 200f;
        grounded = false;

        setBody(BodyFactory
                    .setDynamic(world)
                    .setFixedRotation(true)
                    .setBoxShape(24f, 32f)
                    .setCustomMaterial(0.8f, 0.2f, 0f)
                    .transform(0, 10f)
                    .create()); 
    }

    @Override
    public void update(float delta){
        super.update(delta);

        controls(delta);
    }

    public void controls(float delta) {
        Vector2 velocity = body.getLinearVelocity();
        
        if(In.right()) {
            if(velocity.x < 5f){
                body.applyForceToCenter(turnSpeed, 0f, true);
            } else { 
                body.applyForceToCenter(speed, 0f, true);
            }
            if(velocity.x > 15f){
                body.setLinearVelocity(15f, velocity.y);
            } 
        } else if(In.left()){
            if(velocity.x > -5f){
                body.applyForceToCenter(-turnSpeed, 0f, true);
            } else {
                body.applyForceToCenter(-speed, 0f, true);
            }
            if(velocity.x < -15f){
                body.setLinearVelocity(-15f, velocity.y);
            }
        }

        if(isGrounded() && In.justJumped() && (int) velocity.y == 0){
            jump();
            body.applyLinearImpulse(0f, jumpSpeed, this.body.getPosition().x, this.body.getPosition().y, true);
        }
    }

    public void groundMe(){
        grounded = true;
    }

    public boolean isGrounded(){
        return grounded;
    }
    
    public void setGrounded(boolean grounded){
        this.grounded = grounded;
    }

    public void fall(){
        this.grounded = false;
    }

    public void jump(){
        this.grounded = false;
    }

}