package com.mmeg.glyphes.optimizer.pojo.glyphes;

public class GlypheHexagonal extends Glyphe {

	public GlypheHexagonal(){
		setCarre(0);
		setRond(0);
		setHexagonal(1);
	}

	public GlypheHexagonal(Glyphe glyphe){
		super(glyphe);
		setCarre(0);
		setRond(0);
		setHexagonal(1);
	}
}
