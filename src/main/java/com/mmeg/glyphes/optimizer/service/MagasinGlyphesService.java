package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;

import java.util.List;

public interface MagasinGlyphesService {
	List<Glyphe> getGlyphesRond();
	List<Glyphe> getGlyphesCarre();
	List<Glyphe> getGlyphesHexa();
}
