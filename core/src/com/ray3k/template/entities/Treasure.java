package com.ray3k.template.entities;

import com.badlogic.gdx.audio.Sound;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Event;
import com.ray3k.template.screens.*;

import static com.ray3k.template.JamGame.*;
import static com.ray3k.template.Resources.TreasureSpine.*;

public class Treasure extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationAnimation, true);
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (animationState.getCurrent(0).getAnimation() == clickAnimation) {
                    GameScreen.gameScreen.doTimeline();
                    destroy = true;
                }
            }
    
            @Override
            public void event(AnimationState.TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null && !event.getData().getAudioPath().equals("")) {
                    Sound sound = assetManager.get(event.getData().getAudioPath());
                    sound.play();
                }
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (animationState.getCurrent(0).getAnimation() == animationAnimation && GameScreen.gameScreen.isAnyButtonJustPressed()) {
            animationState.setAnimation(0, clickAnimation, false);
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
