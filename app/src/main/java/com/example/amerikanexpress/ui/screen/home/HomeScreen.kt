import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amerikanexpress.ui.screen.data.Screens

@Composable
fun HomeScreen(
    navController: NavController
) {
    val balance = "$73.47"
    val recentTransactions = listOf(
        "Sent $5 to Walmart",
        "Paid \$20 for Uber ride",
        "Sent \$10 to Starbucks",
        "Paid \$12 for Netflix Subscription",
        "Sent \$15 to Amazon",
        "Paid \$30 for Spotify Subscription"
    )

    Column(modifier = Modifier.fillMaxSize()) {

        TopBar(navController = navController)

        HomeBodyContent(
            balance = balance,
            recentTransactions = recentTransactions,
            onSchedulePaymentsClicked = {
                // Handle schedule payments button click
            }
        )

        Box {
            BottomBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun TopBar(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile Icon
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .padding(8.dp)
                .clickable { navController.navigate(Screens.ProfileScreen.route) } // Use the passed navController
        )

        // Greeting
        Column {
            Text(
                text = "Hello, ${AutofillType.Username}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Welcome back!",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Notification Icon
        IconButton(onClick = { /* Handle notification click */ }) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
            )
        }
    }
}

@Composable
fun HomeBodyContent(
    balance: String,
    recentTransactions: List<String>,
    onSchedulePaymentsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.weight(1f) // Takes remaining space after TopBar
            .padding(16.dp)
    ) {
        // Balance and Schedule Payments Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Balance: $balance",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick =onSchedulePaymentsClicked,
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)

            ) {
                Text("Schedule Payment")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Recent Transactions
        Text(
            text = "Recent Transactions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
Card(
    modifier = Modifier
        .fillMaxWidth(),
    shape = RoundedCornerShape(6.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
) {
    LazyColumn {
        items(recentTransactions) { transaction ->
            Text(
                text = transaction,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
    }
}

@Composable
fun BottomBar(modifier: Modifier= Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Deposit Button
        Button(onClick = { /* Handle deposit click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue
            )) {
            Text("Deposit")
        }

        // Withdraw Outlined Button
        OutlinedButton(onClick = { /* Handle withdraw click */ }) {
            Text("Withdraw",
                color = Color.Blue)
        }
    }
}