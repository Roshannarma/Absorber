package Absorber.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class DrainPower extends AbstractPower implements CloneablePowerInterface {
    abstract public DamageInfo activate(DamageInfo info);
    abstract public int damage_check(int amount);
}
