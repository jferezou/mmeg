package com.mmeg.glyphes.optimizer.pojo;

import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheCarre;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheHexagonal;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheRond;
import com.mmeg.glyphes.optimizer.utils.EmplacementGlyphesEnum;
import lombok.Data;

@Data
public class Glyphage {

	public Glyphage(Glyphage glyphage) {
		this.setSlotCarre1(new GlypheCarre(glyphage.getSlotCarre1()));
		this.setSlotCarre2(new GlypheCarre(glyphage.getSlotCarre2()));

		this.setSlotRond1(new GlypheRond(glyphage.getSlotRond1()));
		this.setSlotRond2(new GlypheRond(glyphage.getSlotRond2()));

		this.setSlotHexa1(new GlypheHexagonal(glyphage.getSlotHexa1()));
		this.setSlotHexa2(new GlypheHexagonal(glyphage.getSlotHexa2()));
	}

	private GlypheCarre slotCarre1;
	private GlypheCarre slotCarre2;
	private GlypheRond slotRond1;
	private GlypheRond slotRond2;
	private GlypheHexagonal slotHexa1;
	private GlypheHexagonal slotHexa2;


	public void changerGlyphe(Glyphe glypheachanger, final EmplacementGlyphesEnum emplacementAChanger) {
		switch(emplacementAChanger) {
			case CARRE1:
				setSlotCarre1((GlypheCarre)glypheachanger);
				break;
			case CARRE2:
				setSlotCarre2((GlypheCarre)glypheachanger);
				break;
			case ROND1:
				setSlotRond1((GlypheRond) glypheachanger);
				break;
			case ROND2:
				setSlotRond2((GlypheRond) glypheachanger);
				break;
			case HEXA1:
				setSlotHexa1((GlypheHexagonal) glypheachanger);
				break;
			case HEXA2:
				setSlotHexa2((GlypheHexagonal) glypheachanger);
				break;
		}
	}
}
