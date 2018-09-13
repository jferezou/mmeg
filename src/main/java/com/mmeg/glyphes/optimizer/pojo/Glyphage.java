package com.mmeg.glyphes.optimizer.pojo;

import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheCarre;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheHexagonal;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheRond;
import lombok.Data;

@Data
public class Glyphage {

	private GlypheCarre slotCarre1;
	private GlypheCarre slotCarre2;
	private GlypheRond slotRond1;
	private GlypheRond slotRond2;
	private GlypheHexagonal slotHexa1;
	private GlypheHexagonal slotHexa2;

}
