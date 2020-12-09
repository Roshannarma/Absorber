package Absorber.actions;

import Absorber.DefaultMod;
import Absorber.cards.ConsumeCards.SnakePlantCard;
import Absorber.cards.starter.ConsumeDagger;
import Absorber.cards.starter.KneeStrike;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.monsters.beyond.GiantHead;
import com.megacrit.cardcrawl.monsters.beyond.Nemesis;
import com.megacrit.cardcrawl.monsters.beyond.OrbWalker;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import Absorber.cards.ConsumeCards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.jvm.hotspot.utilities.Hashtable;

import javax.smartcardio.Card;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

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
        CardMonsterList.put(GiantHead.class.getName(), new GiantSlow());
        CardMonsterList.put(Lagavulin.class.getName(), new LagavulinDrain());
        CardMonsterList.put(Sentry.class.getName(), new SentrySlam());
        CardMonsterList.put(Darkling.class.getName(), new DarklingDrain());
        CardMonsterList.put(Healer.class.getName(), new MysticHeal());
        CardMonsterList.put(SnakePlant.class.getName(), new SnakePlantCard());
        CardMonsterList.put(Nemesis.class.getName(),new NemesisIntangible());
//        CardMonsterList.put(GremlinNob.class.getName(),new KneeStrike());
//        CardMonsterList.put(SlimeBoss.class.getName(),new CorrosiveDagger_L());
//        CardMonsterList.put(Centurion.class.getName(),new KneeStrike());
    }


    public AddCardFromConsume(AbstractCreature target) {
        String m = target.getClass().getName();
        AbstractCard temp = CardMonsterList.get(m);
        if(temp != null){
            DefaultMod.consumed = temp.makeCopy();
        }
        else{
            DefaultMod.consumed = new KneeStrike();
        }
    }
    public static AbstractCard displaycard(AbstractMonster target) {
        String m = target.getClass().getName();
        AbstractCard temp = CardMonsterList.get(m);
        if(temp != null){
            return temp.makeCopy();
        }
        else{
            return null;
        }
    }


}

