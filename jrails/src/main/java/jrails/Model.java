//Todo:
// 1) Whether these two strings are treated as same while copying through find method:
// a) Programming Languages: Build, Prove, b) Programming Languages:Build,Prove,
// Lowercase upper case matters in string?
package jrails;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Model {

    private int id = 0;
    private static int counter = readMaxIdFromDatabase();
    // Extract package name dynamically using reflection
    // private static String packageName = Model.class.getPackageName();
    private static String packageName = "P4TestClass4Model";

    private static int readMaxIdFromDatabase() {
        System.out.println("Finding Counter value");
        int maxId = 0;
        File file = new File("database.csv");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                        System.out.println("value inside readMaxIdFromDatabase " + values[1]);
                        if (values.length > 0) {
                            // Parse the numeric value and update maxId
                            int currentId = Integer.parseInt(values[1].replaceAll("^\"|\"$", ""));
                            maxId = Math.max(maxId, currentId);
                        }
                    }
                    // System.out.println("readDatabaseFile index " + index);
                }
            } catch (IOException e) {
                throw new UnsupportedOperationException("Error reading from the database file.", e);
            }
        }
        System.out.println("Max id already present in CSV file " + maxId);
        return maxId;
    }

    private static void determinePackageName(String className) {
        int lastDotIndex = className.lastIndexOf('.');
        if (lastDotIndex != -1) {
            packageName = className.substring(0, lastDotIndex);
        }
    }

    public void save() {
        // Check the types of @Column fields
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class) && !isValidColumnType(field.getType())) {
                throw new UnsupportedOperationException("Invalid type for @Column field: " + field.getType());
            }
        }
        String className = getClass().getName();
        determinePackageName(className);
        String updatedPackageName = packageName;
        System.out.println("packageName " + updatedPackageName);
        System.out.println("reading database file");
        // List<Model> models = readDatabaseFile();
        // System.out.println("Reading length of the csv file " + models.size());

        if (id == 0) {
            id = ++counter;
            System.out.println("Add new model to the database with id " + id);
        } else {
            // If the model has a non-zero id, check if it's already in the database

            // Model existingModel = findById(models, id);
            // if (existingModel != null) {
            // System.out.println("existingModel model");
            // // If an existing model is found, remove it from the list before saving the
            // // updated model
            // models.remove(existingModel);
            // } else {
            // // If no existing model is found with the given id, throw an exception
            // throw new UnsupportedOperationException(
            // "Cannot save a model with a non-zero id that is not already in the
            // database.");
            // }
        }
        List<Model> models = new ArrayList<>();
        models.add(this);
        System.out.println("writing database file with current id " + this.id());
        writeDatabaseFile(models, true);
    }

    private static List<Model> readDatabaseFile() {
        List<Model> models = new ArrayList<>();
        File file = new File("database.csv");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int index = 1;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("0") || !line.trim().isEmpty()) {
                        Model model = parseModel(line);
                        if (model != null) {
                            System.out.println("readDatabaseFile index " + index);
                            models.add(model);
                            // System.out.println("Model length after insertion " + models.size());
                            System.out.println("line " + line);
                            index++;
                        }
                    }
                    // System.out.println("readDatabaseFile index " + index);
                }
            } catch (IOException e) {
                throw new UnsupportedOperationException("Error reading from the database file.", e);
            }
        }

        return models;
    }

    private static Object parsePrimitiveValue(Class<?> fieldType, String value) {
        // System.out.println("fieldType " + fieldType);
        if (fieldType == String.class) {
            System.out.println("value to be parsed " + value);
            return value.replaceAll("^\"|\"$", "");
        } else if (fieldType == int.class) {
            if (value.isEmpty() || !value.matches("\\d+")) {
                return 0; // Default value for empty string or non-numeric value
            }
            return Integer.parseInt(value);
        } else if (fieldType == boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            // Handle other primitive types as needed
            throw new UnsupportedOperationException("Unsupported primitive type: " + fieldType);
        }
    }

    private static Model parseModel(String line) {
        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        System.out.println("parseModel split value array length " + values.length);
        if (values.length < 2 || values[1].trim().isEmpty()) {
            System.out.println("skip lines error");
            // Skip lines without a valid type identifier or ID
            return null;
        }
        // Assuming your model classes are in the same package as the Model class
        String className = values[0];
        // String className = packageName + "." + values[0].trim();
        // String className = "jrails." + values[0].trim();
        String[] actualValues = Arrays.copyOfRange(values, 2, values.length);

        Class<? extends Model> modelClass;
        try {
            modelClass = (Class<? extends Model>) Class.forName(className);
            // System.out.println("modelClass " + modelClass);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Unknown model type: " + className, e);
        }
        // Assuming that fields are in the same order as they are serialized
        try {
            Model model = modelClass.getDeclaredConstructor().newInstance();
            int idValue = Integer.parseInt(values[1].trim());
            System.out.println("Id to be saved in the model " + idValue);
            model.id = idValue;
            // }
            Field[] fields = modelClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(Column.class)) {
                    fields[i].setAccessible(true);
                    Object fieldValue = parsePrimitiveValue(fields[i].getType(), actualValues[i]);
                    // Trim string values
                    if (fieldValue instanceof String) {
                        fieldValue = ((String) fieldValue).trim();
                    }
                    fields[i].set(model, fieldValue);
                } else {
                    System.out.println("Not column annotated fields" + fields[i]);
                }
            }

            return model;
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception details
            throw new UnsupportedOperationException("Error creating and setting fields during deserialization.", e);
        }
    }

    public static <T> T find(Class<T> c, int id) {
        System.out.println("id to find " + id);
        System.out.println("class given to find " + c);
        String className = c.getName();
        determinePackageName(className);
        String updatedPackageName = packageName;
        System.out.println("packageName " + updatedPackageName);
        List<Model> models = readDatabaseFile();
        Model model = findById(models, id);

        if (model != null && c.isInstance(model)) {
            System.out.println("given class " + c + " is an instance of model class");
            return c.cast(model);
        }

        // System.out.println("Inside parseModel ")
        return null;
    }

    private static void writeDatabaseFile(List<Model> models, boolean isOldFile) {
        System.out.println("Inside writeDatabaseFile " + isOldFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database.csv", isOldFile))) {
            System.out.println("Length of the model list to be written " + models.size());
            for (Model model : models) {
                String serialized = model.serialize();
                System.out.println("String after serialization " + serialized);
                String[] values = serialized.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println("serialized split value array length " + values.length);
                if (values.length < 2 || values[1].trim().isEmpty()) {
                    System.out.println("skip lines error");
                    // Skip lines without a valid type identifier or ID
                    return;
                }
                if (!serialized.isEmpty() && !values[1].equals("0")) { // Skip empty lines
                    System.out.println("Writing serializated string " + serialized);
                    writer.write(serialized);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new UnsupportedOperationException("Error writing to the database file.", e);
        }
    }

    private String serialize() {
        StringBuilder serialized = new StringBuilder();
        // Include the id field in the output at the beginning
        serialized.append(getClass().getName()).append(",");
        serialized.append(id).append(",");
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(this);

                    // Handle null values - Todo do we need to handle null values here
                    if (value != null) {
                        // Escape special characters and enclose strings in double quotes
                        // how many special characters can be there?

                        if (value instanceof String) {
                            System.out.println("Inside serialize " + (String) value);
                            serialized.append("\"").append(escapeSpecialCharacters(((String) value).trim()))
                                    .append("\"");
                        } else {
                            System.out.println("Inside serialize " + value.toString());
                            serialized.append(value.toString());
                        }
                    }

                    serialized.append(",");
                } catch (IllegalAccessException e) {
                    throw new UnsupportedOperationException("Error accessing field during serialization.", e);
                }
            }
        }

        // Remove the last trailing comma
        if (serialized.length() > 0) {
            serialized.setLength(serialized.length() - 1);
        }

        return serialized.toString();
    }

    private boolean isValidColumnType(Class<?> fieldType) {
        return fieldType == String.class || fieldType == int.class || fieldType == boolean.class;
    }

    public int id() {
        return id;
    }

    public static <T> List<T> all(Class<T> c) {
        String className = c.getName();
        determinePackageName(className);
        String updatedPackageName = packageName;
        System.out.println("packageName " + updatedPackageName);
        List<Model> models = readDatabaseFile();
        List<T> filteredModels = new ArrayList<>();

        for (Model model : models) {
            if (c.isInstance(model)) {
                filteredModels.add(c.cast(model));
            }
        }

        return filteredModels;
    }

    

    public static void reset() {
        System.out.println("Resetting the file");
        writeDatabaseFile(new ArrayList<>(), false);
    }

    private static Model findById(List<Model> models, int id) {
        for (Model model : models) {
            if (model.id() == id) {
                return model;
            }
        }
        return null;
    }

    private String escapeSpecialCharacters(String input) {
        // Escape double quotes by doubling them
        return input.replace("\"", "\"\"");
    }

    // Custom exception to represent model-related operations
    public static class ModelOperationException extends RuntimeException {
        public ModelOperationException(String message) {
            super(message);
        }

        public ModelOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
