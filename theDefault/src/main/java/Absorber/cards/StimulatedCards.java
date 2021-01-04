package Absorber.cards;

import Absorber.actions.Stimulated;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public abstract class StimulatedCards extends AbstractDynamicCard {
    protected static boolean is_stimulated;
    public StimulatedCards(final String id,
                           final String img,
                           final int cost,
                           final CardType type,
                           final CardColor color,
                           final CardRarity rarity,
                           final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);
    }
    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
//        logger.info("other card played");
        is_stimulated = EnergyPanel.totalCount-cardPlayed.costForTurn> AbstractDungeon.player.energy.energy;
        if(is_stimulated){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void triggerWhenDrawn() {
        update_glow();
    }


    @Override
    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        update_glow();
    }

    public void update_glow(){
//        logger.info("update");
        is_stimulated = Stimulated.update();
        if(is_stimulated){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
