package com.paulovale.boxy.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class PhysicsStage implements Screen {
    protected static float timeStep = 1/60f;
    protected static int velocityIterations = 4, 
                        positionIterations = 4;
    private float stepAccumulator = 0;
                    
    protected World world;
    private Box2DDebugRenderer debugRenderer;
    protected RayHandler rayHandler;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    private boolean debugMode;

    public PhysicsStage(){
        super();
        init();
    }

    public void init(){
        world = new World(new Vector2(0f, -40f), false);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        camera = new OrthographicCamera(60f, 33.75f);
        batch = new SpriteBatch();
        rayHandler = new RayHandler(world); 
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
        camera.update();
        worldStep(delta);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        draw();
        batch.end();
        debugDraw();
        lights();
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
        System.out.println("Start Dispose");
        world.dispose();
        debugRenderer.dispose();
        rayHandler.dispose();
        batch.dispose();
        System.out.println("Disposed"); 
    }

    public void update(float delta){

    }

    public void draw(){
        Gdx.gl.glClearColor(0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
     
    }

    public void debugDraw(){
        if(debugMode){
            debugRenderer.render(world, camera.combined);
        }
    }

    public void lights(){
        rayHandler.setCombinedMatrix((OrthographicCamera) camera);
        rayHandler.updateAndRender();
    }

}