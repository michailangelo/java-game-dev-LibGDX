import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class LevelScreen2 extends BaseScreen
{
    public void initialize()
    {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "assets/water.jpg" );
        ocean.setSize(800,600);

        BaseActor youWinMessage = new BaseActor(0,0,uiStage);
        youWinMessage.loadTexture("assets/you-win.png");
        youWinMessage.centerAtPosition(400,300);
        youWinMessage.moveBy(0,-100);

    }

    public void update(float dt)
    {
        if (Gdx.input.isKeyPressed(Keys.S)) 
            StarfishGame.setActiveScreen( new LevelScreen() );
    }
}