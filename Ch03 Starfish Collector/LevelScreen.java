import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class LevelScreen extends BaseScreen
{
    private Turtle turtle;
    private boolean win;

    public void initialize() 
    {        
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "assets/water-border.jpg" );
        ocean.setSize(1200,900);
        BaseActor.setWorldBounds(ocean);

        new Starfish(400,400, mainStage);
        new Starfish(500,100, mainStage);
        new Starfish(100,450, mainStage);
        new Starfish(200,250, mainStage);
        new Starfish(700,300, mainStage);

        new Rock(200,150, mainStage);
        new Rock(100,300, mainStage);
        new Rock(300,350, mainStage);
        new Rock(450,200, mainStage);
        new Rock(550,270, mainStage);
        new Rock(550,20, mainStage);
        new Rock(700,200, mainStage);

        new Shark(300,200, mainStage);
        turtle = new Turtle(20,20, mainStage);
        win = false;
    }

    public void update(float dt)
    {
        for (BaseActor rockActor : BaseActor.getList(mainStage, "Rock"))
            turtle.preventOverlap(rockActor);

        for (BaseActor starfishActor : BaseActor.getList(mainStage, "Starfish"))
        {
            Starfish starfish = (Starfish)starfishActor;
            if ( turtle.overlaps(starfish) && !starfish.collected )
            {
                starfish.collected = true;
                starfish.clearActions();
                starfish.addAction( Actions.fadeOut(1) );
                starfish.addAction( Actions.after( Actions.removeActor() ) );

                Whirlpool whirl = new Whirlpool(0,0, mainStage);
                whirl.centerAtActor( starfish );
                whirl.setOpacity(0.25f);
            }
        }

        for (BaseActor sharkActor : BaseActor.getList(mainStage, "Shark"))
        {
            Shark shark = (Shark) sharkActor;
            if(turtle.overlaps(shark))
            {
                turtle.setVisible(false);

                BaseActor gameOverMessage = new BaseActor(0,0,uiStage);
                gameOverMessage.loadTexture("assets/game-over.png");
                gameOverMessage.centerAtPosition(400,300);
                gameOverMessage.setOpacity(0);
                gameOverMessage.addAction( Actions.delay(1) );
                gameOverMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
            }
        }

        if ( BaseActor.count(mainStage, "Starfish") == 0 && !win )
        {
            win = true;
            BaseActor youWinMessage = new BaseActor(0,0,uiStage);
            youWinMessage.loadTexture("assets/you-win.png");
            youWinMessage.centerAtPosition(400,300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction( Actions.delay(1) );
            youWinMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
            youWinMessage.addAction(Actions.after(Actions.fadeOut(1)));

            BaseActor continueMessage = new BaseActor(0,0,uiStage); 
            continueMessage.loadTexture("assets/message-continue.png");
            continueMessage.centerAtPosition(400,300);
            continueMessage.setOpacity(0);
            continueMessage.addAction( Actions.delay(3) );
            continueMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
        }

        if (Gdx.input.isKeyPressed(Keys.C)) 
            StarfishGame.setActiveScreen( new LevelScreen2() );
    }
}