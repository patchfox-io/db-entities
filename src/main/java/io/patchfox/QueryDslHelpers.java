package io.patchfox;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.CollectionPath;
import com.querydsl.core.types.dsl.CollectionPathBase;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class QueryDslHelpers {

    public void addPredicateForField(BooleanBuilder builder, Object fieldObject, String value, String fieldName, Class<?> fieldType) {
        
        if (value.contains(",")) {
            String[] values = value.split(",");
            
            // Clean and filter values
            List<String> cleanValues = Arrays.stream(values)
                                             .map(String::trim)
                                             .filter(s -> !s.isEmpty())
                                             .collect(Collectors.toList());
            
            if (cleanValues.isEmpty()) {
                return;
            }
            
            // Check if all values are simple equality checks (no operators like gt., lt., etc.)
            boolean allSimpleEquality = cleanValues.stream()
                                                   .allMatch(v -> !v.contains(".") || v.startsWith("eq."));
            
            if (allSimpleEquality && cleanValues.size() > 1) {
                // Use IN operator for better performance with large sets
                addInPredicateForField(builder, fieldObject, cleanValues, fieldName, fieldType);
            } else {
                // Fall back to OR chain for complex operators
                BooleanBuilder orBuilder = new BooleanBuilder();
                
                for (String singleValue : cleanValues) {
                    BooleanBuilder tempBuilder = new BooleanBuilder();
                    this.addSinglePredicateForField(tempBuilder, fieldObject, singleValue, fieldName, fieldType);
                    if (tempBuilder.getValue() != null) {
                        orBuilder.or(tempBuilder.getValue());
                    }
                }
                
                if (orBuilder.getValue() != null) {
                    builder.and(orBuilder.getValue());
                }
            }
        } else {
            this.addSinglePredicateForField(builder, fieldObject, value, fieldName, fieldType);
        }
    }

    public void addInPredicateForField(BooleanBuilder builder, Object fieldObject, List<String> values, String fieldName, Class<?> fieldType) {
        try {
            if (fieldObject instanceof NumberPath<?> numberPath) {
                if (fieldType == Long.class || fieldType == Long.TYPE) {
                    List<Long> longValues = values.stream()
                                                  .map(v -> v.startsWith("eq.") ? v.substring(3) : v)
                                                  .map(Long::parseLong)
                                                  .collect(Collectors.toList());
                    @SuppressWarnings("unchecked")
                    NumberPath<Long> longPath = (NumberPath<Long>) numberPath;
                    builder.and(longPath.in(longValues));
                    
                } else if (fieldType == Integer.class || fieldType == Integer.TYPE) {
                    List<Integer> intValues = values.stream()
                                                    .map(v -> v.startsWith("eq.") ? v.substring(3) : v)
                                                    .map(Integer::parseInt)
                                                    .collect(Collectors.toList());
                    @SuppressWarnings("unchecked")
                    NumberPath<Integer> intPath = (NumberPath<Integer>) numberPath;
                    builder.and(intPath.in(intValues));
                    
                } else if (fieldType == Double.class || fieldType == Double.TYPE) {
                    List<Double> doubleValues = values.stream()
                                                      .map(v -> v.startsWith("eq.") ? v.substring(3) : v)
                                                      .map(Double::parseDouble)
                                                      .collect(Collectors.toList());
                    @SuppressWarnings("unchecked")
                    NumberPath<Double> doublePath = (NumberPath<Double>) numberPath;
                    builder.and(doublePath.in(doubleValues));
                }
                
            } else if (fieldObject instanceof StringPath stringPath) {
                List<String> stringValues = values.stream()
                                                  .map(v -> v.startsWith("eq.") ? v.substring(3) : v)
                                                  .collect(Collectors.toList());
                builder.and(stringPath.in(stringValues));
                
            } else if (fieldObject instanceof DateTimePath<?> dateTimePath) {
                List<ZonedDateTime> dateValues = values.stream()
                                                       .map(v -> v.startsWith("eq.") ? v.substring(3) : v)
                                                       .map(ZonedDateTime::parse)
                                                       .collect(Collectors.toList());
                @SuppressWarnings("unchecked")
                DateTimePath<ZonedDateTime> zonedDateTimePath = (DateTimePath<ZonedDateTime>) dateTimePath;
                builder.and(zonedDateTimePath.in(dateValues));
                
            } else if (fieldObject instanceof BooleanPath booleanPath) {
                List<Boolean> boolValues = values.stream()
                                                 .map(Boolean::parseBoolean)
                                                 .collect(Collectors.toList());
                builder.and(booleanPath.in(boolValues));
            }
            
            log.info("=== ADDED IN PREDICATE FOR {}: {} values", fieldName, values.size());
            
        } catch (Exception e) {
            log.error("=== ERROR CREATING IN PREDICATE for field: {} with values: {}", fieldName, values, e);
            throw new IllegalArgumentException("Invalid values for IN predicate on field: " + fieldName, e);
        }
    }

    public void addSinglePredicateForField(BooleanBuilder builder, Object fieldObject, String value, String fieldName, Class<?> fieldType) {
        if (fieldObject instanceof NumberPath) {
            NumberPath<?> numberPath = (NumberPath<?>) fieldObject;
            
            // Use the actual field type to determine how to parse
            if (fieldType == Long.class || fieldType == long.class) {
                addLongPredicate(builder, (NumberPath<Long>) numberPath, value, fieldName);
            } else if (fieldType == Integer.class || fieldType == int.class) {
                addIntegerPredicate(builder, (NumberPath<Integer>) numberPath, value, fieldName);
            } else if (fieldType == Double.class || fieldType == double.class) {
                addDoublePredicate(builder, (NumberPath<Double>) numberPath, value, fieldName);
            } else {
                log.warn("=== UNSUPPORTED NUMBER TYPE: {} for field: {}", fieldType, fieldName);
            }
            
        } else if (fieldObject instanceof StringPath) {
            addStringPredicate(builder, (StringPath) fieldObject, value, fieldName);
            
        } else if (fieldObject instanceof DateTimePath) {
            addDateTimePredicate(builder, (DateTimePath<ZonedDateTime>) fieldObject, value, fieldName);
            
        } else if (fieldObject instanceof BooleanPath) {
            addBooleanPredicate(builder, (BooleanPath) fieldObject, value, fieldName);
            
        } else {
            log.warn("=== UNSUPPORTED FIELD TYPE: {} for field: {}", fieldObject.getClass(), fieldName);
        }
    }
        
    public void addLongPredicate(BooleanBuilder builder, NumberPath<Long> path, String value, String fieldName) {
        log.info("=== ADDING LONG PREDICATE FOR {}: {} with value: {}", fieldName, path, value);
        try {
            if (value.startsWith("gt.")) {
                builder.and(path.gt(Long.parseLong(value.substring(3))));
            } else if (value.startsWith("gte.")) {
                builder.and(path.goe(Long.parseLong(value.substring(4))));
            } else if (value.startsWith("lt.")) {
                builder.and(path.lt(Long.parseLong(value.substring(3))));
            } else if (value.startsWith("lte.")) {
                builder.and(path.loe(Long.parseLong(value.substring(4))));
            } else if (value.startsWith("eq.")) {
                builder.and(path.eq(Long.parseLong(value.substring(3))));
            } else {
                builder.and(path.eq(Long.parseLong(value)));
            }
        } catch (NumberFormatException e) {
            log.error("=== ERROR PARSING LONG VALUE: {} for field: {}", value, fieldName);
            throw new IllegalArgumentException("Invalid long value: " + value + " for field: " + fieldName);
        }
    }
    
    public void addIntegerPredicate(BooleanBuilder builder, NumberPath<Integer> path, String value, String fieldName) {
        log.info("=== ADDING INTEGER PREDICATE FOR {}: {} with value: {}", fieldName, path, value);
        try {
            if (value.startsWith("gt.")) {
                builder.and(path.gt(Integer.parseInt(value.substring(3))));
            } else if (value.startsWith("gte.")) {
                builder.and(path.goe(Integer.parseInt(value.substring(4))));
            } else if (value.startsWith("lt.")) {
                builder.and(path.lt(Integer.parseInt(value.substring(3))));
            } else if (value.startsWith("lte.")) {
                builder.and(path.loe(Integer.parseInt(value.substring(4))));
            } else if (value.startsWith("eq.")) {
                builder.and(path.eq(Integer.parseInt(value.substring(3))));
            } else {
                builder.and(path.eq(Integer.parseInt(value)));
            }
        } catch (NumberFormatException e) {
            log.error("=== ERROR PARSING INTEGER VALUE: {} for field: {}", value, fieldName);
            throw new IllegalArgumentException("Invalid integer value: " + value + " for field: " + fieldName);
        }
    }
    
    public void addDoublePredicate(BooleanBuilder builder, NumberPath<Double> path, String value, String fieldName) {
        log.info("=== ADDING DOUBLE PREDICATE FOR {}: {} with value: {}", fieldName, path, value);
        try {
            if (value.startsWith("gt.")) {
                builder.and(path.gt(Double.parseDouble(value.substring(3))));
            } else if (value.startsWith("gte.")) {
                builder.and(path.goe(Double.parseDouble(value.substring(4))));
            } else if (value.startsWith("lt.")) {
                builder.and(path.lt(Double.parseDouble(value.substring(3))));
            } else if (value.startsWith("lte.")) {
                builder.and(path.loe(Double.parseDouble(value.substring(4))));
            } else if (value.startsWith("eq.")) {
                builder.and(path.eq(Double.parseDouble(value.substring(3))));
            } else {
                builder.and(path.eq(Double.parseDouble(value)));
            }
        } catch (NumberFormatException e) {
            log.error("=== ERROR PARSING DOUBLE VALUE: {} for field: {}", value, fieldName);
            throw new IllegalArgumentException("Invalid double value: " + value + " for field: " + fieldName);
        }
    }
    
    public void addStringPredicate(BooleanBuilder builder, StringPath path, String value, String fieldName) {
        log.info("=== ADDING STRING PREDICATE FOR {}: {} with value: {}", fieldName, path, value);
        if (value.startsWith("eq.")) {
            builder.and(path.eq(value.substring(3)));
        } else {
            builder.and(path.containsIgnoreCase(value));
        }
    }
    
    public void addDateTimePredicate(BooleanBuilder builder, DateTimePath<ZonedDateTime> path, String value, String fieldName) {
        log.info("=== ADDING DATETIME PREDICATE FOR {}: {} with value: {}", fieldName, path, value);
        try {
            if (value.startsWith("gt.")) {
                builder.and(path.gt(ZonedDateTime.parse(value.substring(3))));
            } else if (value.startsWith("gte.")) {
                builder.and(path.goe(ZonedDateTime.parse(value.substring(4))));
            } else if (value.startsWith("lt.")) {
                builder.and(path.lt(ZonedDateTime.parse(value.substring(3))));
            } else if (value.startsWith("lte.")) {
                builder.and(path.loe(ZonedDateTime.parse(value.substring(4))));
            } else if (value.startsWith("eq.")) {
                builder.and(path.eq(ZonedDateTime.parse(value.substring(3))));
            } else {
                builder.and(path.eq(ZonedDateTime.parse(value)));
            }
        } catch (Exception e) {
            log.error("=== ERROR PARSING DATETIME VALUE: {} for field: {}", value, fieldName);
            throw new IllegalArgumentException("Invalid datetime value: " + value + " for field: " + fieldName);
        }
    }
    
    public void addBooleanPredicate(BooleanBuilder builder, BooleanPath path, String value, String fieldName) {
        log.info("=== ADDING BOOLEAN PREDICATE FOR {}: {} with value: {}", fieldName, path, value);
        try {
            boolean boolValue = Boolean.parseBoolean(value);
            builder.and(path.eq(boolValue));
        } catch (Exception e) {
            log.error("=== ERROR PARSING BOOLEAN VALUE: {} for field: {}", value, fieldName);
            throw new IllegalArgumentException("Invalid boolean value: " + value + " for field: " + fieldName);
        }
    }



    //
    //
    // BUILDER HELPERS 
    //
    //

    public class QueryBuilderResult {
        public final BooleanBuilder predicate;
        public final List<OrderSpecifier<?>> orderSpecifiers;
        
        public QueryBuilderResult(BooleanBuilder predicate, List<OrderSpecifier<?>> orderSpecifiers) {
            this.predicate = predicate;
            this.orderSpecifiers = orderSpecifiers;
        }
        
        public BooleanBuilder getPredicate() { return predicate; }
        public List<OrderSpecifier<?>> getOrderSpecifiers() { return orderSpecifiers; }
    }

    
    QueryBuilderResult getBuilderWithSort(Map<String, String> params, EntityPathBase root, Class entityClazz) {
        BooleanBuilder builder = new BooleanBuilder();
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            
            if (value == null || value.trim().isEmpty()) {
                continue;
            }
            
            log.info("=== PROCESSING: {} = {}", fieldName, value);
            
            try {
                if (fieldName.equals("sort")) {
                    // Handle sort parameter
                    handleSortParameter(orderSpecifiers, value, root, entityClazz);
                } else if (fieldName.contains(".")) {
                    // Handle nested field path (e.g., "dataset.name")
                    handleNestedField(builder, fieldName, value, root, entityClazz);
                } else {
                    // Handle simple field (original logic)
                    handleSimpleField(builder, fieldName, value, root, entityClazz);
                }
                
            } catch (NoSuchFieldException e) {
                log.warn("=== FIELD NOT FOUND: {} (skipping)", fieldName);
            } catch (IllegalAccessException e) {
                log.error("=== CANNOT ACCESS FIELD: {}", fieldName, e);
            } catch (Exception e) {
                log.error("=== ERROR PROCESSING FIELD: {} with value: {}", fieldName, value, e);
                throw new IllegalArgumentException("Error processing field: " + fieldName + " with value: " + value, e);
            }
        }
        
        log.info("=== FINAL PREDICATE: {}", builder.getValue());
        log.info("=== SORT SPECIFICATIONS: {}", orderSpecifiers.size());
        return new QueryBuilderResult(builder, orderSpecifiers);
    }


    // Keep the original method for backward compatibility
    public BooleanBuilder getBuilder(Map<String, String> params, EntityPathBase root, Class entityClazz) {
        return getBuilderWithSort(params, root, entityClazz).getPredicate();
    }



    public void handleSimpleField(BooleanBuilder builder, String fieldName, String value, 
                                EntityPathBase root, Class entityClazz) 
                                throws NoSuchFieldException, IllegalAccessException {
        // Get the actual field type from the entity class
        Field entityField = entityClazz.getDeclaredField(fieldName);
        Class<?> fieldType = entityField.getType();
        
        // Get the QueryDSL field object
        Field querydslField = root.getClass().getField(fieldName);
        Object fieldObject = querydslField.get(root);
        
        addPredicateForField(builder, fieldObject, value, fieldName, fieldType);
    }

    
    public void handleNestedField(BooleanBuilder builder, String fieldPath, String value, 
                                EntityPathBase root, Class entityClazz) 
                                throws NoSuchFieldException, IllegalAccessException {
        String[] pathParts = fieldPath.split("\\.");
        
        if (pathParts.length != 2) {
            throw new IllegalArgumentException("Only one level of nesting supported: " + fieldPath);
        }
        
        String parentFieldName = pathParts[0];  // "packages"
        String nestedFieldName = pathParts[1];  // "id"
        
        // Get the parent field from entity class to determine its type
        log.info("parentFieldName is: {}", parentFieldName);
        Field parentEntityField = entityClazz.getDeclaredField(parentFieldName);
        Class<?> parentEntityType = parentEntityField.getType();
        
        // Get the parent QueryDSL field object
        Field parentQuerydslField = root.getClass().getField(parentFieldName);
        Object parentFieldObject = parentQuerydslField.get(root);
        
        if (parentFieldObject == null) {
            log.warn("=== PARENT FIELD {} is null (not initialized), skipping nested field {}", 
                    parentFieldName, fieldPath);
            return;
        }
        
        // Check if this is a collection field BEFORE trying to get nested field type
        log.info("parentFieldObject is: {}", parentFieldObject);
        log.info("parentFieldObject class: {}", parentFieldObject.getClass().getName());
        
        log.info("=== Checking if {} isAssignableFrom CollectionPath", parentFieldObject.getClass().getName());
        log.info("=== Result: {}", SetPath.class.isAssignableFrom(parentFieldObject.getClass()));

        if (parentFieldObject instanceof CollectionPathBase) {
            log.info("=== DETECTED COLLECTION PATH, handling as collection");
            handleCollectionField(builder, (CollectionPathBase<?, ?, ?>) parentFieldObject, nestedFieldName, value, fieldPath);
            return;
        }
        
        // Only do this for non-collection fields
        log.info("nestedFieldName is: {}", nestedFieldName);
        Field nestedEntityField = parentEntityType.getDeclaredField(nestedFieldName);
        Class<?> nestedFieldType = nestedEntityField.getType();
        
        // Get the nested QueryDSL field object
        Field nestedQuerydslField = parentFieldObject.getClass().getField(nestedFieldName);
        Object nestedFieldObject = nestedQuerydslField.get(parentFieldObject);
        
        addPredicateForField(builder, nestedFieldObject, value, fieldPath, nestedFieldType);
    }


    private void handleCollectionField(BooleanBuilder builder, CollectionPathBase<?, ?, ?> collectionPath, 
                                    String nestedFieldName, String value, String fieldPath) {
        try {
            // Get the .any() path to navigate into the collection
            Object anyPath = collectionPath.any();
            
            // Get the nested field from the any() object
            Field nestedQuerydslField = anyPath.getClass().getField(nestedFieldName);
            Object nestedFieldObject = nestedQuerydslField.get(anyPath);
            
            // Determine the field type by looking at the collection's element type
            // This is a bit tricky - we need to get the actual entity class
            // For now, let's assume it's a Long id (most common case)
            Class<?> nestedFieldType = Long.class; // You might need to make this more dynamic
            
            addPredicateForField(builder, nestedFieldObject, value, fieldPath, nestedFieldType);
            
        } catch (Exception e) {
            log.error("=== ERROR HANDLING COLLECTION FIELD: {} with nested field: {}", 
                    fieldPath, nestedFieldName, e);
            throw new IllegalArgumentException("Error processing collection field: " + fieldPath, e);
        }
    }


    public void handleSortParameter(List<OrderSpecifier<?>> orderSpecifiers, String sortValue, 
                                    EntityPathBase root, Class entityClazz) 
                                    throws NoSuchFieldException, IllegalAccessException {
        log.info("=== PROCESSING SORT: {}", sortValue);
        
        // Handle multiple sort fields separated by comma
        String[] sortFields = sortValue.split(",");
        
        for (String sortField : sortFields) {
            sortField = sortField.trim();
            if (sortField.isEmpty()) continue;
            
            boolean ascending = true;
            String fieldPath = sortField;
            
            // Check for direction indicators
            if (sortField.startsWith("-")) {
                ascending = false;
                fieldPath = sortField.substring(1);
            } else if (sortField.startsWith("+")) {
                ascending = true;
                fieldPath = sortField.substring(1);
            } else if (sortField.toLowerCase().endsWith(".desc")) {
                ascending = false;
                fieldPath = sortField.substring(0, sortField.length() - 5);
            } else if (sortField.toLowerCase().endsWith(".asc")) {
                ascending = true;
                fieldPath = sortField.substring(0, sortField.length() - 4);
            }
            
            // Get the field object (supporting nested paths)
            Object fieldObject = getFieldObject(fieldPath, root, entityClazz);
            
            if (fieldObject != null) {
                OrderSpecifier<?> orderSpec = createOrderSpecifier(fieldObject, ascending, fieldPath);
                if (orderSpec != null) {
                    orderSpecifiers.add(orderSpec);
                    log.info("=== ADDED SORT: {} {}", fieldPath, ascending ? "ASC" : "DESC");
                }
            }
        }
    }

    public Object getFieldObject(String fieldPath, EntityPathBase root, Class entityClazz) 
                                throws NoSuchFieldException, IllegalAccessException {
        if (fieldPath.contains(".")) {
            // Handle nested field
            String[] pathParts = fieldPath.split("\\.");
            if (pathParts.length != 2) {
                throw new IllegalArgumentException("Only one level of nesting supported for sort: " + fieldPath);
            }
            
            String parentFieldName = pathParts[0];
            String nestedFieldName = pathParts[1];
            
            // Get parent field object
            Field parentQuerydslField = root.getClass().getField(parentFieldName);
            Object parentFieldObject = parentQuerydslField.get(root);
            
            if (parentFieldObject == null) {
                log.warn("=== PARENT FIELD {} is null for sort field {}", parentFieldName, fieldPath);
                return null;
            }
            
            // Get nested field object
            Field nestedQuerydslField = parentFieldObject.getClass().getField(nestedFieldName);
            return nestedQuerydslField.get(parentFieldObject);
            
        } else {
            // Handle simple field
            Field querydslField = root.getClass().getField(fieldPath);
            return querydslField.get(root);
        }
    }

    @SuppressWarnings("unchecked")
    public OrderSpecifier<?> createOrderSpecifier(Object fieldObject, boolean ascending, String fieldPath) {
        try {
            if (fieldObject instanceof ComparableExpressionBase) {
                ComparableExpressionBase<? extends Comparable> comparableField = 
                    (ComparableExpressionBase<? extends Comparable>) fieldObject;
                return ascending ? comparableField.asc() : comparableField.desc();
            } else {
                log.warn("=== FIELD {} is not sortable (not ComparableExpressionBase): {}", 
                        fieldPath, fieldObject.getClass().getSimpleName());
                return null;
            }
        } catch (Exception e) {
            log.error("=== ERROR CREATING ORDER SPECIFIER for field: {}", fieldPath, e);
            return null;
        }
    }


}
