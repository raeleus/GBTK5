package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class Resources {
    public static Skin skin_skin;

    public static TextureAtlas textures_textures;

    public static Sound sfx_wind;

    public static Music bgm_game;

    public static Music bgm_title;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        CardSpine.skeletonData = assetManager.get("spine/card.json");
        CardSpine.animationData = assetManager.get("spine/card.json-animation");
        CardSpine.attackAnimation = CardSpine.skeletonData.findAnimation("attack");
        CardSpine.dieAnimation = CardSpine.skeletonData.findAnimation("die");
        CardSpine.enemyAttackAnimation = CardSpine.skeletonData.findAnimation("enemy attack");
        CardSpine.hideAnimation = CardSpine.skeletonData.findAnimation("hide");
        CardSpine.hoverAnimation = CardSpine.skeletonData.findAnimation("hover");
        CardSpine.hurtAnimation = CardSpine.skeletonData.findAnimation("hurt");
        CardSpine.showAnimation = CardSpine.skeletonData.findAnimation("show");
        CardSpine.unhoverAnimation = CardSpine.skeletonData.findAnimation("unhover");
        CardSpine.unzoomAnimation = CardSpine.skeletonData.findAnimation("unzoom");
        CardSpine.zoomAnimation = CardSpine.skeletonData.findAnimation("zoom");
        CardSpine.defaultSkin = CardSpine.skeletonData.findSkin("default");
        CardSpine.apeSkin = CardSpine.skeletonData.findSkin("ape");
        CardSpine.batSkin = CardSpine.skeletonData.findSkin("bat");
        CardSpine.bearSkin = CardSpine.skeletonData.findSkin("bear");
        CardSpine.cowSkin = CardSpine.skeletonData.findSkin("cow");
        CardSpine.eagleSkin = CardSpine.skeletonData.findSkin("eagle");
        CardSpine.elephantSkin = CardSpine.skeletonData.findSkin("elephant");
        CardSpine.fishSkin = CardSpine.skeletonData.findSkin("fish");
        CardSpine.horseSkin = CardSpine.skeletonData.findSkin("horse");
        CardSpine.octopusSkin = CardSpine.skeletonData.findSkin("octopus");
        CardSpine.parrotSkin = CardSpine.skeletonData.findSkin("parrot");
        CardSpine.rabbitSkin = CardSpine.skeletonData.findSkin("rabbit");
        CardSpine.sharkSkin = CardSpine.skeletonData.findSkin("shark");
        CardSpine.snakeSkin = CardSpine.skeletonData.findSkin("snake");
        CardSpine.spiderSkin = CardSpine.skeletonData.findSkin("spider");
        CardSpine.tigerSkin = CardSpine.skeletonData.findSkin("tiger");
        CardSpine.toucanSkin = CardSpine.skeletonData.findSkin("toucan");
        CardSpine.turtleSkin = CardSpine.skeletonData.findSkin("turtle");
        CardSpine.walrusSkin = CardSpine.skeletonData.findSkin("walrus");
        CardSpine.wolfSkin = CardSpine.skeletonData.findSkin("wolf");
        CardSpine.zebraSkin = CardSpine.skeletonData.findSkin("zebra");
        HailstromSpine.skeletonData = assetManager.get("spine/hailstrom.json");
        HailstromSpine.animationData = assetManager.get("spine/hailstrom.json-animation");
        HailstromSpine.animationAnimation = HailstromSpine.skeletonData.findAnimation("animation");
        HailstromSpine.standAnimation = HailstromSpine.skeletonData.findAnimation("stand");
        HailstromSpine.defaultSkin = HailstromSpine.skeletonData.findSkin("default");
        MailstromSpine.skeletonData = assetManager.get("spine/mailstrom.json");
        MailstromSpine.animationData = assetManager.get("spine/mailstrom.json-animation");
        MailstromSpine.animationAnimation = MailstromSpine.skeletonData.findAnimation("animation");
        MailstromSpine.hideAnimation = MailstromSpine.skeletonData.findAnimation("hide");
        MailstromSpine.standAnimation = MailstromSpine.skeletonData.findAnimation("stand");
        MailstromSpine.defaultSkin = MailstromSpine.skeletonData.findSkin("default");
        TreasureSpine.skeletonData = assetManager.get("spine/treasure.json");
        TreasureSpine.animationData = assetManager.get("spine/treasure.json-animation");
        TreasureSpine.animationAnimation = TreasureSpine.skeletonData.findAnimation("animation");
        TreasureSpine.clickAnimation = TreasureSpine.skeletonData.findAnimation("click");
        TreasureSpine.defaultSkin = TreasureSpine.skeletonData.findSkin("default");
        textures_textures = assetManager.get("textures/textures.atlas");
        sfx_wind = assetManager.get("sfx/wind.mp3");
        bgm_game = assetManager.get("bgm/game.mp3");
        bgm_title = assetManager.get("bgm/title.mp3");
    }

    public static class CardSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation attackAnimation;

        public static Animation dieAnimation;

        public static Animation enemyAttackAnimation;

        public static Animation hideAnimation;

        public static Animation hoverAnimation;

        public static Animation hurtAnimation;

        public static Animation showAnimation;

        public static Animation unhoverAnimation;

        public static Animation unzoomAnimation;

        public static Animation zoomAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;

        public static com.esotericsoftware.spine.Skin apeSkin;

        public static com.esotericsoftware.spine.Skin batSkin;

        public static com.esotericsoftware.spine.Skin bearSkin;

        public static com.esotericsoftware.spine.Skin cowSkin;

        public static com.esotericsoftware.spine.Skin eagleSkin;

        public static com.esotericsoftware.spine.Skin elephantSkin;

        public static com.esotericsoftware.spine.Skin fishSkin;

        public static com.esotericsoftware.spine.Skin horseSkin;

        public static com.esotericsoftware.spine.Skin octopusSkin;

        public static com.esotericsoftware.spine.Skin parrotSkin;

        public static com.esotericsoftware.spine.Skin rabbitSkin;

        public static com.esotericsoftware.spine.Skin sharkSkin;

        public static com.esotericsoftware.spine.Skin snakeSkin;

        public static com.esotericsoftware.spine.Skin spiderSkin;

        public static com.esotericsoftware.spine.Skin tigerSkin;

        public static com.esotericsoftware.spine.Skin toucanSkin;

        public static com.esotericsoftware.spine.Skin turtleSkin;

        public static com.esotericsoftware.spine.Skin walrusSkin;

        public static com.esotericsoftware.spine.Skin wolfSkin;

        public static com.esotericsoftware.spine.Skin zebraSkin;
    }

    public static class HailstromSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class MailstromSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation hideAnimation;

        public static Animation standAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class TreasureSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation clickAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }
}
