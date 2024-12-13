package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.util.Iterator;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake(Direction.RIGHT);
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit(); // uncomment when it's time to introduce fruits

        while (true) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
            Field.drawFruit(fruit);
        }
    }

    private int generateRandom(int min, int max) {
        return (int) (Math.random() * max) + min;
    }

    private Position generateRandomPosition() {
        int col = generateRandom(1, Field.getWidth() - 1);
        int row = generateRandom(1, Field.getHeight() - 1);

        return new Position(col, row);
    }


    private void generateFruit() {
        boolean isValidPosition = false;
        Iterator<Position> iterator = snake.getFullSnake().iterator();

        do {
            Position position = generateRandomPosition();

            while (iterator.hasNext()) {
                Position currentPosition = iterator.next();

                if (position.equals(currentPosition)) {
                    isValidPosition = false;
                    break;
                } else {

                    isValidPosition = true;
                }
            }


        } while (isValidPosition == false);


        fruit = new Fruit(generateRandomPosition());
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {
        Position head = Snake.getHead();

        if (fruit.getPosition().equals(head)) {
            snake.increaseSize();
            generateFruit();
        }
        if (snake.getTail().equals(head)) {
            snake.die();
        }
        if (head.getCol() >= Field.getWidth() - 1 || head.getRow() >= Field.getHeight() - 1) {
            snake.die();
        }
    }
}
