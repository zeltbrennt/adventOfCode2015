import aocutil.AOCProblem;
import aocutil.InputReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;


public class Day12 implements AOCProblem {

    private String json;

    public Day12(String s) {
        json = InputReader.singleLine(s);
    }

    @Override
    public int solvePart1() {
        return Arrays.stream(json.replaceAll("[^0-9-]", " ").trim().split(" +")).mapToInt(Integer::parseInt).sum();
    }

    private boolean traverseJSONObj(JSONObject obj) {
        Iterator<String> iter = obj.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = obj.get(key);
            if (value instanceof JSONObject && traverseJSONObj((JSONObject) value)) iter.remove();
            else if (value instanceof JSONArray) traverseJSONArr((JSONArray) value);
            else if (value.equals("red")) return true;
        }
        return false;
    }

    private void traverseJSONArr(JSONArray arr) {
        Iterator<Object> iter = arr.iterator();
        while (iter.hasNext()) {
            Object o = iter.next();
            if (o instanceof JSONObject && traverseJSONObj((JSONObject) o)) iter.remove();
            else if (o instanceof JSONArray) traverseJSONArr((JSONArray) o);
        }
    }

    @Override
    public int solvePart2() {
        JSONObject obj = new JSONObject(json);
        traverseJSONObj(obj);
        json = obj.toString();
        return solvePart1();
    }
}
