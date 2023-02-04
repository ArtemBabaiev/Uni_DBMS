//1
db.literature.aggregate([{
    $lookup: {
        from: 'literatureType',
        localField: 'type.$id',
        foreignField: '_id',
        as: 'typeRef'
    }
}, {
    $unwind: {
        path: '$typeRef'
    }
}, {
    $project: {
        name: 1,
        typeRef: 1
    }
}, {
    $set: {
        typeRef: '$typeRef.type'
    }
}, {
    $group: {
        _id: '$typeRef',
        names: {
            $push: {
                name: '$name'
            }
        }
    }
}])

//2
db.readers.aggregate(
    [{
        $lookup: {
            from: 'subscriptions',
            localField: 'subscription.$id',
            foreignField: '_id',
            as: 'subscription'
        }
    }, {
        $match: {
            'subscription.readersList.acceptedBy': {
                $exists: true
            }
        }
    }, {
        $project: {
            _id: 0,
            fullName: 1,
            subscription: {
                $arrayElemAt: [
                    '$subscription.readersList.acceptedBy',
                    0
                ]
            }
        }
    }, {
        $unwind: {
            path: '$subscription'
        }
    }, {
        $project: {
            fullName: 1,
            subscription: 1
        }
    }, { $count: 'subscription' }]
)

//3
db.literature.aggregate(
    [{
        $match: {
            name: 'Гобіт, або туди і звідти'
        }
    }, {
        $replaceWith: {
            nomenclatureNumber: '$nomenclatureNumber',
            name: '$name',
            author: '$author',
            yearOfPublishing: '$yearOfPublishing',
            publisher: '$publisher'
        }
    }, {
        $addFields: {
            _id: ObjectId('623bac5da8d6aab2ddcefef0'),
            dateOfWriteOff: ISODate('2022-03-23T23:25:17.034Z'),
            testfield: {
                $toUpper: 'teststring'
            }
        }
    }, {
        $project: {
            testfield: 0
        }
    }, {
        $merge: {
            into: 'writtenOffBooks'
        }
    }]
)

//4
db.readersOcupation.aggregate(
    [{
        $sort: {
            ocupation: 1
        }
    }, { $skip: 2 }, { $limit: 3 }, {
        $unionWith: {
            coll: 'literatureType'
        }
    }, { $count: 'quantity' }]
)

//5
db.employees.aggregate(
    [{
        $lookup: {
            from: 'libraries',
            localField: 'workPlace.$id',
            foreignField: '_id',
            as: 'workPlace'
        }
    }, {
        $lookup: {
            from: 'subscriptions',
            localField: '_id',
            foreignField: 'readersList.givenBy.$id',
            as: 'subscriptions'
        }
    }, {
        $project: {
            _id: 0,
            fullName: 1,
            workLocation: '$workPlace.location',
            lendedBooks: '$subscriptions.readersList.book.$id'
        }
    }, {
        $unwind: {
            path: '$lendedBooks'
        }
    }, {
        $sort: {
            fullName: -1
        }
    }]
)
