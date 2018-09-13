package com.mmeg.glyphes.optimizer.service.impl;

import com.mmeg.glyphes.optimizer.pojo.Glyphage;
import com.mmeg.glyphes.optimizer.pojo.Mob;
import com.mmeg.glyphes.optimizer.pojo.Solution;
import com.mmeg.glyphes.optimizer.pojo.glyphes.Glyphe;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheCarre;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheHexagonal;
import com.mmeg.glyphes.optimizer.pojo.glyphes.GlypheRond;
import com.mmeg.glyphes.optimizer.service.CalculerTotaux;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static com.mmeg.glyphes.optimizer.utils.Constantes.BONUS_DEFENSE;
import static com.mmeg.glyphes.optimizer.utils.Constantes.BONUS_PUISSANCE;
import static com.mmeg.glyphes.optimizer.utils.Constantes.BONUS_VITALITE;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CalculerTotauxImplTest.TestConfiguration.class})
public class CalculerTotauxImplTest {
	@Resource
	private CalculerTotauxImpl calculerTotaux;

	private static final String ROND = "rond";
	private static final String CARRE = "carre";
	private static final String HEXA = "hexa";

	@Test
	public void totalPourcentEtFlatSansSet() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetVitalite(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetDestruction(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2);
		long total = this.calculerTotaux.totalPourcentEtFlat(glyphes, 100, Glyphe::getAttPourcent, Glyphe::getAttFlat, Glyphe::getSetPuissance, BONUS_PUISSANCE);

		Assertions.assertThat(total).isEqualTo(178);
	}


	@Test
	public void totalPourcentEtFlat1Set() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetVitalite(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetPuissance(1);
		Glyphe g3 = createGlyphe(0,32,33,0,35,36,37,38,39,440,441,ROND);
		g3.setSetVitalite(1);
		Glyphe g4 = createGlyphe(0,32,33,0,35,36,37,38,39,440,441,ROND);
		g4.setSetVitalite(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2,g3,g4);
		long total = this.calculerTotaux.totalPourcentEtFlat(glyphes, 101, Glyphe::getPvPourcent, Glyphe::getPvFlat, Glyphe::getSetVitalite, BONUS_VITALITE);

		Assertions.assertThat(total).isEqualTo(205);
	}



	@Test
	public void totalPourcentEtFlat2Set() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetDefense(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetDefense(1);
		Glyphe g3 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g3.setSetDefense(1);
		Glyphe g4 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g4.setSetDefense(1);
		Glyphe g5 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g5.setSetDefense(1);
		Glyphe g6 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g6.setSetDefense(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2,g3,g4,g5,g6);
		long total = this.calculerTotaux.totalPourcentEtFlat(glyphes, 102, Glyphe::getDefPourcent, Glyphe::getDefFlat, Glyphe::getSetDefense, BONUS_DEFENSE);

		Assertions.assertThat(total).isEqualTo(247);
	}


	@Test
	public void totalPourcentSansSet() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetVitalite(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetDestruction(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2);
		long total = this.calculerTotaux.totalPourcent(glyphes, 100, Glyphe::getAttPourcent,Glyphe::getSetPuissance, BONUS_PUISSANCE);

		Assertions.assertThat(total).isEqualTo(142);
	}


	@Test
	public void totalPourcent1Set() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetVitalite(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetPuissance(1);
		Glyphe g3 = createGlyphe(0,32,33,0,35,36,37,38,39,440,441,ROND);
		g3.setSetVitalite(1);
		Glyphe g4 = createGlyphe(0,32,33,0,35,36,37,38,39,440,441,ROND);
		g4.setSetVitalite(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2,g3,g4);
		long total = this.calculerTotaux.totalPourcent(glyphes, 101, Glyphe::getPvPourcent,Glyphe::getSetVitalite, BONUS_VITALITE);

		Assertions.assertThat(total).isEqualTo(167);
	}



	@Test
	public void totalPourcent2Set() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetDefense(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetDefense(1);
		Glyphe g3 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g3.setSetDefense(1);
		Glyphe g4 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g4.setSetDefense(1);
		Glyphe g5 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g5.setSetDefense(1);
		Glyphe g6 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g6.setSetDefense(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2,g3,g4,g5,g6);
		long total = this.calculerTotaux.totalPourcent(glyphes, 102, Glyphe::getDefPourcent, Glyphe::getSetDefense, BONUS_DEFENSE);

		Assertions.assertThat(total).isEqualTo(199);
	}



	@Test
	public void totalFlatSansSet() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetVitalite(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetDestruction(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2);
		long total = this.calculerTotaux.totalFlat(glyphes, 100, Glyphe::getAttFlat,Glyphe::getSetPuissance, BONUS_PUISSANCE);

		Assertions.assertThat(total).isEqualTo(136);
	}


	@Test
	public void totalFlat1Set() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetVitalite(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetPuissance(1);
		Glyphe g3 = createGlyphe(0,32,33,0,35,36,37,38,39,440,441,ROND);
		g3.setSetVitalite(1);
		Glyphe g4 = createGlyphe(0,32,33,0,35,36,37,38,39,440,441,ROND);
		g4.setSetVitalite(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2,g3,g4);
		long total = this.calculerTotaux.totalFlat(glyphes, 101, Glyphe::getPvFlat,Glyphe::getSetVitalite, BONUS_VITALITE);

		Assertions.assertThat(total).isEqualTo(159);
	}



	@Test
	public void totalFlat2Set() {
		Glyphe g1 = createGlyphe(1,2,3,4,5,6,7,8,9,10,11,ROND);
		g1.setSetDefense(1);
		Glyphe g2 = createGlyphe(31,32,33,34,35,36,37,38,39,440,441,ROND);
		g2.setSetDefense(1);
		Glyphe g3 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g3.setSetDefense(1);
		Glyphe g4 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g4.setSetDefense(1);
		Glyphe g5 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g5.setSetDefense(1);
		Glyphe g6 = createGlyphe(0,0,33,0,0,36,37,38,39,440,441,ROND);
		g6.setSetDefense(1);

		List<Glyphe> glyphes = Arrays.asList(g1,g2,g3,g4,g5,g6);
		long total = this.calculerTotaux.totalFlat(glyphes, 102, Glyphe::getDefFlat, Glyphe::getSetDefense, BONUS_DEFENSE);

		Assertions.assertThat(total).isEqualTo(190);
	}

	@Test
	public void calculateBonusSetTest() {
		Assertions.assertThat(calculerTotaux.calculateBonusSet(0)).isEqualTo(0);
		Assertions.assertThat(calculerTotaux.calculateBonusSet(1)).isEqualTo(0);
		Assertions.assertThat(calculerTotaux.calculateBonusSet(2)).isEqualTo(0);

		Assertions.assertThat(calculerTotaux.calculateBonusSet(3)).isEqualTo(1);
		Assertions.assertThat(calculerTotaux.calculateBonusSet(4)).isEqualTo(1);
		Assertions.assertThat(calculerTotaux.calculateBonusSet(5)).isEqualTo(1);

		Assertions.assertThat(calculerTotaux.calculateBonusSet(6)).isEqualTo(2);
	}

	@Test
	public void convertToPercentTest() {
		Assertions.assertThat(calculerTotaux.convertToPercent(50)).isEqualTo(1.5);
		Assertions.assertThat(calculerTotaux.convertToPercent(0)).isEqualTo(1);
		Assertions.assertThat(calculerTotaux.convertToPercent(1000)).isEqualTo(11);
	}


	@Test
	public void calculRatioTest() {
		Assertions.assertThat(calculerTotaux.calculRatio(500,12)).isEqualTo(40.66666);
		Assertions.assertThat(calculerTotaux.calculRatio(46,254)).isEqualTo(-0.81889);
	}

	private Glyphe createGlyphe(final int hpflat, final int defflat, final int attflat, final int hppourcent, final int defpourcent, final int attpourcent, final int vitesse, final int cc, final int dcc, final int precision, final int resistance, final String typeGlyphe) {
		Glyphe glyphe = null;
		if(ROND.equals(typeGlyphe)) {
			glyphe = new GlypheRond();
		}
		else if(CARRE.equals(typeGlyphe)) {
			glyphe = new GlypheCarre();
		}
		else if(HEXA.equals(typeGlyphe)) {
			glyphe = new GlypheHexagonal();
		}
		glyphe.setPvFlat(hpflat);
		glyphe.setPvPourcent(hppourcent);
		glyphe.setDefFlat(defflat);
		glyphe.setDefPourcent(defpourcent);
		glyphe.setAttFlat(attflat);
		glyphe.setAttPourcent(attpourcent);
		glyphe.setVitesse(vitesse);
		glyphe.setCc(cc);
		glyphe.setDcc(dcc);
		glyphe.setPrecision(precision);
		glyphe.setResistance(resistance);
		return glyphe;
	}

	public static class TestConfiguration {
		public TestConfiguration() {
		}

		@Bean
		@Primary
		public CalculerTotauxImpl getCalculerTotaux() {
			return Mockito.spy(new CalculerTotauxImpl());
		}
	}
}