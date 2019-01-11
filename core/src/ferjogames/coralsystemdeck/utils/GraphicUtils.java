package ferjogames.coralsystemdeck.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import ferjogames.coralsystemdeck.CoralSystemDeck;

/**
 * Created by Fer on 05/08/2017.
 */

public class GraphicUtils {
	public static Pixmap getRoundedRectanglePixmap(int width, int height, int radius, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fillRectangle(0, radius, pixmap.getWidth(), pixmap.getHeight() - 2 * radius);
		pixmap.fillRectangle(radius, 0, pixmap.getWidth() - 2 * radius, pixmap.getHeight());
		pixmap.fillCircle(radius, radius, radius);
		pixmap.fillCircle(radius, pixmap.getHeight() - radius, radius);
		pixmap.fillCircle(pixmap.getWidth() - radius, radius, radius);
		pixmap.fillCircle(pixmap.getWidth() - radius, pixmap.getHeight() - radius, radius);
		return pixmap;
	}

	public static Pixmap getBlackboardPixmap(int width, int height, int radius) {
		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		pixmap.setColor(Colors.WOOD);

		pixmap.fillRectangle(0, radius, pixmap.getWidth(), pixmap.getHeight() - 2 * radius);
		pixmap.fillRectangle(radius, 0, pixmap.getWidth() - 2 * radius, pixmap.getHeight());
		pixmap.setColor(Colors.BLACKBOARD_GREEN);
		pixmap.fillRectangle(2 * radius, 2 * radius, width - 4 * radius, height - 4 * radius);
		pixmap.setColor(Colors.WOOD);

		pixmap.fillCircle(radius, radius, radius);
		pixmap.fillCircle(radius, pixmap.getHeight() - radius, radius);
		pixmap.fillCircle(pixmap.getWidth() - radius, radius, radius);
		pixmap.fillCircle(pixmap.getWidth() - radius, pixmap.getHeight() - radius, radius);

		return pixmap;
	}

	public static TextButton createTextButtonAbsolute(String text, int x, int y, int width, int height, BitmapFont font,
			Color textColor, Color backgroundColor) {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.font.setScale(CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth());

		textButtonStyle.fontColor = textColor;
		Pixmap pixmap = new Pixmap(MathUtils.roundToNextPowerOfTwo(width), 
				MathUtils.roundToNextPowerOfTwo(height), Pixmap.Format.RGBA8888);
		pixmap.setColor(backgroundColor);
		pixmap.fill();
		textButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(pixmap)));
		pixmap.dispose();
		textButtonStyle.down = textButtonStyle.up;
		TextButton textButton = new TextButton(text, textButtonStyle);
		textButton.setBounds(x, y, width, height);
		return textButton;
	}

	public static TextButton createTextButton(String text, int x, int y, int width, int height, BitmapFont font,
			Color textColor, Color backgroundColor) {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.font.setScale(CoralSystemDeck.WORLD_WIDTH / Gdx.graphics.getWidth());

		textButtonStyle.fontColor = textColor;
		Pixmap pixmap = new Pixmap(MathUtils.roundToNextPowerOfTwo(Utils.toPixelsWidth(width)), 
				MathUtils.roundToNextPowerOfTwo(Utils.toPixelsHeight(height)), Pixmap.Format.RGBA8888);
		pixmap.setColor(backgroundColor);
		pixmap.fill();
		textButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(pixmap)));
		pixmap.dispose();
		textButtonStyle.down = textButtonStyle.up;
		TextButton textButton = new TextButton(text, textButtonStyle);
		textButton.setBounds(x, y, width, height);
		return textButton;
	}

	public static Label createLabel(String text, CoralSystemDeck game, String fontName, Color color, float x, float y,
			float width) {
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = game.getFont(fontName);
		Label label = new Label(text, labelStyle);
		label.setColor(color);
		label.setX(x);
		label.setY(y);
		label.setWidth(width);
		label.setAlignment(Align.center);
		return label;
	}

	public static Label createLabel(String text, CoralSystemDeck game, String fontName, Color color) {
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = game.getFont(fontName);
		Label label = new Label(text, labelStyle);
		label.setColor(color);
		label.setAlignment(Align.center);
		return label;
	}

	public static Label createLinkLabel(String text, final String linkText, CoralSystemDeck game, String fontName) {
		Label label = createLabel(text, game, fontName, Colors.BLUE);
		label.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.net.openURI(linkText);
				return true;
			}
		});
		return label;
	}

	public static Label createWebsiteLabel(String address, CoralSystemDeck game, String fontName) {
		return createLinkLabel(address, address, game, fontName);
	}

	public static Label createEmailLabel(String email, CoralSystemDeck game, String fontName) {
		return createLinkLabel(email, "mailto:" + email, game, fontName);
	}
}
