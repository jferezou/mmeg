package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.Solution;

public interface CalculerTotaux {
	void mettreAjourTotaux(final Solution solution);
	void calculerValeurObjectif(final Solution solution);
}
