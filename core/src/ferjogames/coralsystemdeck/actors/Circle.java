package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Circle extends Actor {

    private float x,y,r;
    private Color color;
    private ShapeRenderer shapeRenderer;
    
    public Circle(Camera camera, float x,float y,float r,Color color){
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        shapeRenderer = new ShapeRenderer();
    }

    public void setRadius(float r){
        this.r = r;
    }

    public float getRadius(){
        return r;
    }

    public void setX(float x){
        this.x = x;
    }

    public float getX(){
        return x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getY(){
        return y;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.circle(x, y, r);
        shapeRenderer.end();
        batch.begin();
    }
    
    public void dispose() {
    	
    }
}