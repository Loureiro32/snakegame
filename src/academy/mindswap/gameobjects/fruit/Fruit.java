package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {
    Position position;

    public Fruit(Position initialPosition) {
        position = initialPosition;

    }

    public Position getPosition() {
        return position;
    }
}
