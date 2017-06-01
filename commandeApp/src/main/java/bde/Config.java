package bde;

/**
 * Config class
 * Created by Thierry
 * 01/06/2017
 */
public class Config {
    private static Config ourInstance = new Config();

    public ServeurConfig serveurConfig;

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
        serveurConfig = new ServeurConfig();
    }

    public class ServeurConfig{
        public boolean autoSortServeurs;

        private ServeurConfig(){
            autoSortServeurs = false;
        }
    }
}
