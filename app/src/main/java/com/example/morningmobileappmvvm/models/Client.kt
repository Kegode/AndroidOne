package com.example.morningmobileappmvvm.models

class Client {
    var imageUrl:String=""
    var firstname: String=""
    var lastname: String=""
    var gender: String=""
    var age: String=""
    var bio: String=""
    var id: String=""
    constructor(imageUrl: String,
                firstname: String,
                lastname: String,
                gender:String,
                age: String,
                bio: String,
                id: String){
        this.imageUrl=imageUrl
        this.firstname=firstname
        this.lastname=lastname
        this.gender=gender
        this.age=age
        this.bio=bio
        this.id=id
    }
    constructor()

}