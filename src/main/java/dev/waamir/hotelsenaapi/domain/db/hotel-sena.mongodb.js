//
// Author: William Samir Peña Ortega
// Email: waamirdev@gmail.com
// MongoDB shell commands
//

// Stablishing the connection to the remote server
const conn = new Mongo("mongodb://admin:admin@35.233.158.114:27017/");

// Getting creating the database
const db = conn.getDB("hotel_sena_api");
db.dropDatabase();

// Collecion structure for collection sedes
db.createCollection("roles", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["name"],
            properties: {
                name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                }
            }
        }
    }
});

db.roles.createIndex({ name : 1 }, { unique : true });


