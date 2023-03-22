package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.view.api.WindowState;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Implementation of the main state, it renders the game.
 */
public class GameViewState implements WindowState {
    private static Logger logger = Logger.getLogger("myLog");

    private final GameMap map;
    private boolean started;

    private int i = 0;

    /**
     * constructor for Game View window state.
     */
    public GameViewState() {
        this.map = new GameMapImpl();
        this.started = false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) {
        if (!started) {
            try {
                started = true;
                this.start(stage);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "GameViewError::", e);
            }
        } else {
            try {
                this.repaint(stage);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "GameViewError::", e);
            }
        }
    }

    private void start(final Stage stage) throws IOException {
        final EventHandler<KeyEvent> keyEventHandler = new KeyInputs();
        final EventHandler<MouseEvent> mouseEventHandler = new MouseInputs();
        final var root = new Pane();
        final var scene = new Scene(root, map.getWidth(),
            map.getHeight(), Color.BLACK);
        root.setBackground(null);
        scene.setFill(Color.GREEN);
        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyReleased(keyEventHandler);
        scene.setOnMouseMoved(mouseEventHandler);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseReleased(mouseEventHandler);
        scene.setOnMouseDragged(mouseEventHandler);
        scene.setOnMouseEntered(mouseEventHandler);
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }

    /**
     * Repaint the graphic aspect of the view.
     * @param stage The stage where the game is running
     * @throws IOException Exception if the stage can't be rendered.
     */
    public void repaint(final Stage stage) throws IOException {
        i++;
            stage.getScene().setFill(Color.rgb(i, i, i));
    }
}

