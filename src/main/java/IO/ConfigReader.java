package IO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigReader implements IConfigReader<BasicConfig> {

    private static final ConfigReader configReader = new ConfigReader();

    private ConfigReader(){}

    public static ConfigReader getInstance(){
        return configReader;
    }

    @Override
    public BasicConfig readConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/config.json"));
            Gson gson = new Gson();
            JsonObject object = (JsonObject) new JsonParser().parse(br);
            return gson.fromJson(object, BasicConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new BasicConfig();
        }
    }
}
