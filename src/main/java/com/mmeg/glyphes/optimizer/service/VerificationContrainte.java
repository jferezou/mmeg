package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;

public interface VerificationContrainte {
	boolean verifierContraintes(final Solution solution, final OptimizeParameters optimizeParameters);
	boolean verifierContraintesRelachees(final Solution solution, final OptimizeParameters optimizeParameters, final double ratioMin, final int nbGlyphesMinParset);
}
