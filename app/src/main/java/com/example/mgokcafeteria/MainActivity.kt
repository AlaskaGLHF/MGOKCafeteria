@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mgokcafeteria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mgokcafeteria.ui.theme.MGOKCafeteriaTheme


val budgetMealType = MealType(id = 1, name = "Бюджетный")
val paidMealType = MealType(id = 2, name = "Платный")

val menuItems = listOf(

    //Обед № 1 Бюджет
    MenuItem("Винегрет", "vinaigrette", 2.1, 0.264, 12.744, 62.196, 120, "Картофель очищенный, Морковь очищенная, Свекла очищенная, Горошек зеленый консервированный, Огурцы консервированные без уксуса (без учета заливки) ДП", budgetMealType, 1),
    MenuItem("Поке с овощами и яйцом", "poke_with_vegetables_and_egg", 8.184, 6.66, 10.716, 132.744, 120, "Яйцо куриное пищевое С1, Масло растительное, Помидоры, Огурцы свежие, Брокколи б/з, Крупа Рисовая, Соль, Масло растительное, Кунжут", budgetMealType, 1),
    MenuItem("Салат Цезарь", "caesar_salad", 13.76, 8.244, 11.016, 176.7, 120, "Мясо кур с/м, Помидоры Черри, Хлеб пшеничный, Салат Романо", budgetMealType, 1),
    MenuItem("Хот-Дог", "hot_dog", 14.145, 17.49, 36.015, 360.24, 150, "Булочка для хот-дога, Колбаска, Темат-паста", budgetMealType, 1),
    MenuItem("Картофель фри", "french_fries", 3.69, 3.132, 30.12, 163.872, 120, "Картофель фри, Масло растительное",budgetMealType, 1),
    MenuItem("Рисовая лапша с морепродуктами", "rice_noodles_seafood", 17.622, 5.984, 53.834, 352.704, 220, "Кальмар филе с/м, Рыба филе Трески б/к с/м, Лапша Рисовая, Соль, Масло растительное, Перец б/з, Кабачки 6/3, Морковь очищенная, Лук репчатый очищенный, Соус соевый, Кунжут, Лук зеленый свежий", budgetMealType, 1),
    MenuItem("Чикен ролл", "chicken_roll", 16.575, 16.125, 29.07, 371.355, 150, "Тортилья ПП, Мясо кур (п/ф бедро, голень, грудка) с/м, Масло растительное, Перец сладкий свежий, Капуста Пекинская (Салат Китайский), Соус салатный, Помидоры, Сыр с м.д.ж 45%", budgetMealType, 1),
    MenuItem("Хлебная корзина", "bread_basket", 4.14, 1.61, 31.891, 159.0, 60, "Хлеб пшеничный в нарезке, Хлеб ржано-пшеничный в нарезке, Булочка Столичная", budgetMealType, 1),
    MenuItem("Пирожок с яблоком", "apple_pie", 2.8, 2.35, 26.85, 143.0, 50, "Пирожок с яблоком", budgetMealType, 1),
    MenuItem("Фрукты свежие в ассортименте", "fresh_fruits", 0.57, 0.3, 9.4, 45.67, 100, "Яблоки свежие, Апельсин, Груша свежая", budgetMealType, 1),
    MenuItem("Соуса в ассортименте", "assorted_sauces", 0.00, 4.9, 0.0, 44.95, 5, "Масло растительное, Соус салатный, Соус соевый, Томат-паста, Соль, Сахар-песок, Орегано", budgetMealType, 1),
    MenuItem("Чай/Какао на молоке", "tea_cocoa_milk", 1.84, 1.74, 6.32, 48.74, 200, "Чай черный весовой, Какао-порошок, Молоко, Сахар-песок", budgetMealType, 1),
    MenuItem("Лимон", "lemon", 0.03, 0.0, 0.09, 1.02, 50, "Лимон", budgetMealType, 1),
    MenuItem("Сахар порционный", "portion_sugar", 0.0, 0.0, 5.0, 19.95, 5, "Сахар порционный 5 гр.", budgetMealType, 1),
    MenuItem("Сок", "juice", 2.0, 0.2, 20.2, 92.0, 200, "Сок яблочный", budgetMealType, 1),

    //Обед № 1 Платный
    MenuItem("Салат Цезарь", "caesar_salad", 13.76, 8.244, 11.016, 176.7, 120, "Мясо кур с/м, Помидоры Черри, Хлеб пшеничный, Салат Романо", paidMealType, 1),
    MenuItem("Хот-Дог", "hot_dog", 14.145, 17.49, 36.015, 360.24, 150, "Булочка для хот-дога, Колбаска, Темат-паста", paidMealType, 1),
    MenuItem("Картофель фри", "french_fries", 3.69, 3.132, 30.12, 163.872, 120, "Картофель фри, Масло растительное",paidMealType, 1),
    MenuItem("Хлебная корзина", "bread_basket", 4.14, 1.61, 31.891, 159.0, 60, "Хлеб пшеничный в нарезке, Хлеб ржано-пшеничный в нарезке, Булочка Столичная", paidMealType, 1),
    MenuItem("Пирожок с яблоком", "apple_pie", 2.8, 2.35, 26.85, 143.0, 50, "Пирожок с яблоком", paidMealType, 1),
    MenuItem("Соуса в ассортименте", "assorted_sauces", 0.00, 4.9, 0.0, 44.95, 5, "Масло растительное, Соус салатный, Соус соевый, Томат-паста, Соль, Сахар-песок, Орегано", paidMealType, 1),
    MenuItem("Фрукты свежие в ассортименте", "fresh_fruits", 0.57, 0.3, 9.4, 45.67, 100, "Яблоки свежие, Апельсин, Груша свежая", paidMealType, 1),
    MenuItem("Сок", "juice", 2.0, 0.2, 20.2, 92.0, 200, "Сок яблочный", paidMealType, 1),

    //Обед № 2 Платный
    MenuItem("Поке с овощами и яйцом", "poke_with_vegetables_and_egg", 8.184, 6.66, 10.716, 132.744, 120, "Яйцо куриное пищевое С1, Масло растительное, Помидоры, Огурцы свежие, Брокколи б/з, Крупа Рисовая, Соль, Масло растительное, Кунжут", paidMealType, 2),
    MenuItem("Чикен ролл", "chicken_roll", 16.575, 16.125, 29.07, 371.355, 150, "Тортилья ПП, Мясо кур (п/ф бедро, голень, грудка) с/м, Масло растительное, Перец сладкий свежий, Капуста Пекинская (Салат Китайский), Соус салатный, Помидоры, Сыр с м.д.ж 45%", paidMealType, 2),
    MenuItem("Картофель фри", "french_fries", 3.69, 3.132, 30.12, 163.872, 120, "Картофель фри, Масло растительное",paidMealType, 2),
    MenuItem("Хлебная корзина", "bread_basket", 4.14, 1.61, 31.891, 159.0, 60, "Хлеб пшеничный в нарезке, Хлеб ржано-пшеничный в нарезке, Булочка Столичная", paidMealType, 2),
    MenuItem("Пирожок с яблоком", "apple_pie", 2.8, 2.35, 26.85, 143.0, 50, "Пирожок с яблоком", paidMealType, 2),
    MenuItem("Фрукты свежие в ассортименте", "fresh_fruits", 0.57, 0.3, 9.4, 45.67, 100, "Яблоки свежие, Апельсин, Груша свежая", paidMealType, 2),
    MenuItem("Соуса в ассортименте", "assorted_sauces", 0.00, 4.9, 0.0, 44.95, 5, "Масло растительное, Соус салатный, Соус соевый, Томат-паста, Соль, Сахар-песок, Орегано", paidMealType, 2),
    MenuItem("Чай/Какао на молоке", "tea_cocoa_milk", 1.84, 1.74, 6.32, 48.74, 200, "Чай черный весовой, Какао-порошок, Молоко, Сахар-песок", paidMealType, 2),
    MenuItem("Лимон", "lemon", 0.03, 0.0, 0.09, 1.02, 50, "Лимон", paidMealType, 2),
    MenuItem("Сахар порционный", "portion_sugar", 0.0, 0.0, 5.0, 19.95, 5, "Сахар порционный 5 гр.", paidMealType, 2),

    //Обед № 3 Платный
    MenuItem("Винегрет", "vinaigrette", 2.1, 0.264, 12.744, 62.196, 120, "Картофель очищенный, Морковь очищенная, Свекла очищенная, Горошек зеленый консервированный, Огурцы консервированные без уксуса (без учета заливки) ДП", paidMealType, 3),
    MenuItem("Рисовая лапша с морепродуктами", "rice_noodles_seafood", 17.622, 5.984, 53.834, 352.704, 220, "Кальмар филе с/м, Рыба филе Трески б/к с/м, Лапша Рисовая, Соль, Масло растительное, Перец б/з, Кабачки 6/3, Морковь очищенная, Лук репчатый очищенный, Соус соевый, Кунжут, Лук зеленый свежий", paidMealType, 3),
    MenuItem("Хлебная корзина", "bread_basket", 4.14, 1.61, 31.891, 159.0, 60, "Хлеб пшеничный в нарезке, Хлеб ржано-пшеничный в нарезке, Булочка Столичная", paidMealType, 3),
    MenuItem("Пирожок с яблоком", "apple_pie", 2.8, 2.35, 26.85, 143.0, 50, "Пирожок с яблоком", paidMealType, 3),
    MenuItem("Фрукты свежие в ассортименте", "fresh_fruits", 0.57, 0.3, 9.4, 45.67, 100, "Яблоки свежие, Апельсин, Груша свежая", paidMealType, 3),
    MenuItem("Соуса в ассортименте", "assorted_sauces", 0.00, 4.9, 0.0, 44.95, 5, "Масло растительное, Соус салатный, Соус соевый, Томат-паста, Соль, Сахар-песок, Орегано", paidMealType, 3),
    MenuItem("Сок", "juice", 2.0, 0.2, 20.2, 92.0, 200, "Сок яблочный", paidMealType, 3),
    )

val mealTypes = listOf(
    MealType(1, "Бюджетные обеды"),
    MealType(2, "Платные обеды")
)

data class MenuItem(
    val name: String,
    val imagePath: String,
    val proteins: Double,
    val fats: Double,
    val carbs: Double,
    val calories: Double,
    val weight: Int,
    val ingredients: String,
    val mealType: MealType,
    val mealID: Int
)

data class MealType(
    val id: Int,
    val name: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MGOKCafeteriaTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen(navController = navController)
                    }
                    composable(
                        "meal/{mealTypeId}/{mealNumber}",
                        arguments = listOf(
                            navArgument("mealTypeId") { type = NavType.IntType },
                            navArgument("mealNumber") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val mealTypeId = backStackEntry.arguments?.getInt("mealTypeId") ?: 0
                        val mealNumber = backStackEntry.arguments?.getInt("mealNumber") ?: 0
                        MealScreen(mealTypeId = mealTypeId, selectedMealID = mealNumber, navController = navController)
                    }
                    composable(
                        "details/{itemIndex}",
                        arguments = listOf(navArgument("itemIndex") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val itemIndex = backStackEntry.arguments?.getInt("itemIndex") ?: 0
                        val menuItem = menuItems[itemIndex]
                        DetailsScreen(menuItem = menuItem, onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        Text(
            text = "МГОК Столовая",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        mealTypes.forEach { mealType ->
            Button(
                onClick = {
                    navController.navigate("meal/${mealType.id}/0")
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, Color.DarkGray),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = mealType.name, fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun MealScreen(mealTypeId: Int, selectedMealID: Int, navController: NavHostController) {
    val mealNumbers = menuItems
        .filter { it.mealType.id == mealTypeId }
        .map { it.mealID }
        .distinct()

    var selectedMealIDState by remember { mutableStateOf(selectedMealID) }
    val filteredMenuItems = menuItems.filter {
        it.mealType.id == mealTypeId && it.mealID == selectedMealIDState
    }

    // Find the name of the meal type based on the ID
    val selectedMealTypeName = mealTypes.find { it.id == mealTypeId }?.name ?: "Неизвестный тип"

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "МГОК Столовая",
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Add the new text to show the selected meal type
            Text(
                text = "Вы выбрали: ${selectedMealTypeName}",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                mealNumbers.forEach { mealNumber ->
                    item {
                        MealNumberCard(
                            mealNumber = mealNumber,
                            isSelected = selectedMealIDState == mealNumber
                        ) {
                            selectedMealIDState = mealNumber
                            navController.navigate("meal/$mealTypeId/$mealNumber")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filteredMenuItems.forEach { menuItem ->
                    item {
                        MenuItemCard(menuItem = menuItem) {
                            navController.navigate("details/${menuItems.indexOf(menuItem)}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealNumberCard(mealNumber: Int, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) Color.Black else Color.DarkGray
    val backgroundColor = if (isSelected) Color.Black else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = onClick)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Обед №$mealNumber",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = getImageResource(menuItem.imagePath)),
                contentDescription = menuItem.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = menuItem.name,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DetailsScreen(menuItem: MenuItem, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Назад", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = getImageResource(menuItem.imagePath)),
            contentDescription = menuItem.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = menuItem.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Белки: ${menuItem.proteins} г", color = Color.Black)
        Text("Жиры: ${menuItem.fats} г", color = Color.Black)
        Text("Углеводы: ${menuItem.carbs} г", color = Color.Black)
        Text("Калории: ${menuItem.calories} ккал", color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ингредиенты: ${menuItem.ingredients}", color = Color.Black)
    }
}




            // Helper function to map image path to drawable resource
            @Composable
            fun getImageResource(imagePath: String): Int {
                return when (imagePath) {
                    "caesar_salad" -> R.drawable.caesar_salad
                    "hot_dog" -> R.drawable.hot_dog
                    "french_fries" -> R.drawable.french_fries
                    "poke_with_vegetables_and_egg" -> R.drawable.poke_with_vegetables_and_egg
                    "apple_pie" -> R.drawable.apple_pie
                    "juice" -> R.drawable.juice
                    "bread_basket" -> R.drawable.bread_basket
                    "chicken_roll" -> R.drawable.chicken_roll
                    "chicken_skewers" -> R.drawable.chicken_skewers
                    "fresh_fruits" -> R.drawable.fresh_fruits
                    "lemon" -> R.drawable.lemon
                    "portion_sugar" -> R.drawable.portion_sugar
                    "tea_cocoa_milk" -> R.drawable.tea_cocoa_milk
                    "vinaigrette" -> R.drawable.vinaigrette
                    "assorted_sauces" -> R.drawable.assorted_sauces
                    "rice_noodles_seafood" -> R.drawable.rice_noodles_seafood
                    else -> R.drawable.placeholder // Default placeholder image
                }
            }

