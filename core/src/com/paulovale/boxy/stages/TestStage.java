package com.paulovale.boxy.stages;

import com.paulovale.boxy.physics.BodyFactory;
import com.paulovale.boxy.utils.FrameRate;
import com.paulovale.boxy.utils.In;
import com.paulovale.boxy.utils.Media;
import com.paulovale.boxy.physics.PhysicsObject;
import com.paulovale.boxy.physics.SimpleContactListener;
import com.paulovale.boxy.physics.PhysicsObject.Type;

import box2dLight.PointLight;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.paulovale.boxy.actors.friendly.Player;
import com.paulovale.boxy.objects.Floor;

public class TestStage extends PhysicsStage {
        
    private FrameRate frameRate;
    private LinkedList<PhysicsObject> mapObjects;
    
    private Player player;

    private PointLight torch;

    private box2dLight.DirectionalLight sun;
    private int direction;
    private long time;

    private TextureRegion bg;

    private Floor floor;

    public TestStage(){
        super();
        debugMode(true);
        
        world.setContactListener(new SimpleContactListener());

        frameRate = new FrameRate();

        player = new Player(world);
        
        floor = new Floor(world, 0, -64, 128, 64,);

        mapObjects = new LinkedList<PhysicsObject>();

        // mapObjects.add(new Floor(world, 128f, 32f, 0f, -128f));

        //Dark Cave
        rayHandler.setAmbientLight(1f, 1f, 1f, 0.2f);
        // torch = new PointLight(rayHandler, 8, Color.RED, 10f, 4f, 0f);

        //Sunny Day
        direction = -90;
        sun = new box2dLight.DirectionalLight(rayHandler, 1000, new Color(1, 1, 1, 0.9f), direction);
        time = 0;
        bg = new TextureRegion(Media.loadTexture("bg.jpg"));

    }

    @Override
    public void init(){
        super.init();
    }
    
    @Override
    public void update(float delta){
        super.update(delta);
        frameRate.update();

        if(In.justUp()){
            direction ++;
            sun.setDirection(direction);
        } else if(In.justDown()){
            direction --;
            sun.setDirection(direction);
        }

        if(System.currentTimeMillis() - time > 40){
            time = System.currentTimeMillis();
            direction ++;
            
            float sin = (float) Math.sin(Math.toRadians(direction));
            float cos = (float) Math.cos(Math.toRadians(direction));
            this.world.setGravity(new Vector2(cos * 40f, sin * 40f));

            sun.setDirection(direction);
        }

        if(In.cameraRight()){
            camera.position.x += delta * 50f;
        } else if(In.cameraLeft()){
            camera.position.x -= delta * 50f;
        }
        if(In.cameraUp()){
            camera.position.y += delta * 50f;
        } else if(In.cameraDown()){
            camera.position.y -= delta * 50f;
        }

        if(In.up()){
            camera.zoom -= delta * 1f;
        } else if(In.down()){
            camera.zoom += delta * 1f;
        }


        player.update(delta);
    }

    @Override
    public void draw(){
        super.draw();

        // batch.draw(bg, 0, 0);

        // frameRate.render(batch, camera); 
    }

    @Override
    public void dispose(){
        super.dispose();
        frameRate.dispose();
    }

}