package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.utils.EmplacementGlyphesEnum;

import java.util.List;

public interface VoisinnageService {

	Solution parcourirVoisinage(final Solution solutionCourante, final EmplacementGlyphesEnum emplacementAChanger, final OptimizeParameters optimizeParameters, final List<Solution> listeTabou);
}
