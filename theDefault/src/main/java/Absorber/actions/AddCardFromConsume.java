package Absorber.actions;

import Absorber.DefaultMod;
import Absorber.cards.ConsumeCards.GremlinSmashCard;
import Absorber.cards.ConsumeCards.ReptoDag;
import Absorber.cards.starter.StimNeedle;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import Absorber.cards.ConsumeCards.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class AddCardFromConsume {
    private static final Logger logger = LogManager.getLogger(AddCardFromConsume.class.getName());
    public static HashMap<String, AbstractCard> CardMonsterList = new HashMap<>();

    public AddCardFromConsume() {
        CardMonsterList.put(AcidSlime_S.class.getName(), new CorrosiveDagger_M());
        CardMonsterList.put(AcidSlime_M.class.getName(), new CorrosiveDagger_M());
        CardMonsterList.put(AcidSlime_L.class.getName(), new CorrosiveDagger_M());
        CardMonsterList.put(SpikeSlime_S.class.getName(), new CorrosiveDagger_M());
        CardMonsterList.put(SpikeSlime_M.class.getName(), new CorrosiveDagger_M());
        CardMonsterList.put(SpikeSlime_L.class.getName(), new CorrosiveDagger_M());
        CardMonsterList.put(Cultist.class.getName(), new BloodyFeather());
        CardMonsterList.put(JawWorm.class.getName(), new ArmoredTooth());
        CardMonsterList.put(LouseNormal.class.getName(), new Crystallize());
        CardMonsterList.put(LouseDefensive.class.getName(), new Crystallize());
        CardMonsterList.put(GremlinWarrior.class.getName(), new GremlinArmy());
        CardMonsterList.put(GremlinWizard.class.getName(), new GremlinArmy());
        CardMonsterList.put(GremlinThief.class.getName(), new GremlinArmy());
        CardMonsterList.put(GremlinFat.class.getName(), new GremlinArmy());
        CardMonsterList.put(GremlinTsundere.class.getName(), new GremlinArmy());
        CardMonsterList.put(GremlinLeader.class.getName(), new GremlinRally());
        CardMonsterList.put(SlaverBlue.class.getName(), new EntangleThem());
        CardMonsterList.put(SlaverRed.class.getName(), new EntangleThem());
        CardMonsterList.put(Taskmaster.class.getName(), new TaskMasterWhip());
        CardMonsterList.put(FungiBeast.class.getName(), new FungiStrength());
        CardMonsterList.put(Looter.class.getName(), new LooterDagger());
        CardMonsterList.put(Byrd.class.getName(), new ByrdPeck());
        CardMonsterList.put(ShelledParasite.class.getName(), new ShelledShield());
        CardMonsterList.put(Chosen.class.getName(), new ChosenAttack());
        CardMonsterList.put(SphericGuardian.class.getName(), new SphericShield());
        CardMonsterList.put(BookOfStabbing.class.getName(), new StabbingBook());
        CardMonsterList.put(OrbWalker.class.getName(), new OrbSpray());
        CardMonsterList.put(Lagavulin.class.getName(), new LagavulinDrain());
        CardMonsterList.put(Sentry.class.getName(), new SentrySlam());
        CardMonsterList.put(Darkling.class.getName(), new DarklingShift());
        CardMonsterList.put(Healer.class.getName(), new MysticHeal());
        CardMonsterList.put(Nemesis.class.getName(),new NemesisIntangible());
        CardMonsterList.put(GremlinNob.class.getName(),new GremlinSmashCard());
        CardMonsterList.put(Reptomancer.class.getName(),new ReptoDag());
        CardMonsterList.put(GiantHead.class.getName(),new GiantHeadSlam());
//        CardMonsterList.put(GremlinNob.class.getName(),new KneeStrike());
//        CardMonsterList.put(SlimeBoss.class.getName(),new CorrosiveDagger_L());
//        CardMonsterList.put(Centurion.class.getName(),new KneeStrike());
    }

    public static AbstractCard getMonsterCard(String m, boolean upgraded){
        AbstractCard c = CardMonsterList.get(m);
        if(c == null){
            return null;
        }
        else{
//            for(AbstractRelic r: AbstractDungeon.player.relics){
//                logger.info(r.relicId);
//            }
            AbstractCard temp = c.makeCopy();
//            logger.info("does it get here");
            if(upgraded && c.canUpgrade()){
//                logger.info("daggger_upgraded");
                temp.upgrade();
            }
            else if (temp.type == AbstractCard.CardType.ATTACK && temp.canUpgrade() && !temp.upgraded && AbstractDungeon.player.hasRelic("Molten Egg 2")){
//                logger.info("molten");
                temp.upgrade();
            }
            else if (temp.type == AbstractCard.CardType.POWER && temp.canUpgrade() && !temp.upgraded && AbstractDungeon.player.hasRelic("Frozen Egg 2")) {
//                logger.info("frozen");
                temp.upgrade();
            }
            else if (temp.type == AbstractCard.CardType.SKILL && temp.canUpgrade() && !temp.upgraded && AbstractDungeon.player.hasRelic("Toxic Egg 2")) {
//                logger.info("toxic");
                temp.upgrade();
            }
            return temp;
        }
    }


    public AddCardFromConsume(AbstractCreature target,boolean upgraded) {
        String m = target.getClass().getName();
        AbstractCard temp = getMonsterCard(m,upgraded);
        if(temp != null){
            DefaultMod.consumed = temp;
        }
        else{
            DefaultMod.consumed = new StimNeedle();
        }
    }
    public static AbstractCard displaycard(AbstractMonster target,boolean upgraded) {
        String m = target.getClass().getName();
        AbstractCard temp = getMonsterCard(m, upgraded);
        if(temp != null){
            return temp;
        }
        else{
            return new StimNeedle();
        }
    }
}

