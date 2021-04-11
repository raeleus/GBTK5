package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Skin;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.CardSpine.*;

public class ClickCard extends Entity {
    Skin skin;
    public boolean clickToShow = false;
    
    public ClickCard(Skin skin) {
        this.skin = skin;
    }
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setSkin(skin);
        animationState.setAnimation(0, fadeInAnimation, false);
        if (!clickToShow) {
            animationState.addAnimation(0, showAnimation, false, 0);
            animationState.addAnimation(0, zoomAnimation, false, 0);
        }
        var bone = skeleton.findBone("card");
        bone.setPosition(512, 288);
    
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (animationState.getCurrent(0).getAnimation() == fadeOutAnimation) {
                    GameScreen.gameScreen.doTimeline();
                    destroy = true;
                }
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (animationState.getCurrent(0).getAnimation() == fadeInAnimation && GameScreen.gameScreen.isAnyButtonJustPressed()) {
            animationState.setAnimation(0, showAnimation, false);
            animationState.addAnimation(0, zoomAnimation, false, 0);
        } else if (animationState.getCurrent(0).getAnimation() == zoomAnimation && GameScreen.gameScreen.isAnyButtonJustPressed()) {
            animationState.setAnimation(0, unzoomAnimation, false);
            animationState.addAnimation(0, hideAnimation, false, 0);
            animationState.addAnimation(0, fadeOutAnimation, false, 0);
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
