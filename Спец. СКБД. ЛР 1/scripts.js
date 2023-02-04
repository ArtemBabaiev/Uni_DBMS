//3.1
db.newlyAddedBooks.insertOne(
    {
        "name": "Гобіт, або туди і звідти",
        "newNomenclatureNumber": "000000000",
        "deliveryDate": "<2021-12-25>",
        "numberOfLibrary": 4
    }
)
db.newlyAddedBooks.insertMany([
    {
        "name": "Різдвяна свинка",
        "newNomenclatureNumber": "000000003",
        "deliveryDate": "<2022-02-01>",
        "numberOfLibrary": 4
    },
    {
        "name": "Фізика навчально-практичний довідник",
        "newNomenclatureNumber": "000000007",
        "deliveryDate": "<2021-12-15>",
        "numberOfLibrary": 1
    }
])
//3.2
load("C:/Users/artem/OneDrive/Університет/Спец. СКБД/Спец. СКБД. ЛР 1/libr.js")
//4.1
db.literatureType.find()
//4.2
db.literatureType.find(
    {
    },
    {
        _id: 0,
        type: 1
    }
)
//4.3
db.readersOcupation.find(
    {
        ocupation: "Школяр"
    }
)
//4.4
db.literature.find(
    {},
    {
        "placeOfStorage.library": 1,
        "placeOfStorage.shelf": 1,
        _id: 0
    }
)
//4.5
db.literature.find(
    {
        readingPlace: /Бу+/i
    },
    {
        _id: 0,
        readingPlace: 1
    }
)
//4.6
//a
db.subscription.find().limit(1)
//b
db.subscription.find().skip(4)
//c
db.newlyAddedBooks.find().sort({ $natural: 1 })
//d
db.subscription.find({}, { readersList: { $slice: 1 } })
//4.7
//a
db.writenOffBooks.count()
//b
db.literature.distinct("type")
//4.8
//a
db.readers.find(
    {
        birthYear: { $eq: 1996 }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//b
db.readers.find(
    {
        birthYear: { $ne: 2009 }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//c
db.readers.find(
    {
        birthYear: { $gt: 2002 }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//d
db.readers.find(
    {
        birthYear: { $lt: 2003 }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//e
db.readers.find(
    {
        birthYear: { $gte: 2002 }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//f
db.readers.find(
    {
        birthYear: { $lte: 2002 }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//g
db.readers.find(
    {
        birthYear: { $in: [2003, 2009] }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//h
db.readers.find(
    {
        birthYear: { $nin: [2003, 2009] }
    },
    {
        _id: 0,
        fullName: 1,
        birthYear: 1
    }
)
//4.9
//a
db.libraries.find(
    {
        $or:
            [
                { number: 1 },
                { roomsNumber: 5 }
            ]
    }
)
//b
db.libraries.find(
    {
        $and:
            [
                { number: 1 },
                { roomsNumber: 2 }
            ]
    }
)
//c
db.libraries.find(
    {
        roomsNumber:
        {
            $not: { $gte: 4 }
        }
    }
)
//d
db.libraries.find(
    {
        $nor:
            [
                {
                    number: { $gt: 3 }
                },
                {
                    roomsNumber: { $gt: 4 }
                }
            ]
    }
)
//4.10
//a
db.readers.find(
    {
        "class": { $exists: true }
    }
)
//b
db.readers.find(
    {
        birthYear: { $type: "string" }
    },
    {
        _id: 0,
        fullName: 1
    }
)


