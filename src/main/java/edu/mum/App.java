package edu.mum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.mum.domain.RequestModel;
import edu.mum.domain.ResponseModel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String msg = "{\"payload\":\"user not fount\",\"success\":false}";
        RequestModel model = JSON.parseObject(msg, RequestModel.class);
        System.out.println(model.toString());
        ResponseModel response = JSON.parseObject(msg, ResponseModel.class);
        System.out.println(response.toString());

        JSONObject object = JSON.parseObject(msg);
        if (object.containsKey("cmd")) {
            System.out.println("it's a message");
        } else if (object.containsKey("success")) {
            System.out.println("it's a response");
        }
    }
}
