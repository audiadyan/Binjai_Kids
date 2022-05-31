package entity;

public abstract class Entity {
    protected int x, y;
    protected int speed;
    protected String direction;

    protected abstract void setDefaultValues();
    protected abstract void getImage();
}
