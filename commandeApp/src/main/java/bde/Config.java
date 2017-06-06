package bde;

/**
 * Config class
 *
 * Singleton permettant de stocker les éléments de configuration de l'application
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

    public class ServeurConfig {
        // Définit si le tri des serveurs en fonction de leur rôle doit se faire automatiquement ou non
        public boolean autoSortServeurs;

        private ServeurConfig() {
            autoSortServeurs = false;
        }
    }
}
