package com.v2.remt.engine;

import org.json.JSONException;
import org.json.JSONObject;

//getting value from JSONObject need handeling errors
//its not cute to put try-catch everywhere
//so this class was created

public class JSONgetter
{
    public static String GetString(JSONObject content, String name)
    {
        String value = "";
        try
        {
            value = content.getString(name);
        }
        catch (JSONException e)
        {

        }
        return value;
    }

    public static boolean GetBool(JSONObject content, String name)
    {
        boolean value = false;
        try
        {
            value = content.getBoolean(name);
        }
        catch (JSONException e)
        {

        }
        return value;
    }

    public static int GetInt(JSONObject content, String name)
    {
        int value = -1;
        try
        {
            value = content.getInt(name);
        }
        catch (JSONException e)
        {

        }
        return value;
    }

    public static Double GetDouble(JSONObject content, String name)
    {
        Double value = -1.0;
        try
        {
            value = content.getDouble(name);
        }
        catch (JSONException e)
        {

        }
        return value;
    }
}
