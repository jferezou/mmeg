package com.mmeg.glyphes.optimizer.pojo;

import lombok.Data;

@Data
public class Solution {
	private Glyphage glyphage;
	private Mob mob;
	private long valeurFonctionObjectif;

	private long pvTotaux;
	private long defTotaux;
	private long vitesseTotaux;
	private long attTotaux;
	private long ccTotaux;
	private long dccTotaux;
	private long precisionTotaux;
	private long resistanceTotaux;

}
