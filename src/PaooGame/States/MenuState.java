package PaooGame.States;

import java.awt.*;
import PaooGame.Graphics.CustomButton;
import PaooGame.Audio.Sound;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.CustomSlider;
import PaooGame.Input.Controls;

public class MenuState extends State {

    private Game game;
    private boolean StartHover = false;
    private boolean ExitHover = false;
    private boolean OptionsHover = false;
    private boolean BackHover = false;
    private boolean MenuOptions = false;
    private boolean LoadHover = false;
    private CustomSlider MusicSlider;
    private CustomSlider SoundSlider;

    Rectangle StartButton = new Rectangle(810 - 75,  930 - 450 , 450 , 80);
    Rectangle LoadButton = new Rectangle(810 - 50, 930 - 300, 400, 80);
    Rectangle OptionsButton = new Rectangle(810 - 25, 930 - 150, 350, 80);
    Rectangle ExitButton = new Rectangle(810, 930, 300, 80);
    Rectangle BackButton = new Rectangle(810, 900, 300, 80);

    private static Sound MenuMusic;
    private static Sound ClickSound;

    public MenuState(Game game) {
        this.game = game;
        MenuMusic = new Sound("menu_music.wav");
        ClickSound = new Sound("click_sound.wav");
        MusicSlider =new CustomSlider(780,450,370,80,0,100,30);
        SoundSlider =new CustomSlider(780,650,370,80,0,100,50);
        startMusic();
    }

    public static void startMusic() {
        if (MenuMusic != null) {
            MenuMusic.stop();
            MenuMusic.play();
            MenuMusic.loop();
        }
    }

    @Override
    public void Update() {

        Point mouse = new Point(Controls.Mouse_Position_x, Controls.Mouse_Position_y);
        StartHover = StartButton.contains(mouse);
        OptionsHover = OptionsButton.contains(mouse);
        ExitHover = ExitButton.contains(mouse);
        BackHover = BackButton.contains(mouse);
        LoadHover = LoadButton.contains(mouse);

        float Music_volume = Math.max(0.0001f, MusicSlider.getVal() / 100f);
        MenuMusic.setVolume(Music_volume);
        float Click_volume = Math.max(0.0001f, SoundSlider.getVal() / 100f);
        ClickSound.setVolume(Click_volume);

        if (Controls.Mouse_pressed && !MenuOptions) {
            if (StartHover) {
                ClickSound.play();
                game.setCurrentState(game.getPlayState());
                MenuMusic.stop();
            }else if (LoadHover){
                ClickSound.play();
            }else if (OptionsHover) {
                ClickSound.play();
                MenuOptions = true;
            } else if (ExitHover) {
                MenuMusic.stop();
                ClickSound.play();
                System.exit(0);
            }
            Controls.Mouse_pressed = false;
        }

        if (MenuOptions) {
            MusicSlider.Update();
            SoundSlider.Update();
            if (Controls.Mouse_pressed && BackHover){
                if (BackHover) {
                    ClickSound.play();
                    MenuOptions = false;
                }
                Controls.Mouse_pressed = false;
            }   }
    }

    @Override
    public void Draw(Graphics g) {

        g.drawImage(Assets.menuBackground, 0, 0, 1920, 1080, null);

        if (!MenuOptions) {
            CustomButton.DrawButton(g, "NEW GAME", StartButton, StartHover);
            CustomButton.DrawButton(g,"LOAD GAME" , LoadButton, LoadHover);
            CustomButton.DrawButton(g, "OPTIONS", OptionsButton, OptionsHover);
            CustomButton.DrawButton(g, "EXIT", ExitButton, ExitHover);
        }
        else {
            SoundSlider.Draw(g,"Sounds Effects Volume");
            MusicSlider.Draw(g, "Music Volume");
            CustomButton.DrawButton(g, "BACK", BackButton , BackHover);
        }
    }
}