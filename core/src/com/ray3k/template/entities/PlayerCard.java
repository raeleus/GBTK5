package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Skin;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.CardSpine.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerCard extends Entity {
    Skin skin;
    
    public PlayerCard(Skin skin) {
        this.skin = skin;
    }
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setSkin(skin);
        animationState.setAnimation(0, fadeInAnimation, false);
        depth = 10;
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (entry.getAnimation() == dieAnimation) {
                    destroy = true;
                    gameScreen.doTimeline();
                }
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (gameScreen.isAnyButtonJustPressed() && skeletonBounds.aabbContainsPoint(gameScreen.mouseX, gameScreen.mouseY)) {
            switch (gameScreen.step) {
                case 27:
                    gameScreen.doTimeline();
                    break;
                case 35:
                case 54:
                case 65:
                    gameScreen.doTimeline();
                    animationState.setAnimation(0, showAnimation, false);
                    animationState.addAnimation(0, zoomAnimation, false, 0);
                    animationState.addAnimation(0, unzoomAnimation, false, 2);
                    break;
            }
        }
        
        if (gameScreen.step == 36 || gameScreen.step == 55 || gameScreen.step == 66) {
            moveTowards(600, 412, 50, delta);
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
