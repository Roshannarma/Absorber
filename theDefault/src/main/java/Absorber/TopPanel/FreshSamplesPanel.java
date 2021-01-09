package Absorber.TopPanel;

import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class FreshSamplesPanel extends TopPanelItem {
    private static final Texture IMG = new Texture("AbsorberResources/images/relics/FreshSamplesFullPanel.png");
    private static final Texture USED = new Texture("AbsorberResources/images/relics/FreshSamplesEmptyPanel.png");
    public static final String ID = "Absorber:FreshSamplesPanel";
    public boolean charged = true;

    public FreshSamplesPanel() {
        super(IMG, ID);
    }

    public void reset(){
        this.image = IMG;
        this.charged = true;
    }

    @Override
    protected void onClick() {
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && charged){
            this.charged = false;
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player,1));
            this.image = USED;
        }
    }
    public void ChangeImage(){
        this.image = IMG;
    }
}
