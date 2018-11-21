package com.paulovale.boxy.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsStage implements Screen {
    protected static float timeStep = 1/60f;
    protected static int velocityIterations = 4, 
                        positionIterations = 4;
    private float stepAccumulator = 0;
                        
    protected World world;
    private Box2DDebugRenderer debugRenderer;
    protected SpriteBatch batch;
    protected Camera camera;
    private boolean debugMode;

    public PhysicsStage(){
        super();
        init();
    }

    public void init(){
        world = new World(new Vector2(0f, -10f), false);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        camera = new OrthographicCamera(60, 33.75f);
    }

    public void debugMode(boolean mode){
        debugMode = mode;
    }

    public void toggleDebugMode(){
        debugMode = !debugMode;
    }
    
    private void worldStep(float deltaTime) {
        float frameTime = Math.min(deltaTime, 0.25f);
        stepAccumulator += frameTime;
        System.out.println(stepAccumulator);
        while (stepAccumulator >= timeStep) {
            world.step(timeStep, velocityIterations, positionIterations);
            stepAccumulator -= timeStep;
        }
    }

    @Override
    public void show() {
        System.out.println("Show");
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resizing");
    }

    @Override
    public void pause() {
        System.out.println("Paused");
    }   

    @Override
    public void resume() {
        System.out.println("Resumed");
    }

    @Override
    public void hide() {
        System.out.println("Hidden");
    }

    @Override
    public void dispose() {
        System.out.println("Disposed");
    }

    public void update(float delta){
        camera.update();
        worldStep(delta);
    }

    public void draw(){
        Gdx.gl.glClearColor(0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        // if(debugMode){
            debugRenderer.render(world, camera.combined);
        // }
    }

}