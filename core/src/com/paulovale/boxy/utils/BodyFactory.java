package com.paulovale.boxy.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyFactory {
    private Body body;
    private Shape shape;
    private FixtureDef fixture;
    private BodyDef bodyDef;

    public static enum Material {
		Steel(0), Wood(1), Rubber(2), Stone(3), Misc(-1);

		private int value;

		private Material (int value) {
			this.value = value;
		}
		public int getValue () {
			return value;
		}
	};
    //Main create body
    public static BodyFactory body(World world, BodyDef.BodyType type) {
        BodyFactory bf = new BodyFactory();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bf._setBodyDef(bodyDef);
        
        Body body = world.createBody(bodyDef);
        bf._setBody(body);

        return bf;
    }
    public static BodyFactory setDynamic(World world) {
        return body(world, BodyDef.BodyType.DynamicBody);
    }
    public static BodyFactory setStatic(World world) {
        return body(world, BodyDef.BodyType.StaticBody);
    }
    public static BodyFactory setKinematic(World world) {
        return body(world, BodyDef.BodyType.KinematicBody);
    }
    //misc options
    public BodyFactory setFixedRotation(boolean fixedRotation){
        getBody().setFixedRotation(fixedRotation);
        return this;
    }
    //Defines Shape
    public BodyFactory setBoxShape(float width, float height){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        _setShape(shape);
        return this;
    }
    public BodyFactory setCircleShape(float radius){
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        _setShape(shape);
        return this;
    }
    
    public BodyFactory setMaterial(Material material){
        return setMaterial(material, 1f);
    }
    
    //Sets density, friction and restitution
    public BodyFactory setMaterial(Material material, float density) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = getShape();
        _setFixtureDef(fixtureDef);

        switch(material){
            case Steel:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;
            case Wood:
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.7f;
                fixtureDef.restitution = 0.3f;
                break;
            case Rubber:
                fixtureDef.density = 0.8f;
                fixtureDef.friction = 0.2f;
                fixtureDef.restitution = 0.7f;
                break;
            case Stone:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.9f;
                fixtureDef.restitution = 0.1f;
                break;
            default:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.3f;
        }

        getBody().createFixture(fixtureDef);
        getShape().dispose();
        
        return this;
    }

    public BodyFactory setCustomMaterial(float density, float friction, float restitution) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = getShape();
        _setFixtureDef(fixtureDef);

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        getBody().createFixture(fixtureDef);
        getShape().dispose();
        
        return this;
    }

    //Position
    public BodyFactory transform(float x, float y){
        return transform(x, y, 0);
    }
    public BodyFactory transform(float x, float y, float angle){
        this.body.setTransform(new Vector2(x, y), angle);
        return this;
    }

    //Ends pipeline
    public Body create(){
        return body;
    }
    
    //Getters and setters
    public void _setBody(Body body){
        this.body = body;
    }
    public void _setBodyDef(BodyDef bodyDef){
        this.bodyDef = bodyDef;
    }
    public void _setShape(Shape shape){
        this.shape = shape;
    }
    public void _setFixtureDef(FixtureDef fixture){
        this.fixture = fixture;
    }
    public Body getBody(){
        return body;
    }
    public BodyDef getBodyDef(){
        return bodyDef;
    }
    public Shape getShape(){
        return shape;
    }
    public FixtureDef getFixture(){
        return fixture;
    }
    
}