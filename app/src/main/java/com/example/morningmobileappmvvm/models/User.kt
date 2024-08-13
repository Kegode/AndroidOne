package com.example.morningmobileappmvvm.models

class User {
    var firstName: String = ""
    var secondName: String = ""
    var email: String = ""
    var password: String = ""
    var userid: String=""

    constructor(firstName: String,secondName: String,
    email: String,password: String,userid: String){
        this.firstName=firstName
        this.secondName=secondName
        this.email = email
        this.password=password
        this.userid=userid
    }
    constructor()
}