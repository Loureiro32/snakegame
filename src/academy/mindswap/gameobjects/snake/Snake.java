package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private static LinkedList<Position> body;
    private Direction direction;
    private boolean alive;

    public Snake(Direction initialDirection) {
        body = new LinkedList<>();
        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            body.add(new Position(i + 3, 15));
        }
        direction = initialDirection;
        alive = true;
    }

    public static Position getHead() {
        return body.getLast();
    }

    public void increaseSize() {
        Position tail = getTail();

        body.addFirst(new Position(tail.getCol(), tail.getRow()));
    }

    private boolean isOppositeDirection(Direction currentDirection, Direction newDirection) {
        return (currentDirection == Direction.RIGHT && newDirection == Direction.LEFT) || (currentDirection == Direction.LEFT && newDirection == Direction.RIGHT) || (currentDirection == Direction.UP && newDirection == Direction.DOWN) || (currentDirection == Direction.DOWN && newDirection == Direction.UP);
    }

    public void move(Direction direction) {
        Position head = getHead();
        body.removeFirst();
        if (isOppositeDirection(this.direction, direction)) {
            direction = this.direction;
        }
        switch (direction) {
            case UP:

                head = new Position(head.getCol(), head.getRow() - 1);
                break;

            case DOWN:

                head = new Position(head.getCol(), head.getRow() + 1);
                break;

            case LEFT:

                head = new Position(head.getCol() - 1, head.getRow());
                break;

            case RIGHT:

                head = new Position(head.getCol() + 1, head.getRow());
                break;

        }
        body.addLast(head);
        this.direction = direction; //! Fix the error snake moves always to the right
    }

    public void move() {
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getTail() {
        return body.getFirst();
    }

    public LinkedList<Position> getFullSnake() {
        return body;
    }

    public int getSnakeSize() {
        return 0;
    }
}

