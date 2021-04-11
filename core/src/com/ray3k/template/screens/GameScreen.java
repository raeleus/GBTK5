package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.widgets.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color();
    public Stage stage;
    public ShapeDrawer shapeDrawer;
    public boolean paused;
    public int step = -1;
    BattleGround battleGround;
    PlayerCard playerCard;
    Array<EnemyCard> enemyCards;
    
    @Override
    public void show() {
        super.show();
        enemyCards = new Array<>();
    
        bgm_game.setLooping(true);
        bgm_game.play();
        
        gameScreen = this;
        BG_COLOR.set(Color.PINK);
    
        paused = false;
    
        stage = new Stage(new FitViewport(1024, 576), batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.align(Align.bottomLeft);
        root.pad(10);
        stage.addActor(root);
    
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("white"));
        shapeDrawer.setPixelSize(.5f);
    
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        camera.position.set(512,288,0);
    
        entityController.clear();
    
        battleGround = new BattleGround();
        entityController.add(battleGround);
    
        doTimeline();
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            entityController.act(delta);
            vfxManager.update(delta);
        }
        stage.act(delta);
    }
    
    @Override
    public void draw(float delta) {
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(paused ? 0 : delta);
        batch.end();
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
        
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        entityController.dispose();
    }
    
    public void doTimeline() {
        switch (++step) {
            case 0:
                var pop = new HelpPop("Hey it looks like you're new to Maelstrom!", "OK");
                pop.show(stage);
                break;
            case 1:
                pop = new HelpPop("This quick, easy, simple, affordable, and luxurious tutorial\nwill explain how to play", "OK", "Hide all tutorials");
                pop.show(stage);
                break;
            case 2:
                pop = new HelpPop("Are you ready to begin?", "Yes", "Hide all tutorials");
                pop.show(stage);
                break;
            case 3:
                pop = new HelpPop("You don't look ready. Where are all of your cards?\nDon't enter a battle half-cocked...", "OK", "Hide all tutorials");
                pop.show(stage);
                break;
            case 4:
                pop = new HelpPop("As a new customer, you get a FREE* pack\nof Maelstrom(TM) battle cards!", "OK", "Hide all tutorials");
                pop.show(stage);
                break;
            case 5:
                var treasure = new Treasure();
                entityController.add(treasure);
                break;
            case 6:
                pop = new HelpPop("Wow! Look at all these powerful* cards!\nThis game is won by collecting sexy cards like these.\nClick to see what cards you got!!!");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(1024, 576, Align.topRight);
                
                var clickCard = new ClickCard(CardSpine.walrusSkin);
                clickCard.clickToShow = true;
                entityController.add(clickCard);
                break;
            case 7:
                clickCard = new ClickCard(CardSpine.apeSkin);
                entityController.add(clickCard);
                break;
            case 8:
                clickCard = new ClickCard(CardSpine.bearSkin);
                entityController.add(clickCard);
                break;
            case 9:
                pop = new HelpPop("Yeah, these cards are kind of lame...\nEspecially when you consider this game has been out for awhile\nand other players have far more stronger cards.", "True...", "Hide all tutorials");
                pop.show(stage);
                break;
            case 10:
                pop = new HelpPop("But these basic ones are full of potential!\nKeep playing to unlock new packs!\nThe best way out is ALWAYS THROUGH", "OK I guess", "Hide all tutorials");
                pop.show(stage);
                break;
            case 11:
                pop = new HelpPop("I'll let you in on a secret though:\nThe true path to victory is {COLOR=GREEN}{WAVE}MONEY{RESET}.", "Please Take My Money", "No Thanks");
                pop.show(stage);
                break;
            case 12:
                pop = new HelpPop("Which pack do you want to buy?", "Ultra", "Ultimate", "Uber");
                pop.show(stage);
                break;
            case 13:
                sfx_chaching.play();
                treasure = new Treasure();
                entityController.add(treasure);
                
                var money = new Money();
                money.setPosition(100, 0);
                entityController.add(money);
    
                money = new Money();
                money.setPosition(924, 0);
                entityController.add(money);
                break;
            case 14:
                clickCard = new ClickCard(CardSpine.bearSkin);
                clickCard.clickToShow = true;
                entityController.add(clickCard);
                break;
            case 15:
                clickCard = new ClickCard(CardSpine.bearSkin);
                entityController.add(clickCard);
                break;
            case 16:
                clickCard = new ClickCard(CardSpine.bearSkin);
                entityController.add(clickCard);
                break;
            case 17:
                pop = new HelpPop("Aww, bummer dude! What are the chances of you getting the same shitty cards again?\nSince you aren't in the EU, I don't have to disclose that shit.", "THANKS", "Please stop the tutorials");
                pop.show(stage);
                break;
            case 18:
                pop = new HelpPop("It's okay. You can {BLINK}SMOOSH{RESET} those extra cards\nfor some smurf powder.\nSnort smurf powder to produce more powerful cards!", "SMOOSH", "DON'T SMOOSH");
                final var noSmoosh = pop.buttons.get(1);
                noSmoosh.addListener(new InputListener() {
                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        noSmoosh.setText("SMOOSH");
                    }
                });
                pop.show(stage);
                break;
            case 19:
                pop = new HelpPop("You got 3 powders!");
                pop.row();
                var table = new Table();
                pop.add(table);
                var image = new Image(skin, "powder");
                table.add(image);
                var label = new Label("X 3", skin);
                table.add(label);
                pop.show(stage);
                break;
            case 20:
                pop = new HelpPop("It costs 100 powders to roll for a chance to maybe kinda get a better card.\nSpend powder? YES/NO", "uhhh....", "oof!");
                pop.show(stage);
                break;
            case 21:
                pop = new HelpPop("You can just buy some powder directly. Whaddya say?", "OK", "Whatever");
                pop.show(stage);
                break;
            case 22:
                sfx_chaching.play();
                money = new Money();
                money.setPosition(100, 0);
                entityController.add(money);
    
                money = new Money();
                money.setPosition(924, 0);
                entityController.add(money);
    
                pop = new HelpPop("You got 1 powders!");
                pop.row();
                table = new Table();
                pop.add(table);
                image = new Image(skin, "powder");
                table.add(image);
                label = new Label("X 1", skin);
                table.add(label);
                pop.show(stage);
                break;
            case 23:
                pop = new HelpPop("Aww, that was a bad roll. Try again?", "OK", "Whatever");
                pop.show(stage);
                break;
            case 24:
                sfx_chaching.play();
                money = new Money();
                money.setPosition(100, 0);
                entityController.add(money);
        
                money = new Money();
                money.setPosition(924, 0);
                entityController.add(money);
        
                pop = new HelpPop("You got 1 powders!");
                pop.row();
                table = new Table();
                pop.add(table);
                image = new Image(skin, "powder");
                table.add(image);
                label = new Label("X 1", skin);
                table.add(label);
                pop.show(stage);
                break;
            case 25:
                pop = new HelpPop("Ready to battle in single combat against\na weak foe such as yourself?", "Please");
                pop.show(stage);
                break;
            case 26:
                pop = new HelpPop("Do you want a tutorial on combat?", "NO THANKS");
                pop.show(stage);
    
                table = new Table();
                table.setName("mana");
                table.setBackground(skin.getDrawable("mana-background"));
                table.setPosition(685, 23);
                table.setSize(253, 122);
                stage.addActor(table);
    
                var endButton = new Button(skin);
                endButton.setPosition(900, 268);
                stage.addActor(endButton);
                
                battleGround.animationState.setAnimation(0, BattlegroundSpine.battleAnimation, false);
                
                playerCard = new PlayerCard(CardSpine.apeSkin);
                entityController.add(playerCard);
                playerCard.setPosition(20, 40);
                break;
            case 27:
                pop = new HelpPop("See if I care! I was doing fine\njust sitting here, not telling you shit!\nFigure it out on your own.");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
                break;
            case 28:
                pop = new HelpPop("Ha ha, dummy. You can't just draw a card. You have to gain mana.");
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
                break;
            case 29:
                stage.addAction(Actions.delay(10, Actions.run(() -> {
                    doTimeline();
                })));
                break;
            case 30:
                pop = new HelpPop("See? You do need me. I want you to beg for it.", "Beg. Like a dog.");
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
                break;
            case 31:
                pop = new HelpPop("Draw a figure 8 with your mouse in a counter-clockwise motion.\nGo ahead, it's simple movement, stupid.");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
    
                stage.addAction(Actions.delay(10, Actions.run(() -> {
                    pop.hide();
                    doTimeline();
                })));
                break;
            case 32:
                pop = new HelpPop("That's not what I said you need to do.\nDraw a figure 8 with your mouse in a counter-clockwise motion.");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
        
                stage.addAction(Actions.delay(10, Actions.run(() -> {
                    pop.hide();
                    doTimeline();
                })));
                break;
            case 33:
                pop = new HelpPop("Counter-clockwise means the opposite of how a clock's hands move.\nDraw a figure 8 with your mouse in a counter-clockwise motion.");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
        
                stage.addAction(Actions.delay(10, Actions.run(() -> {
                    pop.hide();
                    doTimeline();
                })));
                break;
            case 34:
                pop = new HelpPop("OMG. Just look at you twirling your mouse around like a moron.\nThere is no figure 8 movement.\nI'll give you your damned mana.", "FFS");
                pop.show(stage);
                pop.setPosition(0, 576, Align.topLeft);
                break;
            case 35:
                table = stage.getRoot().findActor("mana");
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
                break;
            case 36:
                var enemyCard = new EnemyCard(CardSpine.cowSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(230, 330);
                enemyCards.add(enemyCard);
    
                enemyCard = new EnemyCard(CardSpine.horseSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(480, 330);
                enemyCards.add(enemyCard);
    
                enemyCard = new EnemyCard(CardSpine.spiderSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(730, 330);
                enemyCards.add(enemyCard);
                break;
            case 37:
                sfx_explosion.play();
                playerCard.animationState.setAnimation(0, CardSpine.hurtAnimation, false);
                playerCard.animationState.addAnimation(0, CardSpine.dieAnimation, false, 0);
                break;
            case 38:
                pop = new HelpPop("Ha ha. YOU FOOL!\nYour silly animals were no match for your opponent\nthat clearly has more money to spend than you.\nYou lost at this incredibly easy game.", "Hang Head In Shame", "Ritual Suicide");
                pop.show(stage);
                break;
            case 39:
                for (var enemy : enemyCards) {
                    enemy.destroy = true;
                }
                enemyCards.clear();
                
                stage.clear();
                battleGround.animationState.setAnimation(0, BattlegroundSpine.emptyAnimation, false);
    
                pop = new HelpPop("Hey, cheer up buttercup!\nFor completing your first professional match, you get a free pack!", "Yae");
                pop.show(stage);
                break;
            case 40:
                treasure = new Treasure();
                entityController.add(treasure);
                break;
            case 41:
                clickCard = new ClickCard(CardSpine.eagleSkin);
                clickCard.clickToShow = true;
                entityController.add(clickCard);
                break;
            case 42:
                clickCard = new ClickCard(CardSpine.toucanSkin);
                entityController.add(clickCard);
                break;
            case 43:
                clickCard = new ClickCard(CardSpine.parrotSkin);
                entityController.add(clickCard);
                break;
            case 44:
                pop = new HelpPop("WOW! You actually got some relatively rare cards\nfrom this season's Avian Quest!", "SWEEET!");
                pop.show(stage);
                break;
            case 45:
                pop = new HelpPop("Welcome to Season 2!\nUnfortunately, all Avian cards are retired\nYou will be compensated for their equivalent worth in powder!", "Please, end the pain...");
                pop.show(stage);
                break;
            case 46:
                pop = new HelpPop("You got 3 powders!");
                pop.row();
                table = new Table();
                pop.add(table);
                image = new Image(skin, "powder");
                table.add(image);
                label = new Label("X 3", skin);
                table.add(label);
                pop.show(stage);
                break;
            case 47:
                pop = new HelpPop("Wanna just buy a new pack?", "I guess so, I really don't have any volition in this simulation");
                pop.show(stage);
                break;
            case 48:
                sfx_chaching.play();
                treasure = new Treasure();
                entityController.add(treasure);
    
                money = new Money();
                money.setPosition(100, 0);
                entityController.add(money);
    
                money = new Money();
                money.setPosition(924, 0);
                entityController.add(money);
                break;
            case 49:
                clickCard = new ClickCard(CardSpine.rabbitSkin);
                clickCard.clickToShow = true;
                entityController.add(clickCard);
                break;
            case 50:
                clickCard = new ClickCard(CardSpine.wolfSkin);
                entityController.add(clickCard);
                break;
            case 51:
                clickCard = new ClickCard(CardSpine.tigerSkin);
                entityController.add(clickCard);
                break;
            case 52:
                clickCard = new ClickCard(CardSpine.sharkSkin);
                entityController.add(clickCard);
                break;
            case 53:
                pop = new HelpPop("Ready for another match?", "Is the game over yet?");
                pop.show(stage);
                break;
            case 54:
                table = new Table();
                table.setName("mana");
                table.setBackground(skin.getDrawable("mana-background"));
                table.setPosition(685, 23);
                table.setSize(253, 122);
                stage.addActor(table);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                endButton = new Button(skin);
                endButton.setPosition(900, 268);
                stage.addActor(endButton);
    
                battleGround.animationState.setAnimation(0, BattlegroundSpine.battleAnimation, false);
    
                playerCard = new PlayerCard(CardSpine.sharkSkin);
                entityController.add(playerCard);
                playerCard.setPosition(20, 40);
                
                enemyCards.clear();
                break;
            case 55:
                enemyCard = new EnemyCard(CardSpine.batSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(480, 330);
                enemyCards.add(enemyCard);
                break;
            case 56:
                sfx_explosion.play();
                for (var enemy : enemyCards) {
                    enemy.animationState.setAnimation(0, CardSpine.unhoverAnimation, false);
                    enemy.animationState.addAnimation(0, CardSpine.hurtAnimation, false, 0);
                    enemy.animationState.addAnimation(0, CardSpine.dieAnimation, false, 0);
                }
                enemyCards.clear();
                break;
            case 57:
                enemyCard = new EnemyCard(CardSpine.elephantSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(480, 330);
                enemyCards.add(enemyCard);
                break;
            case 58:
                sfx_explosion.play();
                for (var enemy : enemyCards) {
                    enemy.animationState.setAnimation(0, CardSpine.unhoverAnimation, false);
                    enemy.animationState.addAnimation(0, CardSpine.hurtAnimation, false, 0);
                    enemy.animationState.addAnimation(0, CardSpine.dieAnimation, false, 0);
                }
                enemyCards.clear();
                break;
            case 59:
                pop = new HelpPop("Yowza! You're doing such a good job!");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(1024, 0, Align.bottomRight);
                enemyCard = new EnemyCard(CardSpine.fishSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(480, 330);
                enemyCards.add(enemyCard);
                break;
            case 60:
                sfx_explosion.play();
                for (var enemy : enemyCards) {
                    enemy.animationState.setAnimation(0, CardSpine.unhoverAnimation, false);
                    enemy.animationState.addAnimation(0, CardSpine.hurtAnimation, false, 0);
                    enemy.animationState.addAnimation(0, CardSpine.dieAnimation, false, 0);
                }
                enemyCards.clear();
                break;
            case 61:
                pop = new HelpPop("Oh... uhh... this one looks difficult!\nMake sure to do a figure 8 lol.");
                pop.doTimeline = false;
                pop.show(stage);
                pop.setPosition(1024, 0, Align.bottomRight);
                
                enemyCard = new EnemyCard(CardSpine.octopusSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(480, 330);
                enemyCards.add(enemyCard);
                break;
            case 62:
                sfx_explosion.play();
                for (var enemy : enemyCards) {
                    enemy.animationState.setAnimation(0, CardSpine.unhoverAnimation, false);
                    enemy.animationState.addAnimation(0, CardSpine.hurtAnimation, false, 0);
                    enemy.animationState.addAnimation(0, CardSpine.dieAnimation, false, 0);
                }
                enemyCards.clear();
                break;
            case 63:
                playerCard.destroy = true;
                
                for (var enemy : enemyCards) {
                    enemy.destroy = true;
                }
                enemyCards.clear();
    
                stage.clear();
                battleGround.animationState.setAnimation(0, BattlegroundSpine.emptyAnimation, false);
    
                pop = new HelpPop("You've learned so much about this game!\nYou climb to the next rung of the tournament!", "CHEERS!");
                pop.show(stage);
                break;
            case 64:
                pop = new HelpPop("At this level of competition, expect your opponents\nto have already min/maxed their decks and profiled\nthem against the best strategies online.", "There's really no point in going on");
                pop.show(stage);
                break;
            case 65:
                table = new Table();
                table.setName("mana");
                table.setBackground(skin.getDrawable("mana-background"));
                table.setPosition(685, 23);
                table.setSize(253, 122);
                stage.addActor(table);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                image = new Image(skin, "orb-mana");
                table.add(image);
    
                endButton = new Button(skin);
                endButton.setPosition(900, 268);
                stage.addActor(endButton);
    
                battleGround.animationState.setAnimation(0, BattlegroundSpine.battleAnimation, false);
    
                playerCard = new PlayerCard(CardSpine.rabbitSkin);
                entityController.add(playerCard);
                playerCard.setPosition(20, 40);
    
                enemyCards.clear();
                break;
            case 66:
                enemyCard = new EnemyCard(CardSpine.turtleSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(230, 330);
                enemyCards.add(enemyCard);
    
                enemyCard = new EnemyCard(CardSpine.zebraSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(480, 330);
                enemyCards.add(enemyCard);
    
                enemyCard = new EnemyCard(CardSpine.snakeSkin);
                entityController.add(enemyCard);
                enemyCard.setPosition(730, 330);
                enemyCards.add(enemyCard);
                break;
            case 67:
                sfx_explosion.play();
                playerCard.animationState.setAnimation(0, CardSpine.hurtAnimation, false);
                playerCard.animationState.addAnimation(0, CardSpine.dieAnimation, false, 0);
                break;
            case 68:
                for (var enemy : enemyCards) {
                    enemy.destroy = true;
                }
                enemyCards.clear();
    
                stage.clear();
                battleGround.animationState.setAnimation(0, BattlegroundSpine.emptyAnimation, false);
    
                pop = new HelpPop("Congrats! You've hit a brick wall like everyone else\nwho thought they could win at this game.\nYou have successfully wasted your parent's money.", "FIN");
                pop.show(stage);
                break;
            case 69:
                bgm_game.stop();
                core.transition(new TitleScreen());
                break;
        }
    }
}