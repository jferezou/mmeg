package com.mmeg.glyphes.optimizer.service;

import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.utils.EmplacementGlyphesEnum;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.Future;

public interface VoisinnageService {

	Future<Solution> parcourirVoisinageAsync(final Solution solutionCourante, final EmplacementGlyphesEnum emplacementAChanger, final OptimizeParameters optimizeParameters, final Queue<Solution> listeTabou);
}
