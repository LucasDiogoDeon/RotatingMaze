package com.gmail.lucasddeon.rotatingmaze.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.Ball;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.PipeShape;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.PipeShapePath;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.preferences.GameOptionsPreferences;
import com.gmail.lucasddeon.rotatingmaze.preferences.UserProfilePreferences;
import com.gmail.lucasddeon.rotatingmaze.screens.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.H;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class GameSoberModeRenderer extends GameRenderer {

    /**
     *
     */
    protected static final class Consts {

        public static final float ELEMENT_MARGIN_PERCENTAGE = 1.5f / 100f;
        public static final float ELEMENT_SIZE_PERCENTAGE = 15f / 100f;

        public static final float ELEMENT_MARGIN;
        public static final float ELEMENT_HEIGHT;
        public static final float ELEMENT_WIDTH;

        static {
            ELEMENT_MARGIN = H() * ELEMENT_MARGIN_PERCENTAGE;

            float size = H() * ELEMENT_SIZE_PERCENTAGE;
            ELEMENT_HEIGHT = size;
            ELEMENT_WIDTH = size;
        }

        public static final float BUTTON_PLAY_PERCENTAGE = 65 / 100f;

    }

    protected GameScreen fromScreen;

    protected GameWorld world;
    protected GameOptions gameOptions;

    protected Slider slider;

    protected Ball ball;
    protected Circle ballCircle;
    protected PipeShapePath pipeShapePath;
    protected List<PipeShape> pipeShapes;
    protected List<Rectangle> pipeRectangles;
    protected Rectangle finishRectangle;

    protected OrthographicCamera cam;
    protected ShapeRenderer shapeRenderer;

    protected SpriteBatch spriteBatcher;
    protected SpriteBatch spriteBatcherFont;
    protected SpriteBatch spriteBatcherCollision;

    public Stage stage;
    protected Group groupBackground;
    protected Group groupMain;
    protected Group groupControlls;
    protected Group groupMasks;

    protected Image imgBackground;
    protected Image imgBall;
    protected List<Image> imgPipeSquares;
    protected List<Image> imgPipeBorders;
    protected Image imgFinishRectangle;

    protected Image imgMaskInitial;
    protected Image imgMaskPaused;
    protected ImageButton maskPauseButtonPlay;

    // Acelerometro
    // O acelerometro esta disponivel?
    protected boolean isAccelerometerAvailable = false;

    // O acelerometro esta�habilitado?
    private boolean isAccelerometerEnabled = true;

    // Valores atuais do acelerometro
    protected float lastAccY = 0;

    protected float degreesRotation;
    protected float degreesToRotate;
    // / Acelerometro

    // Keyboard
    // O teclado esta� disponivel?
    protected boolean isKeyboardAvailable = false;

    protected final int[] keyboardsUpKeys = {
            Input.Keys.W,
            Input.Keys.D,
            Input.Keys.UP,
            Input.Keys.RIGHT
    };
    protected final int[] keyboardsDownKeys = {
            Input.Keys.S,
            Input.Keys.A,
            Input.Keys.DOWN,
            Input.Keys.LEFT
    };
    // / Keyboard

    protected ImageButton buttonPause;
    protected ImageButton buttonSoundOn;
    protected ImageButton buttonSoundOff;
    protected ImageButton buttonAccelerometerOn;
    protected ImageButton buttonAccelerometerOff;
    protected ImageButton buttonExit;

    private static final float SLIDER_MULTIPLER = 100f;

    protected boolean isToPause = false;
    protected boolean hasStarted = false;

    protected float totalRunTime = 0f;


    protected void showInitialScreen() {

        final GameSoberModeRenderer instance = this;

        float s = Math.max(W(), H());
        imgMaskInitial = new Image(AssetLoader.textureRegionMaskReady);
        imgMaskInitial.setPosition(-s, -s);
        imgMaskInitial.setSize(s * 2, s * 2);

        groupMasks.clear();
        groupMasks.addActor(imgMaskInitial);

        Skin skinComponents = AssetLoader.skin;

        final Label labelLevelNumber;
        String labelLevelNumberText = gameOptions.level + "";

        labelLevelNumber = new Label(labelLevelNumberText,
                skinComponents, "candles_regular_216_beige_dark");
        labelLevelNumber.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelLevelNumber.setSize(W(), H());
        labelLevelNumber.setAlignment(Align.center);

        groupMasks.addActor(labelLevelNumber);

        groupMasks.addAction(
                Actions.sequence(
                        Actions.delay(1.0f),
                        Actions.fadeOut(0.5f),
                        Actions.run(new Runnable() {
                            public void run() {
                                instance.start();
                            }
                        })
                )
        );

    }

    public GameSoberModeRenderer(GameScreen fromScreen, GameWorld gameWorld, GameOptions gameOptions) {

        super(GameMode.SOBER);

        isAccelerometerAvailable = Gdx.input.isPeripheralAvailable(
                Input.Peripheral.Accelerometer);

        isKeyboardAvailable = Gdx.input.isPeripheralAvailable(
                Input.Peripheral.HardwareKeyboard);

        AssetLoader.load();

        this.fromScreen = fromScreen;
        this.world = gameWorld;
        this.gameOptions = gameOptions;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, W(), H());

        spriteBatcher = new SpriteBatch();
        spriteBatcher.setProjectionMatrix(cam.combined);

        spriteBatcherFont = new SpriteBatch();
        spriteBatcherCollision = new SpriteBatch();

        stage = new Stage();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.identity();
        shapeRenderer.translate(gameOptions.centerPosition.x, gameOptions.centerPosition.y, 1);

        initAssets();
        initGameObjects();
        initComponents();

        stage.clear();

        groupBackground = new Group();
        stage.addActor(groupBackground);

        groupMain = new Group();
        groupMain.setOrigin(0, 0);
        groupMain.setPosition(gameOptions.centerPosition.x, gameOptions.centerPosition.y);
        stage.addActor(groupMain);

        groupControlls = new Group();
        stage.addActor(groupControlls);

        groupMasks = new Group();
        stage.addActor(groupMasks);

        after();

        showInitialScreen();

        Gdx.input.setInputProcessor(stage);

    }

    public void render(float runTime, float delta) {

        switch (world.gameState) {
            case RUNNING:
                totalRunTime += delta;
                break;
        }

        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClearColor(233 / 255f, 206 / 255f, 104 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (degreesToRotate != 0) {

            float step = Math.min(
                    Math.abs(degreesToRotate * delta * gameOptions.distanceVelocity),
                    Math.abs(degreesToRotate)
            );

            if (degreesToRotate < 0) {
                degreesRotation -= step;
                slider.setValue(degreesRotation * SLIDER_MULTIPLER);
                degreesToRotate += step;
            } else { // if (degreesToRotate > 0)
                degreesRotation += step;
                slider.setValue(degreesRotation * SLIDER_MULTIPLER);
                degreesToRotate -= step;
            }
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        drawBackground();
        drawPipes();
        drawBall();
        drawControlls();

        shapeRenderer.end();

        spriteBatcher.begin();

        verifyAccelerometer();
        verifyKeyboard(delta);

        stage.act();
        stage.draw();

        spriteBatcher.end();

        if (isGameOver()) {
            if (GameOptionsPreferences.isSoundEnabled()) {
                AssetLoader.soundGameOver.play();
            }
            gameOver();
        }

        if (isGameWon()) {
            if (GameOptionsPreferences.isSoundEnabled()) {
                AssetLoader.soundGameWon.play();
            }
            gameWon();
        }

        spriteBatcherCollision.begin();

        spriteBatcherCollision.end();

    }

    /**
     *
     */
    protected void verifyAccelerometer() {

        switch (world.gameState) {
            case READY:
            case PAUSED:
            case GAMEOVER:
                return;
        }

        if (!isAccelerometerAvailable) {
            return;
        }

        if (!isAccelerometerEnabled) {
            return;
        }

        if (!GameOptionsPreferences.isAccelerometerEnabled()) {
            return;
        }

        final float max = gameOptions.rotationMax;
        final float min = gameOptions.rotationMin;
        final float range = Math.abs(max) + Math.abs(min);
        final float multiplier = range / 20;
        float accY = (Gdx.input.getAccelerometerY() * multiplier);

        if (accY < min) accY = min;
        else if (accY > max) accY = max;

        if (accY < -45f) {
            accY = min; // -90f;
        } else if (accY > 45f) {
            accY = max; // 90f;
        } else {
            accY = 0;
        }

        if (lastAccY != accY) {
            degreesToRotate = degreesToRotate + (accY - lastAccY);
            degreesRotation = slider.getValue() / SLIDER_MULTIPLER;
        }

        lastAccY = accY;

    }

    /**
     *
     * @param delta
     */
    protected void verifyKeyboard(float delta) {

        switch (world.gameState) {
            case READY:
            case PAUSED:
            case GAMEOVER:
                return;
        }

        if (!isKeyboardAvailable) {
            return;
        }

        float step = 0;
        final float multiplier = 30f;
        if (isKeyUpPressed()) {
            step = (multiplier * delta * gameOptions.distanceVelocity);
        } else if (isKeyDownPressed()) {
            step = -(multiplier * delta * gameOptions.distanceVelocity);
        }

        if (step != 0) {
            float value = slider.getValue() / SLIDER_MULTIPLER;
            value += step;
            if (value < gameOptions.rotationMin) {
                value = gameOptions.rotationMin;
            } else if (value > gameOptions.rotationMax) {
                value = gameOptions.rotationMax;
            }
            slider.setValue(value * SLIDER_MULTIPLER);
        }

    }

    /**
     *
     * @return
     */
    protected boolean isKeyUpPressed() {
        for (int key : keyboardsUpKeys) {
            if (Gdx.input.isKeyPressed(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    protected boolean isKeyDownPressed() {
        for (int key : keyboardsDownKeys) {
            if (Gdx.input.isKeyPressed(key)) {
                return true;
            }
        }
        return false;
    }

    protected void initGameObjects() {

        this.ball = world.getBall();
        this.ballCircle = this.ball.getBoundingCircle();

        this.pipeShapePath = world.getPipeShapePath();
        this.pipeShapes = Arrays.asList(world.getPipeShapes());

        this.finishRectangle = this.pipeShapePath.getFinishRectangle();

        this.pipeRectangles = new ArrayList<Rectangle>();
        for (PipeShape ps: this.pipeShapes) {
            for (Rectangle rect : ps.getBoundingRectangles()) {
                this.pipeRectangles.add(rect);
            }
        }

        this.imgPipeSquares = new ArrayList<Image>();
        this.imgPipeBorders = new ArrayList<Image>();

        for (PipeShape ps: this.pipeShapes) {
            this.imgPipeSquares.add(new Image(AssetLoader.textureRegionPipeSquare));

            for (Rectangle rect : ps.getBoundingRectangles()) {
                this.imgPipeBorders.add(new Image(AssetLoader.textureRegionPipeBorder));
            }
        }

        this.imgFinishRectangle = new Image(AssetLoader.textureRegionPipeFinish);

        slider = new Slider(
                -90 * SLIDER_MULTIPLER,
                90 * SLIDER_MULTIPLER,
                1 / SLIDER_MULTIPLER,
                true,
                AssetLoader.skin,
                "knob"
        );
        slider.setValue(0);
        slider.getStyle().knob.setMinWidth(W() * 0.05f);
        slider.getStyle().knob.setMinHeight(W() * 0.05f);
        slider.setWidth(W() * 0.2f);
        slider.setHeight(H() * 0.8f);
        slider.setPosition(W() * 0.8f, H() * 0.1f);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (slider.isDragging()) {
                    if (isAccelerometerEnabled) {
                        degreesToRotate = 0;
                        isAccelerometerEnabled = false;
                        if (buttonAccelerometerOn != null) {
                            buttonAccelerometerOn.setVisible(false);
                        }
                        if (buttonAccelerometerOff != null) {
                            buttonAccelerometerOff.setVisible(false);
                        }
                    }
                }

                float rot = slider.getValue() / SLIDER_MULTIPLER;
                float deltaRotation = world.rotate(rot);

                if (deltaRotation != 0f) {
                    updateRotation(deltaRotation);
                }
            }
        });

        this.imgMaskPaused = new Image(AssetLoader.textureRegionMaskPaused);

    }

    protected void initAssets() {

        imgBackground = new Image(AssetLoader.textureRegionBackground);
        imgBall = new Image(AssetLoader.textureRegionBall);

    }

    protected void initComponents() {

        final GameSoberModeRenderer instance = this;

        {
            buttonPause = new ImageButton(
                    AssetLoader.skin, "pause__plain");
            buttonPause.setSize(Consts.ELEMENT_WIDTH, Consts.ELEMENT_HEIGHT);
            buttonPause.setX(0);
            buttonPause.setY(H() - buttonPause.getHeight());

            buttonPause.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    pause();
                }
            });
        }

        {
            ClickListener clickListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    boolean e = !GameOptionsPreferences.isSoundEnabled();

                    GameOptionsPreferences.setSoundEnabled(e);
                    GameOptionsPreferences.save();

                    buttonSoundOn.setVisible(e);
                    buttonSoundOff.setVisible(!e);
                }
            };

            buttonSoundOn = new ImageButton(
                    AssetLoader.skin, "sound_on__plain");
            buttonSoundOn.setSize(Consts.ELEMENT_WIDTH, Consts.ELEMENT_HEIGHT);
            buttonSoundOn.setX(buttonPause.getX() + buttonPause.getWidth());
            buttonSoundOn.setY(H() - buttonSoundOn.getHeight());
            buttonSoundOn.addListener(clickListener);

            buttonSoundOff = new ImageButton(
                    AssetLoader.skin, "sound_off__plain");
            buttonSoundOff.setSize(Consts.ELEMENT_WIDTH, Consts.ELEMENT_HEIGHT);
            buttonSoundOff.setX(buttonPause.getX() + buttonPause.getWidth());
            buttonSoundOff.setY(H() - buttonSoundOff.getHeight());
            buttonSoundOff.addListener(clickListener);

            boolean enabled = GameOptionsPreferences.isSoundEnabled();
            buttonSoundOn.setVisible(enabled);
            buttonSoundOff.setVisible(!enabled);
        }

        if (isAccelerometerAvailable) {

            ClickListener clickListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    boolean e = !GameOptionsPreferences.isAccelerometerEnabled();

                    GameOptionsPreferences.setAccelerometerEnabled(e);
                    GameOptionsPreferences.save();

                    buttonAccelerometerOn.setVisible(e);
                    buttonAccelerometerOff.setVisible(!e);
                }
            };

            buttonAccelerometerOn = new ImageButton(
                    AssetLoader.skin, "accelerometer_on__plain");
            buttonAccelerometerOn.setSize(Consts.ELEMENT_WIDTH, Consts.ELEMENT_HEIGHT);
            buttonAccelerometerOn.setX(buttonSoundOn.getX() + buttonSoundOn.getWidth());
            buttonAccelerometerOn.setY(H() - buttonAccelerometerOn.getHeight());
            buttonAccelerometerOn.addListener(clickListener);

            buttonAccelerometerOff = new ImageButton(
                    AssetLoader.skin, "accelerometer_off__plain");
            buttonAccelerometerOff.setSize(Consts.ELEMENT_WIDTH, Consts.ELEMENT_HEIGHT);
            buttonAccelerometerOff.setX(buttonSoundOn.getX() + buttonSoundOn.getWidth());
            buttonAccelerometerOff.setY(H() - buttonAccelerometerOff.getHeight());
            buttonAccelerometerOff.addListener(clickListener);

            boolean enabled = GameOptionsPreferences.isAccelerometerEnabled();
            buttonAccelerometerOn.setVisible(enabled);
            buttonAccelerometerOff.setVisible(!enabled);

        }

        {
            buttonExit = new ImageButton(
                    AssetLoader.skin, "exit__plain");
            buttonExit.setSize(Consts.ELEMENT_WIDTH, Consts.ELEMENT_HEIGHT);
            buttonExit.setX(0);
            buttonExit.setY(0);

            buttonExit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    int gameLevel = gameOptions.level;

                    UserProfilePreferences.incFailedAttempts();
                    UserProfilePreferences.incTotalAttempts();
                    UserProfilePreferences.incTotalTime(totalRunTime);

                    UserProfilePreferences.save();

                    instance.fromScreen.dispose();
                }
            });
        }

    }

    public void start() {
        groupMasks.clear();

        hasStarted = true;

        world.start();

        if (isToPause) {
            pause();
        }
    }

    public void tryPause() {
        pause();
    }

    public void tryResume() {
        if (!hasStarted) {
            isToPause = false;
        }
    }

    public void pause() {

        if (!hasStarted) {
            isToPause = true;
            return;
        }

        isToPause = false;

        final GameSoberModeRenderer instance = this;

        {
            groupMasks.clear();
            groupMasks = new Group();

            imgMaskPaused.setPosition(0, 0);
            imgMaskPaused.setSize(W(), H());

            maskPauseButtonPlay = new ImageButton(AssetLoader.skin, "play__opacity");
            maskPauseButtonPlay.setSize(
                    MinWH() * Consts.BUTTON_PLAY_PERCENTAGE,
                    MinWH() * Consts.BUTTON_PLAY_PERCENTAGE);
            maskPauseButtonPlay.setX((W() / 2) - (maskPauseButtonPlay.getWidth() / 2));
            maskPauseButtonPlay.setY((H() / 2) - (maskPauseButtonPlay.getHeight() / 2));

            groupMasks.clear();
            stage.addActor(groupMasks);

            groupMasks.addActor(imgMaskPaused);
            groupMasks.addActor(maskPauseButtonPlay);

            maskPauseButtonPlay.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    instance.resume();
                }
            });
        }

        world.pause();
        slider.setDisabled(true);

    }

    public void resume() {

        maskPauseButtonPlay.setDisabled(true);

        final float duration = 1f;

        stage.addAction(
            Actions.sequence(
                Actions.run(new Runnable() {
                    public void run() {
                        groupMasks.addAction(
                            Actions.fadeOut(duration)
                        );
                    }
                }),
                Actions.delay(duration),
                Actions.run(new Runnable() {
                    public void run() {
                        groupMasks.clear();

                        world.resume();
                        slider.setDisabled(false);
                    }
                })
            )
        );

    }

    protected void gameOver() {

        int gameLevel = gameOptions.level;

        UserProfilePreferences.incFailedAttempts();
        UserProfilePreferences.incTotalAttempts();
        UserProfilePreferences.incTotalTime(totalRunTime);

        UserProfilePreferences.save();

        fromScreen.showGameOver(gameLevel, gameMode);

    }

    public void gameWon() {

        int savedLevel = UserProfilePreferences.getCurrentLevel();
        int gameLevel = gameOptions.level;

        if (gameLevel >= savedLevel) {
            UserProfilePreferences.setCurrentLevel(gameLevel);
        }

        UserProfilePreferences.incSuccessfulAttempts();
        UserProfilePreferences.incTotalAttempts();
        UserProfilePreferences.incTotalTime(totalRunTime);

        UserProfilePreferences.save();

        fromScreen.showLevelPassed(gameLevel, gameMode);

    }

    protected void after() {

        this.groupBackground.addActor(imgBackground);

        int j = -1;
        int i = -1;
        for (PipeShape ps : this.pipeShapes) {
            i++;
            this.groupMain.addActor(this.imgPipeSquares.get(i));

            for (Rectangle rect : ps.getBoundingRectangles()) {
                j++;
                this.groupMain.addActor(this.imgPipeBorders.get(j));
            }
        }

        this.groupMain.addActor(this.imgFinishRectangle);

        this.groupMain.addActor(imgBall);

    }

    protected void drawBackground() {

        imgBackground.setPosition(0, 0);
        imgBackground.setSize(W(), H());

    }

    protected void drawControlls() {

        groupControlls.addActor(slider);

        groupControlls.addActor(buttonPause);
        groupControlls.addActor(buttonSoundOn);
        groupControlls.addActor(buttonSoundOff);

        if (isAccelerometerAvailable) {
            groupControlls.addActor(buttonAccelerometerOn);
            groupControlls.addActor(buttonAccelerometerOff);
        }

        groupControlls.addActor(buttonExit);

    }


    protected void drawBall() {

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(ball.getX(), ball.getY(), ball.getRadius());

        imgBall.setPosition(
                ball.getX() - ball.getRadius(),
                ball.getY() - ball.getRadius());
        imgBall.setSize(
                ball.getRadius() * 2,
                ball.getRadius() * 2);

    }

    protected void drawPipes() {

        float x = world.getDistanceX();
        float y = world.getDistanceY();

        switch (world.gameState) {
            case READY:
            case PAUSED:
            case GAMEOVER:
                x = y = 0;
                break;
        }


        int j = -1;
        int i = -1;

        for (PipeShape ps : this.pipeShapes) {
            i++;

            {
                Rectangle bounds = ps.getBoundsRectangle();

                bounds.setX(bounds.getX() - x);
                bounds.setY(bounds.getY() - y);

                Image img = imgPipeSquares.get(i);
                img.setPosition(bounds.getX(), -bounds.getY() - bounds.getHeight());
                img.setSize(bounds.getWidth(), bounds.getHeight());
            }

            for (Rectangle rect : ps.getBoundingRectangles()) {
                j++;

                rect.setX(rect.getX() - x);
                rect.setY(rect.getY() - y);

                shapeRenderer.rect(
                        rect.getX(), rect.getY(),
                        rect.getWidth(), rect.getHeight());
                {
                    Image img = this.imgPipeBorders.get(j);
                    img.setPosition(rect.getX(), -rect.getY() - rect.getHeight());
                    img.setSize(rect.getWidth(), rect.getHeight());
                }

            }

        }

        {
            Rectangle rect = this.finishRectangle;
            rect.setX(rect.getX() - x);
            rect.setY(rect.getY() - y);

            shapeRenderer.rect(
                    rect.getX(), rect.getY(),
                    rect.getWidth(), rect.getHeight());
            {
                Image img = this.imgFinishRectangle;
                img.setPosition(rect.getX(), -rect.getY() - rect.getHeight());
                img.setSize(rect.getWidth(), rect.getHeight());
            }
        }

    }

    /**
     *
     * @return
     */
    protected boolean isGameOver() {

        for (Rectangle rect : this.pipeRectangles) {

            if (Intersector.overlaps(this.ballCircle, rect)) {
                return true;
            }

        }

        return false;

    }

    /**
     *
     * @return
     */
    protected boolean isGameWon() {

        if (Intersector.overlaps(this.ballCircle, this.finishRectangle)) {
            return true;
        }

        return false;

    }

    public void updateRotation(float deltaRotation) {

        if (deltaRotation != 0f) {
            shapeRenderer.rotate(0, 0, 1, deltaRotation);
            groupMain.rotateBy(-deltaRotation);
        }

    }

}
