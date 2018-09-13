package com.mmeg.glyphes.optimizer.pojo;

import com.mmeg.glyphes.optimizer.pojo.servicesConsommes.mmegdb.MobStats;
import lombok.Data;

@Data
public class Mob {
	private String nom;
	private int pvBase;
	private int defBase;
	private int vitesseBase;
	private int attBase;
	private int ccBase;
	private int dccBase;
	private int precisionBase;
	private int resistanceBase;

	public static Mob of(MobStats mobStats){
		Mob mob= new Mob();
		mob.setNom(mobStats.getData().getName());
		mob.setPvBase(mobStats.getData().getStatsGlobales().getMax().getHp());
		mob.setDefBase(mobStats.getData().getStatsGlobales().getMax().getDefense());
		mob.setVitesseBase(mobStats.getData().getStatsGlobales().getMax().getSpeed());
		mob.setCcBase(mobStats.getData().getStatsGlobales().getMax().getCriticalChance());
		mob.setDccBase(mobStats.getData().getStatsGlobales().getMax().getCriticalDamage());
		mob.setPrecisionBase(mobStats.getData().getStatsGlobales().getMax().getAccuracy());
		mob.setResistanceBase(mobStats.getData().getStatsGlobales().getMax().getResistance());
		mob.setAttBase(mobStats.getData().getStatsGlobales().getMax().getAttack());

		return mob;
	}
}
