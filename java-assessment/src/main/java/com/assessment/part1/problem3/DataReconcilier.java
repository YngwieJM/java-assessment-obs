package com.assessment.part1.problem3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReconcilier {

    public List<Map<String, Object>> mergeData(
        List<Map<String, Object>> sourceA,
        List<Map<String, Object>> sourceB,
        String key
    ){
        if (sourceA == null || sourceB == null){
            throw new IllegalArgumentException("Input data sources cannot be null");
        }
        if (key == null || key.trim().isEmpty()){
            throw new IllegalArgumentException("Key field cannot be null or empty");
        }

        Map<Object, Map<String, Object>> result = new HashMap<>();

        for (Map<String, Object> recordA : sourceA){
            if (!recordA.containsKey(key)){
                throw new IllegalArgumentException("Record in sourceA missing key field: " + key);
            }
            Object keyValue = recordA.get(key);
            result.put(keyValue, new HashMap<>(recordA));
        }

        for (Map<String, Object> recordB : sourceB){
            if (!recordB.containsKey(key)){
                throw new IllegalArgumentException("Record in sourceB missing key field: " + key);
            }
            Object keyValue = recordB.get(key);
            
            if (!result.containsKey(keyValue)){
                result.put(keyValue, new HashMap<>(recordB));
                continue;
            }

        Map<String, Object> mergedRecord = result.get(keyValue);

        for (Map.Entry<String, Object> entry : recordB.entrySet()){
                String field = entry.getKey();
                Object value = entry.getValue();

                if (value == null){
                    continue;
                }

                if (value instanceof String && ((String) value).isEmpty()){
                    continue;
                }

                mergedRecord.put(field, value);
            }
        }

    return new ArrayList<>(result.values());
    }

    public static void main(String[] args) {
        DataReconcilier reconcilier = new DataReconcilier();

        List<Map<String, Object>> sourceA = new ArrayList<>();
        List<Map<String, Object>> sourceB = new ArrayList<>();

        Map<String, Object> recordA1 = new HashMap<>();
        recordA1.put("id", 1);
        recordA1.put("name", "Apple");
        recordA1.put("price", 100);
        sourceA.add(recordA1);

        Map<String, Object> recordB1 = new HashMap<>();
        recordB1.put("id", 1);
        recordB1.put("name", "");
        recordB1.put("price", 150);
        sourceB.add(recordB1);

        List<Map<String, Object>> merged = reconcilier.mergeData(sourceA, sourceB, "id");
        System.out.println(merged);
    }
}
