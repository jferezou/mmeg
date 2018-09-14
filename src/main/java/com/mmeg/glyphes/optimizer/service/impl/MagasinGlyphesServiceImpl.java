package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.service.MagasinGlyphesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagasinGlyphesServiceImpl implements MagasinGlyphesService {
	@Override
	public List<Glyphe> getGlyphesRond() {
		return null;
	}

	@Override
	public List<Glyphe> getGlyphesCarre() {
		return null;
	}

	@Override
	public List<Glyphe> getGlyphesHexa() {
		return null;
	}
}
