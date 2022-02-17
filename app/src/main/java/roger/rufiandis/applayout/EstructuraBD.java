package roger.rufiandis.applayout;

public abstract class EstructuraBD
{

    public static final String TABLE_NAME = "CLIENT";
    public static final String COLUMN_NAME1 = "Codi";
    public static final String COLUMN_NAME2 = "Nom";
    public static final String COLUMN_NAME3 = "Cognoms";
    public static final String COLUMN_NAME4 = "Telefon";
    public static final String COLUMN_NAME5 = "Email";
    public static final String COLUMN_NAME6 = "Genere";
    public static final String COLUMN_NAME7 = "Actiu";
    public static final String TEXT_TYPE = "INTEGER";
    public static final String INT_TYPE = "Text";
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String COMMA_SEP = " ,";
            
    public static final String SQL_CREATE_ENTRIES = 
            "CREATE TABLE " + TABLE_NAME + " " +
                "(" + COLUMN_NAME1 + INT_TYPE + PRIMARY_KEY + COMMA_SEP
                + COLUMN_NAME2 + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME3 + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME4 + INT_TYPE + COMMA_SEP
                + COLUMN_NAME5 + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME6 + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME7 + INT_TYPE
                + ")";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
