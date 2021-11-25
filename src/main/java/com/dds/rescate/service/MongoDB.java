package com.dds.rescate.service;


import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.util.GeneradorID;
import com.dds.rescate.util.Recomendacion_API;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class MongoDB {

    private static MongoClientURI connectionString;
    private static MongoClientSettings settings;
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static Morphia morphia;
    private static Datastore datastore;

    private static String password = "poner_password";

    private static MongoDB instance = null;

    public static MongoDB getInstance() {
        if(instance == null) {
            instance = new MongoDB();

            connectionString = new MongoClientURI("mongodb+srv://admin:"+password+"@patitas.o9qjv.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
            mongoClient = new MongoClient(connectionString);

            morphia = new Morphia();
            morphia.mapPackage("com.dds.rescate.util");
            datastore = morphia.createDatastore( mongoClient, "patitas");
            datastore.ensureIndexes();

            checkGenerador();
        }
        return instance;
    }

    public static Datastore getDatastore(){
        return datastore;
    }

    //el unico problema que escribe en la base 33 veces
    public int asignarID(){
        Query<GeneradorID> query = datastore.createQuery(GeneradorID.class)
                .filter("_id ==", 0);

        int id_actual = query.find().toList().get(0).ultimoID;

        UpdateOperations<GeneradorID> updates = datastore.createUpdateOperations(GeneradorID.class)
                .inc("ultimoID", 1);

        datastore.update(query, updates);
        return id_actual;
    }

    private static void checkGenerador(){
        try{
            Query<GeneradorID> query = datastore.createQuery(GeneradorID.class)
                    .filter("_id ==", 0);
            GeneradorID gen = query.find().toList().get(0);

        }
        catch (IndexOutOfBoundsException ve){
            GeneradorID gen = new GeneradorID();
            datastore.save(gen);
        }
    }

    public Recomendacion_API getUltimaReco(UsuarioDuenio usuario){
        return datastore.createQuery(Recomendacion_API.class).filter("id_user ==", usuario.getID())
                .order("-_id").limit(1)
                .find().toList().get(0);
    }

}
