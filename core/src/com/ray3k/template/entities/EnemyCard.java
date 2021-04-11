package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Skin;

import static com.ray3k.template.Resources.CardSpine.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EnemyCard extends Entity {
    Skin skin;
    boolean disabled;
    
    public EnemyCard(Skin skin) {
        this.skin = skin;
    }
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setSkin(skin);
        animationState.setAnimation(0, fadeInAnimation, false);
        animationState.addAnimation(0, showAnimation, false, 0);
    
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
        if (!disabled) {
            var inside = skeletonBounds.aabbContainsPoint(gameScreen.mouseX, gameScreen.mouseY);
            if (animationState.getCurrent(0).getAnimation() != zoomAnimation && inside) {
                animationState.setAnimation(0, zoomAnimation, false);
                animationState.setAnimation(1, hoverAnimation, false);
            }
    
            if (animationState.getCurrent(0).getAnimation() == zoomAnimation && !inside) {
                animationState.setAnimation(0, unzoomAnimation, false);
                animationState.setAnimation(1, unhoverAnimation, false);
            }
    
            if (gameScreen.isAnyButtonJustPressed() && inside) {
                switch (gameScreen.step) {
                    case 36:
                    case 55:
                    case 57:
                    case 59:
                    case 61:
                    case 66:
                        disabled = true;
                        gameScreen.doTimeline();
                        break;
                }
            }
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
