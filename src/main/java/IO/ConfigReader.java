package IO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigReader implements IConfigReader<BasicConfig> {

    private static final ConfigReader configReader = new ConfigReader();
    private BasicConfig basicConfig = null;

    private ConfigReader(){}

    public static ConfigReader getInstance(){
        return configReader;
    }

    @Override
    public BasicConfig readConfig() {
        try {
            if (basicConfig==null) {
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/config.json"));
                Gson gson = new Gson();
                JsonObject object = (JsonObject) new JsonParser().parse(br);
                basicConfig = gson.fromJson(object, BasicConfig.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicConfig;
    }
}
