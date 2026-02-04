package programacion.avanzada.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import programacion.avanzada.model.*;

import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class MongoConnection {

    @Produces
    @ApplicationScoped
    public Datastore datastore() {
        Properties props = new Properties();

        // Usar el ClassLoader de la propia clase es más confiable en entornos SE
        try (InputStream is = MongoConnection.class.getClassLoader().getResourceAsStream("mongo.properties")) {
            if (is == null) {
                throw new RuntimeException("¡Archivo mongo.properties no encontrado en src/main/resources!");
            }
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Error crítico: No se pudo cargar mongo.properties", e);
        }

        String uri = props.getProperty("mongo.uri");
        String db = props.getProperty("mongo.database");

        if (uri == null || db == null) {
            throw new RuntimeException("Propiedades mongo.uri o mongo.database ausentes en el archivo");
        }

        MongoClient client = MongoClients.create(uri);
        Datastore datastore = Morphia.createDatastore(client, db);

// Registramos todas las clases del modelo para que Morphia cree los Codecs
        datastore.getMapper().map(java.util.List.of(
                Author.class,
                Book.class,
                Customer.class,
                Inventory.class,
                LineItem.class,
                PurchaseOrder.class
        ));

        datastore.ensureIndexes();
        return datastore;
    }
}