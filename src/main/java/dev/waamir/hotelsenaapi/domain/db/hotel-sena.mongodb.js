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

// Collecion structure for collection roles
db.createCollection("roles", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["name"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.roles.createIndex({ name: 1 }, { unique: true });

// Collection structure for collection users
db.createCollection("users", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["username", "password", "enabled", "role_id", "created_at"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                username: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                password: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                enabled: {
                    bsonType: "bool",
                    description: "must be a boolean and is required"
                },
                role_id: {
                    bsonType: "objectId",
                    description: "must be an objecId and is required"
                },
                created_at: {
                    bsonType: "date",
                    description: "must be a date and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.users.createIndex({ username: 1 }, { unique: true });

// Collection structure for collection tokens
db.createCollection("tokens", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["token", "type", "revoked", "user_id"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                token: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                type: {
                    bsonType: "string",
                    enum: ["BEARER"],
                    description: "must be a string and is required"
                },
                revoked: {
                    bsonType: "bool",
                    description: "must be a boolean and is required"
                },
                user_id: {
                    bsonType: "objectId",
                    description: "must be an objectId and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});


// Collection structure for collection account_verifications
db.createCollection("account_verifications", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["url", "user_id"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                url: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                user_id: {
                    bsonType: "objectId",
                    description: "must be an objectId and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});


// Collection structure for collection workers
db.createCollection("workers", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["doc_number", "doc_type", "name", "last_name", "birth_date", "email", "address", "guests"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                doc_number: {
                    bsonType: "long",
                    description: "must be a long and is required"
                },
                doc_type: {
                    bsonType: "string",
                    enum: ["CC", "TI", "PEP", "PASSPORT"],
                    description: "must be a string and is required"
                },
                name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                last_name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                birth_date: {
                    bsonType: "date",
                    description: "must be a date and is required"
                },
                email: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                address: {
                    bsonType: "object",
                    description: "must be an object and is required"
                },
                phone_numbers: {
                    bsonType: "array",
                    items: {
                        bsonType: "object"
                    },
                    description: "must be an array"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.workers.createIndex({ email: 1 }, { unique: true });
db.workers.createIndex({ doc_number: 1 }, { unique: true });

// Collection structure for collection guests
db.createCollection("guests", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["email"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                doc_number: {
                    bsonType: "long",
                    description: "must be a long"
                },
                doc_type: {
                    bsonType: "string",
                    enum: ["CC", "TI", "PEP", "PASSPORT"],
                    description: "must be a string"
                },
                name: {
                    bsonType: "string",
                    description: "must be a string"
                },
                last_name: {
                    bsonType: "string",
                    description: "must be a string"
                },
                birth_date: {
                    bsonType: "date",
                    description: "must be a date"
                },
                email: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                address: {
                    bsonType: "object",
                    description: "must be an object"
                },
                phone_numbers: {
                    bsonType: "array",
                    items: {
                        bsonType: "object"
                    },
                    description: "must be an array"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.guests.createIndex({ email: 1 }, { unique: true });
db.guests.createIndex({ doc_number: 1 }, { unique: true });

// Collection structure for collection rooms
db.createCollection("rooms", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["number", "description", "room_type_id"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                number: {
                    bsonType: "long",
                    description: "must be a long and is required"
                },
                description: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                room_type_id: {
                    bsonType: "objectId",
                    description: "must be an objectId and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.rooms.createIndex({ number: 1 }, { unique: true });

// Collection structure for collection room_types
db.createCollection("room_types", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["name", "daily_price"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                daily_price: {
                    bsonType: "double",
                    description: "must be a double and is required"
                },
                description: {
                    bsonType: "string",
                    description: "must be a string"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.room_types.createIndex({ name: 1 }, { unique: true });

// Collection structure for collection qualifications
db.createCollection("qualifications", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["guest_id", "stars", "description"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                guest_id: {
                    bsonType: "objectId",
                    description: "must be an objectId and is required"
                },
                stars: {
                    bsonType: "long",
                    description: "must be a long and is required"
                },
                description: {
                    bsonType: "string",
                    description: "must be a string and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});


// Collection structure for collection bookings
db.createCollection("bookings", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["guest_id", "check_in", "check_out", "total_payment", "status"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                guest_id: {
                    bsonType: "objectId",
                    description: "must be an objectId and id required"
                },
                check_in: {
                    bsonType: "date",
                    description: "must be a date and is required"
                },
                check_out: {
                    bsonType: "date",
                    description: "must be a date and is required"
                },
                total_payment: {
                    bsonType: "double",
                    description: "must be a double and is required"
                },
                status: {
                    bsonType: "string",
                    enum: ["CANCELED", "FINISHED", "IN_PROGRESS"],
                    description: "must be a string and is required"
                },
                payments: {
                    bsonType: "array",
                    items: {
                        bsonType: "object"
                    },
                    description: "must be a array of objects"
                }
            }
        }
    },
    validationLevel: "strict"
});


// Collection structure for collection booking_details
db.createCollection("booking_details", {
    autoIndexId: true,
    collation: {
        locale: "es",
        caseLevel: true,
        strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["booking_id", "cant_people"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId"
                },
                booking_id: {
                    bsonType: "objectId",
                    description: "must be an objectId and is required"
                },
                cant_people: {
                    bsonType: "long",
                    description: "must be a long and is required"
                },
                observations: {
                    bsonType: "string",
                    description: "must be a string"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.booking_details.createIndex({ booking_id: 1 }, { unique: true });

// Collection structure for collection payment_types
db.createCollection("payment_types", {
    autoIndexId: true,
    collation: {
    locale: "es",
    caseLevel: true,
    strength: 2
    },
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["name"],
            properties: {
                _id: {
                    bsonType: "objectId",
                    description: "must be an objectId an is required"
                },
                name: {
                    bsonType: "string",
                    description: "must be a string and is required"
                }
            }
        }
    },
    validationLevel: "strict"
});

db.payment_types.createIndex({ name: 1 }, { unique: true });