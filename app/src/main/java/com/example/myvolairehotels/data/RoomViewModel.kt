package com.example.myvolairehotels.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.myvolairehotels.models.Room
import com.example.myvolairehotels.navigation.ROUT_ADDROOM

import com.example.myvolairehotels.navigation.ROUT_LOGIN
import com.example.myvolairehotels.navigation.ROUT_VIEWROOMUSER



import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class RoomViewModel (
    var navController: NavHostController,
    var context: Context
){
    private var authRepository: AuthViewModel = AuthViewModel(navController, context)
    private var progress: ProgressDialog

    init {
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUT_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Saving \uD83D\uDCBE")
        progress.setMessage("Please wait...")
    }
    fun saveRoom(
        roomName: String,
        roomType: String,
        roomPrice: String,
        filePath: Uri,
    ) {
        val roomId = System.currentTimeMillis().toString()
        val storageReference = FirebaseStorage.getInstance().getReference().child("Rooms/$roomId")
        storageReference.putFile(filePath).addOnCompleteListener{
            progress.show()
            if (
                roomName.isBlank()|| roomType.isBlank()|| roomPrice.isBlank()
            ) {
                progress.dismiss()
                Toast.makeText(context, "Fill all the fields please", Toast.LENGTH_LONG).show()
                navController.navigate(ROUT_ADDROOM)
            } else if (it.isSuccessful){
                progress.dismiss()
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    val roomImageUrl = it.toString()
                    val houseData = Room.Rooms(
                        roomName,
                        roomType,
                        roomPrice,
                        roomImageUrl,
                        roomId
                    )
                    val dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms/$roomId")
                    dbRef.setValue(houseData)
                    val toast = Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    navController.navigate(ROUT_ADDROOM)
                }
            } else {
                progress.dismiss()
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewRooms(
        room: MutableState<Room.Rooms>,
        rooms: SnapshotStateList<Room.Rooms>
    ): SnapshotStateList<Room.Rooms> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Rooms")
        progress = ProgressDialog(context)
        progress.setTitle("Loading ")
        progress.setMessage("Please wait...")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                rooms.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Room.Rooms::class.java)
                    room.value = value!!
                    rooms.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        )
        return rooms
    }

    fun deleteRoom(roomId: String) {
        val delRef = FirebaseDatabase.getInstance().getReference().child("Rooms/$roomId")
//        progress.show()
        delRef.removeValue().addOnCompleteListener {task ->
//            progress.dismiss()
            if (task.isSuccessful) {
                Log.d("Delete Room", "Room deleted")
                Toast.makeText(context, "Room deleted", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("Delete Room", "Error deleting room", task.exception)
                Toast.makeText(context, "Error deleting room: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun updateRoom(
        roomName: String,
        roomType: String,
        roomPrice: String,
        roomId: String,
        filePath: Uri?
    ) {
        val storageReference = FirebaseStorage.getInstance().getReference().child("Rooms/$roomId")

        val updateData = Room.Rooms(
            roomName,
            roomType,
            roomPrice,
            "",
            roomId
        )

        // Update book details in Firebase Realtime Database
        if (filePath != null) {
            val dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms/$roomId")
            dbRef.setValue(updateData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // If an image file is provided, update the image in Firebase Storage
                    filePath.let { fileUri ->
                        storageReference.putFile(fileUri).addOnCompleteListener { storageTask ->
                            if (storageTask.isSuccessful) {
                                storageReference.downloadUrl.addOnSuccessListener { uri ->
                                    val imageUrl = uri.toString()
                                    updateData.roomImageUrl = imageUrl
                                    dbRef.setValue(updateData) // Update the book entry with the image URL
                                }
                            } else {
                                Toast.makeText(context, "Upload Failure", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    // Show success message
                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_VIEWROOMUSER)
                } else {
                    // Handle database update error
                    Toast.makeText(context, "ERROR: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else{
            val dbRef = FirebaseDatabase.getInstance().getReference().child("Rooms/$roomId")
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val room = snapshot.getValue(Room.Rooms::class.java)
                    val existingImageUrl = room?.roomImageUrl ?: ""

                    val updateData = Room.Rooms(
                        roomName,
                        roomType,
                        roomPrice,
                        existingImageUrl,
                        roomId
                    )

                    dbRef.setValue(updateData).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Show success message
                            Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                            navController.navigate(ROUT_VIEWROOMUSER)
                        } else {
                            // Handle database update error
                            Toast.makeText(context, "ERROR: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                    Toast.makeText(context, "ERROR: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
            )
            Toast.makeText(context, "Success with Image Retained", Toast.LENGTH_LONG).show()
        }
    }

}