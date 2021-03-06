package com.ph.rpg.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ph.rpg.game.Game;
import com.ph.rpg.utils.ClassFileManager;

/**
 * Created by Jock on 23.05.2016.
 */
public class DeadObject extends AnimatedObject {
    private float startTime = 0;
    private boolean active = false;

    public DeadObject() {
        super(ClassFileManager.DeadXML);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float stateTime) {
        if (active) {
            setCoord(new Vector2(MovingObject.mainObject.getPosition()));
            currentCoord.add(0,-30);
            super.draw(spriteBatch, stateTime- startTime);
            if(stateTime-startTime>8*7*8*4/1000.){
                active = false;
            }
        }
    }

    public void activate(){
        startTime = Game.stateTime;
        active = true;
    }
}
