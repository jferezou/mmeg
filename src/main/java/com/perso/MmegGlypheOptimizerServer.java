package com.perso;

import com.perso.config.ServerComponent;

/**
 * Classe principale
 * @author jferezou
 *
 */
public class MmegGlypheOptimizerServer extends ServerComponent {
	public static void main(final String[] args) {
		run("mmegGlyphesOptimizer", "mmegGlyphesOptimizer", MmegGlypheOptimizerServer.class, args);
	}
}
