package ferjogames.coralsystemdeck.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Circle extends Actor {

    private static final String VERT_SHADER =
            "#ifdef GL_ES\n"
                    +"precision mediump float;\n"
                    +"#endif\n"
                    +"attribute vec2 a_position;\n"
                    +"uniform mat4 u_projTrans;\n"
                    +"uniform vec2 u_pos_center;\n"
                    +"varying vec4 v_pos_pixel;\n"
                    +"varying vec4 v_pos_center;\n"
                    +"void main() {\n"
                    +"v_pos_pixel  =  u_projTrans * vec4(a_position.xy, 0.0 , 1.0);\n"
                    +"v_pos_center = u_projTrans * vec4(u_pos_center.xy, 0.0 , 1.0);\n"
                    +"gl_Position = v_pos_pixel;\n"
                    +"}";


    private static final String FRAG_SHADER =
            "#ifdef GL_ES\n"
                    +"precision mediump float;\n"
                    +"#endif\n"
                    +"varying vec4 v_pos_pixel;\n"
                    +"varying vec4 v_pos_center;\n"
                    +"uniform mat4  u_projTrans;\n"
                    +"uniform vec4  u_color;\n"
                    +"uniform float u_radius;\n"
                    +"uniform float u_scale;\n"
                    +"void main() {\n"
                    +"vec4 r = u_projTrans * vec4(u_radius,0.0,0.0,0.0);\n"
                    +"vec4 p_pixel = v_pos_pixel,p_center = v_pos_center;\n"
                    +"p_pixel.x /= u_scale;\n"
                    +"p_center.x /= u_scale;\n"
                    +"float dist = distance(p_pixel.xy, p_center.xy);\n"
                    +"float alpha = smoothstep(r.x, r.x + 0.05, dist);\n"
                    +"vec4 col = mix(u_color, vec4(0.0), alpha);\n"
                    +"col.w = smoothstep(r.x + 0.005, r.x,dist);\n"
                    +"col.w *= u_color.w / 1.0;"
                    +"gl_FragColor = col;\n"
                    +"}";

    private float x,y,r;
    private Color color;
    private Mesh mesh;
    private ShaderProgram shader;
    private Camera camera;

    public Circle(Camera camera, float x,float y,float r,Color color){
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.camera = camera;

        mesh = createMesh();
        shader = createMeshShader();
    }

    protected Mesh createMesh() {
        Mesh mesh = new Mesh(true, 4, 6, new VertexAttribute(Usage.Position, 2, "a_position"));

        mesh.setVertices(generateVertices());
        mesh.setIndices(new short[]{ 0,1,2,1,2,3 });

        return mesh;
    }

    protected float[] generateVertices(){
        float v[] = new float[4 * 2];

        v[0] = x - r * 1.5f;
        v[1] = y - r * 1.5f;

        v[2] = x - r * 1.5f;
        v[3] = y + r * 1.5f;

        v[4] = x + r * 1.5f;
        v[5] = y - r * 1.5f;

        v[6] = x + r * 1.5f;
        v[7] = y + r * 1.5f;

        return v;
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

    protected ShaderProgram createMeshShader() {
        ShaderProgram.pedantic = false;
        ShaderProgram shader = new ShaderProgram(VERT_SHADER, FRAG_SHADER);
        if (!shader.isCompiled())
            throw new GdxRuntimeException("Circle shader not compiled");
        return shader;
    }

    public void dispose() {
        mesh.dispose();
        shader.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        Gdx.gl.glDepthMask(false);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shader.begin();
        shader.setUniformMatrix("u_projTrans", camera.combined);
        shader.setUniformf("u_color",color);
        shader.setUniformf("u_scale",(float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
        shader.setUniformf("u_radius", r/1.51f);
        shader.setUniformf("u_pos_center", new Vector2(x,y));
        mesh.render(shader,GL20.GL_TRIANGLES, 0, 6);
        shader.end();
        batch.begin();
    }
}