package com.paulovale.boxy.utils;

import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsObject{
    public static final int PXM = 16;
    protected Body body;
	protected Type type;
	
	public PhysicsObject(Type type){
		this.type = type;
	}

	protected void setBody(Body body){
		this.body = body;
		this.body.setUserData(this);
	}

    public static enum Type {
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