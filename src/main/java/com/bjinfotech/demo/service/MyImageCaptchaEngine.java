package com.bjinfotech.demo.service;

import com.octo.captcha.component.image.backgroundgenerator.*;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-9
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
public class MyImageCaptchaEngine extends ListImageCaptchaEngine {
  @Override
  protected void buildInitialFactories() {
    WordGenerator wgen = new RandomWordGenerator("ABCDEFGHJKLMNOPQRSTUVWXYZ123456789");

    RandomRangeColorGenerator fontColorGenerator = new RandomRangeColorGenerator(
        new int[] {0, 80},
        new int[] {0, 80},
        new int[] {0, 80});

    TextPaster textPaster = new RandomTextPaster(new Integer(4), new Integer(4), fontColorGenerator, true);

    BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(new Integer(100), new Integer(50));
//    BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(new Integer(100), new Integer(50));

    Font[] fontsList = new Font[] {
        new Font("Arial", Font.BOLD, 10),
        new Font("Tahoma", Font.BOLD, 10),
        new Font("Verdana", Font.BOLD, 10),
    };

    FontGenerator fontGenerator = new RandomFontGenerator(new Integer(15), new Integer(25), fontsList);

    WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
    this.addFactory(new GimpyFactory(wgen, wordToImage));
  }
}
