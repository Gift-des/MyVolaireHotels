package com.example.myvolairehotels.ui.theme.screens.clients


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myvolairehotels.data.ClientViewModel
import com.example.myvolairehotels.navigation.ROUT_VIEWROOM
import com.example.myvolairehotels.ui.theme.darkpurple

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale



@SuppressLint("NewApi")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AddClientScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(400.dp, 650.dp)
                .padding(10.dp),
            elevation = CardDefaults.cardElevation(), shape = CardDefaults.shape
        ) {
            var context = LocalContext.current
            Text(
                text = "Client details;",
                fontSize = 50.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                modifier = Modifier.padding(10.dp),

            )

            var clientName by remember { mutableStateOf(TextFieldValue("")) }
            var clientId by remember { mutableStateOf(TextFieldValue("")) }
            var clientNo by remember { mutableStateOf(TextFieldValue("")) }
            var clientdate by remember { mutableStateOf(TextFieldValue("")) }
            var isDateExpanded by remember {
                mutableStateOf(false)
            }
            var clientRoom by remember { mutableStateOf(TextFieldValue("")) }

            OutlinedTextField(
                value = clientName,
                onValueChange = { clientName = it },
                label = { Text(text = "Name*", fontWeight = FontWeight.Bold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = clientId,
                onValueChange = { clientId = it },
                label = { Text(text = "ID/Passport*", fontWeight = FontWeight.Bold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = clientNo,
                onValueChange = { clientNo = it },
                label = { Text(text = "Telephone*", fontWeight = FontWeight.Bold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = clientdate,
                onValueChange = { clientdate = it },
                label = { Text(text = " Day of reservation*", fontWeight = FontWeight.Bold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(8.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { isDateExpanded = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "pick date")
                    }
                }
            )
            if (isDateExpanded) {
                val today = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, day)
                        val sdf = SimpleDateFormat("dd-MM-Y", Locale.getDefault())
                        clientdate = TextFieldValue(sdf.format(selectedDate.time))
                        isDateExpanded = false
                    },
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = clientRoom,
                onValueChange = { clientRoom = it },
                label = { Text(text = "Room name*", fontWeight = FontWeight.Bold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Button(
                    onClick = { navController.navigate(ROUT_VIEWROOM) },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "back")
                }
                Spacer(modifier = Modifier.width(100.dp))
                Button(onClick = {
                    val clientRepository = ClientViewModel(navController, context)
                    clientRepository.saveClient(
                        clientName.text.trim(),
                        clientId.text.trim(),
                        clientNo.text.trim(),
                        clientdate.text.trim(),
                        clientRoom.text.trim()
                    )

                }, colors = ButtonDefaults.buttonColors(darkpurple)) {
                    Text(text = "Submit")
                    Color.Black
                }
            }


        }


    }
}
@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddScreenPreview() {
    AddClientScreen(rememberNavController())
}