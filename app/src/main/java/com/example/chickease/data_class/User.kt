package com.example.chickease.data_class

class User {
    var name: String ?= null
    var email: String ?= null
    var uid: String ?= null
    var password:String ?= null
    var phone:String ?= null

    constructor(){}

    constructor(name:String,email: String,uid: String,password:String,phone:String){

        this.name = name
        this.email = email
        this.uid = uid
        this.password = password
        this.phone = phone
    }

}
