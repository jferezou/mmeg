package com.mmeg.glyphes.optimizer.pojo;

import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheCarre;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheHexagonal;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheRond;
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

}
