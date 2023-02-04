//4.1
db.readers.find()
//4.2
db.readers.find({},{"_id":0, fullName:1})
//4.3
db.readers.find({birthYear: 2002}, {_id:0, fullName: 1, birthYear: 1})
//4.4
db.literature.find({}, {"placeOfStorage.library":1, "placeOfStorage.shelf":1, _id:0})
//4.5
db.literature.find({readingPlace:/^H\w+/i}, {_id:0, readingPlace:1})
//4.6
//a
db.subscription.find().limit(2)
//b
db.subscription.find().skip(4)
//c
db.libraries.find().sort({$natural:1})
//d
db.subscription.find ({}, {readersList: {$slice : [1,1]}})
//4.7
//a
db.libraries.count()
//b
db.readers.distinct("fullName")
//4.8
//a
db.readers.find({birthYear:{$eq: 2009}}, {_id:0, fullName:1, birthYear:1})
//b
db.readers.find({birthYear:{$ne: 2009}}, {_id:0, fullName:1, birthYear:1})
//c
db.readers.find({birthYear:{$gt: 2002}}, {_id:0, fullName:1, birthYear:1})
//d
db.readers.find({birthYear:{$lt: 2003}}, {_id:0, fullName:1, birthYear:1})
//e
db.readers.find({birthYear:{$gte: 2003}}, {_id:0, fullName:1, birthYear:1})
//f
db.readers.find({birthYear:{$lte: 2003}}, {_id:0, fullName:1, birthYear:1})
//g
db.readers.find({birthYear:{$in: [2003,2009]}}, {_id:0, fullName:1, birthYear:1})
//h
db.readers.find({birthYear:{$nin: [2003,2009]}}, {_id:0, fullName:1, birthYear:1})
//4.9
//a
db.libraries.find({$or:[{number:1},{roomsNumber:5}]})
//b
db.libraries.find({$and:[{number:7},{roomsNumber:6}]})
//c
db.libraries.find({roomsNumber: {$not:{$gte: 4}}})
//d
db.libraries.find( { $nor: [ { number: {$gt:3} }, { roomsNumber: { $gt: 4 } } ] } )
//4.10
//a
db.readers.find({"class": {$exists: true}})
//b
db.readers.find({birthYear: {$type: "string"}}, {_id:0, fullName:1})