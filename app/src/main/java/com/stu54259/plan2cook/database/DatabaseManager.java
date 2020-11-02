/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.stu54259.plan2cook.R;

public class DatabaseManager extends SQLiteOpenHelper {
    // Table Names
    public static final String TABLE_CATEGORY = "CATEGORY";
    public static final String TABLE_RECIPE = "RECIPE";
    public static final String TABLE_MEAL_PLAN = "MEAL_PLAN";
    public static final String TABLE_QUANTITY = "QUANTITY";
    public static final String TABLE_SHOPPING_LIST = "SHOPPING_LIST";
    public static final String TABLE_PLAN_RECIPES = "PLAN_RECIPES";
    public static final String TABLE_COURSE = "COURSE";
    public static final String TABLE_INGREDIENTS = "INGREDIENTS";
    public static final String TABLE_MEASUREMENT = "MEASUREMENT";
    public static final String TABLE_INGREDIENT_TYPE = "INGREDIENT_TYPE";
    public static final String TABLE_ALLERGENS = "ALLERGENS";
    public static final String TABLE_RECIPES_ALLERGENS = "RECIPES_ALLERGENS";
    public static final String TABLE_FAVOURITES = "FAVOURITES";
    // Logcat identifier
    private static final String LOG = "DatabaseManager";
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "Plan2Cook.db";
    // Common column names
    private static final String COL_ID = "id";
    private static final String COL_MEASUREMENT = "measurement";
    private static final String COL_INGREDIENT_TYPE = "ingredient_type";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_IMAGE = "image";
    // Category column names
    private static final String COL_CATEGORY_NAME = "category_name";
    // Recipe column names
    private static final String COL_RECIPE_NAME = "recipe_name";
    private static final String COL_SERVINGS = "servings";
    private static final String COL_CALORIES = "calories";
    private static final String COL_PREPARATION_TIME = "preparation_time";
    private static final String COL_METHOD = "method";
    private static final String COL_COURSE = "course";
    private static final String COL_CATEGORY = "category";
    private static final String COL_COST = "cost";
    private static final String COL_IMAGE2 = "image2";
    //Quantity column names
    private static final String COL_INGREDIENT_QUANTITY = "ingredient_quantity";
    private static final String COL_RECIPE = "recipe";
    private static final String COL_INGREDIENT = "ingredient";
    //Meal Plan column names
    private static final String COL_PLAN_NAME = "plan_name";
    private static final String COL_PLAN_RECIPE = "plan_recipe";
    //Shopping List column names
    private static final String COL_QUANTITY = "quantity";
    private static final String COL_PLANID = "planID";
    //Plan Recipes column names
    private static final String COL_DATE = "date";
    private static final String COL_DAY_OF_WEEK = "dayOfWeek";
    private static final String COL_DATE_STRING = "date_string";
    //Course column names
    private static final String COL_COURSE_NAME = "course_name";
    //Ingredients column names
    private static final String COL_INGREDIENT_NAME = "ingredient_name";
    // Create Table Quantity
    public static final String CREATE_TABLE_QUANTITY = "CREATE TABLE " + TABLE_QUANTITY + "(" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_INGREDIENT_QUANTITY + " NUMERIC, " +
            COL_RECIPE + " TEXT," + COL_INGREDIENT + " TEXT, FOREIGN KEY (" + COL_RECIPE + ") " +
            "REFERENCES " + TABLE_RECIPE + "(" + COL_RECIPE_NAME + "), FOREIGN KEY (" + COL_INGREDIENT + ") " +
            "REFERENCES " + TABLE_INGREDIENTS + "(" + COL_INGREDIENT_NAME + "))";
    //Measurement column names
    private static final String COL_MEASUREMENT_NAME = "measurement_name";
    //Ingredient Type column names
    private static final String COL_TYPE_NAME = "type_name";
    /* *************************************************************************************
     ************************* CREATE TABLE STATEMENTS **************************************
     ****************************************************************************************
     */
    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    // Create Table Ingredients
    public static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE " + TABLE_INGREDIENTS + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_INGREDIENT_NAME + " TEXT,"
            + COL_DESCRIPTION + " TEXT," + COL_MEASUREMENT_NAME + " TEXT," + COL_INGREDIENT_TYPE + " TEXT, " +
            "FOREIGN KEY (" + COL_MEASUREMENT_NAME + ") REFERENCES " + TABLE_MEASUREMENT + "(" + COL_MEASUREMENT_NAME + "), " +
            "FOREIGN KEY (" + COL_INGREDIENT_TYPE + ") REFERENCES " + TABLE_INGREDIENT_TYPE + "(" + COL_TYPE_NAME + "))";
    //Allergens column names
    private static final String COL_ALLERGEN_NAME = "allergen_name";
    // Create Table Category
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_CATEGORY_NAME
            + " TEXT," + COL_IMAGE + " INTEGER)";
    // Create Table Recipe
    private static final String CREATE_TABLE_RECIPE = "CREATE TABLE " + TABLE_RECIPE + "(" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_RECIPE_NAME + " TEXT," + COL_DESCRIPTION
            + " TEXT," + COL_COURSE + " TEXT," + COL_SERVINGS + " INTEGER," + COL_CALORIES + " NUMERIC,"
            + COL_PREPARATION_TIME + " NUMERIC," + COL_METHOD + " TEXT," + COL_CATEGORY + " TEXT,"
            + COL_IMAGE + " INTEGER," + COL_IMAGE2 + " TEXT," + COL_COST + " NUMERIC," + " FOREIGN KEY (" + COL_CATEGORY + ") REFERENCES " +
            TABLE_CATEGORY + "(" + COL_CATEGORY_NAME + "), FOREIGN KEY (" + COL_COURSE + ") REFERENCES " +
            TABLE_COURSE + "(" + COL_COURSE_NAME + "))";
    // Create Table Shopping List
    private static final String CREATE_TABLE_SHOPPING_LIST = "CREATE TABLE " + TABLE_SHOPPING_LIST +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_INGREDIENT_TYPE + " TEXT,"
            + COL_QUANTITY + " NUMERIC," + COL_INGREDIENT + " TEXT," + COL_MEASUREMENT + " TEXT," + COL_PLAN_NAME + " TEXT," +
            " FOREIGN KEY (" + COL_INGREDIENT_TYPE + ") REFERENCES " + TABLE_INGREDIENT_TYPE + "(" + COL_ID + "), " +
            "FOREIGN KEY (" + COL_QUANTITY + ") REFERENCES " + TABLE_QUANTITY + "(" + COL_ID + "), " +
            "FOREIGN KEY (" + COL_MEASUREMENT + ") REFERENCES " + TABLE_MEASUREMENT + "(" + COL_ID + "), " +
            "FOREIGN KEY (" + COL_PLAN_NAME + ") REFERENCES " + TABLE_MEAL_PLAN + "(" + COL_PLAN_NAME + "))";
    // Create Table Course
    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " + TABLE_COURSE + "(" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_COURSE_NAME + " TEXT," + COL_IMAGE + " INTEGER)";
    // Create Table Measurement
    private static final String CREATE_TABLE_MEASUREMENT = "CREATE TABLE " + TABLE_MEASUREMENT + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_MEASUREMENT_NAME + " TEXT)";
    // Create Table Ingredient Type
    private static final String CREATE_TABLE_INGREDIENT_TYPE = "CREATE TABLE " + TABLE_INGREDIENT_TYPE +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TYPE_NAME + " TEXT)";
    // Create Table Allergens
    private static final String CREATE_TABLE_ALLERGENS = "CREATE TABLE " + TABLE_ALLERGENS +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_ALLERGEN_NAME + " TEXT)";
    // Create Table Recipes_Allergens
    private static final String CREATE_TABLES_RECIPES_ALLERGENS = "CREATE TABLE " + TABLE_RECIPES_ALLERGENS +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_ALLERGEN_NAME + " TEXT," +
            COL_RECIPE + " TEXT," + "FOREIGN KEY (" + COL_ALLERGEN_NAME + ") REFERENCES " + TABLE_ALLERGENS +
            "(" + COL_ALLERGEN_NAME + "), " + "FOREIGN KEY (" + COL_RECIPE + ") REFERENCES " + TABLE_RECIPE +
            "(" + COL_RECIPE_NAME + "))";
    // Create Table Plan Recipes
    private static final String CREATE_TABLE_PLAN_RECIPES = "CREATE TABLE " + TABLE_PLAN_RECIPES + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_DATE + " DATE," + COL_DATE_STRING + " TEXT," +
            COL_DAY_OF_WEEK + " TEXT," + COL_RECIPE_NAME + " TEXT, FOREIGN KEY (" + COL_RECIPE_NAME + ") " +
            "REFERENCES " + TABLE_RECIPE + "(" + COL_RECIPE_NAME + "))";
    // Create Table Meal Plan
    private static final String CREATE_TABLE_MEAL_PLAN = "CREATE TABLE " +
            TABLE_MEAL_PLAN + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_PLAN_NAME + " TEXT," + COL_PLAN_RECIPE + " INTEGER," + " FOREIGN KEY (" + COL_PLAN_RECIPE + ") " +
            "REFERENCES " + TABLE_PLAN_RECIPES + "(" + COL_ID + "))";
    // Create Table Favourites
    private static final String CREATE_TABLE_FAVOURITES = "CREATE TABLE " + TABLE_FAVOURITES + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_RECIPE_NAME + " TEXT," +
            "FOREIGN KEY (" + COL_RECIPE_NAME + ") REFERENCES " + TABLE_RECIPE + "(" + COL_RECIPE_NAME + "))";
    public final String INSERT_MEASUREMENT =
            "INSERT INTO " + TABLE_MEASUREMENT + "("
                    + COL_MEASUREMENT_NAME + ") values ";
    public final String INSERT_INGREDIENT_TYPE =
            " INSERT INTO " + TABLE_INGREDIENT_TYPE + "("
                    + COL_TYPE_NAME + ") values ";
    public final String INSERT_INGREDIENT =
            " INSERT INTO " + TABLE_INGREDIENTS + "("
                    + COL_INGREDIENT_NAME + "," + COL_DESCRIPTION + ","
                    + COL_MEASUREMENT_NAME + "," + COL_INGREDIENT_TYPE + ") values ";
    public final String INSERT_CATEGORY =
            " INSERT INTO " + TABLE_CATEGORY + "("
                    + COL_CATEGORY_NAME + "," + COL_IMAGE + ") values ";
    public final String INSERT_COURSE =
            " INSERT INTO " + TABLE_COURSE + "("
                    + COL_COURSE_NAME + "," + COL_IMAGE + ") values ";
    public final String INSERT_RECIPE =
            " INSERT INTO " + TABLE_RECIPE + "("
                    + COL_RECIPE_NAME + "," + COL_DESCRIPTION + ","
                    + COL_COURSE + "," + COL_SERVINGS + ","
                    + COL_CALORIES + "," + COL_PREPARATION_TIME + ","
                    + COL_METHOD + "," + COL_CATEGORY + ","
                    + COL_IMAGE + ","
                    + COL_IMAGE2 + ","
                    + COL_COST + ") values";
    public final String INSERT_QUANTITY =
            " INSERT INTO " + TABLE_QUANTITY + "("
                    + COL_INGREDIENT_QUANTITY + ","
                    + COL_RECIPE + ","
                    + COL_INGREDIENT + ") values";
    public final String INSERT_ALLERGEN =
            " INSERT INTO " + TABLE_ALLERGENS + "("
                    + COL_ALLERGEN_NAME + ") values";
    public final String INSERT_RECIPES_ALLERGENS =
            " INSERT INTO " + TABLE_RECIPES_ALLERGENS + "("
                    + COL_ALLERGEN_NAME + ","
                    + COL_RECIPE + ") values";
    public final String INSERT_FAVOURITE =
            " INSERT INTO " + TABLE_FAVOURITES + "("
                    + COL_RECIPE_NAME + ") values";
    public final String INSERT_PLAN_RECIPE =
            " INSERT INTO " + TABLE_PLAN_RECIPES + "("
                    + COL_DATE + ","
                    + COL_DATE_STRING + ","
                    + COL_DAY_OF_WEEK + ","
                    + COL_RECIPE_NAME + ") values";
    public final String INSERT_PLAN =
            " INSERT INTO " + TABLE_MEAL_PLAN + "("
                    + COL_PLAN_NAME + ","
                    + COL_PLAN_RECIPE + ") values";
    String recipe;


    /**
     * All this code is created by Rhys Evans, STU54259.
     */

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_INGREDIENT_TYPE);
        db.execSQL(CREATE_TABLE_INGREDIENTS);
        db.execSQL(CREATE_TABLE_MEASUREMENT);
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_QUANTITY);
        db.execSQL(CREATE_TABLE_PLAN_RECIPES);
        db.execSQL(CREATE_TABLE_MEAL_PLAN);
        db.execSQL(CREATE_TABLE_ALLERGENS);
        db.execSQL(CREATE_TABLES_RECIPES_ALLERGENS);
        db.execSQL(CREATE_TABLE_SHOPPING_LIST);
        db.execSQL(CREATE_TABLE_FAVOURITES);
        Log.d("Tables created", "yes");
        // Creating Default Values: Measurement

        db.execSQL(getInsertMeasurementString("g"));
        db.execSQL(getInsertMeasurementString("tsp"));
        db.execSQL(getInsertMeasurementString("kg"));
        db.execSQL(getInsertMeasurementString("tbsp"));
        db.execSQL(getInsertMeasurementString("400g tin"));
        db.execSQL(getInsertMeasurementString("ml"));
        db.execSQL(getInsertMeasurementString("clove"));
        db.execSQL(getInsertMeasurementString("sheet"));
        db.execSQL(getInsertMeasurementString("250g pouch"));
        db.execSQL(getInsertMeasurementString("piece"));
        db.execSQL(getInsertMeasurementString("cube"));
        db.execSQL(getInsertMeasurementString("pinch"));

        // Creating Default Values: Course

        db.execSQL(getInsertCourse("Main Course", R.drawable.main));
        db.execSQL(getInsertCourse("_Desserts", R.drawable.desserts_course));
        db.execSQL(getInsertCourse("Side", R.drawable.side));
        db.execSQL(getInsertCourse("Soups", R.drawable.soups));
        db.execSQL(getInsertCourse("Starters", R.drawable.starters));

        // Creating Default Values: Category

        db.execSQL(getInsertCategory("_Comfort", R.drawable.comfort));
        db.execSQL(getInsertCategory("Mexican", R.drawable.mexican));
        db.execSQL(getInsertCategory("_Italian", R.drawable.italian));
        db.execSQL(getInsertCategory("_Desserts", R.drawable.desserts));
        db.execSQL(getInsertCategory("_Asian", R.drawable.asian));
        db.execSQL(getInsertCategory("American", R.drawable.american));
        db.execSQL(getInsertCategory("_Greek", R.drawable.greek));
        db.execSQL(getInsertCategory("African", R.drawable.african));
        db.execSQL(getInsertCategory("_Vegetarian", R.drawable.vegetarian));


        // Creating Default Values: Ingredient_Type

        db.execSQL(getInsertIngredientType("spices"));
        db.execSQL(getInsertIngredientType("poultry"));
        db.execSQL(getInsertIngredientType("frozen"));
        db.execSQL(getInsertIngredientType("store cupboard"));
        db.execSQL(getInsertIngredientType("fresh herbs"));
        db.execSQL(getInsertIngredientType("Milk & Dairy"));
        db.execSQL(getInsertIngredientType("fresh herbs"));
        db.execSQL(getInsertIngredientType("fruit & vegetables"));
        db.execSQL(getInsertIngredientType("bakery"));
        db.execSQL(getInsertIngredientType("meat"));
        db.execSQL(getInsertIngredientType("vegetarian "));
        db.execSQL(getInsertIngredientType("Alcohol "));
        db.execSQL(getInsertIngredientType("Deli "));

        // Creating Default Values: Allergen Names

        db.execSQL(getInsertAllergenName("Celery"));
        db.execSQL(getInsertAllergenName("Gluten"));
        db.execSQL(getInsertAllergenName("Crustaceans"));
        db.execSQL(getInsertAllergenName("Eggs"));
        db.execSQL(getInsertAllergenName("Fish"));
        db.execSQL(getInsertAllergenName("Lupin"));
        db.execSQL(getInsertAllergenName("Milk"));
        db.execSQL(getInsertAllergenName("Molluscs"));
        db.execSQL(getInsertAllergenName("Mustard"));
        db.execSQL(getInsertAllergenName("Peanuts"));
        db.execSQL(getInsertAllergenName("Sesame"));
        db.execSQL(getInsertAllergenName("Soya"));
        db.execSQL(getInsertAllergenName("Sulphur Dioxide"));
        db.execSQL(getInsertAllergenName("Sulphites"));
        db.execSQL(getInsertAllergenName("Tree Nuts"));


        // Creating Default Values: Ingredients
        db.execSQL(getInsertIngredients("cayenne pepper", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("cumin powder", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("garlic powder", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("olive oil", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("salt & pepper", "", "pinch", "spices"));
        db.execSQL(getInsertIngredients("turkey mince", "", "g", "poultry"));
        db.execSQL(getInsertIngredients("onions", "finely chopped", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("spring onions", "chopped", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("peppers", "chopped", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("chopped tomatoes", "", "400g tin", "store cupboard"));
        db.execSQL(getInsertIngredients("kidney beans", "drained", "", "store cupboard"));
        db.execSQL(getInsertIngredients("fresh coriander", "chopped", "tbsp", "fresh herbs"));
        db.execSQL(getInsertIngredients("tortilla chips", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("low-fat crème fraîche", "", "tbsp", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("chicken stock", "", "cube", "store cupboard"));
        db.execSQL(getInsertIngredients("dried egg noodles", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("frozen peas", "", "g", "frozen"));
        db.execSQL(getInsertIngredients("shredded cooked chicken", "", "g", "poultry"));
        db.execSQL(getInsertIngredients("red chilli", "thinly sliced", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("fresh root ginger", "peeled and thinly sliced", "piece", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("garlic", "thinly sliced", "clove", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("soy sauce", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("chilli powder", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("tomato purée", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("chicken thigh fillets", "", "g", "poultry"));
        db.execSQL(getInsertIngredients("broccoli", "", "g", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("red pepper", "thinly sliced", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("green pepper", "thinly sliced", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("boiling water", "", "ml", ""));
        db.execSQL(getInsertIngredients("cous cous", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("lime", "zest and juice", "", "fruit"));
        db.execSQL(getInsertIngredients("sweet potatoes", "peeled chopped", "kg", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("oat flour", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("fresh basil", "roughly chopped", "", "fresh herbs"));
        db.execSQL(getInsertIngredients("sugar", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("pitted black olives", "", "", "store cupboard"));
        db.execSQL(getInsertIngredients("vegan mozzarella", "", "", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("extra virgin olive oil", "", "", "store cupboard"));
        db.execSQL(getInsertIngredients("red onion", "thinly sliced", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("dried chilli flakes", "", "", "store cupboard"));
        db.execSQL(getInsertIngredients("baked beans", "", "400g tin", "store cupboard"));
        db.execSQL(getInsertIngredients("free range eggs", "", "", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("cheddar", "grated", "g", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("fruit & vegetables oil", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("garam masala", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("tumeric", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("coconut milk", "", "400g tin", "store cupboard"));
        db.execSQL(getInsertIngredients("butternut squash", "deseeded and chopped", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("chickpeas", "", "400g tin", "store cupboard"));
        db.execSQL(getInsertIngredients("basmati rice", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("salt", "", "", "spices"));
        db.execSQL(getInsertIngredients("frozen cod fillets", "thawed almost completely and finely chopped", "", "frozen"));
        db.execSQL(getInsertIngredients("flat leaf parsley", "finely chopped", "", "fresh herbs"));
        db.execSQL(getInsertIngredients("Thai green curry paste", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("sesame oil", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("sesame oil", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("frozen sliced green beans", "", "g", "frozen"));
        db.execSQL(getInsertIngredients("beansprouts", "", "g", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("fresh egg noodles", "", "g", "Deli"));
        db.execSQL(getInsertIngredients("Sriracha chilli sauce", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("tikka curry paste", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("cashew nuts", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("cooked basmati rice", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("hot smoked paprika", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("oranges", "juice only", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("pork shoulder joint, boneless", "rind removed", "g", "meat"));
        db.execSQL(getInsertIngredients("soft bread rolls", "split in half", "", "bakery"));
        db.execSQL(getInsertIngredients("ready made coleslaw", "", "", "Deli"));
        db.execSQL(getInsertIngredients("sour cream", "", "", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("salad", "", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("onion granules", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("garlic granules", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("dried oregano", "", "", "spices"));
        db.execSQL(getInsertIngredients("paprika", "", "", "spices"));
        db.execSQL(getInsertIngredients("plain flour", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("cornflour", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("milk", "", "ml", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("cauliflower", "", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("potatoes", "washed and cut into wedges", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("courgette", "sliced", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("harissa paste", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("cherry tomatoes", "halved", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("lemon", "juice only", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("plain yoghurt", "", "tbsp", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("tortilla wraps", "", "", "bakery"));
        db.execSQL(getInsertIngredients("peanut butter", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("garlic clove", "crushed", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("water", "", "tbsp", ""));
        db.execSQL(getInsertIngredients("round lettuce", "", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("cucumber", "", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("pitta breads", "", "", "bakery"));
        db.execSQL(getInsertIngredients("bread", "thick slices", "", "bakery"));
        db.execSQL(getInsertIngredients("pasta", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("feta", "crumbled", "g", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("mixed beans", "", "400g tin", "store cupboard"));
        db.execSQL(getInsertIngredients("chipolte paste", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("chicken breasts", "", "g", "poultry"));
        db.execSQL(getInsertIngredients("carrot", "grated", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("hummus", "", "tbsp", "Deli"));
        db.execSQL(getInsertIngredients("smoked streaky bacon", "roughly chopped", "", "meat"));
        db.execSQL(getInsertIngredients("mince beef", "", "g", "meat"));
        db.execSQL(getInsertIngredients("onion", "finely chopped", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("celery", "roughly chopped", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("mixed herbs", "", "tsp", "spices"));
        db.execSQL(getInsertIngredients("red wine", "", "ml", "Alcohol"));
        db.execSQL(getInsertIngredients("spaghetti", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("parmesan", "", "", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("mayonaise", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("tinned tuna", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("cannellini beans", "", "400g tin", "store cupboard"));
        db.execSQL(getInsertIngredients("avocado", "thickly sliced", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("light soy sauce", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("salsa", "", "", "store cupboard"));
        db.execSQL(getInsertIngredients("iceberg lettuce", "", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("chestnut mushrooms", "sliced", "g", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("dijon mustard", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("curry powder", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("vegetarian sausages", "", "", "vegetarian "));
        db.execSQL(getInsertIngredients("gravy granules", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("baking potatoes", "", "large", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("beef stock cube", "", "", "store cupboard"));
        db.execSQL(getInsertIngredients("butter", "", "", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("microwave rice", "", "250g pouch", "store cupboard"));
        db.execSQL(getInsertIngredients("dark soy sauce", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("yellow pepper", "cut in to chunks", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("orange pepper", "cut in to chunks", "", "fruit & vegetables"));
        db.execSQL(getInsertIngredients("gnocchi", "", "g", "store cupboard"));
        db.execSQL(getInsertIngredients("pork sausages", "", "", "meat"));
        db.execSQL(getInsertIngredients("honey", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("tomato ketchup", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("vinegar", "", "tsp", "store cupboard"));
        db.execSQL(getInsertIngredients("sweet chilli sauce", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("whole chicken", "", "kg", "poultry"));
        db.execSQL(getInsertIngredients("dried lasagne", "", "sheet", "store cupboard"));
        db.execSQL(getInsertIngredients("grated mozzarella", "", "tbsp", "Milk & Dairy"));
        db.execSQL(getInsertIngredients("tomato pasta sauce", "", "tbsp", "store cupboard"));
        db.execSQL(getInsertIngredients("ricotta cheese", "", "tbsp", "Milk & Dairy"));

        // Creating Default Values: Recipes

        db.execSQL(getInsertRecipe("Turkey Taco Bowls", "Make your own seasoning for these tasty and affordable turkey taco bowls.", "Main Course", 4, 534.0, 30, "1.To make the Mexican seasoning, mix the ingredients together in a bowl. 2.Put the turkey mince and Mexican seasoning into a large bowl and mix well with your hands. 3.Heat half the olive oil in a frying pan over a high heat and cook the onions, spring onions and peppers for 3 minutes. Add a pinch of salt and pepper. Remove the vegetables from the pan and set aside. 4.Heat the remaining oil in the same pan and cook the turkey mince for 4 minutes, or until browned, making sure you break it up as finely as possible with a wooden spoon. 5.Add the chopped tomatoes, kidney beans and a pinch of salt and pepper. Lower the heat and simmer for 10 minutes. 6.Stir in the cooked vegetables and most of the coriander, and simmer for another 2 minutes. 7.Evenly cover the base of four bowls with the tortilla chips. Divide the turkey mixture between the bowls and top each with a tablespoon of crème fraîche. Sprinkle over the remaining coriander and serve.", "Mexican", R.drawable.turkey_taco_bowls, null, 1.25));

        db.execSQL(getInsertRecipe("5-minute chicken noodle soup", "Four ingredients and five minutes are all you need for this ridiculously easy chicken noodle soup! Add the optional extras to take the flavour to another level!", "Soups", 1, 208.0, 30, "1. Pour 700ml/1¼ pint freshly boiled water into a saucepan, add the stock cube and stir well to dissolve. Add the noodles, spring onions, peas and chicken, bring to the boil and cook for 5 minutes, or until the noodles and peas are cooked and the chicken is hot through. 2. Ladle into a deep, wide bowl and, if you fancy, top with the chilli, ginger, garlic, coriander and soy sauce.", "_Asian", R.drawable.chicken_noodle, null, 1.10));

        db.execSQL(getInsertRecipe("Speedy chicken couscous", "This chicken couscous makes a quick and tasty dinner that will double as a decent packed lunch for tomorrow. Part of Robs chicken meal prep series. Check out his chicken cashew curry and barbecue-style chicken wraps for the full set.", "Main Course", 2, 310.0, 30, "1. Mix together the chicken seasoning ingredients in a large bowl. Add the chicken and coat well in the mixture. 2. Heat a teaspoon of the olive oil in a frying pan over a high heat, add the chicken and cook for 3 minutes, or until cooked through. Remove the chicken and set aside. 3. Pour the boiling water into a bowl, add the couscous, lime zest and a pinch of salt and pepper. Cover with a plate and leave for 5 minutes. 4. Heat a the remaining olive oil in the pan and cook the broccoli and peppers for 4 minutes. 5. Return the chicken to the pan with the spring onions and cook for 2 minutes. 6. Meanwhile, add the lime juice and coriander to the couscous, stir and put the plate back on for back on for 2 minutes. 7. Use a fork to fluff up the couscous, take the chicken off the heat and enjoy together!", "_Greek", R.drawable.speedy_chicken_couscous, null, 1.40));

        db.execSQL(getInsertRecipe("Sweet potato pizza", "Rachel Ama packs plenty of nutrition into her vegan sweet potato pizza. Freeze the bases and sauce separately for an instant pizza when you need it.", "Main Course", 4, 380.0, 30, "1. To make the pizza base, put the sweet potatoes in a large saucepan, cover with water and bring to the boil. Cook for 10 minutes, or until softened. 2. Drain the potatoes and place in a bowl with the oat flour and some salt and pepper. Mash together with a potato masher then use your hands to bring together in a ball. 3. Roll the dough out into your desired pizza shape and place on a lightly oiled baking tray. 4. Preheat the oven to 200C/180C Fan/Gas 6. 5. To make the sauce, heat the oil in a saucepan and fry the onion and garlic until softened. Add the tomatoes, season with salt, pepper and sugar and simmer for 10 minutes. Add the basil, then remove from the heat. 6. Spread some of the sauce over the pizza base and sprinkle with the olives. Top with the vegan cheese and basil and bake for 10 minutes, or until the vegan cheese has melted and the pizza base has lightly browned. Meanwhile, heat the oil in a small saucepan and fry the red onions until slightly caramelised. 8. Remove the pizza from the oven and top with the caramelised red onions. Season with salt and pepper, drizzle over some extra virgin olive oil and serve.", "_Italian", R.drawable.sweet_potato_pizza, null, 1.25));

        db.execSQL(getInsertRecipe("Baked bean shakshuka", "A tin of baked beans makes this shakshuka so easy to make. It can be ready in less than 10 minutes, much quicker and cheaper than going out for brunch!", "Main Course", 1, 400.0, 10, "1. Heat the oil in a frying pan. Fry the onion and pepper over a low heat for 5 minutes, or until softened and beginning to brown, stirring regularly with a wooden spoon. 2. Add the cumin and chilli flakes and cook for 20–30 seconds, stirring constantly. Tip the beans into the pan and cook for 2 minutes, stirring occasionally. 3. Make two dips in the beans and break an egg into each one. 4. Reduce the heat to low, cover with a lid and cook for 5 minutes, without stirring, until the egg whites are set but the yolks are still runny. 5. Sprinkle with the cheese, if using, and serve with toast on the side for dipping.", "African", R.drawable.african, null, 0.95));

        db.execSQL(getInsertRecipe("Easy chickpea and squash curry", "When you want a healthy, cheap dinner, this easy vegan curry recipe is the one. Serves four or freezes really well.", "Main Course", 2, 550.0, 60, "1. Heat the oil in a large saucepan, add the onions, garlic and ginger and fry over a medium heat for 5 minutes. Add the garam masala, fresh red chilli, cumin and turmeric and cook until the onions have softened. 2. Pour in the tomatoes and coconut milk, bring to a boil then add pumpkin and chickpeas. Reduce the heat, cover with a lid and simmer for 30–40 minutes. Check occasionally and add water if needed. 3. After 30 minutes, remove the lid and cook for another 15 minutes until the sauce has thickened. 4. Place the rice in a large bowl and cover with water, carefully discard the water and repeat until the water runs clear. 5. Place the rice in a saucepan and add 500ml/18fl oz water, season with salt and bring to the boil. Reduce the heat to its lowest setting, cover with a lid and cook for 10 minutes. Remove the rice from the heat and leave to stand with the lid on for a further 10 minutes. 6. Serve the curry with rice and a sprinkling of fresh coriander.", "_Asian", R.drawable.easy_chickpea, null, 1.50));

        // Creating Default Values: Quantities
        db.execSQL(getInsertQuantity(2.0, "Turkey Taco Bowls", "cayenne pepper"));
        db.execSQL(getInsertQuantity(2.0, "Turkey Taco Bowls", "cumin powder"));
        db.execSQL(getInsertQuantity(2.0, "Turkey Taco Bowls", "garlic powder"));
        db.execSQL(getInsertQuantity(1.0, "Turkey Taco Bowls", "olive oil"));
        db.execSQL(getInsertQuantity(0.0, "Turkey Taco Bowls", "salt & pepper"));
        db.execSQL(getInsertQuantity(500.0, "Turkey Taco Bowls", "turkey mince"));
        db.execSQL(getInsertQuantity(2.0, "Turkey Taco Bowls", "olive oil"));
        db.execSQL(getInsertQuantity(2.0, "Turkey Taco Bowls", "onions"));
        db.execSQL(getInsertQuantity(6.0, "Turkey Taco Bowls", "spring onions"));
        db.execSQL(getInsertQuantity(2.0, "Turkey Taco Bowls", "peppers"));
        db.execSQL(getInsertQuantity(1.0, "Turkey Taco Bowls", "chopped tomatoes"));
        db.execSQL(getInsertQuantity(1.0, "Turkey Taco Bowls", "kidney beans"));
        db.execSQL(getInsertQuantity(3.0, "Turkey Taco Bowls", "fresh coriander"));
        db.execSQL(getInsertQuantity(180.0, "Turkey Taco Bowls", "tortilla chips"));
        db.execSQL(getInsertQuantity(4.0, "Turkey Taco Bowls", "crème fraîche"));
        db.execSQL(getInsertQuantity(1.0, "5-minute chicken noodle soup", "chicken stock"));
        db.execSQL(getInsertQuantity(50.0, "5-minute chicken noodle soup", "dried egg noodles"));
        db.execSQL(getInsertQuantity(2.0, "5-minute chicken noodle soup", "spring onions"));
        db.execSQL(getInsertQuantity(50.0, "5-minute chicken noodle soup", "frozen peas"));
        db.execSQL(getInsertQuantity(50.0, "5-minute chicken noodle soup", "shredded cooked chicken"));
        db.execSQL(getInsertQuantity(0.5, "5-minute chicken noodle soup", "red chilli"));
        db.execSQL(getInsertQuantity(0.25, "5-minute chicken noodle soup", "fresh root ginger"));
        db.execSQL(getInsertQuantity(0.5, "5-minute chicken noodle soup", "garlic"));
        db.execSQL(getInsertQuantity(0.0, "5-minute chicken noodle soup", "fresh coriander"));
        db.execSQL(getInsertQuantity(0.5, "5-minute chicken noodle soup", "soy sauce"));
        db.execSQL(getInsertQuantity(1.0, "Speedy chicken couscous", "fresh coriander"));
        db.execSQL(getInsertQuantity(2.0, "Speedy chicken couscous", "chilli powder"));
        db.execSQL(getInsertQuantity(1.0, "Speedy chicken couscous", "tomato purée"));
        db.execSQL(getInsertQuantity(1.0, "Speedy chicken couscous", "soy sauce"));
        db.execSQL(getInsertQuantity(0.0, "Speedy chicken couscous", "salt & pepper"));
        db.execSQL(getInsertQuantity(2.0, "Speedy chicken couscous", "olive oil"));
        db.execSQL(getInsertQuantity(200.0, "Speedy chicken couscous", "chicken thigh fillets"));
        db.execSQL(getInsertQuantity(150.0, "Speedy chicken couscous", "broccoli"));
        db.execSQL(getInsertQuantity(1.0, "Speedy chicken couscous", "red pepper"));
        db.execSQL(getInsertQuantity(1.0, "Speedy chicken couscous", "green pepper"));
        db.execSQL(getInsertQuantity(4.0, "Speedy chicken couscous", "spring onions"));
        db.execSQL(getInsertQuantity(200.0, "Speedy chicken couscous", "boiling water"));
        db.execSQL(getInsertQuantity(150.0, "Speedy chicken couscous", "cous cous"));
        db.execSQL(getInsertQuantity(0.5, "Speedy chicken couscous", "lime"));
        db.execSQL(getInsertQuantity(2.0, "Speedy chicken couscous", "fresh coriander"));
        db.execSQL(getInsertQuantity(1.0, "Sweet potato pizza", "sweet potatoes"));
        db.execSQL(getInsertQuantity(200.0, "Sweet potato pizza", "oat flour"));
        db.execSQL(getInsertQuantity(0.0, "Sweet potato pizza", "salt & pepper"));
        db.execSQL(getInsertQuantity(1.0, "Sweet potato pizza", "olive oil"));
        db.execSQL(getInsertQuantity(1.0, "Sweet potato pizza", "onions"));
        db.execSQL(getInsertQuantity(4.0, "Sweet potato pizza", "garlic"));
        db.execSQL(getInsertQuantity(2.0, "Sweet potato pizza", "chopped tomatoes"));
        db.execSQL(getInsertQuantity(1.0, "Sweet potato pizza", "sugar"));
        db.execSQL(getInsertQuantity(0.0, "Sweet potato pizza", "fresh basil"));
        db.execSQL(getInsertQuantity(0.0, "Sweet potato pizza", "pitted black olives"));
        db.execSQL(getInsertQuantity(1.0, "Sweet potato pizza", "olive oil"));
        db.execSQL(getInsertQuantity(0.0, "Sweet potato pizza", "vegan mozzarella"));
        db.execSQL(getInsertQuantity(0.0, "Sweet potato pizza", "extra virgin olive oil"));
        db.execSQL(getInsertQuantity(1.0, "Sweet potato pizza", "red onion"));
        db.execSQL(getInsertQuantity(2.0, "Baked bean shakshuka", "olive oil"));
        db.execSQL(getInsertQuantity(0.5, "Baked bean shakshuka", "onions"));
        db.execSQL(getInsertQuantity(0.5, "Baked bean shakshuka", "red pepper"));
        db.execSQL(getInsertQuantity(0.5, "Baked bean shakshuka", "cumin powder"));
        db.execSQL(getInsertQuantity(0.0, "Baked bean shakshuka", "dried chilli flakes"));
        db.execSQL(getInsertQuantity(1.0, "Baked bean shakshuka", "baked beans"));
        db.execSQL(getInsertQuantity(2.0, "Baked bean shakshuka", "free range eggs"));
        db.execSQL(getInsertQuantity(30.0, "Baked bean shakshuka", "cheddar"));
        db.execSQL(getInsertQuantity(1.0, "Easy chickpea and squash curry", "vegetable oil"));
        db.execSQL(getInsertQuantity(1.0, "Easy chickpea and squash curry", "onions"));
        db.execSQL(getInsertQuantity(4.0, "Easy chickpea and squash curry", "garlic"));
        db.execSQL(getInsertQuantity(1.0, "Easy chickpea and squash curry", "fresh root ginger"));
        db.execSQL(getInsertQuantity(1.0, "Easy chickpea and squash curry", "garam masala"));
        db.execSQL(getInsertQuantity(0.5, "Easy chickpea and squash curry", "cumin powder"));
        db.execSQL(getInsertQuantity(0.5, "Easy chickpea and squash curry", "tumeric"));
        db.execSQL(getInsertQuantity(2.0, "Easy chickpea and squash curry", "chopped tomatoes"));
        db.execSQL(getInsertQuantity(1.0, "Easy chickpea and squash curry", "coconut milk"));
        db.execSQL(getInsertQuantity(0.5, "Easy chickpea and squash curry", "butternut squash"));
        db.execSQL(getInsertQuantity(1.0, "Easy chickpea and squash curry", "chickpeas"));
        db.execSQL(getInsertQuantity(0.0, "Easy chickpea and squash curry", "fresh coriander"));
        db.execSQL(getInsertQuantity(150.0, "Easy chickpea and squash curry", "basmati rice"));
        db.execSQL(getInsertQuantity(0.0, "Easy chickpea and squash curry", "salt & pepper"));

        // Creating Default Values: Recipes Allergens

        db.execSQL(getINSERT_RECIPES_ALLERGENS("Eggs", "5-minute chicken noodle soup"));
        db.execSQL(getINSERT_RECIPES_ALLERGENS("Eggs", "Baked bean shakshuka"));
        db.execSQL(getINSERT_RECIPES_ALLERGENS("Gluten", "Turkey Taco Bowls"));
        db.execSQL(getINSERT_RECIPES_ALLERGENS("Gluten", "5-minute chicken noodle soup"));
        db.execSQL(getINSERT_RECIPES_ALLERGENS("Milk", "Turkey Taco Bowls"));
        db.execSQL(getINSERT_RECIPES_ALLERGENS("Milk", "Sweet potato pizza"));
        db.execSQL(getINSERT_RECIPES_ALLERGENS("Milk", "Baked bean shakshuka"));

        db.execSQL(getInsertFavourite("Baked bean shakshuka"));

    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL_PLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASUREMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAN_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUANTITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLERGENS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES_ALLERGENS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITES);


        onCreate(db);

    }

    // Insert into Course String
    public String getInsertCourse(String course_name, int image) {
        return INSERT_COURSE + " ( " +
                "'" + course_name + "'," +
                "'" + image + "')";
    }

    // Insert into favourite String
    public String getInsertFavourite(String recipe_name) {
        return INSERT_FAVOURITE + " ( " +
                "'" + recipe_name + "')";
    }

    // Insert into Measurement String
    public String getInsertMeasurementString(String measurement_name) {
        return INSERT_MEASUREMENT + " ( " +
                "'" + measurement_name + "')";
    }

    // Insert into ingredient type string
    public String getInsertIngredientType(String ingredient_type) {
        return INSERT_INGREDIENT_TYPE + " ( " +
                "'" + ingredient_type + "')";
    }

    // Insert into ingredients string
    public String getInsertIngredients(String ingredient_name, String description, String measurement_name, String ingredient_type) {
        return INSERT_INGREDIENT + " ( " +
                "'" + ingredient_name + "'," +
                "'" + description + "'," +
                "'" + measurement_name + "'," +
                "'" + ingredient_type + "')";
    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    // Insert into recipe string
    public String getInsertRecipe(String recipe_name, String description, String course, Integer servings, Double calories,
                                  Integer preparation_time, String method, String category, Integer image, String image2, Double cost) {
        return INSERT_RECIPE + " ( " +
                "'" + recipe_name + "'," +
                "'" + description + "'," +
                "'" + course + "'," +
                "'" + servings + "'," +
                "'" + calories + "'," +
                "'" + preparation_time + "'," +
                "'" + method + "'," +
                "'" + category + "'," +
                "'" + image + "'," +
                "'" + image2 + "'," +
                "'" + cost + "')";
    }

    // Insert into category string
    public String getInsertCategory(String category_name, int image) {
        return INSERT_CATEGORY + " ( " +
                "'" + category_name + "'," +
                "'" + image + "')";
    }

    // Insert into quantity table string

    public String getInsertQuantity(Double ingredient_quantity, String recipe, String ingredient) {
        return INSERT_QUANTITY + " ( " +
                "'" + ingredient_quantity + "'," +
                "'" + recipe + "'," +
                "'" + ingredient + "')";
    }
    // Insert into allergen table string


    public String getInsertAllergenName(String allergen_name) {
        return INSERT_ALLERGEN + " ( " +
                "'" + allergen_name + "')";
    }

    // Insert into recipes allergens string
    public String getINSERT_RECIPES_ALLERGENS(String allergen_name, String recipe) {
        return INSERT_RECIPES_ALLERGENS + " ( " +
                "'" + allergen_name + "'," +
                "'" + recipe + "')";
    }

    // Insert into plan recipes string
    public String getINSERT_PLAN_RECIPE(Integer date, String date_string, String dayOfWeek, String recipe_name) {
        return INSERT_PLAN_RECIPE + " ( " +
                "'" + date + "'," +
                "'" + date_string + "'," +
                "'" + dayOfWeek + "'," +
                "'" + recipe_name + "')";
    }

    public String getInsertPlan(String plan_name, Integer plan_recipe) {
        return INSERT_PLAN + " ( " +
                "'" + plan_name + "'," +
                "'" + plan_recipe + "')";
    }

    public void createPlanRecipe(Integer date, String date_string, String dayOfWeek, String recipe_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(getINSERT_PLAN_RECIPE(date, date_string, dayOfWeek, recipe_name));
    }

    public void createPlan(String plan_name, Integer plan_recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select last_insert_rowid() as id", null);
        if (c.moveToFirst()) {
            plan_recipe = c.getInt(c.getColumnIndex("id"));
        }
        db.execSQL(getInsertPlan(plan_name, plan_recipe));
    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void addIngredients(double ingredient_quantity, String recipe, String name, String ingredient,
                               String description, String measurement_name, String ingredient_type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(getInsertQuantity(ingredient_quantity, recipe, name));
        db.execSQL(getInsertIngredients(ingredient, description, measurement_name, ingredient_type));
    }

    public void addRecipe(String recipe_name, String description, String course, int servings, Double calories,
                          Integer preparation_time, String method, String category, Integer image, String image2, Double cost, String allergen_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(getInsertRecipe(recipe_name, description, course, servings, calories, preparation_time, method, category, image, image2, cost));
        db.execSQL(getINSERT_RECIPES_ALLERGENS(allergen_name, recipe_name));
    }

    public void addFavourite(String search_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(getInsertFavourite(search_name));
    }

    // Delete from
    public void delete(String TABLE) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE);

    }

    // Close Database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
