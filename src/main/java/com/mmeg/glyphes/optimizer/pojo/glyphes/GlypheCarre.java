package com.mmeg.glyphes.optimizer.pojo.glyphes;

import lombok.Data;

@Data
public class GlypheCarre extends Glyphe {
	public GlypheCarre(){
		setCarre(1);
		setRond(0);
		setHexagonal(0);
	}
}
