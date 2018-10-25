package au.edu.curtin.myapplication;

public class AreaSchema {
    public static class AreaTable
    {
        public static final String TABLE_NAME = "areas";
        public static class Cols
        {
            public static final String AREAID = "areaId";
            public static final String TOWN = "townBool";
            public static final String AREADESCRIPTION = "areaDescription";
            public static final String STARRED = "starredBool";
            public static final String EXPLORED = "exploredBool";
            public static final String CURRENTOCCUPIED = "currentOccupiedBool";
            public static final String AREAROW = "areaRow";
            public static final String AREACOL = "areaCol";
        }
    }
}
