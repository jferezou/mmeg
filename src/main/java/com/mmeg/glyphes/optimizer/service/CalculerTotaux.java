package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;

public interface CalculerTotaux {
	void mettreAjourTotaux(final Solution solution);
	double calculerValeurObjectif(final Solution solution, final OptimizeParameters optimizeParameters);
}
