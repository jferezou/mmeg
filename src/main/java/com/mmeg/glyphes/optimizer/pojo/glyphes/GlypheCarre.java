package com.mmeg.glyphes.optimizer.pojo.glyphes;

import lombok.Data;

@Data
public class GlypheCarre extends Glyphe {
	public GlypheCarre(){
		setCarre(true);
		setRond(false);
		setHexagonal(false);
	}
}
