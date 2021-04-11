package com.ray3k.template.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.gdx.typinglabel.TypingConfig;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.ray3k.stripe.PopTable;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;

public class HelpPop extends PopTable {
    private ButtonListener buttonListener = new ButtonListener(this);
    private FinishListener finishListener = new FinishListener(this);
    public boolean doTimeline = true;
    public Array<TextButton> buttons = new Array<>();
    
    public HelpPop(String text, String... buttonTexts) {
        var style = new PopTableStyle();
        style.background = skin.getDrawable("bubble-10");
        setStyle(style);
        setHideOnUnfocus(buttonTexts.length <= 0);
        if (buttonTexts.length == 0) {
            setTouchable(Touchable.enabled);
            addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hide();
                }
            });
        }
    
        TypingConfig.DEFAULT_SPEED_PER_CHAR = .01f;
        TypingConfig.INTERVAL_MULTIPLIERS_BY_CHAR.put('\n', .1f);
        var label = new TypingLabel(text, skin);
        label.setAlignment(Align.center);
        add(label);
        
        defaults().space(20f);
        row();
        var table = new Table();
        add(table);
        
        table.defaults().space(10);
        for (var buttonText : buttonTexts) {
            var button = new TextButton(buttonText, skin);
            table.add(button);
            button.addListener(buttonListener);
            buttons.add(button);
        }
        addListener(finishListener);
    }
    
    public static class ButtonListener extends ChangeListener {
        HelpPop popTable;
    
        public ButtonListener(HelpPop popTable) {
            this.popTable = popTable;
        }
    
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            popTable.hide();
            Resources.sfx_click.play();
        }
    }
    
    public static class FinishListener extends TableShowHideListener {
        HelpPop popTable;
    
        public FinishListener(HelpPop popTable) {
            this.popTable = popTable;
        }
    
        @Override
        public void tableShown(Event event) {
        
        }
    
        @Override
        public void tableHidden(Event event) {
            if (popTable.doTimeline) GameScreen.gameScreen.doTimeline();
        }
    }
}
