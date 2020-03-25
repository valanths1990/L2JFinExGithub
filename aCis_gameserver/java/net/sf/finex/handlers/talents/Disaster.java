/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.finex.handlers.talents;

import net.sf.finex.model.talents.ITalentHandler;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.skills.AbnormalEffect;
import net.sf.l2j.gameserver.skills.EffectTemplate;
import net.sf.l2j.gameserver.skills.L2Skill;
import net.sf.l2j.gameserver.skills.Stats;
import net.sf.l2j.gameserver.skills.basefuncs.FuncTemplate;
import net.sf.l2j.gameserver.skills.basefuncs.LambdaConst;
import net.sf.l2j.gameserver.templates.StatsSet;
import net.sf.l2j.gameserver.templates.skills.EEffectBonusType;
import net.sf.l2j.gameserver.templates.skills.ESkillType;

/**
 *
 * @author finfan
 */
public class Disaster implements ITalentHandler {

	@Override
	public EffectTemplate invoke(Object... args) {
		final StatsSet set = new StatsSet();
		set.set("attachCond");
		set.set("applayCond");
		set.set("lambda", new LambdaConst(0));
		set.set("count", 1);
		set.set("time", 5);
		set.set("abnormal", AbnormalEffect.STUN);
		set.set("stackType", "earthquake");
		set.set("stackOrder", 4f);
		set.set("showIcon", true);
		set.set("effectPower", -1);
		set.set("effectType", ESkillType.DEBUFF);
		set.set("triggeredId", -1);
		set.set("triggeredLevel", -1);
		set.set("chanceType");
		set.set("bonus", EEffectBonusType.NONE);
		final EffectTemplate et = new EffectTemplate("Debuff", set);
		et.attach(new FuncTemplate(null, null, "Mul", Stats.Speed, 0x30, new LambdaConst(0.3)));
		return et;
	}

	public static final boolean validate(Creature creature, L2Skill skill) {
		if (!creature.isPlayer()) {
			return false;
		}

		return skill.getId() == 347 && creature.getPlayer().hasTalent(SkillTable.FrequentTalent.DISASTER);
	}
}
