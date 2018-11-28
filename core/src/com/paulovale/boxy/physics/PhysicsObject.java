package com.paulovale.boxy.physics;

import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsObject{
	public static final float PXM = 16;
	public static final float MPX = 0.0625f;
	
    protected Body body;
	protected Type type;
	
	public PhysicsObject(Type type){
		this.type = type;
	}

	public PhysicsObject(){
		this.type = Type.Unset;
	}

	protected void setBody(Body body){
		this.body = body;
		this.body.setUserData(this);
	}
	
	protected void setTransform(float x, float y, float a){
		this.body.setTransform(x / PXM, y / PXM, a);
	}

    public static enum Type {
		Unset(-1),
        Player(0),
        Floor(1),
		Wall(2),
		Enemy(3),
		Npc(4),
		Item(5),
		Structure(6);

		private int value;
		private Type(int value) {
			this.value = value;
		}
		public int getValue () {
			return value;
		}
	};
}