package au.edu.curtin.myapplication;

public class PlayerSchema {
    public static class PlayerTable
    {
        public static final String TABLE_NAME = "player";
        public static class Cols
        {
            public static final String PLAYERID = "playerID";
            public static final String ROWLOCATION = "rowLocation";
            public static final String COLLOCATION = "colLocation";
            public static final String CASH = "cash";
            public static final String PLAYERHEALTH = "playerHealth";
            public static final String EQUIPMENTMASS = "equipmentMass";
        }
    }
}
