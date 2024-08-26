package com.example.myvolairehotels.models

class Room {
    class Rooms {
        var roomName: String = ""
        var roomType: String = ""
        var roomPrice: String = ""
        var roomImageUrl: String = ""
        var roomId: String = ""

        constructor(
            roomName: String,
            roomType: String,
            roomPrice: String,
            roomImageUrl: String,
            roomId: String
        ){
            this.roomName = roomName
            this.roomType = roomType
            this.roomPrice = roomPrice
            this.roomImageUrl = roomImageUrl
            this.roomId = roomId
        }

        constructor()
    }}