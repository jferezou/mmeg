package com.mmeg.glyphes.optimizer.pojo.glyphes;

public class GlypheRond extends Glyphe {
	public GlypheRond(){
		setCarre(0);
		setRond(1);
		setHexagonal(0);
	}

	public GlypheRond(Glyphe glyphe){
		super(glyphe);
		setCarre(0);
		setRond(1);
		setHexagonal(0);
	}
}
