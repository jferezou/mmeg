package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheCarre;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheHexagonal;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheRond;

import java.util.List;

public interface MagasinGlyphesService {
	List<GlypheRond> getGlyphesRond();
	List<GlypheCarre> getGlyphesCarre();
	List<GlypheHexagonal> getGlyphesHexa();
}