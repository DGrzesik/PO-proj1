package agh.ics.oop;

public enum MapDirections {
    NORTH,NORTHEAST,EAST,SOUTHEAST, SOUTH,SOUTHWEST, WEST,NORTHWEST;

    public String toString(MapDirections x) {
        return switch (x) {
            case EAST -> ("Wschód");
            case SOUTHEAST -> ("Południowy Wschód");
            case NORTHEAST -> ("Północny Wschód");
            case WEST -> ("Zachód");
            case NORTHWEST -> ("Północny Zachód");
            case SOUTHWEST -> ("Południowy Zachód");
            case NORTH -> ("Północ");
            case SOUTH -> ("Południe");
        };
    }

    public MapDirections next() {
        return switch (this) {
            case EAST -> (SOUTHEAST);
            case WEST -> (NORTHWEST);
            case NORTH -> (NORTHEAST);
            case SOUTH -> (SOUTHWEST);
            case NORTHEAST -> (EAST);
            case NORTHWEST -> (NORTH);
            case SOUTHEAST -> (SOUTH);
            case SOUTHWEST -> (WEST);
        };
    }

    public MapDirections previous() {
        return switch (this) {
            case EAST -> (NORTHEAST);
            case SOUTHWEST -> (SOUTH);
            case SOUTHEAST -> (EAST);
            case NORTHWEST -> (WEST);
            case NORTHEAST -> (NORTH);
            case WEST -> (SOUTHWEST);
            case NORTH -> (NORTHWEST);
            case SOUTH -> (SOUTHEAST);
        };
    }
    public Vector2d toUnitVector(){
        return switch (this) {
            case EAST -> (new Vector2d(1, 0));
            case WEST -> (new Vector2d(-1, 0));
            case NORTH -> (new Vector2d(0, 1));
            case SOUTH -> (new Vector2d(0, -1));
            case NORTHEAST -> (new Vector2d(1,1));
            case NORTHWEST -> (new Vector2d(-1,1));
            case SOUTHEAST -> (new Vector2d(1,-1));
            case SOUTHWEST -> (new Vector2d(-1,-1));
        };
    }
}
